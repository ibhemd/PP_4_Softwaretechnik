package ga.problems.knapsack;

import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class KnapsackMutation implements EvolutionaryOperator {
    @Override
    public Solution evolve(Solution solution) throws EvolutionException {

        // cast Solution zu KnapsackSolution
        KnapsackSolution knapsackSolution = (KnapsackSolution) solution;

        // hole zufälliges item aus Knapsack
        Random random = new Random();
        int randomIndex = random.nextInt(knapsackSolution.getItemsInKnapsackList().size());
        Item randomItem = knapsackSolution.getItemsInKnapsackList().get(randomIndex);

        // lösche Gewicht aus Knapsack
        knapsackSolution.setItemsInKnapsackWeight(knapsackSolution.getItemsInKnapsackWeight() - knapsackSolution.getItemsInKnapsackList().get(randomIndex).getWeight());
        // lösche item aus Knapsack
        knapsackSolution.getItemsInKnapsackList().remove(randomIndex);

        // hole Liste der Elemente, die noch nicht im Rucksack sind
        List<Item> itemsNotInKnapsackList = knapsackSolution.getItemsNotInKnapsackList();

        // hole knapsackCapacity und knapsackWeight
        int capacity = knapsackSolution.getCapacity();
        int weight = knapsackSolution.getItemsInKnapsackWeight();

        // lösche alle Elemente aus Liste, die nicht mehr in den Rucksack passen
        itemsNotInKnapsackList.removeIf(i -> i.getWeight() > (capacity - weight));

        // hole zufälliges Element, das Knapsack hinzugefügt werden kann
        random = new Random();
        randomIndex = random.nextInt(itemsNotInKnapsackList.size());
        Item newItem = itemsNotInKnapsackList.get(randomIndex);
        
        // füge Item und Weight der Solution hinzu
        knapsackSolution.setItemsInKnapsackWeight(knapsackSolution.getItemsInKnapsackWeight()+ newItem.getWeight());
        knapsackSolution.getItemsNotInKnapsackList().add(newItem);

        return null;
    }
}
