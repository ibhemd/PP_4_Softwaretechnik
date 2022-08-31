package ga.problems.knapsack;

import ga.framework.GeneticAlgorithm;
import ga.framework.TopKSurvival;
import ga.framework.TournamentSelection;
import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;

public class ConcreteProblem {

    public static void main(String[] args) throws SurvivalException, EvolutionException, NoSolutionException {

        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(5, 10));
        itemList.add(new Item(4,8));
        itemList.add(new Item(4,6));
        itemList.add(new Item(4,4));
        itemList.add(new Item(3,7));
        itemList.add(new Item(3,4));
        itemList.add(new Item(2,6));
        itemList.add(new Item(2,3));
        itemList.add(new Item(1,3));
        itemList.add(new Item(1,1));

        int populationSize = 4;
        int evolutionSteps = 10;

        Problem knapsackProblem = new KnapsackProblem(11, itemList);
        FitnessEvaluator fitnessEvaluator = new KnapsackFitnessEvaluator();
        SurvivalOperator survivalOperator = new TopKSurvival(4);
        SelectionOperator selectionOperator = new TournamentSelection();
        EvolutionaryOperator evolutionaryOperator = new KnapsackMutation();

        GeneticAlgorithm ga = new GeneticAlgorithm();
        List<Solution> res = ga.solve(knapsackProblem)
                .withPopulationsSize(populationSize)
                .surviveWith(survivalOperator)
                .selectWith(selectionOperator)
                .evolvingSolutionsWith(evolutionaryOperator) // Evolutionary Operator ???
                .evaluatingSolutionsWith(fitnessEvaluator)
                .stoppingAtEvolution(evolutionSteps)
                .run();

        System.out.println(res);
    }

}
