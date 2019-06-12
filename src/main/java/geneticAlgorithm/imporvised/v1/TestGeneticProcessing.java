package geneticAlgorithm.imporvised.v1;

import java.util.ArrayList;
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
	public static Random RANDOM = new Random();
	
	public static void processIntegerCounter() throws CloneNotSupportedException {
		GeneticProcessing<Integer> gp = new GeneticProcessing<Integer>(
				() -> {
					// Supplier function that provides Population List<List<T>>
					List<List<Integer>> individuals = new LinkedList<List<Integer>>();
					for (int i = 0; i < 10; i++) {
						List<Integer> individual = new LinkedList<Integer>();
						for (int j = 0; j < 5; j++) {
							individual.add(Math.abs(RANDOM.nextInt() % 2));
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
					return (RANDOM.nextInt()%7 < 5);
				},
				(Individual<Integer> individual) -> {
					// Mutation Point Function which takes param as List<T> (individual/offspring) & fitnessFactor and returns the Mutation Point for an Offspring
					return RANDOM.nextInt(individual.getGenes().size());
				},
				(Gene<Integer> gene, Individual<Integer> individual) -> {
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
	
	public static void processKnapsack() throws CloneNotSupportedException {
		// Problem and Item Details
		final String itemNames[] = {"SLEEPING_BAG", "ROPE", "POCKET_KNIFE", "TORCH", "BOTTLE", "GLUCOSE"};
		final Integer weights[] = {15, 3, 2, 5, 9, 20};
		final Integer survivalPoints[] = {15, 7, 10, 5, 8, 17};
		final Integer MAX_WEIGHT = 30;
		
		// Solution
		
		// Alleles (Gene)
		class BagItem {
			String name;
			Integer weight;
			Integer survivalPoints;
			Boolean isPresent;
			
			public BagItem(String name, Integer weight, Integer survivalPoints) {
				this.name = name;
				this.weight = weight;
				this.survivalPoints = survivalPoints;
			}

			@SuppressWarnings("unused")
			public String getName() {
				return name;
			}

			public Integer getWeight() {
				return weight;
			}

			public Integer getSurvivalPoints() {
				return survivalPoints;
			}

			public Boolean getIsPresent() {
				return isPresent;
			}

			public void setIsPresent(Boolean isPresent) {
				this.isPresent = isPresent;
			}
			
			public String toString() {
				if (this.isPresent) {
					return "{" +this.name + " - Weight = " + this.weight + " - Survival Points = " + this.survivalPoints + "}";
				} else {
					return "";
				}
			}
		}
		Boolean logIntermediateSteps = true;
		Function<Individual<BagItem>, String> debugIndividualFunction = (Individual<BagItem> individual) -> {
			int totalBagWeight = 0;
			for (final Gene<BagItem> individualGene : individual.getGenes()) {
				if (individualGene.getAlleles().getIsPresent()) {
					totalBagWeight += individualGene.getAlleles().getWeight();
				}
			}
			return individual.getGenes().toString() + " Fitness Quotient = " + individual.getFitnessFactor() + " Total Weight = " + totalBagWeight;
		};
		@SuppressWarnings("unchecked")
		GeneticProcessing<BagItem> gp = new GeneticProcessing<BagItem>(
				() -> {
					// Supplier function that provides Population List<List<T>>
					List<List<BagItem>> individuals = new LinkedList<List<BagItem>>();
					List<String> containedCombination = new ArrayList<String>();
					for (int i = 0; i < 64; i++) {
						List<BagItem> individual = new LinkedList<BagItem>();
						String binaryEquivalent = FunctionalUtil.decimalToBinary(i, 6);
						int totalWeightOfBag = 0;
						String combination = "";
						for (int j = 0; j < binaryEquivalent.length(); j++) {
							BagItem bagItem = new BagItem(itemNames[j], weights[j], survivalPoints[j]);
							if (totalWeightOfBag + bagItem.getWeight() <= MAX_WEIGHT) {
								bagItem.setIsPresent(FunctionalUtil.binaryToBoolean(Integer.parseInt(String.valueOf(binaryEquivalent.charAt(j)))));
								if (bagItem.getIsPresent()) {
									totalWeightOfBag += bagItem.getWeight();
								}
							} else {
								bagItem.setIsPresent(false);
							}
							individual.add(bagItem);
							combination += bagItem.getIsPresent() ? "1" : "0";
						}
						if (!containedCombination.contains(combination)) {
							containedCombination.add(combination);
							individuals.add(individual);
						} 
					}
					return individuals;
				},
				(Gene<BagItem> gene) -> {
					// Fitness function which takes param as T (alleles) and returns it's Fitness as an Empirical Integer
					return gene.getAlleles().getIsPresent() ? gene.getAlleles().getSurvivalPoints() : 0;
				}, 
				null, // Selection Function -> For default leave NULL
				null, // Crossover Point Function -> For default leave NULL
				(SelectedIndividualPool<BagItem> selectedIndividualPool, Function<Individual<BagItem>, Integer> crossoverPointFunction) -> {
					Individual<BagItem> offspringPrimary = selectedIndividualPool.getBestCandidate().clone();
					Individual<BagItem> offspringSecondary = selectedIndividualPool.getSecondBestCandidate().clone();
					Integer crossoverPoint = crossoverPointFunction.apply(selectedIndividualPool.getBestCandidate());
					int totalBagWeightOffspringPrimary = 0;
					for (final Gene<BagItem> individualGene : offspringPrimary.getGenes()) {
						if (individualGene.getAlleles().getIsPresent()) {
							totalBagWeightOffspringPrimary += individualGene.getAlleles().getWeight();
						}
					}
					int totalBagWeightOffspringSecondary = 0;
					for (final Gene<BagItem> individualGene : offspringSecondary.getGenes()) {
						if (individualGene.getAlleles().getIsPresent()) {
							totalBagWeightOffspringSecondary += individualGene.getAlleles().getWeight();
						}
					}
					for (int i = 0; i <= crossoverPoint; i++) {
						Gene<BagItem> tempGene = offspringPrimary.getGenes().get(i);
						int newWeightOffspringPrimary = 0;
						if (offspringSecondary.getGenes().get(i).getAlleles().getIsPresent()) {
							if (offspringPrimary.getGenes().get(i).getAlleles().getIsPresent()) {
								newWeightOffspringPrimary = totalBagWeightOffspringPrimary - offspringPrimary.getGenes().get(i).getAlleles().getWeight() + offspringSecondary.getGenes().get(i).getAlleles().getWeight();
							} else {
								newWeightOffspringPrimary = totalBagWeightOffspringPrimary + offspringSecondary.getGenes().get(i).getAlleles().getWeight();
							}
						} else {
							if (offspringPrimary.getGenes().get(i).getAlleles().getIsPresent()) {
								newWeightOffspringPrimary = totalBagWeightOffspringPrimary - offspringPrimary.getGenes().get(i).getAlleles().getWeight();
							} else {
								newWeightOffspringPrimary = totalBagWeightOffspringPrimary;
							}
						}
						if (newWeightOffspringPrimary <= MAX_WEIGHT) {
							offspringPrimary.getGenes().set(i, offspringSecondary.getGenes().get(i));
						}
						int newWeightOffspringSecondary = 0;
						if (tempGene.getAlleles().getIsPresent()) {
							if (offspringSecondary.getGenes().get(i).getAlleles().getIsPresent()) {
								newWeightOffspringSecondary = totalBagWeightOffspringSecondary - offspringSecondary.getGenes().get(i).getAlleles().getWeight() + tempGene.getAlleles().getWeight();
							} else {
								newWeightOffspringSecondary = totalBagWeightOffspringSecondary + tempGene.getAlleles().getWeight();
							}
						} else {
							if (offspringSecondary.getGenes().get(i).getAlleles().getIsPresent()) {
								newWeightOffspringSecondary = totalBagWeightOffspringSecondary - offspringSecondary.getGenes().get(i).getAlleles().getWeight();
							} else {
								newWeightOffspringSecondary = totalBagWeightOffspringSecondary;
							}
						}
						if (newWeightOffspringSecondary <= MAX_WEIGHT) {
							offspringSecondary.getGenes().set(i, tempGene);
						}
						offspringPrimary.computeFitnessFactor();
						offspringSecondary.computeFitnessFactor();
			        }
					YieldedOffspringPool<BagItem> yieldedOffspringPool = new YieldedOffspringPool<BagItem>();
					yieldedOffspringPool.setOffspringPrimary(offspringPrimary);
					yieldedOffspringPool.setOffspringSecondary(offspringSecondary);
					yieldedOffspringPool.setTotalOffspringYielded(2);
					yieldedOffspringPool.setYieldedOffspring(new Individual[] {offspringPrimary, offspringSecondary});
					FunctionalUtil.logStep(logIntermediateSteps, "			Crossover Point = " + crossoverPoint);
					FunctionalUtil.logStep(logIntermediateSteps, "			Offspring Primary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
					FunctionalUtil.logStep(logIntermediateSteps, "			Offspring Secondary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
					return yieldedOffspringPool;
				}, // Crossover Function -> For default leave NULL
				(Individual<BagItem> individual) -> {
					// Mutable Check Function which takes param as List<T> (individual/offspring) & fitnessFactor to check if an Offspring should be Mutated
					return (RANDOM.nextInt()%2 == 9);
				},
				(Individual<BagItem> individual) -> {
					// Mutation Point Function which takes param as List<T> (individual/offspring) & fitnessFactor and returns the Mutation Point for an Offspring
					return RANDOM.nextInt(individual.getGenes().size());
				},
				(Gene<BagItem> gene, Individual<BagItem> individual) -> {
					// Mutation Function which takes param as T (alleles) & fitnessFactor and Mutate the Offspring alleles
					int totalBagWeight = 0;
					for (final Gene<BagItem> individualGene : individual.getGenes()) {
						if (individualGene.getAlleles().getIsPresent()) {
							totalBagWeight += individualGene.getAlleles().getWeight();
						}
					}
					if (!gene.getAlleles().getIsPresent()) {
						if (totalBagWeight + gene.getAlleles().getWeight() <= MAX_WEIGHT) {
							gene.getAlleles().setIsPresent(true);
						}
					} else {
						gene.getAlleles().setIsPresent(false);
					}
					return gene.getAlleles();
				},
				/*(List<Individual<BagItem>> individuals, SelectedIndividualPool<BagItem> selectedIndividualPool, YieldedOffspringPool<BagItem> yieldedOffspringPool) -> {
					if (yieldedOffspringPool.getOffspringPrimary().getFitnessFactor() >= individuals.get(selectedIndividualPool.getBestCandidateIndex()).getFitnessFactor()) {
						individuals.set(selectedIndividualPool.getBestCandidateIndex(), yieldedOffspringPool.getOffspringPrimary());
						FunctionalUtil.logStep(logIntermediateSteps, "		Replaced MaleParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getBestCandidate()) + " @ Index = " + selectedIndividualPool.getBestCandidateIndex() + " with Offspring First >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
						if (yieldedOffspringPool.getOffspringPrimary().getFitnessFactor() < selectedIndividualPool.getWorstCandidate().getFitnessFactor()) {
							selectedIndividualPool.setWorstCandidate(yieldedOffspringPool.getOffspringPrimary());
							selectedIndividualPool.setWorstCandidateIndex(selectedIndividualPool.getBestCandidateIndex());
						}
					}
					if (yieldedOffspringPool.getOffspringSecondary().getFitnessFactor() >= individuals.get(selectedIndividualPool.getSecondBestCandidateIndex()).getFitnessFactor()) {
						individuals.set(selectedIndividualPool.getSecondBestCandidateIndex(), yieldedOffspringPool.getOffspringSecondary());
						FunctionalUtil.logStep(logIntermediateSteps, "		Replaced FemaleParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getSecondBestCandidate()) + " @ Index = " + selectedIndividualPool.getSecondBestCandidateIndex() + " with Offspring Second >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
						if (yieldedOffspringPool.getOffspringSecondary().getFitnessFactor() < selectedIndividualPool.getWorstCandidate().getFitnessFactor()) {
							selectedIndividualPool.setWorstCandidate(yieldedOffspringPool.getOffspringSecondary());
							selectedIndividualPool.setWorstCandidateIndex(selectedIndividualPool.getSecondBestCandidateIndex());
						}
					}
					FunctionalUtil.logStep(logIntermediateSteps, "		New WorstParent Individual >> " + debugIndividualFunction.apply(selectedIndividualPool.getWorstCandidate()) + " @ Index = " + selectedIndividualPool.getWorstCandidateIndex());
					// Replace least fitness Individual with Best offspring
					Individual<BagItem> fittest = null;
					Integer fittestIndex = -1;
					Integer bestFitnessFactor = Integer.MIN_VALUE;
					for (int i = 0; i < individuals.size(); i++) {
						Individual<BagItem> individual = individuals.get(i);
						if (individual.getFitnessFactor() > bestFitnessFactor) {
							fittest = individual;
							bestFitnessFactor = individual.getFitnessFactor();
							fittestIndex = i;
						} 
					}
					FunctionalUtil.logStep(logIntermediateSteps, "		Fittest Individual >> " + debugIndividualFunction.apply(fittest) + " @ Index = " + fittestIndex);
					if (yieldedOffspringPool.getBestOffspring().getFitnessFactor() > individuals.get(selectedIndividualPool.getWorstCandidateIndex()).getFitnessFactor()) {
						individuals.set(selectedIndividualPool.getWorstCandidateIndex(), yieldedOffspringPool.getBestOffspring());
						FunctionalUtil.logStep(logIntermediateSteps, "		Replaced WorstParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getWorstCandidate()) + " @ Index = " + selectedIndividualPool.getWorstCandidateIndex() + " with Yielded Fittest Offspring Individual >> " + debugIndividualFunction.apply(fittest));
					}
					return fittest;
				}*/null, // Replacement Function -> For default leave NULL
				(Individual<BagItem> currentBestCandidate) -> {
					// Convergence Check Function which takes param as List<T> (individual) to check if Convergence is achieved and Genetic Population Processing should be stopped
					return (currentBestCandidate.getFitnessFactor() > 35);
				},
				(Individual<BagItem> currentBestCandidate, Integer currentGenerationRank) -> {					
					// Threshold Check Function which takes param as List<T> (individual) and Integer Generation-Number to check if Genetic Population Processing should be stopped if Convergence is not achieved
					Boolean thersholdValue = currentGenerationRank >= 50000;
					if (thersholdValue) {
						System.out.println("Threshold Limit Reached");
					}
					return thersholdValue;
				}, 
				logIntermediateSteps, 
				debugIndividualFunction 
				);
		gp.start();
		Individual<BagItem> bestSolution = gp.bestSolution();
		int totalBagWeight = 0;
		for (final Gene<BagItem> individualGene : bestSolution.getGenes()) {
			if (individualGene.getAlleles().getIsPresent()) {
				totalBagWeight += individualGene.getAlleles().getWeight();
			}
		}
		System.out.println("Best Solution Individual = " + bestSolution.getGenes() + " Fitness Quotient = " + bestSolution.getFitnessFactor() + " Total Weight = " + totalBagWeight);
		System.out.println("Solution Found @ Generation = " + gp.generationNumber());
	}
	
	public static void main(String[] args) throws Exception {
		processKnapsack();
	}
}
