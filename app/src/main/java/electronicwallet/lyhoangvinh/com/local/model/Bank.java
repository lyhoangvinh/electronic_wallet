package electronicwallet.lyhoangvinh.com.local.model;

public class Bank {
    private String url;
    private String title;
    private boolean isTick;

    public boolean isTick() {
        return isTick;
    }

    public void setTick(boolean tick) {
        isTick = tick;
    }

    public Bank(String url, String title, boolean isTick) {
        this.url = url;
        this.title = title;
        this.isTick = isTick;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
