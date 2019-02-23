package electronicwallet.lyhoangvinh.com.local.model;

public class Settings {
    private int position;
    private String title;
    private int icon;

    public Settings(int position, String title, int icon) {
        this.position = position;
        this.title = title;
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}
