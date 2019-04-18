package geneticAlgorithm.imporvised.v1.model;

import geneticAlgorithm.imporvised.v1.utils.Validator;

public class YieldedOffspringPool<T> implements Cloneable {
	
	private Integer totalOffspringYielded;
	private Individual<T>[] yieldedOffspring;
	private Individual<T> offspringPrimary;
	private Individual<T> offspringSecondary;
	private Individual<T> bestOffspring;
	
	public YieldedOffspringPool() {}
	
	public Integer getTotalOffspringYielded() {
		return totalOffspringYielded;
	}

	public void setTotalOffspringYielded(Integer totalOffspringYielded) {
		this.totalOffspringYielded = totalOffspringYielded;
	}

	public Individual<T>[] getYieldedOffspring() {
		return yieldedOffspring;
	}

	public void setYieldedOffspring(Individual<T>[] yieldedOffspring) {
		this.yieldedOffspring = yieldedOffspring;
	}

	public Individual<T> getOffspringPrimary() {
		return offspringPrimary;
	}

	public void setOffspringPrimary(Individual<T> offspringPrimary) {
		this.offspringPrimary = offspringPrimary;
	}

	public Individual<T> getOffspringSecondary() {
		return offspringSecondary;
	}

	public void setOffspringSecondary(Individual<T> offspringSecondary) {
		this.offspringSecondary = offspringSecondary;
	}
	
	public void computeBestOffspring() {
		this.bestOffspring = null;
		for (Individual<T> offspring : yieldedOffspring) {
			if (Validator.checkObjectAvailability(this.bestOffspring)) {
				if (offspring.getFitnessFactor() > this.bestOffspring.getFitnessFactor()) {
					this.bestOffspring = offspring;
				}
			} else {
				this.bestOffspring = offspring;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public YieldedOffspringPool<T> clone() throws CloneNotSupportedException {
		return (YieldedOffspringPool<T>)super.clone();
	}
}
