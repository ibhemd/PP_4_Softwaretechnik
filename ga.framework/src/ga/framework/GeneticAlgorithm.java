package ga.framework;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    Problem problem;
    int populationSize;
    List<EvolutionaryOperator> evolutionaryOperatorList = new ArrayList<>();
    FitnessEvaluator fitnessEvaluator;
    SurvivalOperator survivalOperator;
    SelectionOperator selectionOperator;
    int evolutionSteps;

    List<Solution> solutionList = new ArrayList<>();
    List<Solution> selectedSolutionList = new ArrayList<>();
    List<Solution> nachkommenList = new ArrayList<>();

    public List<Solution> runOptimization() throws EvolutionException, NoSolutionException, SurvivalException {

        // erstelle Startpopulation
        for (int i = 0; i < populationSize; i++) {
            solutionList.add(problem.createNewSolution());
        }

        // evaluate, um Fitness der Startpopulation zu bestimmen
        fitnessEvaluator.evaluate(solutionList);

        // führe Iterationen aus, bis Limit erreicht ist
        for (int i = 0; i < evolutionSteps; i++) {
            // Random EvolutionOperator
            Random random = new Random();
            int randomIndex = random.nextInt(evolutionaryOperatorList.size());
            EvolutionaryOperator evolutionaryOperator = evolutionaryOperatorList.get(randomIndex);

                // selektiere Parents aus 2 zufälligen Solutions bis populationsSize erreicht ist
                while (selectedSolutionList.size() < populationSize) {
                    selectedSolutionList.add(selectionOperator.selectParent(solutionList));
                }
                // wende nun evolve auf díe selektierten Parent-Solutions an

            // evolve, um Operator auf jede Solution in 'selectedSolutionsList' anzuwenden und in nachkommenList zu speichern
            for (Solution s : selectedSolutionList) {
                nachkommenList.add(evolutionaryOperator.evolve(s));
            }

            // bestimme Fitness der Nachkommen
            fitnessEvaluator.evaluate(nachkommenList);

            // füge Nachkommen der solutionList hinzu
            solutionList.addAll(nachkommenList);

            // wähle solutions für nächste Operation
            survivalOperator.selectPopulation(solutionList, populationSize);
        }

        // gebe aktuelle Liste aus
        return solutionList;
    }

    public WithPopulationsSize solve(Problem p) {
        problem = p;
        return new WithPopulationsSize();
    }

    public class WithPopulationsSize {
        public SurviveWith withPopulationsSize(int i) {
            populationSize = i;
            return new SurviveWith();
        }
    }

    public class SurviveWith {
        public SelectWith surviveWith(SurvivalOperator sO) {
            survivalOperator = sO;
            return new SelectWith();
        }
    }

    public class SelectWith {
        public EvolvingSolutionsWith selectWith(SelectionOperator sO) {
            selectionOperator = sO;
            return new EvolvingSolutionsWith();
        }
    }

    public class EvolvingSolutionsWith {
        public EvolvingSolutionsWith evolvingSolutionsWith(EvolutionaryOperator eO) {
            evolutionaryOperatorList.add(eO);
            return new EvolvingSolutionsWith();
        }
        public PerformingSelectionWith evaluatingSolutionsWith(FitnessEvaluator fE) {
            fitnessEvaluator = fE;
            return new PerformingSelectionWith();
        }
    }

    public class PerformingSelectionWith {
        public StoppingAtEvolution stoppingAtEvolution(int i) {
            evolutionSteps = i;
            return new StoppingAtEvolution();
        }
    }

    public class StoppingAtEvolution {
        public List<Solution> run() throws SurvivalException, EvolutionException, NoSolutionException {
            return runOptimization();
        }
    }

    public static void main(String[] args) throws SurvivalException, EvolutionException, NoSolutionException {
        Problem yourProblem = null;
        EvolutionaryOperator yourEvolutionaryOperator = null;
        FitnessEvaluator yourFitnessEvaluator = null;
        SurvivalOperator yourSurvivalOperator = null;
        SelectionOperator yourSelectionOperator = null;

        GeneticAlgorithm ga = new GeneticAlgorithm();
        List<Solution> result = ga.solve(yourProblem)
                .withPopulationsSize(10)
                .surviveWith(yourSurvivalOperator)
                .selectWith(yourSelectionOperator)
                .evolvingSolutionsWith(yourEvolutionaryOperator)
                .evolvingSolutionsWith(yourEvolutionaryOperator)
                .evaluatingSolutionsWith(yourFitnessEvaluator)
                .stoppingAtEvolution(100)
                .run();
    }

}