package ga.problems.knapsack;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class KnapsackProblem implements Problem {

    final int knapsackCapacity;
    int itemsInKnapsackWeight;
    int itemsInKnapsackValue;
    List<Item> itemList;
    List<Item> itemsInKnapsackList = new ArrayList<>();

    public KnapsackProblem(int cap, List<Item> items) {
        this.knapsackCapacity = cap;
        this.itemList = items;
    }

    @Override
    public Solution createNewSolution() throws NoSolutionException {

        // Exception, wenn alle Gegenstände zu schwer
        itemList.sort(Comparator.comparing(Item::getWeight));
        if (itemList.get(1).getWeight() > knapsackCapacity) {
            throw new NoSolutionException("Alle Gegenstände sind zu schwer");
        }

        // befülle Rucksack, solange Gegenstände reinpassen
        while (itemList.get(1).getWeight() < (knapsackCapacity-itemsInKnapsackWeight)
                && itemList.size() > 0) { // kleinstes Item passt noch in Knapsack

            // zufälliges Element aus ItemList holen
            Random random = new Random();
            int randomIndex = random.nextInt(itemList.size());
            Item randomItem = itemList.get(randomIndex);

            // wenn zufälliges Item in Knapsack passt, füge Item dem Knapsack hinzu und lösche Item aus Listen
            if (randomItem.getWeight() < (knapsackCapacity-itemsInKnapsackWeight)) {
                itemsInKnapsackList.add(randomItem); // füge Item dem Rucksack hinzu
                itemsInKnapsackWeight += randomItem.getWeight(); // füge Weight dem Knapsack hinzu
                itemsInKnapsackValue += randomItem.getValue(); // füge Value dem Knapsack hinzu

                itemList.remove(randomItem); // lösche Element aus Gesamtliste aller Elemente
            }
        }

        // übergebe fertige Liste an 'Solution'
        return new KnapsackSolution(itemsInKnapsackList, itemList, itemsInKnapsackWeight, itemsInKnapsackValue, knapsackCapacity);
    }
}
