package geneticAlgorithm.imporvised.v1;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chromosome {
	
	Function<List<GeneticMaterial>, Integer> crossoverPointFunction;
	Predicate<List<GeneticMaterial>> mutable;
	Function<List<GeneticMaterial>, Integer> mutationPointFunction;
	Consumer<GeneticMaterial> mutate;
	Genotype genotype;	
	
	Chromosome(
		List<GeneticMaterial> genomeList,
		Function<List<GeneticMaterial>, Integer> crossoverPointFunction,
		Predicate<List<GeneticMaterial>> mutable,
		Function<List<GeneticMaterial>, Integer> mutationPointFunction,
		Consumer<GeneticMaterial> mutate
	) {
		this.genotype = new Genotype(genomeList);
		this.computeFitnessFactor();
		this.crossoverPointFunction = crossoverPointFunction;
		this.mutable = mutable;
		this.mutationPointFunction = mutationPointFunction;
		this.mutate = mutate;
	}
	
	class Genotype {
		List<Gene> genes;
		Integer fitnessFactor;
		
		Genotype(List<GeneticMaterial> genomeList) {
			this.genes = new LinkedList<Gene>();
			for (GeneticMaterial genome : genomeList) {
				this.genes.add(new Gene(genome));
			}
		}
	}
	
	void computeFitnessFactor() {
		this.genotype.fitnessFactor = Integer.MIN_VALUE;
		if (Validator.checkNonEmptyList(this.genotype.genes)) {
			this.genotype.fitnessFactor = 0;
			for (final Gene gene : this.genotype.genes) {
				this.genotype.fitnessFactor += gene.genome.clacFitnessFactor();
			}
		}
	}
	
	private Boolean isMutable() {
		return this.mutable.test(this.getGenomeList());
	}
	
	void mutate() {
		if (isMutable()) {
			Integer mutationPoint = this.mutationPointFunction.apply(this.getGenomeList());
			this.mutate.accept(this.genotype.genes.get(mutationPoint).genome);
		}
	}
	
	List<GeneticMaterial> getGenomeList() {
		List<GeneticMaterial> genomeList = new LinkedList<GeneticMaterial>();
		for (final Gene gene : this.genotype.genes) {
			genomeList.add(gene.genome);
		}
		return genomeList;
	}
}
