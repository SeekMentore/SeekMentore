package geneticAlgorithm.imporvised.v1.utils;

public class FunctionalUtil {
	
	public static void logStep(Boolean logIntermediateSteps, String message) {
		if (Validator.checkObjectAvailability(logIntermediateSteps)) {
			if (logIntermediateSteps) {
				System.out.println(message);
			}
		}
	}
	
	public static String decimalToBinary(Integer number, Integer length) {
		String binary = "";
		int[] binaryNum = new int[1000]; 
        // counter for binary array 
        int i = 0; 
        while (number > 0) { 
            // storing remainder in binary array 
            binaryNum[i] = number % 2; 
            number = number / 2; 
            i++; 
        } 
        // storing binary array in reverse order 
        for (int j = i - 1; j >= 0; j--) {
        	binary += binaryNum[j];
        }
        if (Validator.checkNonNegativeNonZeroNumberAvailability(length)) {
        	if (binary.length() < length) {
        		int paddingZerosCount = length - binary.length();
        		for (int k = 0; k < paddingZerosCount; k++) {
        			binary = "0" + binary;
        		}
        	}
        }
        return binary;
	}
	
	public static void main(String args[]) {
		for (int i = 0;i< 64 ; i++)
		System.out.println(i + "	" + decimalToBinary(i, 6));
	}
	
	public static Boolean binaryToBoolean(Integer binary) {
		return (binary == 1);
	}
}
