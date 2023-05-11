import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private final int diceCount;
    private final int min;
    private final int max;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
        this.min = 1;
        this.max = 6;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int rollDice() {
        int diceNumber = 0;
        for(int i = 0; i < diceCount; i++) {
            diceNumber += ThreadLocalRandom.current()
                    .nextInt(min, max + 1);
        }

        return diceNumber;
    }
}
