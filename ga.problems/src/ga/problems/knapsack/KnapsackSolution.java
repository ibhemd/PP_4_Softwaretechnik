package ga.problems.knapsack;

import ga.framework.model.Solution;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution extends Solution {

    private List<Item> itemsInKnapsackList;
    private List<Item> itemsNotInKnapsackList;
    private int itemsInKnapsackWeight;
    private int itemsInKnapsackValue;
    private int itemCount;
    private int capacity;

    public KnapsackSolution(List<Item> itemList, List<Item> notInKnapsack, int knapsackWeight, int knapsackValue, int knapsackCapacity) {
        itemsInKnapsackList = itemList;
        itemsNotInKnapsackList = notInKnapsack;
        itemsInKnapsackWeight = knapsackWeight;
        itemsInKnapsackValue = knapsackValue;
        itemCount = itemsInKnapsackList.size();
        capacity = knapsackCapacity;
    }

    public KnapsackSolution(KnapsackSolution oldKnapsackSolution) {
        this.itemsInKnapsackList = new ArrayList<>();
        this.itemsInKnapsackList.addAll(oldKnapsackSolution.itemsInKnapsackList);
        this.itemsNotInKnapsackList = new ArrayList<>();
        this.itemsNotInKnapsackList.addAll(oldKnapsackSolution.itemsNotInKnapsackList);
        this.itemsInKnapsackWeight = oldKnapsackSolution.itemsInKnapsackWeight;
        this.itemsInKnapsackValue = oldKnapsackSolution.itemsInKnapsackValue;
        this.itemCount = oldKnapsackSolution.itemCount;
        this.capacity = oldKnapsackSolution.capacity;
    }

    // getter
    public List<Item> getItemsInKnapsackList() {return itemsInKnapsackList;}
    public List<Item> getItemsNotInKnapsackList() {return itemsNotInKnapsackList;}
    public int getItemsInKnapsackWeight() {return itemsInKnapsackWeight;}
    public int getItemsInKnapsackValue() {return itemsInKnapsackValue;}
    public int getItemCount() {return itemCount;}
    public int getCapacity() {return capacity;}

    // setter
    public void setItemsInKnapsackWeight(int w) {itemsInKnapsackWeight = w;}
    public void setItemsInKnapsackValue(int v) {itemsInKnapsackValue = v;}

}
