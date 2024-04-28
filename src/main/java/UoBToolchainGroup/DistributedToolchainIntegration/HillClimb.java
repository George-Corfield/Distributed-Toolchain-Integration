package UoBToolchainGroup.DistributedToolchainIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;
import UoBToolchainGroup.DistributedToolchainIntegration.model.VariablesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.BuildMultiPartRequestBody;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.io.BufferedWriter;

public class HillClimb {

    private int iterations;
    private boolean maximising;
    private List<List<Variable>> variablesArray;
    private List<ModulesFile> modules;
    //optimisation data
    private List<Result> results;
    private Result currentResult;
    private double minimum;
    private double maximum;
    

    public HillClimb(int iterations, boolean maximising, List<List<Variable>> variablesArray,List<ModulesFile> modules) throws IOException, InterruptedException{
        this.iterations = iterations;
        this.maximising = maximising;
        this.variablesArray = variablesArray;
        this.modules = modules;
        this.results = new ArrayList<>();
        Result r = CalculateInitialResult();
        currentResult = r;
        results.add(r);
        minimum = r.getOutputValue();
        maximum = r.getOutputValue();
        optimise();
    }

    public Result CalculateInitialResult() throws IOException, InterruptedException{
        //calculates an initial result based on the initial values
        Result r = new Result();
        for (int i= 0; i < modules.size(); i++){
            double execute = executeModule(modules.get(i), variablesArray.get(i));
            if (i != modules.size()-1){
                variablesArray.get(i+1).add(new Variable("PR",execute));
            } else {
                r.setOutputValue(execute);
            }
        }
        r.setVariables(setResultVariables());
        return r;
    }

    public List<Variable> setResultVariables(){
        //creates a set of all variables and their values
        List<Variable> resultVariables = new ArrayList<>();
        for (int i = 0; i < variablesArray.size(); i++){
            List<Variable> v = variablesArray.get(i);
            for (int j = 0; j < v.size(); j++){
                Variable var = v.get(j);
                if (!var.getResult()){
                    resultVariables.add(var);
                }
            }
        }
        return resultVariables;
    }

    public double executeModule(ModulesFile module, List<Variable> variables) throws IOException, InterruptedException{
        //Create a New Http Client
        HttpClient client = HttpClient.newHttpClient();

        //Get the relevant variablesfile
        VariablesFile jsonData = new VariablesFile("json_file.json", "json", variables);

        List<byte[]> data = List.of(jsonData.getFileContent(), module.getFileContent());

        //Build the request
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create("http://localhost:5000/optimise"))
            .header("Content-Type", "multipart/form-data; boundary=boundary")
            .POST(BuildMultiPartRequestBody.buildMultiPartRequestBodyBytes(data))
            .build();

        //Make the request and get the response
        HttpResponse<String> res = client.send(req, BodyHandlers.ofString());
        System.out.println(res.body());

        return Double.parseDouble(res.body());
    }

    public Result GenerateNeighbor(){
        //modifies 1 variable value and outputs the new value of running the modules
        //TODO connect to module running logic to actually calculate results
        Result neighbor = new Result();
        Random r = new Random();
        int rand = r.nextInt(variablesArray.size());
        List<Variable> mod = variablesArray.get(rand);
        int rand2 = r.nextInt(mod.size());
        Variable var = mod.get(rand2);
        while (var.getResult()){
            rand2 = r.nextInt(mod.size());
            var = mod.get(rand2);
        }
        double lowerBound = var.getLowBound();
        double upperBound = var.getUpBound();
        double value = var.getInitVal();
        double diff = upperBound - lowerBound;
        diff = diff * 0.1 * r.nextDouble(-1, 1);
        value = Math.min(Math.max(value+diff, lowerBound), upperBound);
        var.setInitVal(value);;
        mod.set(rand2, var);
        variablesArray.set(rand,mod);
        neighbor.setOutputValue(r.nextFloat(501));
        neighbor.setVariables(setResultVariables());
        return neighbor;
    }   

    public Result compareNeighbor(Result neighbor){
        //compares if neighbor is a better or worse result than current result
        if (maximising){
            if (neighbor.getOutputValue() > currentResult.getOutputValue()){
                return neighbor;
            } else {return currentResult;}
        } else {
            if (neighbor.getOutputValue() < currentResult.getOutputValue()){
                return neighbor;
            } else {return currentResult;}
        }
    }

    public void updateMinMax(Result neighbor){
        double output = neighbor.getOutputValue();
        if (output > maximum) {
            maximum = output;
        } else if (output < minimum){
            minimum = output;
        }
    }

    public Result evaluateFitness(Result result){
        //Evaluates the fitness of each result on a scale of 0 to 100
        double diff = maximum-minimum;
        double value = result.getOutputValue();
        if (maximising){
            double fitness = ((value-minimum)/(diff)) * 100;
            result.setFitnessLevel(fitness);
        } else {
            double fitness = ((maximum-value)/(diff)) * 100;
            result.setFitnessLevel(fitness);
        }
        return result;
    }

    public List<Result> getResults(){
        return results;
    }

    public void optimise(){
        //main function run to generate all results
        int iter = 1;
        while (iter != iterations){

            Result neighborSolution = GenerateNeighbor();
            updateMinMax(neighborSolution);
            results.add(neighborSolution);
            currentResult = compareNeighbor(neighborSolution);
            iter++;
        }
        for(Result result: results){
            result = evaluateFitness(result);
        }
    }

}
