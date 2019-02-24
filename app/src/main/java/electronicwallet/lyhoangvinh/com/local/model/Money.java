package electronicwallet.lyhoangvinh.com.local.model;

public class Money {
    private int price;
    private String content;
    private boolean tick = false;

    public Money(int price, String content, boolean tick) {
        this.price = price;
        this.content = content;
        this.tick = tick;
    }

    public int getPrice() {
        return price;
    }

    public String getContent() {
        return content;
    }

    public boolean isTick() {
        return tick;
    }

    public void setTick(boolean tick) {
        this.tick = tick;
    }
}
