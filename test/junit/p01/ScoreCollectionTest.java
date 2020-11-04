package junit.p01;


import org.junit.Assert;
import org.junit.Test;

public class ScoreCollectionTest {
    public ScoreCollectionTest() {
    }

    @Test
    public void arithmeticMeanOfFiveAndSevenResultsInSix() {
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> {
            return 5;
        });
        collection.add(() -> {
            return 7;
        });
        int actualResult = collection.arithmeticMean();
        Assert.assertEquals(6L, (long)actualResult);
    }

    @Test
    public void arithmeticMeanOfTenAndTwentyResultsInFifteen() {
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> {
            return 10;
        });
        collection.add(() -> {
            return 20;
        });
        int actualResult = collection.arithmeticMean();
        Assert.assertEquals(15L, (long)actualResult);
    }
}
