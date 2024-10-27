package baseball;

import baseball.io.ConsoleInputHandler;
import baseball.io.ConsoleOutputHandler;

public class Application {

    private static final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private static final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private static final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    public static final int CONTINUE = 0;
    public static final int WIN = 1;
    public static final int DEFAULT_DIGIT_COUNT = 3;

    private static int strike = 0;
    private static int ball = 0;
    private static int gameStatus = 0;


    public static void main(String[] args) {

        consoleOutputHandler.showStartGameComment();
        int[] randomDigits = randomNumberGenerator.getRandomDigits(DEFAULT_DIGIT_COUNT);

        while (true) {

            int[] digitsFromUserInput = getDigitsFromUserInput();
            actOnDigits(randomDigits, digitsFromUserInput);

            if (doesUserWinTheGame()) {
                consoleOutputHandler.printGameWinningComment();

                int userAction = getUserActionFromUser();
                if (doesUserChooseToContinue(userAction)) {
                    gameStatus = CONTINUE;
                    break;
                }
            }
        }
    }

    private static void actOnDigits(int[] randomDigits, int[] digitsFromUserInput) {
        countStrikeAndBall(randomDigits, digitsFromUserInput);
        consoleOutputHandler.showStrikeAndBallCounts(strike, ball);

        if (isWinningStrike()) {
            gameStatus = WIN;
        }

        resetStrikeAndBall();
    }

    private static int[] getDigitsFromUserInput() {
        int userInputNumber = getNumberFromUserInput();

        if (isValidRange(userInputNumber)) {
            throw new IllegalArgumentException();
        }

        int[] digitsFromUserInputNumber = splitDigitsFrom(userInputNumber);

        if (hasDuplicates(digitsFromUserInputNumber)) {
            throw new IllegalArgumentException();
        }
        return digitsFromUserInputNumber;
    }

    private static boolean doesUserWinTheGame() {
        return gameStatus == WIN;
    }

    private static void resetStrikeAndBall() {
        strike = 0;
        ball = 0;
    }

    private static boolean hasDuplicates(int[] digitsFromUserInputNumber) {
        return digitsFromUserInputNumber[0] == digitsFromUserInputNumber[1] || digitsFromUserInputNumber[0] == digitsFromUserInputNumber[2] || digitsFromUserInputNumber[1] == digitsFromUserInputNumber[2];
    }

    private static boolean isValidRange(int number) {
        return number < 100 || number > 999;
    }

    private static int getUserActionFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private static int getNumberFromUserInput() {
        consoleOutputHandler.printCommentForNumber();
        return consoleInputHandler.getUserInput();
    }

    private static boolean doesUserChooseToContinue(int select) {
        return select == 1;
    }

    private static boolean isWinningStrike() {
        return strike == DEFAULT_DIGIT_COUNT;
    }

    private static void countStrikeAndBall(int[] randomDigits, int[] digitsFromUserInput) {
        if (digitsFromUserInput[0] == randomDigits[0]) {
            strike++;
        } else if (digitsFromUserInput[0] == randomDigits[1] || digitsFromUserInput[0] == randomDigits[2]) {
            ball++;
        }

        if (digitsFromUserInput[1] == randomDigits[1]) {
            strike++;
        } else if (digitsFromUserInput[1] == randomDigits[0] || digitsFromUserInput[1] == randomDigits[2]) {
            ball++;
        }

        if (digitsFromUserInput[2] == randomDigits[2]) {
            strike++;
        } else if (digitsFromUserInput[2] == randomDigits[0] || digitsFromUserInput[2] == randomDigits[1]) {
            ball++;
        }
    }

    private static int[] splitDigitsFrom(int number) {
        int[] digits = new int[DEFAULT_DIGIT_COUNT];
        digits[0] = number / 100;
        digits[1] = (number - digits[0] * 100) / 10;
        digits[2] = number % 10;
        return digits;
    }
}