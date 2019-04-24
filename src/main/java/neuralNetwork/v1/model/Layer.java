package neuralNetwork.v1.model;

import java.util.List;

import neuralNetwork.v1.functionalInterfaces.TrainingFunction;

public class Layer<T> {
	
	private Layer<T> inputNodeLayer;
	private List<Node<T>> selfNodeList;
	private TrainingResult<T> trainingResult;
	
	private TrainingFunction<T> trainingFunction;

	public Layer (
		Layer<T> inputNodeLayer,
		List<Node<T>> selfNodeList,
		TrainingFunction<T> trainingFunction
	) {
		this.inputNodeLayer = inputNodeLayer;
		this.selfNodeList = selfNodeList;
		this.trainingFunction = trainingFunction;
	}
	
	public void trainNodeList() {
		this.trainingResult = this.trainingFunction.train(this.inputNodeLayer, this.selfNodeList);
	}

	public Layer<T> getInputNodeLayer() {
		return inputNodeLayer;
	}

	public List<Node<T>> getSelfNodeList() {
		return selfNodeList;
	}

	public TrainingResult<T> getTrainingResult() {
		return trainingResult;
	}

	public TrainingFunction<T> getTrainingFunction() {
		return trainingFunction;
	}
}
