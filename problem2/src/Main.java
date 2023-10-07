public class Main {
    public static void main(String[] args) {
        int sum = 10;
        int[] coins = new int[]{2, 5, 3, 6};
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.getAmount(sum, coins));
    }
}