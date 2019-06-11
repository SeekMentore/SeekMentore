package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import geneticAlgorithm.imporvised.v1.model.Gene;
import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;
import geneticAlgorithm.imporvised.v1.model.YieldedOffspringPool;
import geneticAlgorithm.imporvised.v1.utils.FunctionalUtil;

public class TestGeneticProcessing {
	
	public static String[] ALPHABETS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public static String[] VOWELS = {"A","E","I","O","U"};
	
	public static void processIntegerCounter() throws CloneNotSupportedException {
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
				null, // Crossover Point Function -> For default leave NULL
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
				}, 
				true, 
				null // Debug Individual Function -> For default leave NULL
				);
		gp.start();
		Individual<Integer> bestSolution = gp.bestSolution();
		System.out.println("Best Solution Individual = " + bestSolution.getGenes() + " Fitness Quotient = " + bestSolution.getFitnessFactor());
		System.out.println("Solution Found @ Generation = " + gp.generationNumber());
	}
	
	public static void processBestWordSelector() throws CloneNotSupportedException {
		Random rn = new Random();
		GeneticProcessing<String> gp = new GeneticProcessing<String>(
				() -> {
					// Supplier function that provides Population List<List<T>>
					List<List<String>> individuals = new LinkedList<List<String>>();
					for (int i = 0; i < 10; i++) {
						List<String> individual = new LinkedList<String>();
						for (int j = 0; j < 5 + 1; j++) {
							individual.add(ALPHABETS[rn.nextInt(ALPHABETS.length)]);
						}
						individuals.add(individual);
					}
					return individuals;
				},
				(Gene<String> gene) -> {
					// Fitness function which takes param as T (alleles) and returns it's Fitness as an Empirical Integer
					int fitnessFactor = -1;
					int vowelCount = 0;
					for (int i = 0; i < gene.getAlleles().length(); i++) {
						if (i < 9) {
							String character = String.valueOf(gene.getAlleles().charAt(i));
							for (String vowel : VOWELS) {
								if (vowel.equals(character) && vowelCount < 3) {
									fitnessFactor += 10;
								} else {
									fitnessFactor += 5;
								}
							}
							if (i > 4) {
								fitnessFactor += 3;
							}
						} else {
							fitnessFactor -= 3;
						}
					}
					return fitnessFactor;
				}, 
				null, // Selection Function -> For default leave NULL
				(Individual<String> individual) -> {
					// Crossover Point Function -> For default leave NULL
					return rn.nextInt(individual.getGenes().get(0).getAlleles().length());
				}, 
				(SelectedIndividualPool<String> selectedIndividualPool, Function<Individual<String>, Integer> crossoverPointFunction) -> {
					Individual<String> offspringPrimary = selectedIndividualPool.getBestCandidate().clone();
					Individual<String> offspringSecondary = selectedIndividualPool.getSecondBestCandidate().clone();
					Integer crossoverPoint = crossoverPointFunction.apply(selectedIndividualPool.getBestCandidate());
					Gene<String> tempGene = offspringPrimary.getGenes().get(0);
					for (int i = 0; i <= crossoverPoint; i++) {
						offspringPrimary.getGenes().set(i, offspringSecondary.getGenes().get(i));
						offspringSecondary.getGenes().set(i, tempGene);
						offspringPrimary.computeFitnessFactor();
						offspringSecondary.computeFitnessFactor();
			        }
					YieldedOffspringPool<String> yieldedOffspringPool = new YieldedOffspringPool<String>();
					yieldedOffspringPool.setOffspringPrimary(offspringPrimary);
					yieldedOffspringPool.setOffspringSecondary(offspringSecondary);
					yieldedOffspringPool.setTotalOffspringYielded(2);
					yieldedOffspringPool.setYieldedOffspring(new Individual[] {offspringPrimary, offspringSecondary});
					FunctionalUtil.logStep(true, "			Crossover Point = " + crossoverPoint);
					//FunctionalUtil.logStep(true, "			Offspring Primary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
					//FunctionalUtil.logStep(true, "			Offspring Secondary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
					return yieldedOffspringPool;
				}, // Crossover Function -> For default leave NULL
				(Individual<String> individual) -> {
					// Mutable Check Function which takes param as List<T> (individual/offspring) & fitnessFactor to check if an Offspring should be Mutated
					return (rn.nextInt()%2 == 0);
				},
				(Individual<String> individual) -> {
					// Mutation Point Function which takes param as List<T> (individual/offspring) & fitnessFactor and returns the Mutation Point for an Offspring
					return rn.nextInt(individual.getGenes().get(0).getAlleles().length());
				},
				(Gene<String> gene) -> {
					// Mutation Function which takes param as T (alleles) & fitnessFactor and Mutate the Offspring alleles
					return null;
				},
				null, // Replacement Function -> For default leave NULL
				(Individual<String> currentBestCandidate) -> {
					// Convergence Check Function which takes param as List<T> (individual) to check if Convergence is achieved and Genetic Population Processing should be stopped
					return (currentBestCandidate.getFitnessFactor() >= 5);
				},
				(Individual<String> currentBestCandidate, Integer currentGenerationRank) -> {
					// Threshold Check Function which takes param as List<T> (individual) and Integer Generation-Number to check if Genetic Population Processing should be stopped if Convergence is not achieved
					Boolean thersholdValue = currentGenerationRank >= 50000;
					if (thersholdValue) {
						System.out.println("Threshold Limit Reached");
					}
					return thersholdValue;
				}, 
				true, 
				null // Debug Individual Function -> For default leave NULL
				);
		gp.start();
		Individual<String> bestSolution = gp.bestSolution();
		System.out.println("Best Solution Individual = " + bestSolution.getGenes() + " Fitness Quotient = " + bestSolution.getFitnessFactor());
		System.out.println("Solution Found @ Generation = " + gp.generationNumber());
	}

	public static void main(String[] args) throws Exception {
		processIntegerCounter();
	}
}
