package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chromosome<T> implements Cloneable {
	
	private Function<List<T>, Integer> crossoverPointFunction;
	private Predicate<List<T>> mutable;
	private Function<List<T>, Integer> mutationPointFunction;
	private Function<T, T> mutate;
	private Genotype<T> genotype;	
	private Boolean logIntermediateSteps;
	private BiFunction<List<T>, Integer, String> debugIndividual;
	
	public Chromosome(
		List<T> genomeList,
		Function<T, Integer> fitnessFunction,
		Function<List<T>, Integer> crossoverPointFunction,
		Predicate<List<T>> mutable,
		Function<List<T>, Integer> mutationPointFunction,
		Function<T, T> mutate,
		Boolean logIntermediateSteps,
		BiFunction<List<T>, Integer, String> debugIndividual
	) {
		this.logIntermediateSteps = logIntermediateSteps;
		this.debugIndividual = debugIndividual;
		this.genotype = new Genotype<T>(genomeList, fitnessFunction);
		this.computeFitnessFactor();
		this.crossoverPointFunction = crossoverPointFunction;
		this.mutable = mutable;
		this.mutationPointFunction = mutationPointFunction;
		this.mutate = mutate;
	}
	
	public class Genotype<R> implements Cloneable {
		private List<Gene<R>> genes;
		private Integer fitnessFactor;
		
		private Genotype(
			List<R> genomeList,
			Function<R, Integer> fitnessFunction
		) {
			this.genes = new LinkedList<Gene<R>>();
			for (R genome : genomeList) {
				this.genes.add(new Gene<R>(genome, fitnessFunction));
			}
		}

		public List<Gene<R>> getGenes() {
			return genes;
		}

		public Integer getFitnessFactor() {
			return fitnessFactor;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Genotype<T> clone() throws CloneNotSupportedException {
			return (Genotype<T>)super.clone();
		}
	}
	
	public void computeFitnessFactor() {
		this.genotype.fitnessFactor = Integer.MIN_VALUE;
		if (Validator.checkNonEmptyList(this.genotype.genes)) {
			this.genotype.fitnessFactor = 0;
			for (final Gene<T> gene : this.genotype.genes) {
				this.genotype.fitnessFactor += gene.getFitnessFunction().apply(gene.getGenome());
			}
		}
	}
	
	private Boolean isMutable() {
		return this.mutable.test(this.getGenomeList());
	}
	
	public void mutate() {
		Boolean isMutable = isMutable();
		logStep("					Is Offspring Mutable >> " + isMutable);
		if (isMutable) {
			Integer mutationPoint = this.mutationPointFunction.apply(this.getGenomeList());
			logStep("					Mutation Point >> " + mutationPoint);
			this.genotype.genes.get(mutationPoint).setGenome(this.mutate.apply(this.genotype.genes.get(mutationPoint).getGenome()));
			this.computeFitnessFactor();
			logStep("					New Mutated Offspring >> " + this.debugIndividual.apply(this.getGenomeList(), this.getGenotype().getFitnessFactor()));
		}
	}
	
	public List<T> getGenomeList() {
		List<T> genomeList = new LinkedList<T>();
		for (final Gene<T> gene : this.genotype.genes) {
			genomeList.add(gene.getGenome());
		}
		return genomeList;
	}

	public Function<List<T>, Integer> getCrossoverPointFunction() {
		return crossoverPointFunction;
	}

	public Genotype<T> getGenotype() {
		return genotype;
	}
	
	private void logStep(String message) {
		if (this.logIntermediateSteps) {
			System.out.println(message);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Chromosome<T> clone() throws CloneNotSupportedException {
		return (Chromosome<T>)super.clone();
	}
}
