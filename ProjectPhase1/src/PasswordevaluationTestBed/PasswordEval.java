package PasswordevaluationTestBed;

public class PasswordEval {

	public static String passwordErrorMessage = "";
	public static String passwordInput = "";
	public static int passwordIndexofError = -1;
	public static boolean foundUpperCase = false;
	public static boolean foundLowerCase = false;
	public static boolean foundNumericDigit = false;
	public static boolean foundSpecialChar = false;
	public static boolean foundLongEnough = false;
	private static String inputLine = "";
	private static char currentChar;
	private static int currentCharNdx;
	private static boolean running;

	private static void displayInputState() {
		System.out.println(inputLine);
		System.out.println(inputLine.substring(0, currentCharNdx) + "?");
		System.out.println("The password size: " + inputLine.length() + "  |  The currentCharNdx: " + 
				currentCharNdx + "  |  The currentChar: \"" + currentChar + "\"");
	}

	public static String evaluatePassword(String input) {
		passwordErrorMessage = "";
		passwordIndexofError = 0;
		inputLine = input;
		currentCharNdx = 0;

		if (input.length() <= 0) return "*** Error *** The password is empty!";

		currentChar = input.charAt(0);
		passwordInput = input;
		foundUpperCase = false;
		foundLowerCase = false;	
		foundNumericDigit = false;
		foundSpecialChar = false;
		foundLongEnough = false;
		running = true;

		while (running) {
			displayInputState();
			if (currentChar >= 'A' && currentChar <= 'Z') {
				foundUpperCase = true;
			} else if (currentChar >= 'a' && currentChar <= 'z') {
				foundLowerCase = true;
			} else if (currentChar >= '0' && currentChar <= '9') {
				foundNumericDigit = true;
			} else if ("~`!@#$%^&*()_-+={}[]|\\:;\"'<>,.?/".indexOf(currentChar) >= 0) {
				foundSpecialChar = true;
			} else {
				passwordIndexofError = currentCharNdx;
				return "*** Error *** An invalid character has been found!";
			}
			if (currentCharNdx >= 7) {
				foundLongEnough = true;
			}
			currentCharNdx++;
			if (currentCharNdx >= inputLine.length())
				running = false;
			else
				currentChar = input.charAt(currentCharNdx);
		}
		
		String errMessage = "";
		if (!foundUpperCase) errMessage += "Upper case; ";
		if (!foundLowerCase) errMessage += "Lower case; ";
		if (!foundNumericDigit) errMessage += "Numeric digits; ";
		if (!foundSpecialChar) errMessage += "Special character; ";
		if (!foundLongEnough) errMessage += "Long Enough; ";

		if (errMessage.isEmpty()) return "";
		
		passwordIndexofError = currentCharNdx;
		return errMessage + "conditions were not satisfied";
	}
}