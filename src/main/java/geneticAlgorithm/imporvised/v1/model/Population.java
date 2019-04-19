package geneticAlgorithm.imporvised.v1.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import geneticAlgorithm.imporvised.v1.functionalInterfaces.CrossoverFunction;
import geneticAlgorithm.imporvised.v1.functionalInterfaces.ReplacementFunction;
import geneticAlgorithm.imporvised.v1.functionalInterfaces.SelectionFunction;
import geneticAlgorithm.imporvised.v1.utils.FunctionalUtil;

public class Population<T> implements Cloneable {
	
	private List<Individual<T>> individuals;
	private Individual<T> fittest;
	private Integer currentGeneration = 0;
	
	// Control Variables
	private Boolean logIntermediateSteps;
	// Functions
	private Function<Gene<T>, Integer> fitnessFunction;
	private SelectionFunction<T> selectionFunction;
	private Function<Individual<T>, Integer> crossoverPointFunction;
	private CrossoverFunction<T> crossoverFunction;
	private Function<Individual<T>, Boolean> mutableCheckFunction;
	private Function<Individual<T>, Integer> mutationPointFunction;
	private Function<Gene<T>, T> mutateFunction;
	private ReplacementFunction<T> replacementFunction;
	private Function<Individual<T>, String> debugIndividualFunction;
	private Function<Individual<T>, Boolean> convergenceFunction;
	private BiFunction<Individual<T>, Integer, Boolean> thresholdExitFunction;
	
	public Population(
		List<List<T>> individualsAlleles,
		Function<Gene<T>, Integer> fitnessFunction,
		SelectionFunction<T> selectionFunction,
		Function<Individual<T>, Integer> crossoverPointFunction,
		CrossoverFunction<T> crossoverFunction,
		Function<Individual<T>, Boolean> mutableCheckFunction,
		Function<Individual<T>, Integer> mutationPointFunction,
		Function<Gene<T>, T> mutateFunction,
		ReplacementFunction<T> replacementFunction,
		Function<Individual<T>, Boolean> convergenceFunction,
		BiFunction<Individual<T>, Integer, Boolean> thresholdExitFunction,
		Boolean logIntermediateSteps,
		Function<Individual<T>, String> debugIndividualFunction
	) {
		this.fitnessFunction = fitnessFunction;
		this.selectionFunction = selectionFunction;
		this.crossoverPointFunction = crossoverPointFunction;
		this.crossoverFunction = crossoverFunction;
		this.mutableCheckFunction = mutableCheckFunction;
		this.mutationPointFunction = mutationPointFunction;
		this.mutateFunction = mutateFunction;
		this.replacementFunction = replacementFunction;
		this.convergenceFunction = convergenceFunction;
		this.thresholdExitFunction = thresholdExitFunction;
		this.logIntermediateSteps = logIntermediateSteps;
		this.debugIndividualFunction = debugIndividualFunction;
		
		FunctionalUtil.logStep(this.logIntermediateSteps, "Intializing Population");
		this.individuals = new LinkedList<Individual<T>>();
		this.individuals.addAll(individualsAlleles.parallelStream().map((List<T> individualAlleles) -> {
								List<Gene<T>> genes = new LinkedList<Gene<T>>();
								genes.addAll(individualAlleles.parallelStream().map((T alleles) -> {
									Gene<T> gene = new Gene<T>(alleles);
									gene.setFitnessFactor(this.fitnessFunction.apply(gene));
									return gene;
								}).collect(Collectors.toList()));
								Individual<T> individual = new Individual<T>(genes);
								FunctionalUtil.logStep(this.logIntermediateSteps, "	Individual = " + this.debugIndividualFunction.apply(individual));
								return individual;
							}).collect(Collectors.toList()));
	}
	
	public void startProcessing() throws CloneNotSupportedException {
		FunctionalUtil.logStep(this.logIntermediateSteps, "Starting Genetic Process");
		Boolean canContinue = true;
		do {
			processGeneration();
			Boolean thresholdExitClause = this.thresholdExitFunction.apply(this.fittest, this.currentGeneration);
			Boolean convergenceAchieved = this.convergenceFunction.apply(this.fittest);
			FunctionalUtil.logStep(this.logIntermediateSteps, "	Threshold Exit Clause >> " + thresholdExitClause);
			FunctionalUtil.logStep(this.logIntermediateSteps, "	Convergence Achieved >> " + convergenceAchieved);
			canContinue = !thresholdExitClause && !convergenceAchieved;
		} while(canContinue);
		FunctionalUtil.logStep(this.logIntermediateSteps, "Completed Genetic Process");
	}
	
	private void processGeneration() throws CloneNotSupportedException {
		this.currentGeneration++;
		FunctionalUtil.logStep(this.logIntermediateSteps, "	Processing Generation >> " + this.currentGeneration);
		// Selection
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Parent Selection Starts");
		SelectedIndividualPool<T> selectedIndividualPool = this.selectionFunction.select(this.individuals);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Parent Selection Ends");
		// Crossover
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Crossover Starts");
		YieldedOffspringPool<T> yieldedOffspringPool = this.crossoverFunction.crossover(selectedIndividualPool, this.crossoverPointFunction);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Crossover Ends");
		// Mutation
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Mutation Starts");
		for (Individual<T> offspring : yieldedOffspringPool.getYieldedOffspring()) {
			Boolean isMutable = this.mutableCheckFunction.apply(offspring);
			FunctionalUtil.logStep(this.logIntermediateSteps, "			Offspring >> " + this.debugIndividualFunction.apply(offspring));
			FunctionalUtil.logStep(this.logIntermediateSteps, "				Is Offspring Mutable >> " + isMutable);
			if (isMutable) {
				Integer mutationPoint = this.mutationPointFunction.apply(offspring);
				FunctionalUtil.logStep(this.logIntermediateSteps, "				Mutation Point >> " + mutationPoint);
				Gene<T> gene = offspring.getGenes().get(mutationPoint);
				gene.setAlleles(this.mutateFunction.apply(gene));
				gene.setFitnessFactor(this.fitnessFunction.apply(gene));
				offspring.computeFitnessFactor();
				FunctionalUtil.logStep(this.logIntermediateSteps, "				New Mutated Offspring >> " + debugIndividualFunction.apply(offspring));
			}
		}
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Mutation Ends");
		yieldedOffspringPool.computeBestOffspring();
		// Replacement
		this.fittest = this.replacementFunction.replaceIndividuals(this.individuals, selectedIndividualPool, yieldedOffspringPool);
		FunctionalUtil.logStep(this.logIntermediateSteps, "	Completed Generation >> " + this.currentGeneration);
	}
	
	public Individual<T> getFittest() {
		return fittest;
	}

	public Integer getCurrentGeneration() {
		return currentGeneration;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Population<T> clone() throws CloneNotSupportedException {
		return (Population<T>)super.clone();
	}
}
