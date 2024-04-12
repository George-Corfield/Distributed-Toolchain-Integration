package UoBToolchainGroup.DistributedToolchainIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;

import UoBToolchainGroup.DistributedToolchainIntegration.model.Result;
import UoBToolchainGroup.DistributedToolchainIntegration.model.Variable;

public class HillClimb {

    private int iterations;
    private boolean maximising;
    private List<List<Variable>> variablesArray;
    private JSONArray modules;
    //optimisation data
    private List<Result> results;
    private Result currentResult;
    private double minimum;
    private double maximum;
    

    public HillClimb(int iterations, boolean maximising, List<List<Variable>> variablesArray,JSONArray modules){
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

    public Result CalculateInitialResult(){
        Result r = new Result();
        Random rand = new Random();
        r.setOutputValue(rand.nextFloat(500));
        for (int i = 0; i < variablesArray.size(); i++){
            List<Variable> v = variablesArray.get(i);
            for (int j = 0; j < v.size(); j++){
                Variable var = v.get(j);
                if (!var.getVariableName().equals("PR")){
                    r.addVariable(var);
                }
            }
        }
        return r;
    }

    public Result GenerateNeighbor(){
        Result neighbor = new Result();
        Random r = new Random();
        int rand = r.nextInt(variablesArray.size());
        List<Variable> mod = variablesArray.get(rand);
        int rand2 = r.nextInt(mod.size());
        Variable var = mod.get(rand2);
        while (var.getVariableName().equals("PR")){
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
        for (int i = 0; i < variablesArray.size(); i++){
            List<Variable> v = variablesArray.get(i);
            for (int j = 0; j < v.size(); j++){
                Variable vars = v.get(j);
                if (!vars.getVariableName().equals("PR")){
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
