package userNameRecognizerTestBed;

public class UserNameRecognizer {
    public static String userNameRecognizerErrorMessage = "";
    public static String userNameRecognizerInput = "";
    public static int userNameRecognizerIndexofError = -1;
    private static int state = 0;
    private static int nextState = 0;
    private static boolean finalState = false;
    private static String inputLine = "";
    private static char currentChar;
    private static int currentCharNdx;
    private static boolean running;
    private static int userNameSize = 0;

    private static void displayDebuggingInfo() {
        if (currentCharNdx >= inputLine.length())
            System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state +
                    ((finalState) ? "       F   " : "           ") + "None");
        else
            System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state +
                    ((finalState) ? "       F   " : "           ") + "  " + currentChar + " " +
                    ((nextState > 99) ? "" : (nextState > 9) || (nextState == -1) ? "   " : "    ") +
                    nextState + "     " + userNameSize);
    }

    private static void moveToNextCharacter() {
        currentCharNdx++;
        if (currentCharNdx < inputLine.length())
            currentChar = inputLine.charAt(currentCharNdx);
        else {
            currentChar = ' ';
            running = false;
        }
    }

    public static String checkForValidUserName(String input) {
        if (input.length() <= 0) return "";
        state = 0;
        inputLine = input;
        currentCharNdx = 0;
        currentChar = input.charAt(0);

        userNameRecognizerInput = input;
        running = true;
        nextState = -1;
        System.out.println("\nCurrent Final Input  Next  Date\nState   State Char  State  Size");

        userNameSize = 0;

        while (running) {
            switch (state) {
                case 0:
                    if ((currentChar >= 'A' && currentChar <= 'Z') ||
                        (currentChar >= 'a' && currentChar <= 'z') ||
                        (currentChar >= '0' && currentChar <= '9')) {
                        nextState = 1;
                        userNameSize++;
                    } else
                        running = false;
                    break;

                case 1:
                    if ((currentChar >= 'A' && currentChar <= 'Z') ||
                        (currentChar >= 'a' && currentChar <= 'z') ||
                        (currentChar >= '0' && currentChar <= '9') ||
                        (currentChar == '.') ||
                        (currentChar == '-') ||
                        (currentChar == '_')) {
                        nextState = 1;
                        userNameSize++;
                    } else if (currentChar == '.') {
                        nextState = 2;
                        userNameSize++;
                    } else
                        running = false;

                    if (userNameSize > 16)
                        running = false;
                    break;

                case 2:
                    if ((currentChar >= 'A' && currentChar <= 'Z') ||
                        (currentChar >= 'a' && currentChar <= 'z') ||
                        (currentChar >= '0' && currentChar <= '9')) {
                        nextState = 1;
                        userNameSize++;
                        if (userNameSize > 16)
                            running = false;
                    } else
                        running = false;
                    break;
            }

            if (running) {
                displayDebuggingInfo();
                moveToNextCharacter();
                state = nextState;
                if (state == 1) finalState = true;
                nextState = -1;
            }
        }
        displayDebuggingInfo();
        System.out.println("The loop has ended.");
        userNameRecognizerIndexofError = currentCharNdx;

        userNameRecognizerErrorMessage = "\n*** ERROR *** ";
        System.out.print(" state=" + state + "; ");
        switch (state) {
            case 0:
                userNameRecognizerIndexofError = currentCharNdx;
                userNameRecognizerErrorMessage += "A UserName must start with A-Z, a-z, or 0-9.\n";
                return userNameRecognizerErrorMessage;

            case 1:
                if (userNameSize < 4) {
                    userNameRecognizerIndexofError = currentCharNdx;
                    userNameRecognizerErrorMessage += "A UserName must have at least 4 characters.\n";
                    return userNameRecognizerErrorMessage;
                } else if (userNameSize > 16) {
                    userNameRecognizerIndexofError = currentCharNdx;
                    userNameRecognizerErrorMessage += "A UserName must have no more than 16 characters.\n";
                    return userNameRecognizerErrorMessage;
                } else {
                    userNameRecognizerIndexofError = -1;
                    userNameRecognizerErrorMessage = "";
                    return userNameRecognizerErrorMessage;
                }

            case 2:
                userNameRecognizerIndexofError = currentCharNdx;
                userNameRecognizerErrorMessage += "A UserName character after a period must be A-Z, a-z, 0-9.\n";
                return userNameRecognizerErrorMessage;

            default:
                return "";
        }
    }
}
