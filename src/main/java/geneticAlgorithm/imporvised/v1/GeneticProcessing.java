package geneticAlgorithm.imporvised.v1;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GeneticProcessing<T> {
	
	private Population<T> population;

	public GeneticProcessing(
		Supplier<List<List<T>>> individualsSupplierFunction,
		Function<T, Integer> fitnessFunction,
		Function<List<T>, Integer> crossoverPointFunction,
		Predicate<List<T>> mutable,
		Function<List<T>, Integer> mutationPointFunction,
		Function<T, T> mutate,
		BiFunction<List<T>, Integer, Boolean> convergenceFunction,
		BiFunction<List<T>, Integer, Boolean> thresholdExitFunction,
		Boolean logIntermediateSteps,
		BiFunction<List<T>, Integer, String> debugIndividual
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
				System.out.println("Individual cannot have NULL Genome Set");
				System.exit(0);
			}
		}
		if (!Validator.checkObjectAvailability(fitnessFunction)) {
			System.out.println("Fitness function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(crossoverPointFunction)) {
			System.out.println("Mating crossover point function cannot be NULL");
			System.exit(0);
		}
		if (!Validator.checkObjectAvailability(mutable)) {
			System.out.println("Mutable function is NULL - Making it mutable false");
			mutable = (List<T> individual) -> {
				return false;
			};
			mutationPointFunction = (List<T> individual) -> {
				return -1;
			};
			mutate = (T genome) -> {
				return genome;
			};
		} else {
			if (!Validator.checkObjectAvailability(mutationPointFunction) || !Validator.checkObjectAvailability(mutate)) {
				System.out.println("Offsprings can Mutate");
				if (!Validator.checkObjectAvailability(mutationPointFunction)) {
					System.out.println("Mutation point function cannot be NULL");
					System.exit(0);
				}
				if (!Validator.checkObjectAvailability(mutate)) {
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
			thresholdExitFunction = (List<T> individual, Integer solutionGenerationRank) -> {
				return false;
			};
		}
		if (!Validator.checkObjectAvailability(logIntermediateSteps)) {
			logIntermediateSteps = false;
		}
		if (logIntermediateSteps) {
			if (!Validator.checkObjectAvailability(debugIndividual)) {
				System.out.println("Debugging is ON -> Individual Debug function cannot be NULL");
				System.exit(0);
			}
		} else {
			debugIndividual = (List<T> individual, Integer fitnessFactor) -> {
				return "";
			};
		}
		this.population = new Population<T>(individuals, fitnessFunction, crossoverPointFunction, mutable, mutationPointFunction, mutate, convergenceFunction, thresholdExitFunction, logIntermediateSteps, debugIndividual);
	}
	
	public void start() throws CloneNotSupportedException {
		this.population.startProcessing();
	}
	
	public List<T> bestSolution() {
		return this.population.getFittest().getGenomeList();
	}
	
	public Integer generationNumber() {
		return this.population.getCurrentGeneration();
	}
}
