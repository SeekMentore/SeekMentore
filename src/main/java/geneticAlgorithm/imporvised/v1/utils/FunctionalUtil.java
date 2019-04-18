package geneticAlgorithm.imporvised.v1.utils;

public class FunctionalUtil {
	
	public static void logStep(Boolean logIntermediateSteps, String message) {
		if (Validator.checkObjectAvailability(logIntermediateSteps)) {
			if (logIntermediateSteps) {
				System.out.println(message);
			}
		}
	}

}
