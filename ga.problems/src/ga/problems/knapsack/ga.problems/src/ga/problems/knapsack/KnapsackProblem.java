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
    List<Item> itemList;
    List<Item> itemsInKnapsackList = new ArrayList<>();

    public KnapsackProblem(int cap, List<Item> items) {
        this.knapsackCapacity = cap;
        this.itemList = items;
    }

    @Override
    public Solution createNewSolution() throws NoSolutionException {

        // Exception, wenn alle Gegenstände zu schwer
        List<Item> itemListSorted = itemList;
        itemListSorted.sort(Comparator.comparing(Item::getWeight));
        if (itemListSorted.get(1).getWeight() > knapsackCapacity) {
            throw new NoSolutionException("Alle Gegenstände sind zu schwer");
        }

        // befülle Rucksack, solange Gegenstände reinpassen
        while (itemListSorted.get(1).getWeight() < (knapsackCapacity-itemsInKnapsackWeight)) { // kleinstes Item passt noch in Knapsack

            // zufälliges Element aus ItemList holen
            Random random = new Random();
            int randomIndex = random.nextInt(itemList.size());
            Item randomItem = itemList.get(randomIndex);

            // wenn zufälliges Item in Knapsack pack, füge Item dem Knapsack hinzu und lösche Item aus Listen
            if (randomItem.getWeight() < (knapsackCapacity-itemsInKnapsackWeight)) {
                itemsInKnapsackList.add(randomItem); // füge Item dem Rucksack hinzu
                itemsInKnapsackWeight += randomItem.getWeight(); // füge Gewicht dem Rucksack hinzu

                itemList.remove(randomItem); // lösche Element aus Gesamtliste
                itemListSorted.remove(randomItem); // lösche Element aus sortierter Liste
            }
        }

        // übergebe fertige Liste an 'Solution'
        return new KnapsackSolution(itemsInKnapsackList, itemList, itemsInKnapsackWeight, knapsackCapacity);
    }
}
