package geneticAlgorithm.imporvised.v1.functionalInterfaces;

import java.util.function.Function;

import geneticAlgorithm.imporvised.v1.model.Individual;
import geneticAlgorithm.imporvised.v1.model.SelectedIndividualPool;
import geneticAlgorithm.imporvised.v1.model.YieldedOffspringPool;

@FunctionalInterface
public interface CrossoverFunction<T> {

	YieldedOffspringPool<T> crossover(SelectedIndividualPool<T> selectedIndividualPool, Function<Individual<T>, Integer> crossoverPointFunction) throws CloneNotSupportedException;
}
