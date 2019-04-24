package neuralNetwork.v1.functionalInterfaces;

import java.util.List;

import neuralNetwork.v1.model.Stimuli;

@FunctionalInterface
public interface NetStimulusFunction<T> {

	Integer calcNetStimulus(List<Stimuli<T>> stimulusList);
}
