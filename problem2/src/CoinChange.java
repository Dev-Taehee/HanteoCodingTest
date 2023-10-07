public class CoinChange {

    public long getAmount(int sum, int[] coins) {

        long[] coinCombinations = new long[sum + 1]; //인덱스가 1부터 시작

        coinCombinations[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = 1; j <= sum; j++) {
                if(coins[i] <= j) coinCombinations[j] += coinCombinations[j-coins[i]];
            }
        }

        return coinCombinations[sum];
    }


}
