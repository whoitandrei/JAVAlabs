package model;
import model.interfaces.IGameProcess;

import java.util.*;

public class GameProcess implements IGameProcess {
    public GameProcess() {}

    @Override
    public void playGame(){
        Scanner in = new Scanner(System.in);
        final int[] NUMBER = createNumber();

        int counter = 0;
        while (true){
            ++counter;

            System.out.printf("input %d: ", counter);
            int guess = in.nextInt();
            if (guess == 999999){
                break;
            }

            final int[] cmpResult = cmpGuess(getDigits(guess), NUMBER);
            if (cmpResult[0] == 4){
                System.out.printf("YOU WIN! ANSWER IS: %d\nattempts used:%d\n\ntype any message + Enter to exit...",guess, counter);
                String choice = in.nextLine();
                String choice1 = in.nextLine();
                break;
            }

            System.out.printf("%d) BULLS: %d; COWS: %d\n\n", counter, cmpResult[0], cmpResult[1]);
        }
    }

    private int[] cmpGuess(int[] guess, int[] NUMBER){
        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < 4; i++) {
            if (guess[i] == NUMBER[i]) {
                bulls++;
            } else {
                for (int j = 0; j < 4; j++) {
                    if (guess[i] == NUMBER[j] && i != j) {
                        cows++;
                        break;
                    }
                }
            }
        }

        return new int[]{bulls, cows};
    }

    private int[] createNumber(){
        Random random = new Random();
        Set<Integer> usedDigits = new HashSet<>();
        int[] digits = new int[4];

        digits[0] = random.nextInt(9) + 1;
        usedDigits.add(digits[0]);

        for (int i = 1; i < 4; i++) {
            int digit;
            do {
                digit = random.nextInt(10);
            } while (usedDigits.contains(digit));
            digits[i] = digit;
            usedDigits.add(digit);
        }

        return digits;
    }

    private int[] getDigits(int number) {
        int[] digits = new int[4];
        digits[0] = number / 1000;
        digits[1] = (number / 100) % 10;
        digits[2] = (number / 10) % 10;
        digits[3] = number % 10;
        return digits;
    }
}
