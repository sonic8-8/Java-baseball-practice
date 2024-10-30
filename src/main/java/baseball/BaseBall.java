package baseball;

import baseball.io.ConsoleInputHandler;
import baseball.io.ConsoleOutputHandler;

public class BaseBall {

    private static final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private static final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private static final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    public static final int CONTINUE = 0;
    public static final int WIN = 1;
    public static final int DEFAULT_DIGIT_COUNT = 3;

    private static int strikes = 0;
    private static int balls = 0;
    private static int gameStatus = 0;

    public void run() {

        consoleOutputHandler.showStartGameComment();

        while (true) {

//            int[] randomDigits = randomNumberGenerator.getRandomDigits(DEFAULT_DIGIT_COUNT);

            while (true) {

                int[] randomDigits = randomNumberGenerator.getRandomDigits(DEFAULT_DIGIT_COUNT);

                if (gameStatus == WIN) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }

                int[] digitsFromUserInput = getDigitsFromUserInput();
                actOnDigits(randomDigits, digitsFromUserInput);
            }

            int userAction = getUserActionFromUser();
            if (doesUserChooseToContinue(userAction)) {
                gameStatus = CONTINUE;
            }

            if (doesUserChooseToEnd(userAction)) {
                break;
            }

        }
    }

    private void actOnDigits(int[] randomDigits, int[] digitsFromUserInput) {
        countStrikeAndBall(randomDigits, digitsFromUserInput);
        consoleOutputHandler.showStrikeAndBallCounts(strikes, balls);

        if (isWinningStrike()) {
            gameStatus = WIN;
        }

        resetStrikeAndBall();
    }

    private int[] getDigitsFromUserInput() {
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

    private boolean doesUserWinTheGame() {
        return gameStatus == WIN;
    }

    private void resetStrikeAndBall() {
        strikes = 0;
        balls = 0;
    }

    private boolean hasDuplicates(int[] digitsFromUserInputNumber) {
        return digitsFromUserInputNumber[0] == digitsFromUserInputNumber[1] || digitsFromUserInputNumber[0] == digitsFromUserInputNumber[2] || digitsFromUserInputNumber[1] == digitsFromUserInputNumber[2];
    }

    private boolean isValidRange(int number) {
        return number < 100 || number > 999;
    }

    private int getUserActionFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private int getNumberFromUserInput() {
        consoleOutputHandler.printCommentForNumber();
        return consoleInputHandler.getUserInput();
    }

    private boolean doesUserChooseToContinue(int select) {
        return select == 1;
    }

    private boolean doesUserChooseToEnd(int select) {
        return select == 2;
    }

    private boolean isWinningStrike() {
        return strikes == DEFAULT_DIGIT_COUNT;
    }

    private void countStrikeAndBall(int[] randomDigits, int[] digitsFromUserInput) {
        if (digitsFromUserInput[0] == randomDigits[0]) {
            strikes++;
        } else if (digitsFromUserInput[0] == randomDigits[1] || digitsFromUserInput[0] == randomDigits[2]) {
            balls++;
        }

        if (digitsFromUserInput[1] == randomDigits[1]) {
            strikes++;
        } else if (digitsFromUserInput[1] == randomDigits[0] || digitsFromUserInput[1] == randomDigits[2]) {
            balls++;
        }

        if (digitsFromUserInput[2] == randomDigits[2]) {
            strikes++;
        } else if (digitsFromUserInput[2] == randomDigits[0] || digitsFromUserInput[2] == randomDigits[1]) {
            balls++;
        }
    }

    private int[] splitDigitsFrom(int number) {
        int[] digits = new int[DEFAULT_DIGIT_COUNT];
        digits[0] = number / 100;
        digits[1] = (number - digits[0] * 100) / 10;
        digits[2] = number % 10;
        return digits;

    }

}
