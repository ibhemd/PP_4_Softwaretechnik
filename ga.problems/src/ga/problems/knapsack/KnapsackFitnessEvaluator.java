package ga.problems.knapsack;

import ga.framework.model.Solution;
import ga.framework.operators.FitnessEvaluator;

import java.util.List;

public class KnapsackFitnessEvaluator implements FitnessEvaluator {

    @Override
    public void evaluate(List<Solution> population) {
        for (Solution s : population) {
            int f =
            s.setFitness(f);
        }
        population.stream()
                .forEach(s -> s.setFitness(0)); // Fitnessberechnung?
    }
}
