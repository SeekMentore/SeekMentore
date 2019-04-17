package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Population {
	
	List<Chromosome> phenotypes;
	Chromosome fittest;
	Integer currentGeneration = 0;
	Predicate<List<GeneticMaterial>> terminate;
	BiFunction<List<GeneticMaterial>, Integer, Boolean> thresholdFunction;
	
	void initialize(
		List<List<GeneticMaterial>> individuals,
		Function<GeneticMaterial, Integer> fitnessFunction,
		Function<List<GeneticMaterial>, Integer> crossoverPointFunction,
		Predicate<List<GeneticMaterial>> mutable,
		Function<List<GeneticMaterial>, Integer> mutationPointFunction,
		Consumer<GeneticMaterial> mutate,
		Predicate<List<GeneticMaterial>> terminate,
		BiFunction<List<GeneticMaterial>, Integer, Boolean> thresholdFunction
	) {
		this.phenotypes = new LinkedList<Chromosome>();
		for (List<GeneticMaterial> individual : individuals) {
			this.phenotypes.add(new Chromosome(individual, crossoverPointFunction, mutable, mutationPointFunction, mutate));
		}
		this.terminate = terminate;
		this.thresholdFunction = thresholdFunction;
	}
	
	void startProcessing() {
		do {
			phenotypeProcessing();
		} while(!terminate.test(fittest.getGenomeList()) || threshold.apply(fittest.getGenomeList(), currentGeneration));
	}
	
	class MateAndMutate {
		Chromosome male;
		Chromosome female;
		Chromosome offspringFirst;
		Chromosome offspringSecond;
		
		public MateAndMutate(Chromosome male, Chromosome female) {
			this.male = male;
			this.female = female;
			mate();
			mutate();
		}
		
		void mate() {
			offspringFirst = male;
			offspringSecond = female;
			for (int i = 0; i < male.crossoverPoint.apply(male.getGenomeList()); i++) {
				Gene tempGene = offspringFirst.genotype.genes.get(i);
				offspringFirst.genotype.genes.set(i, offspringSecond.genotype.genes.get(i));
				offspringSecond.genotype.genes.set(i, tempGene);
				
				offspringFirst.computeFitnessFactor();
				offspringSecond.computeFitnessFactor();
	        }
		}
		
		void mutate() {
			offspringFirst.mutate();
			offspringSecond.mutate();
		}
		
		Chromosome bestOffspring() {
			if (offspringFirst.genotype.fitnessFactor > offspringSecond.genotype.fitnessFactor) {
				return offspringFirst;
			}
			return offspringSecond;
		}
	}
	
	void phenotypeProcessing() {
		currentGeneration++;
		// Selection
		Chromosome maleParent = null;
		Integer maleIndex = -1;
		Chromosome femaleParent = null;
		Integer femaleIndex = -1;
		Integer worstIndex = -1;
		Integer bestFitnessFactor = Integer.MIN_VALUE;
		Integer secondBestFitnessFactor = Integer.MIN_VALUE;
		Integer leastFitnessFactor = Integer.MAX_VALUE;
		for (int i = 0; i < phenotypes.size(); i++) {
			Chromosome phenotype = phenotypes.get(i);
			if (phenotype.genotype.fitnessFactor > bestFitnessFactor) {
				if (Validator.checkObjectAvailability(maleParent)) {
					femaleParent = maleParent;
					secondBestFitnessFactor = maleParent.genotype.fitnessFactor;
					femaleIndex = maleIndex;
				}
				maleParent = phenotype;
				bestFitnessFactor = phenotype.genotype.fitnessFactor;
				maleIndex = i;
			} else {
				if (phenotype.genotype.fitnessFactor > secondBestFitnessFactor) {
					femaleParent = phenotype;
					secondBestFitnessFactor = phenotype.genotype.fitnessFactor;
					femaleIndex = i;
				}
			}
			if (phenotype.genotype.fitnessFactor < leastFitnessFactor) {
				leastFitnessFactor = phenotype.genotype.fitnessFactor;
				worstIndex = i;
			}
		}
		// Crossover & Mutation
		MateAndMutate mateAndMutate = new MateAndMutate(maleParent, femaleParent);
		// Replace parents with their Offsprings
		phenotypes.set(maleIndex, mateAndMutate.offspringFirst);
		if (mateAndMutate.offspringFirst.genotype.fitnessFactor < leastFitnessFactor) {
			leastFitnessFactor = mateAndMutate.offspringFirst.genotype.fitnessFactor;
			worstIndex = maleIndex;
		}
		phenotypes.set(femaleIndex, mateAndMutate.offspringSecond);
		if (mateAndMutate.offspringSecond.genotype.fitnessFactor < leastFitnessFactor) {
			leastFitnessFactor = mateAndMutate.offspringSecond.genotype.fitnessFactor;
			worstIndex = femaleIndex;
		}
		// Replace least fitness Chromosome with Best offspring
		fittest = mateAndMutate.bestOffspring();
		phenotypes.set(worstIndex, fittest);
	}
}
