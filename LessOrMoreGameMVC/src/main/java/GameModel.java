import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {


    public void setMIN_VALUE(int MIN_VALUE) {
        this.MIN_VALUE = MIN_VALUE;
    }

    public void setMAX_VALUE(int MAX_VALUE) {
        this.MAX_VALUE = MAX_VALUE;
    }

    private int MIN_VALUE;
    private int MAX_VALUE;
    private int rememberedValue;
    private int amountTry = 0;
    private ArrayList<Integer> historyMoves = new ArrayList<>();

    public GameModel(int MIN_VALUE, int MAX_VALUE) {
        this.MIN_VALUE = MIN_VALUE;
        this.MAX_VALUE = MAX_VALUE;
    }

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
        this.rememberedValue = ThreadLocalRandom.current().nextInt(MIN_VALUE+1, MAX_VALUE);
        return rememberedValue;
    }

    public WinStatus isBiggerLessOrCorrect(int value) {
        amountTry++;
        if (value == rememberedValue) {
            return WinStatus.CORRECT_ANSWER;//создать енам на ето
        } else if (rememberedValue < value) {
            return WinStatus.ANSWER_IS_BIGGER;
        } else {
            return WinStatus.ANSWER_IS_SMOLLER;
        }
    }
}
