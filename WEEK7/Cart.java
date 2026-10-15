public class Cart {
    private int[] prices;
    private int count;
    private final String cartId;

    public Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new int[maxItems];
        this.count = 0;
    }

    public void addItem(int price) {
        if (count < prices.length) {
            prices[count++] = price;
        }
    }

    public int getTotal() {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += prices[i];
        }
        return sum;
    }

    public int getItemCount() {
        return count;
    }
}
