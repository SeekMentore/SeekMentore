package geneticAlgorithm.imporvised.v1.functionalInterfaces;

import java.util.List;

import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;
import geneticAlgorithm.imporvised.v1.model.YieldedOffspringPool;

@FunctionalInterface
public interface ReplacementFunction<T> {

	Individual<T> replaceIndividuals(List<Individual<T>> individuals, SelectedIndividualPool<T> selectedIndividualPool, YieldedOffspringPool<T> yieldedOffspringPool);
}
