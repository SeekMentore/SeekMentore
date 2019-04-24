package neuralNetwork.v1.functionalInterfaces;

import java.util.List;

import neuralNetwork.v1.model.Stimuli;

@FunctionalInterface
public interface ActivationFunction<T> {

	Boolean activate(List<Stimuli<T>> stimulusList, Integer netStimulusFactor);
}
