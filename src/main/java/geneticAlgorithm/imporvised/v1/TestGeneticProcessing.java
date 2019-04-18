package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestGeneticProcessing {

	public static void main(String[] args) throws Exception {
		Random rn = new Random();
		GeneticProcessing<Integer> gp = new GeneticProcessing<Integer>(
				() -> {
					// Supplier function that provides Population List<List<T>>
					List<List<Integer>> individuals = new LinkedList<List<Integer>>();
					for (int i = 0; i < 10; i++) {
						List<Integer> individual = new LinkedList<Integer>();
						for (int j = 0; j < 5; j++) {
							individual.add(Math.abs(rn.nextInt() % 2));
						}
						individuals.add(individual);
					}
					return individuals;
				},
				(Integer genome) -> {
					// Fitness function which takes param as T (genome) and returns it's Fitness as an Empirical Integer
					return ((genome == 1) ? 1 : 0);
				},
				(List<Integer> individual) -> {
					// Crossover Point Function which takes param as List<T> (individual) and returns the Crossover Mating Point for an Individual
					return rn.nextInt(individual.size());
				},
				(List<Integer> individual) -> {
					// Mutable Check Function which takes param as List<T> (individual/offspring) to check if an Offspring should be Mutated
					return (rn.nextInt()%7 < 5);
				},
				(List<Integer> individual) -> {
					// Mutation Point Function which takes param as List<T> (individual/offspring) and returns the Mutation Point for an Offspring
					return rn.nextInt(individual.size());
				},
				(Integer genome) -> {
					// Mutation Function which takes param as T (genome) and Mutate the Offspring genome
					if (genome == 0) {
						return 1;
			        } else {
			        	return 0;
			        }
				},
				(List<Integer> currentBestCandidate, Integer bestCandidateFitnessFactor) -> {
					// Convergence Check Function which takes param as List<T> (individual) to check if Convergence is achieved and Genetic Population Processing should be stopped
					return (bestCandidateFitnessFactor >= 5);
				},
				(List<Integer> individual, Integer solutionGenerationRank) -> {
					// Threshold Check Function which takes param as List<T> (individual) and Integer Generation-Number to check if Genetic Population Processing should be stopped if Convergence is not achieved
					Boolean thersholdValue = solutionGenerationRank >= 50000;
					if (thersholdValue) {
						System.out.println("Threshold Limit Reached");
					}
					return thersholdValue;
				}, true, 
				(List<Integer> individual, Integer fitnessFactor) -> {
					// Debug Individual Function which takes param as List<T> (individual/offspring) and returns the Mutation Point for an Offspring
					return individual.toString() + " Fitness Quotient = " + fitnessFactor;
				});
		gp.start();
		System.out.println(gp.generationNumber());
		System.out.println(gp.bestSolution());
	}

}
