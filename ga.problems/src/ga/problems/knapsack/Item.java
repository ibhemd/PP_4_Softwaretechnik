package ga.problems.knapsack;

public class Item {

    private int weight;
    private int value;

    public Item(int w, int v) {
        this.weight = w;
        this.value = v;
    }

    public int getWeight() {return this.weight;}
    public int getValue() {return this.value;}

}
