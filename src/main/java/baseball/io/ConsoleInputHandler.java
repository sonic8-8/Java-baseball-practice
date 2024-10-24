package baseball.io;

import java.util.Scanner;

public class ConsoleInputHandler {

    public static final Scanner SCANNER = new Scanner(System.in);

    public int getUserInput() {
        return SCANNER.nextInt();
    }

}
