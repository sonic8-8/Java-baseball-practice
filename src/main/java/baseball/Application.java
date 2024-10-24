package baseball;

import baseball.io.ConsoleInputHandler;
import baseball.io.ConsoleOutputHandler;

import java.util.Random;
import java.util.Scanner;

public class Application {

    private static final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private static final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

    private static int strike = 0;
    private static int ball = 0;
    private static int gameStatus = 0;


    public static void main(String[] args) {

        consoleOutputHandler.showStartGameComment();

        Random random = new Random();
        int random1 = getRandomOneDigitNumber(random);
        int random2 = random.nextInt(9) + 1;
        int random3 = random.nextInt(9) + 1;

        while (true) {

            int threeDigitNumber = getThreeDigitNumberFromUser();

            if (threeDigitNumber < 100 || threeDigitNumber > 999) {
                throw new IllegalArgumentException();
            }

            int number1 = threeDigitNumber / 100;
            int number2 = (threeDigitNumber - number1 * 100) / 10;
            int number3 = threeDigitNumber % 10;

            if (number1 == number2 || number1 == number3 || number2 == number3) {
                throw new IllegalArgumentException();
            }

            countStrikeAndBall(number1, random1, random2, random3, number2, number3);

            consoleOutputHandler.showGameHint(strike, ball);

            if (isWinningStrike()) {
                consoleOutputHandler.printGameWinningComment();
                gameStatus = 1;
                break;
            }

            strike = 0;
            ball = 0;

            if (gameStatus == 1) {
                int userAction = getUserActionFromUser();
                
                if (doesUserChooseToContinue(userAction)) {
                    gameStatus = 0;
                    break;
                }
            }
        }
    }

    private static int getRandomOneDigitNumber(Random random) {
        int random1 = random.nextInt(9) + 1;
        return random1;
    }

    private static int getUserActionFromUser() {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private static int getThreeDigitNumberFromUser() {
        consoleOutputHandler.printCommentForThreeDigitNumber();
        return consoleInputHandler.getUserInput();
    }

    private static boolean doesUserChooseToContinue(int select) {
        return select == 1;
    }

    private static boolean isWinningStrike() {
        return strike == 3;
    }

    private static void countStrikeAndBall(int number1, int random1, int random2, int random3, int number2, int number3) {
        if (number1 == random1) {
            strike++;
        } else if (number1 == random2 || number1 == random3) {
            ball++;
        }

        if (number2 == random2) {
            strike++;
        } else if (number2 == random1 || number2 == random3) {
            ball++;
        }

        if (number3 == random3) {
            strike++;
        } else if (number3 == random1 || number3 == random2) {
            ball++;
        }
    }
}