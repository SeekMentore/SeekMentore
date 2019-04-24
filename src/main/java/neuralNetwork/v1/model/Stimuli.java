package neuralNetwork.v1.model;

public class Stimuli<T> {

	private T type;
	private Integer weightCoefficient;
	
	public Stimuli(T type, Integer weightCoefficient) {
		this.type = type;
		this.weightCoefficient = weightCoefficient;
	}

	public T getType() {
		return type;
	}

	public Integer getWeightCoefficient() {
		return weightCoefficient;
	}
}
