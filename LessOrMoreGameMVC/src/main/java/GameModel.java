import java.util.ArrayList;
import java.util.Random;

public class GameModel {


    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = 100;
    private int rememberedValue;
    private int amountTry = 0;
    private ArrayList<Integer> historyMoves = new ArrayList<>();



    public int getAmountTry() {
        return amountTry;
    }

    public int getMIN_VALUE() {
        return MIN_VALUE;
    }

    public int getMAX_VALUE() {
        return MAX_VALUE;
    }

    public int getRememberedValue() {
        return rememberedValue;
    }

    public int setRememberedValue() {
        this.rememberedValue = new Random().nextInt(MAX_VALUE + 1);
        return rememberedValue;
    }

    public WinStatus isBiggerLessOrCorrect(int value) {
        amountTry++;
        if (value == rememberedValue) {
            return WinStatus.CORRECT_ANSWER;//создать енам на ето
        } else if (rememberedValue < value) {
            return WinStatus.BIGGER_ANSWER;
        } else {
            return WinStatus.LESS_ANSWER;
        }
    }
}
