package baseball;

import java.util.Random;

public class RandomNumberGenerator {

    public static final Random RANDOM = new Random();

    public int[] getRandomDigits(int count) {
        int[] digits = new int[count];
        for (int i = 0; i < count; i++) {
            RANDOM.nextInt(10);
            digits[i] = RANDOM.nextInt(9) + 1;

            if (i > 0 && digits[i] == digits[i-1]) {
                i--;
            }

            if (i > 1 && digits[i] == digits[i-2]) {
                i--;
            }
        }
        return digits;
    }

}
