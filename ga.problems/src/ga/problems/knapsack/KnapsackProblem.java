package ga.problems.knapsack;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class KnapsackProblem implements ga.framework.model.Problem {

    private final int knapsackCapacity;
    private int itemsInKnapsackWeight;
    List<Item> itemList = new ArrayList<>(); // befüllen
    List<Item> itemsInKnapsackList = new ArrayList<>();

    public KnapsackProblem(int cap) {
        this.knapsackCapacity = cap;
    }

    @Override
    public Solution createNewSolution() throws NoSolutionException {

        // Exception, wenn alle Gegenstände zu schwer
        List<Item> itemListSorted = itemList;
        itemListSorted.sort(Comparator.comparing(Item::getWeight));
        if (itemListSorted.get(1).getWeight() > knapsackCapacity) {
            throw new NoSolutionException("Alle Gegenstände sind zu schwer");
        }

        // zufälliges Element aus ItemList holen ...
        Random random = new Random();
        int randomIndex = new Random().nextInt(itemList.size());

        // befülle Rucksack, solange Gegenstände reinpassen
        while (itemList.get(randomIndex).getWeight() < (knapsackCapacity-itemsInKnapsackWeight) // ausgewähltes Item passt in Knapsack
                && itemListSorted.get(1).getWeight() < (knapsackCapacity-itemsInKnapsackWeight)) { // kleinstes Item passt noch in Knapsack

            itemsInKnapsackList.add(itemList.get(randomIndex)); // füge Item dem Rucksack hinzu
            itemsInKnapsackWeight += itemList.get(randomIndex).getWeight(); // füge Gewicht dem Rucksack hinzu

            itemList.remove(randomIndex); // lösche Element aus Gesamtliste
            itemListSorted.remove(itemList.get(randomIndex)); // lösche Element aus sortierter Liste
        }

        // übergebe fertige Liste an 'Solution'
        return new KnapsackSolution(itemsInKnapsackList, itemsInKnapsackWeight);
    }
}
