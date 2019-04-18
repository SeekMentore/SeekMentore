package geneticAlgorithm.imporvised.v1.model;

import java.util.List;

import geneticAlgorithm.imporvised.v1.utils.Validator;

public class Individual<T> implements Cloneable {

	private List<Gene<T>> genes;
	private Integer fitnessFactor;
	
	public Individual(List<Gene<T>> genes) {
		this.genes = genes;
		this.computeFitnessFactor();
	}
	
	public void computeFitnessFactor() {
		this.fitnessFactor = Integer.MIN_VALUE;
		if (Validator.checkNonEmptyList(this.genes)) {
			this.fitnessFactor = 0;
			for (final Gene<T> gene : this.genes) {
				this.fitnessFactor += gene.getFitnessFactor();
			}
		}
	}

	public List<Gene<T>> getGenes() {
		return genes;
	}

	public Integer getFitnessFactor() {
		return fitnessFactor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Individual<T> clone() throws CloneNotSupportedException {
		return (Individual<T>)super.clone();
	}
}
