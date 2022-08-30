package ga.framework;

import ga.framework.model.Solution;
import ga.framework.operators.SurvivalException;
import ga.framework.operators.SurvivalOperator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TopKSurvival implements SurvivalOperator {

    // deklariere k zum Vergleichen
    int k;

    // Konstruktor für 'TopKSurvival'
    public TopKSurvival(int k) {
        this.k = k;
    }

    @Override
    public List<Solution> selectPopulation(List<Solution> candidates, int populationSize) throws SurvivalException {

        List<Solution> bestKSolutions = new ArrayList<>();

        // Exception, wenn 'k>populationSize'
        if (k > populationSize) {
            throw new SurvivalException("ERROR : k > populationSize");
        }

        // sortiere Liste nach fitness absteigend
        candidates.sort(Comparator.comparing(Solution::getFitness).reversed());

        // füge beste K Solutions neuer Liste hinzu
        for (int i = 1; i <= k; i++) {
            bestKSolutions.add(candidates.get(i));
        }

        // füge weitere zufällige Elemente hinzu, falls 'Population > k'
        if (populationSize > k) {
            while (populationSize > bestKSolutions.size()) {
                Random random = new Random();
                int RandomIndex = random.nextInt(candidates.size());
                bestKSolutions.add(candidates.get(RandomIndex));
            }
        }

        return bestKSolutions;
    }
}
