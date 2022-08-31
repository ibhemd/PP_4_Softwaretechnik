package ga.problems.knapsack;

import ga.framework.model.Solution;

import java.util.List;

public class KnapsackSolution extends Solution {

    private List<Item> itemsInKnapsackList;
    private List<Item> itemsNotInKnapsackList;
    private int itemsInKnapsackWeight;
    private int itemCount;
    private int capacity;

    public KnapsackSolution(List<Item> itemList, List<Item> notInKnapsack, int knapsackWeight, int knapsackCapacity) {
        itemsInKnapsackList = itemList;
        itemsNotInKnapsackList = notInKnapsack;
        itemsInKnapsackWeight = knapsackWeight;
        itemCount = itemsInKnapsackList.size();
        capacity = knapsackCapacity;
    }

    // getter
    public List<Item> getItemsInKnapsackList() {return itemsInKnapsackList;}
    public List<Item> getItemsNotInKnapsackList() {return itemsNotInKnapsackList;}
    public int getItemsInKnapsackWeight() {return itemsInKnapsackWeight;}
    public int getItemCount() {return itemCount;}
    public int getCapacity() {return capacity;}

    // setter
    public void setItemsInKnapsackWeight(int w) {itemsInKnapsackWeight = w;}

}
