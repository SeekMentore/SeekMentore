package neuralNetwork.v1.functionalInterfaces;

import java.util.List;

import neuralNetwork.v1.model.Layer;
import neuralNetwork.v1.model.Node;
import neuralNetwork.v1.model.TrainingResult;

@FunctionalInterface
public interface TrainingFunction<T> {

	TrainingResult<T> train(Layer<T> inputNodeLayer, List<Node<T>> selfNodeList);
}
