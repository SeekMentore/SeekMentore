package geneticAlgorithm.imporvised.v1;

import java.util.function.Function;

public class Gene<T> implements Cloneable {

	private T genome;
	private Function<T, Integer> fitnessFunction;
	
	public Gene(
		T genome,
		Function<T, Integer> fitnessFunction
	) {
		this.genome = genome;
		this.fitnessFunction = fitnessFunction;
	}

	public T getGenome() {
		return genome;
	}
	
	public void setGenome(T genome) {
		this.genome = genome;
	}

	public Function<T, Integer> getFitnessFunction() {
		return fitnessFunction;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Gene<T> clone() throws CloneNotSupportedException {
		return (Gene<T>)super.clone();
	}
}
