package baseball;

import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int strikeNum = 0;
        int ballNum = 0;
        int gameStatus = 0; // 이기면 1로 변경, 기본 상태는 0
//        int exceptionStatus = 0; // 1이면 예외 발생한 것

        System.out.println("숫자 야구 게임을 시작합니다.");
        int random1 = random.nextInt(9) + 1;
        int random2 = random.nextInt(9) + 1;
        int random3 = random.nextInt(9) + 1;

        System.out.println(random1 + " " + random2 + " " + random3);

        while (gameStatus == 0) {

            System.out.print("숫자를 입력해주세요 : ");
            int number = scanner.nextInt();

            int number1 = number / 100;
            int number2 = (number - number1 * 100) / 10;
            int number3 = number % 10;

            if (number1 == number2 || number1 == number3 || number2 == number3) {
                throw new IllegalArgumentException();
            }

            if (number < 100 || number > 999) {
                throw new IllegalArgumentException();
            }

            if (number1 == random1) {
                strikeNum++;
            } else if (number1 == random2 || number1 == random3) {
                ballNum++;
            }

            if (number2 == random2) {
                strikeNum++;
            } else if (number2 == random1 || number2 == random3) {
                ballNum++;
            }

            if (number3 == random3) {
                strikeNum++;
            } else if (number3 == random1 || number3 == random2) {
                ballNum++;
            }

            if (strikeNum == 3) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                gameStatus = 1;
            } else if (strikeNum != 0) {
                System.out.println(strikeNum + "스트라이크");
            } else if (ballNum != 0) {
                System.out.println(ballNum + "볼");
            } else {
                System.out.println("낫싱");
            }

            strikeNum = 0;
            ballNum = 0;

            if (gameStatus == 1) {

                System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                int select = scanner.nextInt();

                if (select == 1) {
                    gameStatus = 0;
                }
            }
        }
    }
}