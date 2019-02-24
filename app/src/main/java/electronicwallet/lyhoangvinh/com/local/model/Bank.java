package electronicwallet.lyhoangvinh.com.local.model;

public class Bank {
    private String url;
    private String title;

    public Bank(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
