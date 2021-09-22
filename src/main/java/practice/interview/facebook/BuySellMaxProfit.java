package practice.interview.facebook;

public class BuySellMaxProfit {
    public static void main(String[] args) {
        int[] pre = new int[]{8, 5, 12, 9, 19, 1};
        int[] now = new int[]{21, 12, 11, 9, 6, 3};

        int max_profit = -1;
        int max_profit_index = -1;
        int max_loss= 100000;
        int max_loss_index = -1;

        for(int i = 0;i < pre.length; i++) {
            int diff = (now[i] - pre[i]);

            if(diff > 0) {
                if(diff > max_profit) {
                    max_profit = diff;
                    max_profit_index = i;
                }
            } else {
                if(diff < max_loss) {
                    max_loss = diff;
                    max_loss_index = i;
                }
            }
        }

        if(max_profit_index != -1) {
            System.out.println("buy [" + max_profit_index + "] => " + max_profit);
        }

        if(max_loss_index != -1) {
            System.out.println("sell [" + max_loss_index + "] => " + max_loss);
        }
    }

}
