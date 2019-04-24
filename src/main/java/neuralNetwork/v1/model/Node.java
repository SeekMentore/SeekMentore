package neuralNetwork.v1.model;

import java.util.List;

import neuralNetwork.v1.functionalInterfaces.ActivationFunction;
import neuralNetwork.v1.functionalInterfaces.NetStimulusFunction;

public class Node<T> {

	private List<Stimuli<T>> stimulusList;
	private Integer netStimulusFactor;
	private Boolean isActivated;
	
	private NetStimulusFunction<T> netStimulusFunction;
	private ActivationFunction<T> activationFunction;
	
	public Node (
		List<Stimuli<T>> stimulusList,
		NetStimulusFunction<T> netStimulusFunction,
		ActivationFunction<T> activationFunction
	) {
		this.stimulusList = stimulusList;
		this.netStimulusFunction = netStimulusFunction;
		this.netStimulusFactor = this.netStimulusFunction.calcNetStimulus(this.stimulusList);
		this.activationFunction = activationFunction;
		this.isActivated = false;
	}
	
	public Boolean getIsActivated() {
		return isActivated;
	}

	public void fire() {
		this.isActivated = this.activationFunction.activate(this.stimulusList, this.netStimulusFactor);
	}

	public List<Stimuli<T>> getStimulusList() {
		return stimulusList;
	}

	public Integer getNetStimulusFactor() {
		return netStimulusFactor;
	}

	public NetStimulusFunction<T> getNetStimulusFunction() {
		return netStimulusFunction;
	}

	public ActivationFunction<T> getActivationFunction() {
		return activationFunction;
	}
}
