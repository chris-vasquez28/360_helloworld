package userNameRecognizerTestBed;

import java.util.Scanner;

public class userNameRecognizerTestB {
    
    static String inputLine;

    public static void main(String[] args) {
        System.out.println("Welcome to the UserName Recognizer Testbed\n");
        System.out.println("Please enter a UserName or an empty line to stop.");

        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNextLine()) {
            inputLine = keyboard.nextLine();
            if (inputLine.length() == 0) {
                System.out.println("\n*** Empty input line detected, the loop stops.");
                keyboard.close();
                System.exit(0);
            }

            String errMessage = UserNameRecognizer.checkForValidUserName(inputLine);
            if (!errMessage.isEmpty()) {
                System.out.println(errMessage);
                if (UserNameRecognizer.userNameRecognizerIndexofError <= -1) return;
                System.out.println(inputLine);
                System.out.println(inputLine.substring(0, UserNameRecognizer.userNameRecognizerIndexofError) + "\u21EB");
            } else {
                System.out.println("Success! The UserName is valid.");
            }

            System.out.println("\nPlease enter UserName or an empty line to stop.");
        }
    }
}
