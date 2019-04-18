package geneticAlgorithm.imporvised.v1.functionalInterfaces;

import java.util.List;

import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;

@FunctionalInterface
public interface SelectionFunction<T> {
	
	SelectedIndividualPool<T> select(List<Individual<T>> individuals);
}
