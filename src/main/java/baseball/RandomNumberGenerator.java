package baseball;

import java.util.Random;

public class RandomNumberGenerator {

    public static final Random RANDOM = new Random();

    public int[] getRandomDigits(int count) {
        int[] digits = new int[count];
        for (int i = 0; i < count; i++) {
            RANDOM.nextInt(10);
            digits[i] = RANDOM.nextInt(10);
        }
        return digits;
    }

}
