package ga.problems.knapsack;

import ga.framework.model.Solution;

import java.util.List;

public class KnapsackSolution extends Solution {

    List<Item> itemsInKnapsackList;
    int itemsInKnapsackWeight;

    public KnapsackSolution(List<Item> itemList, int knapsackWeight) {
        itemsInKnapsackList = itemList;
        itemsInKnapsackWeight = knapsackWeight;
    }

}
