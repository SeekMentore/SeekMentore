package geneticAlgorithm.imporvised.v1;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import geneticAlgorithm.imporvised.v1.functionalInterfaces.CrossoverFunction;
import geneticAlgorithm.imporvised.v1.functionalInterfaces.SelectionFunction;
import geneticAlgorithm.imporvised.v1.model.Gene;
import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.Population;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;
import geneticAlgorithm.imporvised.v1.model.YieldedOffspringPool;
import geneticAlgorithm.imporvised.v1.utils.Validator;

public class GeneticProcessing<T> {
	
	private Population<T> population;

	public GeneticProcessing(
		Supplier<List<List<T>>> individualsSupplierFunction,
		Function<Gene<T>, Integer> fitnessFunction,
		SelectionFunction<T> selectionFunction,
		CrossoverFunction<T> crossoverFunction,
		Function<Individual<T>, Integer> crossoverPointFunction,
		Function<Individual<T>, Boolean> mutableCheckFunction,
		Function<Individual<T>, Integer> mutationPointFunction,
		Function<Gene<T>, T> mutateFunction,
		Function<Individual<T>, Boolean> convergenceFunction,
		BiFunction<Individual<T>, Integer, Boolean> thresholdExitFunction,
		Boolean logIntermediateSteps,
		Function<Individual<T>, String> debugIndividualFunction
	) {
		if (!Validator.checkObjectAvailability(individualsSupplierFunction)) {
			System.out.println("Population Individual Supplier function cannot be NULL");
			System.exit(0);
		}
		List<List<T>> individuals = individualsSupplierFunction.get();
		if (!Validator.checkNonEmptyList(individuals)) {
			System.out.println("Population Individual List Empty");
			System.exit(0);
		}
		for (List<T> individual : individuals) {
			if (!Validator.checkNonEmptyList(individual)) {
				System.out.println("Individual cannot have NULL Alleles Set");
				System.exit(0);
			}
		}
		if (!Validator.checkObjectAvailability(fitnessFunction)) {
			System.out.println("Fitness function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(selectionFunction)) {
			System.out.println("Selection Function is NULL");
			selectionFunction = getDefaultSelectionFunction();
		}
		if (!Validator.checkObjectAvailability(crossoverFunction)) {
			System.out.println("Crossover Function is NULL");
			crossoverFunction = getDefaultCrossoverFunction();
		}
		if (!Validator.checkObjectAvailability(crossoverPointFunction)) {
			System.out.println("Mating crossover point function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(mutableCheckFunction)) {
			System.out.println("Mutable function is NULL - Making it mutable false");
			mutableCheckFunction = getDefaultMutableCheckFunction();
			mutationPointFunction = getDefaultMutationPointFunction();
			mutateFunction = getDefaultMutateFunction();
		} else {
			if (!Validator.checkObjectAvailability(mutationPointFunction) || !Validator.checkObjectAvailability(mutateFunction)) {
				System.out.println("Offsprings can Mutate");
				if (!Validator.checkObjectAvailability(mutationPointFunction)) {
					System.out.println("Mutation point function cannot be NULL");
					System.exit(0);
				}
				if (!Validator.checkObjectAvailability(mutateFunction)) {
					System.out.println("Mutate function cannot be NULL");
					System.exit(0);
				}
			}
		}
		if (!Validator.checkObjectAvailability(convergenceFunction)) {
			System.out.println("Convergence function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(thresholdExitFunction)) {
			System.out.println("Threshold Exit function is NULL - Making it false");
			thresholdExitFunction = getDefaultThresholdExitFunction();
		}
		if (!Validator.checkObjectAvailability(logIntermediateSteps)) {
			logIntermediateSteps = false;
		}
		if (logIntermediateSteps) {
			if (!Validator.checkObjectAvailability(debugIndividualFunction)) {
				System.out.println("Debugging is ON -> Individual Debug Individual function cannot be NULL");
				System.exit(0);
			}
		} else {
			debugIndividualFunction = getDefaultDebugIndividualFunction();
		}
		this.population = new Population<T>(
								individuals, 
								fitnessFunction, 
								selectionFunction, 
								crossoverFunction,
								crossoverPointFunction, 
								mutableCheckFunction, 
								mutationPointFunction, 
								mutateFunction, 
								convergenceFunction, 
								thresholdExitFunction, 
								logIntermediateSteps, 
								debugIndividualFunction
							);
	}
	
	@SuppressWarnings("unchecked")
	private SelectionFunction<T> getDefaultSelectionFunction() {
		System.out.println("Getting Default Selection Function");
		return (List<Individual<T>> individuals) -> {
			Individual<T> bestParent = null;
			Integer bestParentIndex = -1;
			Individual<T> secondBestParent = null;
			Integer secondBestParentIndex = -1;
			Individual<T> worstParent = null;
			Integer worstParentIndex = -1;
			Integer bestFitnessFactor = Integer.MIN_VALUE;
			Integer secondBestFitnessFactor = Integer.MIN_VALUE;
			Integer leastFitnessFactor = Integer.MAX_VALUE;
			SelectedIndividualPool<T> selectedIndividualPool = new SelectedIndividualPool<T>();
			for (int i = 0; i < individuals.size(); i++) {
				Individual<T> individual = individuals.get(i);
				if (individual.getFitnessFactor() > bestFitnessFactor) {
					if (Validator.checkObjectAvailability(bestParent)) {
						secondBestParent = bestParent;
						secondBestFitnessFactor = bestParent.getFitnessFactor();
						secondBestParentIndex = bestParentIndex;
					}
					bestParent = individual;
					bestFitnessFactor = individual.getFitnessFactor();
					bestParentIndex = i;
				} else {
					if (individual.getFitnessFactor() > secondBestFitnessFactor) {
						secondBestParent = individual;
						secondBestFitnessFactor = individual.getFitnessFactor();
						secondBestParentIndex = i;
					}
				}
				if (individual.getFitnessFactor() < leastFitnessFactor) {
					worstParent = individual;
					leastFitnessFactor = individual.getFitnessFactor();
					worstParentIndex = i;
				}
			}
			selectedIndividualPool.setBestCandidate(bestParent);
			selectedIndividualPool.setBestCandidateIndex(bestParentIndex);
			selectedIndividualPool.setSecondBestCandidate(secondBestParent);
			selectedIndividualPool.setSecondBestCandidateIndex(secondBestParentIndex);
			selectedIndividualPool.setWorstCandidate(worstParent);
			selectedIndividualPool.setWorstCandidateIndex(worstParentIndex);
			selectedIndividualPool.setTotalCandidatesSelected(3);
			selectedIndividualPool.setSelectedCandidatesInDescendingOrderOfFitnessFactor(new Individual[] {bestParent, secondBestParent, worstParent});
			selectedIndividualPool.setSelectedCandidatesIndexInDescendingOrderOfFitnessFactor(new Integer[] {bestParentIndex, secondBestParentIndex, worstParentIndex});
			return selectedIndividualPool;
		};
	}
	
	@SuppressWarnings("unchecked")
	private CrossoverFunction<T> getDefaultCrossoverFunction() {
		System.out.println("Getting Default Crossover Function");
		return (SelectedIndividualPool<T> selectedIndividualPool, Function<Individual<T>, Integer> crossoverPointFunction) -> {
			Individual<T> bestParent = selectedIndividualPool.getBestCandidate();
			Individual<T> secondBestParent = selectedIndividualPool.getSecondBestCandidate();
			Individual<T> offspringPrimary = bestParent.clone();
			Individual<T> offspringSecondary = secondBestParent.clone();
			Integer crossoverPoint = crossoverPointFunction.apply(bestParent);
			for (int i = 0; i < crossoverPoint; i++) {
				Gene<T> tempGene = offspringPrimary.getGenes().get(i);
				offspringPrimary.getGenes().set(i, offspringSecondary.getGenes().get(i));
				offspringSecondary.getGenes().set(i, tempGene);
				offspringPrimary.computeFitnessFactor();
				offspringSecondary.computeFitnessFactor();
	        }
			YieldedOffspringPool<T> yieldedOffspringPool = new YieldedOffspringPool<T>();
			yieldedOffspringPool.setOffspringPrimary(offspringPrimary);
			yieldedOffspringPool.setOffspringSecondary(offspringSecondary);
			yieldedOffspringPool.setTotalOffspringYielded(2);
			yieldedOffspringPool.setYieldedOffspring(new Individual[] {offspringPrimary, offspringSecondary});
			return yieldedOffspringPool;
		};
	}
	
	private Function<Individual<T>, Boolean> getDefaultMutableCheckFunction() {
		System.out.println("Getting Default Mutable Check Function");
		return (Individual<T> individual) -> {
			return false;
		};
	}
	
	private Function<Individual<T>, Integer> getDefaultMutationPointFunction() {
		System.out.println("Getting Default Mutation Point Function");
		return (Individual<T> individual) -> {
			return -1;
		};
	}
	
	private Function<Gene<T>, T> getDefaultMutateFunction() {
		System.out.println("Getting Default Mutate Function");
		return (Gene<T> gene) -> {
			return gene.getAlleles();
		};
	}
	
	private BiFunction<Individual<T>, Integer, Boolean> getDefaultThresholdExitFunction() {
		System.out.println("Getting Default Threshold Exit Function");
		return (Individual<T> individual, Integer solutionGenerationRank) -> {
			return false;
		};
	}
	
	private Function<Individual<T>, String> getDefaultDebugIndividualFunction() {
		System.out.println("Getting Default Debug Individual Function");
		return (Individual<T> individual) -> {
			return "";
		};
	}
	
	public void start() throws CloneNotSupportedException {
		this.population.startProcessing();
	}
	
	public Individual<T> bestSolution() {
		return this.population.getFittest();
	}
	
	public Integer generationNumber() {
		return this.population.getCurrentGeneration();
	}
}
