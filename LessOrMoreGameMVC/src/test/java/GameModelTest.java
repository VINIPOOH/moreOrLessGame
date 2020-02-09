import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameModelTest {

    static GameModel model;

    @BeforeClass
    public static void setUp() {
        model = new GameModel(0,100);
    }

    @Test
    public void isBiggerLessOrCorrectBiggerValue() {
        int valueWhichIsCorrectAnswer = model.setRememberedValue();
        while (valueWhichIsCorrectAnswer==model.getMAX_VALUE()){
            valueWhichIsCorrectAnswer = model.setRememberedValue();
        }
        Assert.assertEquals(WinStatus.BIGGER_ANSWER,
                model.isBiggerLessOrCorrect(valueWhichIsCorrectAnswer + 1));

    }

    @Test
    public void isBiggerLessOrCorrectLessValue() {
        int valueWhichIsCorrectAnswer = model.setRememberedValue();
        while (valueWhichIsCorrectAnswer==model.getMIN_VALUE()){
            valueWhichIsCorrectAnswer = model.setRememberedValue();
        }
        Assert.assertEquals(WinStatus.LESS_ANSWER,
                model.isBiggerLessOrCorrect(valueWhichIsCorrectAnswer - 1));

    }

    @Test
    public void isBiggerLessOrCorrectEquals() {
        int valueWhichIsCorrectAnswer = model.setRememberedValue();
        Assert.assertEquals(WinStatus.CORRECT_ANSWER,
                model.isBiggerLessOrCorrect(valueWhichIsCorrectAnswer));

    }

    @Test
    public void setRememberedValue() {
        int randomResult;
        for (int i = 10000; i > 0; i--) {
            model = new GameModel(0,100);
            randomResult=model.setRememberedValue();
            Assert.assertTrue(model.getRememberedValue() > model.getMIN_VALUE()
                    && model.getRememberedValue() < model.getMAX_VALUE());
        }
    }

    @Test
    public void amountMoves() {
        int userTryValue = 1;
        int amountMoves = 10;
        for (int i = amountMoves-1; i > 0; i--) {
            model.isBiggerLessOrCorrect(userTryValue);
        }
        Assert.assertEquals(amountMoves, model.getAmountTry());
    }
}