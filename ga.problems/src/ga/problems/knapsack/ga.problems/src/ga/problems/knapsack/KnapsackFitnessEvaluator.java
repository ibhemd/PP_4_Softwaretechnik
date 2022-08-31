package ga.problems.knapsack;

import ga.framework.model.Solution;
import ga.framework.operators.FitnessEvaluator;

import java.util.List;

public class KnapsackFitnessEvaluator implements FitnessEvaluator {

    @Override
    public void evaluate(List<Solution> population) {
        population.stream()
                .map(s -> (KnapsackSolution) s) // cast Solution zu KnapsackSolution
                .forEach(s -> s.setFitness(s.getItemsInKnapsackWeight())); // setze KnapsackWeight als Fitness
    }
}
