import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinChangeTest {

    CoinChange coinChange = new CoinChange();

    @Test
    @DisplayName("CoinChange getAmount 첫 번째 테스트")
    void getAmountTest1() {
        int sum = 4;
        int[] coins = new int[]{1, 2, 3};

        Assert.assertEquals(4, coinChange.getAmount(sum, coins));
    }

    @Test
    @DisplayName("CoinChange getAmount 두 번째 테스트")
    void getAmountTest2() {
        int sum = 10;
        int[] coins = new int[]{2, 5, 3, 6};

        Assert.assertEquals(5, coinChange.getAmount(sum, coins));
    }

}