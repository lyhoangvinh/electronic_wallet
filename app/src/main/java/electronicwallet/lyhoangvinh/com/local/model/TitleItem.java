package electronicwallet.lyhoangvinh.com.local.model;

import electronicwallet.lyhoangvinh.com.base.interfaces.Item;

public class TitleItem implements Item {
    private String title;

    public TitleItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
