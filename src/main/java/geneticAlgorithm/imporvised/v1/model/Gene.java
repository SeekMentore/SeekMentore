package geneticAlgorithm.imporvised.v1.model;

public class Gene<T> implements Cloneable {

	private T alleles;
	private Integer fitnessFactor;
	
	public Gene(T alleles) {
		this.alleles = alleles;
	}

	public T getAlleles() {
		return alleles;
	}
	
	public void setAlleles(T alleles) {
		this.alleles = alleles;
	}
	
	public Integer getFitnessFactor() {
		return fitnessFactor;
	}
	
	public void setFitnessFactor(Integer fitnessFactor) {
		this.fitnessFactor = fitnessFactor;
	}
	
	public String toString() {
		return this.alleles.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Gene<T> clone() throws CloneNotSupportedException {
		return (Gene<T>)super.clone();
	}
}
