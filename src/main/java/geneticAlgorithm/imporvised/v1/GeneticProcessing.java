package geneticAlgorithm.imporvised.v1;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import geneticAlgorithm.imporvised.v1.functionalInterfaces.CrossoverFunction;
import geneticAlgorithm.imporvised.v1.functionalInterfaces.ReplacementFunction;
import geneticAlgorithm.imporvised.v1.functionalInterfaces.SelectionFunction;
import geneticAlgorithm.imporvised.v1.model.Gene;
import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.Population;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;
import geneticAlgorithm.imporvised.v1.model.YieldedOffspringPool;
import geneticAlgorithm.imporvised.v1.utils.FunctionalUtil;
import geneticAlgorithm.imporvised.v1.utils.Validator;

public class GeneticProcessing<T> {
	
	private Random random = new Random();
	private Population<T> population;

	public GeneticProcessing(
		Supplier<List<List<T>>> individualsSupplierFunction,
		Function<Gene<T>, Integer> fitnessFunction,
		SelectionFunction<T> selectionFunction,
		Function<Individual<T>, Integer> crossoverPointFunction,
		CrossoverFunction<T> crossoverFunction,
		Function<Individual<T>, Boolean> mutableCheckFunction,
		Function<Individual<T>, Integer> mutationPointFunction,
		BiFunction<Gene<T>, Individual<T>,T> mutateFunction,
		ReplacementFunction<T> replacementFunction,
		Function<Individual<T>, Boolean> convergenceFunction,
		BiFunction<Individual<T>, Integer, Boolean> thresholdExitFunction,
		Boolean logIntermediateSteps,
		Function<Individual<T>, String> debugIndividualFunction
	) {
		if (!Validator.checkObjectAvailability(logIntermediateSteps)) {
			logIntermediateSteps = false;
		}
		if (logIntermediateSteps) {
			if (!Validator.checkObjectAvailability(debugIndividualFunction)) {
				FunctionalUtil.logStep(logIntermediateSteps, "Debug Individual Function is NULL");
				debugIndividualFunction = getDefaultDebugIndividualFunction(logIntermediateSteps);
			}
		} else {
			debugIndividualFunction = getDefaultBlankDebugIndividualFunction(logIntermediateSteps);
		}
		if (!Validator.checkObjectAvailability(individualsSupplierFunction)) {
			FunctionalUtil.logStep(true, "Population Individual Supplier Function cannot be NULL");
			System.exit(0);
		}
		List<List<T>> individuals = individualsSupplierFunction.get();
		if (!Validator.checkNonEmptyList(individuals)) {
			FunctionalUtil.logStep(true, "Population Individual List Empty");
			System.exit(0);
		}
		for (List<T> individual : individuals) {
			if (!Validator.checkNonEmptyList(individual)) {
				FunctionalUtil.logStep(true, "Individual cannot have NULL Alleles Set");
				System.exit(0);
			}
		}
		if (!Validator.checkObjectAvailability(fitnessFunction)) {
			FunctionalUtil.logStep(true, "Fitness Function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(selectionFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Selection Function is NULL");
			selectionFunction = getDefaultSelectionFunction(logIntermediateSteps, debugIndividualFunction);
		}
		if (!Validator.checkObjectAvailability(crossoverPointFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Crossover Point Function is NULL");
			crossoverPointFunction = getDefaultCrossoverPointFunction(logIntermediateSteps, debugIndividualFunction);
		}
		if (!Validator.checkObjectAvailability(crossoverFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Crossover Function is NULL");
			crossoverFunction = getDefaultCrossoverFunction(logIntermediateSteps, debugIndividualFunction);
		}
		if (!Validator.checkObjectAvailability(mutableCheckFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Mutable Function is NULL - Making it mutable false");
			mutableCheckFunction = getDefaultMutableCheckFunction(logIntermediateSteps, debugIndividualFunction);
			mutationPointFunction = getDefaultMutationPointFunction(logIntermediateSteps, debugIndividualFunction);
			mutateFunction = getDefaultMutateFunction(logIntermediateSteps, debugIndividualFunction);
		} else {
			if (!Validator.checkObjectAvailability(mutationPointFunction) || !Validator.checkObjectAvailability(mutateFunction)) {
				FunctionalUtil.logStep(logIntermediateSteps, "Offsprings can Mutate");
				if (!Validator.checkObjectAvailability(mutationPointFunction)) {
					FunctionalUtil.logStep(true, "Mutation point Function cannot be NULL");
					System.exit(0);
				}
				if (!Validator.checkObjectAvailability(mutateFunction)) {
					FunctionalUtil.logStep(true, "Mutate Function cannot be NULL");
					System.exit(0);
				}
			}
		}
		if (!Validator.checkObjectAvailability(replacementFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Replacement Function is NULL");
			replacementFunction = getDefaultReplacementFunction(logIntermediateSteps, debugIndividualFunction);
		}
		if (!Validator.checkObjectAvailability(convergenceFunction)) {
			FunctionalUtil.logStep(true, "Convergence Function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(thresholdExitFunction)) {
			FunctionalUtil.logStep(logIntermediateSteps, "Threshold Exit Function is NULL - Making it false");
			thresholdExitFunction = getDefaultThresholdExitFunction(logIntermediateSteps, debugIndividualFunction);
		}
		this.population = new Population<T>(
								individuals, 
								fitnessFunction, 
								selectionFunction, 
								crossoverPointFunction, 
								crossoverFunction,
								mutableCheckFunction, 
								mutationPointFunction, 
								mutateFunction, 
								replacementFunction,
								convergenceFunction, 
								thresholdExitFunction, 
								logIntermediateSteps, 
								debugIndividualFunction
							);
	}
	
	private Function<Individual<T>, String> getDefaultDebugIndividualFunction(Boolean logIntermediateSteps) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Debug Individual Function");
		return (Individual<T> individual) -> {
			return individual.getGenes().toString() + " Fitness Quotient = " + individual.getFitnessFactor();
		};
	}
	
	private Function<Individual<T>, String> getDefaultBlankDebugIndividualFunction(Boolean logIntermediateSteps) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Debug Individual Function");
		return (Individual<T> individual) -> {
			return "";
		};
	}
	
	@SuppressWarnings("unchecked")
	private SelectionFunction<T> getDefaultSelectionFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Selection Function");
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
			FunctionalUtil.logStep(logIntermediateSteps, "			Best Parent Individual >> " + debugIndividualFunction.apply(selectedIndividualPool.getBestCandidate()) + " @ Index = " + selectedIndividualPool.getBestCandidateIndex());
			FunctionalUtil.logStep(logIntermediateSteps, "			Second Best Parent Individual >> " + debugIndividualFunction.apply(selectedIndividualPool.getSecondBestCandidate()) + " @ Index = " + selectedIndividualPool.getSecondBestCandidateIndex());
			FunctionalUtil.logStep(logIntermediateSteps, "			Worst Parent Individual >> " + debugIndividualFunction.apply(selectedIndividualPool.getWorstCandidate()) + " @ Index = " + selectedIndividualPool.getWorstCandidateIndex());
			return selectedIndividualPool;
		};
	}
	
	@SuppressWarnings("unchecked")
	private CrossoverFunction<T> getDefaultCrossoverFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Crossover Function");
		return (SelectedIndividualPool<T> selectedIndividualPool, Function<Individual<T>, Integer> crossoverPointFunction) -> {
			Individual<T> offspringPrimary = selectedIndividualPool.getBestCandidate().clone();
			Individual<T> offspringSecondary = selectedIndividualPool.getSecondBestCandidate().clone();
			Integer crossoverPoint = crossoverPointFunction.apply(selectedIndividualPool.getBestCandidate());
			for (int i = 0; i <= crossoverPoint; i++) {
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
			FunctionalUtil.logStep(logIntermediateSteps, "			Crossover Point = " + crossoverPoint);
			FunctionalUtil.logStep(logIntermediateSteps, "			Offspring Primary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
			FunctionalUtil.logStep(logIntermediateSteps, "			Offspring Secondary >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
			return yieldedOffspringPool;
		};
	}
	
	private Function<Individual<T>, Integer> getDefaultCrossoverPointFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Crossover Point Function");
		return (Individual<T> individual) -> {
			return random.nextInt(individual.getGenes().size());
		};
	}
	
	private Function<Individual<T>, Boolean> getDefaultMutableCheckFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Mutable Check Function");
		return (Individual<T> individual) -> {
			return false;
		};
	}
	
	private Function<Individual<T>, Integer> getDefaultMutationPointFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Mutation Point Function");
		return (Individual<T> individual) -> {
			return -1;
		};
	}
	
	private BiFunction<Gene<T>, Individual<T>, T> getDefaultMutateFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Mutate Function");
		return (Gene<T> gene, Individual<T> individual) -> {
			return gene.getAlleles();
		};
	}
	
	private ReplacementFunction<T> getDefaultReplacementFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Crossover Function");
		return (List<Individual<T>> individuals, SelectedIndividualPool<T> selectedIndividualPool, YieldedOffspringPool<T> yieldedOffspringPool) -> {
			individuals.set(selectedIndividualPool.getBestCandidateIndex(), yieldedOffspringPool.getOffspringPrimary());
			FunctionalUtil.logStep(logIntermediateSteps, "		Replaced MaleParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getBestCandidate()) + " @ Index = " + selectedIndividualPool.getBestCandidateIndex() + " with Offspring First >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
			if (yieldedOffspringPool.getOffspringPrimary().getFitnessFactor() < selectedIndividualPool.getWorstCandidate().getFitnessFactor()) {
				selectedIndividualPool.setWorstCandidate(yieldedOffspringPool.getOffspringPrimary());
				selectedIndividualPool.setWorstCandidateIndex(selectedIndividualPool.getBestCandidateIndex());
			}
			individuals.set(selectedIndividualPool.getSecondBestCandidateIndex(), yieldedOffspringPool.getOffspringSecondary());
			FunctionalUtil.logStep(logIntermediateSteps, "		Replaced FemaleParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getSecondBestCandidate()) + " @ Index = " + selectedIndividualPool.getSecondBestCandidateIndex() + " with Offspring Second >> " + debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
			if (yieldedOffspringPool.getOffspringSecondary().getFitnessFactor() < selectedIndividualPool.getWorstCandidate().getFitnessFactor()) {
				selectedIndividualPool.setWorstCandidate(yieldedOffspringPool.getOffspringSecondary());
				selectedIndividualPool.setWorstCandidateIndex(selectedIndividualPool.getSecondBestCandidateIndex());
			}
			FunctionalUtil.logStep(logIntermediateSteps, "		New WorstParent Individual >> " + debugIndividualFunction.apply(selectedIndividualPool.getWorstCandidate()) + " @ Index = " + selectedIndividualPool.getWorstCandidateIndex());
			// Replace least fitness Individual with Best offspring
			Individual<T> fittest = yieldedOffspringPool.getBestOffspring();
			FunctionalUtil.logStep(logIntermediateSteps, "		Fittest Individual >> " + debugIndividualFunction.apply(fittest));
			individuals.set(selectedIndividualPool.getWorstCandidateIndex(), fittest);
			FunctionalUtil.logStep(logIntermediateSteps, "		Replaced WorstParent >> " + debugIndividualFunction.apply(selectedIndividualPool.getWorstCandidate()) + " @ Index = " + selectedIndividualPool.getWorstCandidateIndex() + " with Fittest Individual >> " + debugIndividualFunction.apply(fittest));
			return fittest;
		};
	}
	
	private BiFunction<Individual<T>, Integer, Boolean> getDefaultThresholdExitFunction(Boolean logIntermediateSteps, Function<Individual<T>, String> debugIndividualFunction) {
		FunctionalUtil.logStep(logIntermediateSteps, "Getting Default Threshold Exit Function");
		return (Individual<T> individual, Integer solutionGenerationRank) -> {
			return false;
		};
	}
	
	public void start() throws CloneNotSupportedException {
		this.population.startProcessing();
	}
	
	public Individual<T> bestSolution() {
		return this.population.getFittestIndividual();
	}
	
	public Integer generationNumber() {
		return this.population.getCurrentGeneration();
	}
}
