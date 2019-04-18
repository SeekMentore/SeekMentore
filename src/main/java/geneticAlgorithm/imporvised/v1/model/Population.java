package geneticAlgorithm.imporvised.v1.model;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import geneticAlgorithm.imporvised.v1.functionalInterfaces.CrossoverFunction;
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
	private CrossoverFunction<T> crossoverFunction;
	private Function<Individual<T>, Integer> crossoverPointFunction;
	private Function<Individual<T>, Boolean> mutableCheckFunction;
	private Function<Individual<T>, Integer> mutationPointFunction;
	private Function<Gene<T>, T> mutateFunction;
	private Function<Individual<T>, String> debugIndividualFunction;
	private Function<Individual<T>, Boolean> convergenceFunction;
	private BiFunction<Individual<T>, Integer, Boolean> thresholdExitFunction;
	
	public Population(
		List<List<T>> individualsAlleles,
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
		this.fitnessFunction = fitnessFunction;
		this.selectionFunction = selectionFunction;
		this.crossoverFunction = crossoverFunction;
		this.crossoverPointFunction = crossoverPointFunction;
		this.mutableCheckFunction = mutableCheckFunction;
		this.mutationPointFunction = mutationPointFunction;
		this.mutateFunction = mutateFunction;
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
			phenotypeProcessing();
			Boolean thresholdExitClause = this.thresholdExitFunction.apply(this.fittest, this.currentGeneration);
			Boolean convergenceAchieved = this.convergenceFunction.apply(this.fittest);
			FunctionalUtil.logStep(this.logIntermediateSteps, "	Threshold Exit Clause >> " + thresholdExitClause);
			FunctionalUtil.logStep(this.logIntermediateSteps, "	Convergence Achieved >> " + convergenceAchieved);
			canContinue = !thresholdExitClause && !convergenceAchieved;
		} while(canContinue);
		FunctionalUtil.logStep(this.logIntermediateSteps, "Completed Genetic Process");
	}
	
	private class MateAndMutate implements Cloneable {
		private Individual<T> male;
		private Individual<T> female;
		private Individual<T> offspringFirst;
		private Individual<T> offspringSecond;
		
		private MateAndMutate(Individual<T> male, Individual<T> female) throws CloneNotSupportedException {
			this.male = male;
			this.female = female;
			mate();
			mutate();
		}
		
		private void mate() throws CloneNotSupportedException {
			FunctionalUtil.logStep(logIntermediateSteps, "			Mating Starts");
			this.offspringFirst = this.male.clone();
			this.offspringSecond = this.female.clone();
			Integer crossoverPoint = crossoverPointFunction.apply(this.male);
			FunctionalUtil.logStep(logIntermediateSteps, "				Crossover Point = " + crossoverPoint);
			for (int i = 0; i < crossoverPoint; i++) {
				Gene<T> tempGene = this.offspringFirst.getGenes().get(i);
				this.offspringFirst.getGenes().set(i, this.offspringSecond.getGenes().get(i));
				this.offspringSecond.getGenes().set(i, tempGene);
				this.offspringFirst.computeFitnessFactor();
				this.offspringSecond.computeFitnessFactor();
	        }
			FunctionalUtil.logStep(logIntermediateSteps, "				Offspring First >> " + debugIndividualFunction.apply(this.offspringFirst));
			FunctionalUtil.logStep(logIntermediateSteps, "				Offspring Second >> " + debugIndividualFunction.apply(this.offspringSecond));
			FunctionalUtil.logStep(logIntermediateSteps, "			Mating Ends");
		}
		
		private void mutate() {
			FunctionalUtil.logStep(logIntermediateSteps, "			Mutating Starts");
			FunctionalUtil.logStep(logIntermediateSteps, "				Offspring First");
			mutate(this.offspringFirst);
			FunctionalUtil.logStep(logIntermediateSteps, "				Offspring Second");
			mutate(this.offspringSecond);
			FunctionalUtil.logStep(logIntermediateSteps, "			Mutating Ends");
		}
		
		private Individual<T> bestOffspring() {
			if (this.offspringFirst.getFitnessFactor() > this.offspringSecond.getFitnessFactor()) {
				return this.offspringFirst;
			}
			return this.offspringSecond;
		}
		
		public void mutate(Individual<T> offspring) {
			Boolean isMutable = mutableCheckFunction.apply(offspring);
			FunctionalUtil.logStep(logIntermediateSteps, "					Is Offspring Mutable >> " + isMutable);
			if (isMutable) {
				Integer mutationPoint = mutationPointFunction.apply(offspring);
				FunctionalUtil.logStep(logIntermediateSteps, "					Mutation Point >> " + mutationPoint);
				Gene<T> gene = offspring.getGenes().get(mutationPoint);
				gene.setAlleles(mutateFunction.apply(gene));
				gene.setFitnessFactor(fitnessFunction.apply(gene));
				offspring.computeFitnessFactor();
				FunctionalUtil.logStep(logIntermediateSteps, "					New Mutated Offspring >> " + debugIndividualFunction.apply(offspring));
			}
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public MateAndMutate clone() throws CloneNotSupportedException {
			return (MateAndMutate)super.clone();
		}
	}
	
	private void phenotypeProcessing() throws CloneNotSupportedException {
		this.currentGeneration++;
		FunctionalUtil.logStep(this.logIntermediateSteps, "	Processing Generation >> " + this.currentGeneration);
		// Selection
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Parent Selection Starts");
		SelectedIndividualPool<T> selectedIndividualPool = selectionFunction.select(this.individuals);
		Individual<T> maleParent = selectedIndividualPool.getBestCandidate();
		Integer maleIndex = selectedIndividualPool.getBestCandidateIndex();
		Individual<T> femaleParent = selectedIndividualPool.getSecondBestCandidate();
		Integer femaleIndex = selectedIndividualPool.getSecondBestCandidateIndex();
		Individual<T> worstParent = selectedIndividualPool.getWorstCandidate();
		Integer worstIndex = selectedIndividualPool.getWorstCandidateIndex();
		Integer leastFitnessFactor = worstParent.getFitnessFactor();
		FunctionalUtil.logStep(this.logIntermediateSteps, "			Best Parent Individual >> " + this.debugIndividualFunction.apply(maleParent) + " @ Index = " + maleIndex);
		FunctionalUtil.logStep(this.logIntermediateSteps, "			Second Best Parent Individual >> " + this.debugIndividualFunction.apply(femaleParent) + " @ Index = " + femaleIndex);
		FunctionalUtil.logStep(this.logIntermediateSteps, "			Worst Parent Individual >> " + this.debugIndividualFunction.apply(worstParent) + " @ Index = " + worstIndex);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Parent Selection Ends");
		// Crossover
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Crossover Starts");
		YieldedOffspringPool<T> yieldedOffspringPool = this.crossoverFunction.crossover(selectedIndividualPool, this.crossoverPointFunction);
		FunctionalUtil.logStep(this.logIntermediateSteps, "			Offspring Primary" + this.debugIndividualFunction.apply(yieldedOffspringPool.getOffspringPrimary()));
		FunctionalUtil.logStep(this.logIntermediateSteps, "			Offspring Secondary" + this.debugIndividualFunction.apply(yieldedOffspringPool.getOffspringSecondary()));
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Crossover Ends");
		// Mutation
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Mutation Starts");
		for (Individual<T> offspring : yieldedOffspringPool.getYieldedOffspring()) {
			Boolean isMutable = this.mutableCheckFunction.apply(offspring);
			FunctionalUtil.logStep(this.logIntermediateSteps, "			Is Offspring Mutable >> " + isMutable);
			if (isMutable) {
				Integer mutationPoint = this.mutationPointFunction.apply(offspring);
				FunctionalUtil.logStep(this.logIntermediateSteps, "			Mutation Point >> " + mutationPoint);
				Gene<T> gene = offspring.getGenes().get(mutationPoint);
				gene.setAlleles(this.mutateFunction.apply(gene));
				gene.setFitnessFactor(this.fitnessFunction.apply(gene));
				offspring.computeFitnessFactor();
				FunctionalUtil.logStep(this.logIntermediateSteps, "			New Mutated Offspring >> " + debugIndividualFunction.apply(offspring));
			}
		}
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Mutation Ends");
		yieldedOffspringPool.computeBestOffspring();
		MateAndMutate mateAndMutate = new MateAndMutate(maleParent, femaleParent);
		// Replace parents with their Offsprings
		this.individuals.set(maleIndex, mateAndMutate.offspringFirst);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Replaced MaleParent >> " + this.debugIndividualFunction.apply(maleParent) + " @ Index = " + maleIndex + " with Offspring First >> " + this.debugIndividualFunction.apply(mateAndMutate.offspringFirst));
		if (mateAndMutate.offspringFirst.getFitnessFactor() < leastFitnessFactor) {
			worstParent = mateAndMutate.offspringFirst;
			leastFitnessFactor = mateAndMutate.offspringFirst.getFitnessFactor();
			worstIndex = maleIndex;
		}
		this.individuals.set(femaleIndex, mateAndMutate.offspringSecond);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Replaced FemaleParent >> " + this.debugIndividualFunction.apply(femaleParent) + " @ Index = " + femaleIndex + " with Offspring Second >> " + this.debugIndividualFunction.apply(mateAndMutate.offspringSecond));
		if (mateAndMutate.offspringSecond.getFitnessFactor() < leastFitnessFactor) {
			worstParent = mateAndMutate.offspringSecond;
			leastFitnessFactor = mateAndMutate.offspringSecond.getFitnessFactor();
			worstIndex = femaleIndex;
		}
		FunctionalUtil.logStep(this.logIntermediateSteps, "		New WorstParent Individual >> " + this.debugIndividualFunction.apply(worstParent) + " @ Index = " + worstIndex);
		// Replace least fitness Individual with Best offspring
		this.fittest = mateAndMutate.bestOffspring();
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Fittest Individual >> " + this.debugIndividualFunction.apply(this.fittest));
		this.individuals.set(worstIndex, this.fittest);
		FunctionalUtil.logStep(this.logIntermediateSteps, "		Replaced WorstParent >> " + this.debugIndividualFunction.apply(worstParent) + " @ Index = " + worstIndex + " with Fittest Individual >> " + this.debugIndividualFunction.apply(this.fittest));
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
