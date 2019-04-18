package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Population<T> implements Cloneable {
	
	private List<Chromosome<T>> phenotypes;
	private Chromosome<T> fittest;
	private Integer currentGeneration = 0;
	private BiFunction<List<T>, Integer, Boolean> convergenceFunction;
	private BiFunction<List<T>, Integer, Boolean> thresholdExitFunction;
	private Boolean logIntermediateSteps;
	private BiFunction<List<T>, Integer, String> debugIndividual;
	
	public Population(
		List<List<T>> individuals,
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
		this.logIntermediateSteps = logIntermediateSteps;
		this.debugIndividual = debugIndividual;
		this.phenotypes = new LinkedList<Chromosome<T>>();
		logStep("Intializing Population");
		int counter = 0;
		for (List<T> individual : individuals) {
			Chromosome<T> phenotype = new Chromosome<T>(individual, fitnessFunction, crossoverPointFunction, mutable, mutationPointFunction, mutate, logIntermediateSteps, debugIndividual);
			logStep("	Index = " + counter + " Individual = " + this.debugIndividual.apply(phenotype.getGenomeList(), phenotype.getGenotype().getFitnessFactor()));
			counter++;
			this.phenotypes.add(phenotype);
		}
		this.convergenceFunction = convergenceFunction;
		this.thresholdExitFunction = thresholdExitFunction;
	}
	
	public void startProcessing() throws CloneNotSupportedException {
		logStep("Starting Genetic Process");
		Boolean canContinue = true;
		do {
			phenotypeProcessing();
			Boolean thresholdExitClause = thresholdExitFunction.apply(fittest.getGenomeList(), currentGeneration);
			Boolean convergenceAchieved = convergenceFunction.apply(fittest.getGenomeList(), fittest.getGenotype().getFitnessFactor());
			logStep("	Threshold Exit Clause >> " + thresholdExitClause);
			logStep("	Convergence Achieved >> " + convergenceAchieved);
			canContinue = !thresholdExitClause && !convergenceAchieved;
		} while(canContinue);
		logStep("Completed Genetic Process");
	}
	
	private class MateAndMutate {
		private Chromosome<T> male;
		private Chromosome<T> female;
		private Chromosome<T> offspringFirst;
		private Chromosome<T> offspringSecond;
		
		private MateAndMutate(Chromosome<T> male, Chromosome<T> female) throws CloneNotSupportedException {
			this.male = male;
			this.female = female;
			mate();
			mutate();
		}
		
		private void mate() throws CloneNotSupportedException {
			logStep("			Mating Starts");
			this.offspringFirst = this.male.clone();
			this.offspringSecond = this.female.clone();
			Integer crossoverPoint = this.male.getCrossoverPointFunction().apply(this.male.getGenomeList());
			logStep("				Crossover Point = " + crossoverPoint);
			for (int i = 0; i < crossoverPoint; i++) {
				Gene<T> tempGene = this.offspringFirst.getGenotype().getGenes().get(i);
				this.offspringFirst.getGenotype().getGenes().set(i, this.offspringSecond.getGenotype().getGenes().get(i));
				this.offspringSecond.getGenotype().getGenes().set(i, tempGene);
				this.offspringFirst.computeFitnessFactor();
				this.offspringSecond.computeFitnessFactor();
	        }
			logStep("				Offspring First >> " + debugIndividual.apply(this.offspringFirst.getGenomeList(), this.offspringFirst.getGenotype().getFitnessFactor()));
			logStep("				Offspring Second >> " + debugIndividual.apply(this.offspringSecond.getGenomeList(), this.offspringSecond.getGenotype().getFitnessFactor()));
			logStep("			Mating Ends");
		}
		
		private void mutate() {
			logStep("			Mutating Starts");
			logStep("				Offspring First");
			this.offspringFirst.mutate();
			logStep("				Offspring Second");
			this.offspringSecond.mutate();
			logStep("			Mutating Ends");
		}
		
		private Chromosome<T> bestOffspring() {
			if (this.offspringFirst.getGenotype().getFitnessFactor() > this.offspringSecond.getGenotype().getFitnessFactor()) {
				return this.offspringFirst;
			}
			return this.offspringSecond;
		}
	}
	
	private void phenotypeProcessing() throws CloneNotSupportedException {
		this.currentGeneration++;
		logStep("	Processing Generation >> " + this.currentGeneration);
		// Selection
		Chromosome<T> maleParent = null;
		Integer maleIndex = -1;
		Chromosome<T> femaleParent = null;
		Integer femaleIndex = -1;
		Chromosome<T> worstParent = null;
		Integer worstIndex = -1;
		Integer bestFitnessFactor = Integer.MIN_VALUE;
		Integer secondBestFitnessFactor = Integer.MIN_VALUE;
		Integer leastFitnessFactor = Integer.MAX_VALUE;
		for (int i = 0; i < this.phenotypes.size(); i++) {
			Chromosome<T> phenotype = this.phenotypes.get(i);
			if (phenotype.getGenotype().getFitnessFactor() > bestFitnessFactor) {
				if (Validator.checkObjectAvailability(maleParent)) {
					femaleParent = maleParent;
					secondBestFitnessFactor = maleParent.getGenotype().getFitnessFactor();
					femaleIndex = maleIndex;
				}
				maleParent = phenotype;
				bestFitnessFactor = phenotype.getGenotype().getFitnessFactor();
				maleIndex = i;
			} else {
				if (phenotype.getGenotype().getFitnessFactor() > secondBestFitnessFactor) {
					femaleParent = phenotype;
					secondBestFitnessFactor = phenotype.getGenotype().getFitnessFactor();
					femaleIndex = i;
				}
			}
			if (phenotype.getGenotype().getFitnessFactor() < leastFitnessFactor) {
				worstParent = phenotype;
				leastFitnessFactor = phenotype.getGenotype().getFitnessFactor();
				worstIndex = i;
			}
		}
		logStep("		MaleParent Individual >> " + this.debugIndividual.apply(maleParent.getGenomeList(), maleParent.getGenotype().getFitnessFactor()) + " @ Index = " + maleIndex);
		logStep("		FemaleParent Individual >> " + this.debugIndividual.apply(femaleParent.getGenomeList(), femaleParent.getGenotype().getFitnessFactor()) + " @ Index = " + femaleIndex);
		logStep("		WorstParent Individual >> " + this.debugIndividual.apply(worstParent.getGenomeList(), worstParent.getGenotype().getFitnessFactor()) + " @ Index = " + worstIndex);
		logStep("		Mating & Mutating (Male and Female)");
		// Crossover & Mutation
		MateAndMutate mateAndMutate = new MateAndMutate(maleParent, femaleParent);
		// Replace parents with their Offsprings
		this.phenotypes.set(maleIndex, mateAndMutate.offspringFirst);
		logStep("		Replaced MaleParent >> " + this.debugIndividual.apply(maleParent.getGenomeList(), maleParent.getGenotype().getFitnessFactor()) + " @ Index = " + maleIndex + " with Offspring First >> " + this.debugIndividual.apply(mateAndMutate.offspringFirst.getGenomeList(), mateAndMutate.offspringFirst.getGenotype().getFitnessFactor()));
		if (mateAndMutate.offspringFirst.getGenotype().getFitnessFactor() < leastFitnessFactor) {
			worstParent = mateAndMutate.offspringFirst;
			leastFitnessFactor = mateAndMutate.offspringFirst.getGenotype().getFitnessFactor();
			worstIndex = maleIndex;
		}
		this.phenotypes.set(femaleIndex, mateAndMutate.offspringSecond);
		logStep("		Replaced FemaleParent >> " + this.debugIndividual.apply(femaleParent.getGenomeList(), femaleParent.getGenotype().getFitnessFactor()) + " @ Index = " + femaleIndex + " with Offspring Second >> " + this.debugIndividual.apply(mateAndMutate.offspringSecond.getGenomeList(), mateAndMutate.offspringSecond.getGenotype().getFitnessFactor()));
		if (mateAndMutate.offspringSecond.getGenotype().getFitnessFactor() < leastFitnessFactor) {
			worstParent = mateAndMutate.offspringSecond;
			leastFitnessFactor = mateAndMutate.offspringSecond.getGenotype().getFitnessFactor();
			worstIndex = femaleIndex;
		}
		logStep("		New WorstParent Individual >> " + this.debugIndividual.apply(worstParent.getGenomeList(), worstParent.getGenotype().getFitnessFactor()) + " @ Index = " + worstIndex);
		// Replace least fitness Chromosome with Best offspring
		this.fittest = mateAndMutate.bestOffspring();
		logStep("		Fittest Individual >> " + this.debugIndividual.apply(this.fittest.getGenomeList(), this.fittest.getGenotype().getFitnessFactor()));
		this.phenotypes.set(worstIndex, this.fittest);
		logStep("		Replaced WorstParent >> " + this.debugIndividual.apply(worstParent.getGenomeList(), worstParent.getGenotype().getFitnessFactor()) + " @ Index = " + worstIndex + " with Fittest Individual >> " + this.debugIndividual.apply(this.fittest.getGenomeList(), this.fittest.getGenotype().getFitnessFactor()));
		logStep("	Completed Generation >> " + this.currentGeneration);
	}
	
	public Chromosome<T> getFittest() {
		return fittest;
	}

	public Integer getCurrentGeneration() {
		return currentGeneration;
	}
	
	private void logStep(String message) {
		if (this.logIntermediateSteps) {
			System.out.println(message);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Population<T> clone() throws CloneNotSupportedException {
		return (Population<T>)super.clone();
	}
}
