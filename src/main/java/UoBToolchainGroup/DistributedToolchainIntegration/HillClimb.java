package UoBToolchainGroup.DistributedToolchainIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import UoBToolchainGroup.DistributedToolchainIntegration.model.ModulesFile;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;

public class HillClimb {

    private int iterations;
    private boolean maximising;
    private JSONArray variablesArray;
    private JSONArray modules;
    private ObjectId partId;
    //optimisation data
    private List<Result> results;
    private Result currentResult;
    private double minimum;
    private double maximum;
    

    public HillClimb(int iterations, boolean maximising, JSONArray variablesArray,JSONArray modules, ObjectId partId){
        this.iterations = iterations;
        this.maximising = maximising;
        this.variablesArray = variablesArray;
        this.modules = modules;
        this.partId = partId;
        this.results = new ArrayList<>();
        Result r = CalculateInitialResult();
        currentResult = r;
        results.add(r);
        minimum = r.getOutputValue();
        maximum = r.getOutputValue();
        optimise();
    }

    public Result CalculateInitialResult(){
        Result r = new Result();
        Random rand = new Random();
        r.setPartId(partId);
        r.setOutputValue(rand.nextFloat(500));
        for (int i = 0; i < variablesArray.length(); i++){
            JSONArray v = variablesArray.getJSONArray(i);
            for (int j = 0; j < v.length(); j++){
                JSONObject var = v.getJSONObject(j);
                if (var.getString("variableName") != "PR"){
                    r.addVariable(var);
                }
            }
        }
        return r;
    }

    public Result GenerateNeighbor(){
        Result neighbor = new Result();
        Random r = new Random();
        int rand = r.nextInt(variablesArray.length());
        JSONArray mod = variablesArray.getJSONArray(rand);
        int rand2 = r.nextInt(mod.length());
        JSONObject var = mod.getJSONObject(rand2);
        while (var.get("variableName").equals("PR")){
            rand2 = r.nextInt(mod.length());
            var = mod.getJSONObject(rand2);
        }
        double lowerBound = var.getDouble("lowBound");
        double upperBound = var.getDouble("upBound");
        double value = var.getDouble("initVal");
        double diff = upperBound - lowerBound;
        diff = diff * 0.1 * r.nextDouble(-1, 1);
        value = Math.min(Math.max(value+diff, lowerBound), upperBound);
        var.put("initVal",value);
        mod.put(rand2, var);
        variablesArray.put(rand,mod);
        neighbor.setOutputValue(r.nextFloat(501));
        for (int i = 0; i < variablesArray.length(); i++){
            JSONArray v = variablesArray.getJSONArray(i);
            for (int j = 0; j < v.length(); j++){
                JSONObject vars = v.getJSONObject(j);
                if (vars.getString("variableName") != "PR"){
                    neighbor.addVariable(vars);
                }
            }
        }
        return neighbor;
    }   

    public Result compareNeighbor(Result neighbor){
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
        double diff = maximum-minimum;
        double value = result.getOutputValue();
        if (maximising){
            double fitness = ((value-minimum)/(maximum-minimum)) * 100;
            result.setFitnessLevel(fitness);
        } else {
            double fitness = ((maximum-value)/(maximum-minimum)) * 100;
            result.setFitnessLevel(fitness);
        }
        return result;
    }

    public List<Result> getResults(){
        return results;
    }


    public void optimise(){
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
