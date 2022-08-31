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
        KnapsackSolution oldKnapsackSolution = (KnapsackSolution) solution;

        // erstelle Kopie der KnapsackSolution
        KnapsackSolution newKnapsackSolution = new KnapsackSolution(oldKnapsackSolution);

        // Exception, wenn keine Mutation möglich ist
        boolean canDeleteItem = true;
        boolean canAddItem = true;
        if (newKnapsackSolution.getItemsInKnapsackList().size() == 0) { // wenn Liste der Items im Knapsack leer ist
            canDeleteItem = false;
        }
        if (newKnapsackSolution.getItemsNotInKnapsackList().size() == 0 // wenn Liste der hinzuzufügenden Elemente leer ist
            || newKnapsackSolution.getItemsInKnapsackWeight() == newKnapsackSolution.getCapacity()) { // wenn Maximalgewicht des Knapsack erreicht ist
            canAddItem = false;
        }
        if (!canAddItem && !canDeleteItem) { // werfe Exception
            throw new EvolutionException("Kann keine Mutation ausführen");
        } else if (!canDeleteItem) {
            return AddRandomItem.addRandomItem(newKnapsackSolution);
        } else if (!canAddItem) {
            return DeleteRandomItem.deleteRandomItem(newKnapsackSolution);
        } else {
            // Zufallsvariable für "switch case"
            Random random = new Random();
            int randomIndex = random.nextInt(2);

            // "switch case" für Mutationsvariante
            if (randomIndex < 1) { // entferne zufälliges Item
                // mutiere Liste
                return DeleteRandomItem.deleteRandomItem(newKnapsackSolution);
            } else { // füge neues Item hinzu
                // mutiere Liste
                return AddRandomItem.addRandomItem(newKnapsackSolution);
            }
        }

    }

    static class DeleteRandomItem {
        public static Solution deleteRandomItem(KnapsackSolution knapsackSolution) {

            // hole Solution-Variablen
            List<Item> newItemsInKnapsackList = knapsackSolution.getItemsInKnapsackList();
            int newItemsInKnapsackWeight = knapsackSolution.getItemsInKnapsackWeight();
            int newItemsInKnapsackValue = knapsackSolution.getItemsInKnapsackValue();

            // hole zufälliges item aus Knapsack
            Random random = new Random();
            int randomIndex = random.nextInt(newItemsInKnapsackList.size());

            // lösche Gewicht aus Knapsack
            knapsackSolution.setItemsInKnapsackWeight(newItemsInKnapsackWeight - newItemsInKnapsackList.get(randomIndex).getWeight());
            // lösche Value aus Knapsack
            knapsackSolution.setItemsInKnapsackValue(newItemsInKnapsackValue - newItemsInKnapsackList.get(randomIndex).getValue());
            // lösche Item aus Knapsack
            newItemsInKnapsackList.remove(randomIndex);

            return knapsackSolution;
        }
    }

    static class AddRandomItem {
        public static Solution addRandomItem(KnapsackSolution knapsackSolution) {

            // hole Solution-Variablen
            List<Item> newItemsInKnapsackList = knapsackSolution.getItemsInKnapsackList();
            List<Item> newItemsNotInKnapsackList = knapsackSolution.getItemsNotInKnapsackList();
            int newCapacity = knapsackSolution.getCapacity();
            int newWeight = knapsackSolution.getItemsInKnapsackWeight();
            int newValue = knapsackSolution.getItemsInKnapsackValue();

            // lösche alle Elemente aus Liste, die nicht mehr in den Rucksack passen
            newItemsNotInKnapsackList.removeIf(i -> i.getWeight() > (newCapacity - newWeight));

            // hole zufälliges Element, das Knapsack hinzugefügt werden kann
            Random random = new Random();
            int randomIndex = random.nextInt(newItemsNotInKnapsackList.size());
            Item newItem = newItemsNotInKnapsackList.get(randomIndex);

            // füge Weight, Value und Item der Solution hinzu
            knapsackSolution.setItemsInKnapsackWeight(newWeight + newItem.getWeight());
            knapsackSolution.setItemsInKnapsackValue(newValue + newItem.getValue());
            newItemsInKnapsackList.add(newItem);

            return knapsackSolution;
        }
    }

}
