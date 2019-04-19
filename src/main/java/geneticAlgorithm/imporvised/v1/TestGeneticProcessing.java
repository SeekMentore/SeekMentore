package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import geneticAlgorithm.imporvised.v1.model.Gene;
import geneticAlgorithm.imporvised.v1.model.Individual;

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
				(Gene<Integer> gene) -> {
					// Fitness function which takes param as T (alleles) and returns it's Fitness as an Empirical Integer
					return ((gene.getAlleles() == 1) ? 1 : 0);
				}, 
				null, // Selection Function -> For default leave NULL
				(Individual<Integer> individual) -> {
					// Crossover Point Function which takes param as List<T> (individual) & fitnessFactor and returns the Crossover Mating Point for an Individual
					return rn.nextInt(individual.getGenes().size());
				},
				null, // Crossover Function -> For default leave NULL
				(Individual<Integer> individual) -> {
					// Mutable Check Function which takes param as List<T> (individual/offspring) & fitnessFactor to check if an Offspring should be Mutated
					return (rn.nextInt()%7 < 5);
				},
				(Individual<Integer> individual) -> {
					// Mutation Point Function which takes param as List<T> (individual/offspring) & fitnessFactor and returns the Mutation Point for an Offspring
					return rn.nextInt(individual.getGenes().size());
				},
				(Gene<Integer> gene) -> {
					// Mutation Function which takes param as T (alleles) & fitnessFactor and Mutate the Offspring alleles
					if (gene.getAlleles() == 0) {
						return 1;
			        } else {
			        	return 0;
			        }
				},
				null, // Replacement Function -> For default leave NULL
				(Individual<Integer> currentBestCandidate) -> {
					// Convergence Check Function which takes param as List<T> (individual) to check if Convergence is achieved and Genetic Population Processing should be stopped
					return (currentBestCandidate.getFitnessFactor() >= 5);
				},
				(Individual<Integer> currentBestCandidate, Integer currentGenerationRank) -> {
					// Threshold Check Function which takes param as List<T> (individual) and Integer Generation-Number to check if Genetic Population Processing should be stopped if Convergence is not achieved
					Boolean thersholdValue = currentGenerationRank >= 50000;
					if (thersholdValue) {
						System.out.println("Threshold Limit Reached");
					}
					return thersholdValue;
				}, true, 
				(Individual<Integer> individual) -> {
					// Debug Individual Function which takes param as List<T> (individual/offspring) and returns the Mutation Point for an Offspring
					return individual.getGenes().toString() + " Fitness Quotient = " + individual.getFitnessFactor();
				});
		gp.start();
		Individual<Integer> bestSolution = gp.bestSolution();
		System.out.println("Best Solution Individual = " + bestSolution.getGenes() + " Fitness Quotient = " + bestSolution.getFitnessFactor());
		System.out.println("Solution Found @ Generation = " + gp.generationNumber());
	}
}
