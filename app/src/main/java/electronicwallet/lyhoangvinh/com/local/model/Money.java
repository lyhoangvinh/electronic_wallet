package electronicwallet.lyhoangvinh.com.local.model;

public class Money {
    private int price;
    private boolean tick = false;

    public Money(int price, boolean tick) {
        this.price = price;
        this.tick = tick;
    }

    public int getPrice() {
        return price;
    }

    public boolean isTick() {
        return tick;
    }

    public void setTick(boolean tick) {
        this.tick = tick;
    }
}
