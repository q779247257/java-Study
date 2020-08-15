package 算法.初级算法;

/**
 * 力扣算法题：
 * @url： https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class 买卖股票的最佳时机2 {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        //总利润
        int totalPrice = 0;

        for (int i=0 ; i<prices.length ; i++){
            if ( (i+1) == prices.length) return totalPrice;

            //当天的利润
            int price = prices[i+1] - prices[i];

            //当天利润大于0进行积累
            if (price > 0) totalPrice += price;
        }
        return totalPrice;
    }
}
