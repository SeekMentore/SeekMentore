package geneticAlgorithm.imporvised.v1;

import java.util.function.Function;

public class Gene {

	GeneticMaterial genome;
	Function<GeneticMaterial, Integer> fitnessFunction;
	
	Gene(
		GeneticMaterial genome,
		Function<GeneticMaterial, Integer> fitnessFunction
	) {
		this.genome = genome;
		this.fitnessFunction = fitnessFunction;
	}
}
