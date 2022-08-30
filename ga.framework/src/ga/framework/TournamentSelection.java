package ga.framework;

import ga.framework.model.Solution;
import ga.framework.operators.SelectionOperator;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionOperator {

    @Override
    public Solution selectParent(List<Solution> candidates) {

        // erstelle neue Random Solutions
        Random random1 = new Random();
        int randomIndex1 = random1.nextInt(candidates.size());
        Solution solution1 = candidates.get(randomIndex1);

        Random random2 = new Random();
        int randomIndex2 = random2.nextInt(candidates.size());
        Solution solution2 = candidates.get(randomIndex2);

        // solution1, wenn fitness gleich oder fitness1 > fitness2 ; solution2, wenn fitness2 > fitness1
        if (solution1.getFitness() == solution2.getFitness()
                || solution1.getFitness() > solution2.getFitness()) {
            return solution1;
        } else {
            return solution2;
        }

    }
}
