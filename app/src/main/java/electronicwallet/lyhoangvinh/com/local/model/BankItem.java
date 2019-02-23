package electronicwallet.lyhoangvinh.com.local.model;

import electronicwallet.lyhoangvinh.com.base.interfaces.Item;

public class BankItem implements Item {

    private String title;
    private String name;

    public BankItem(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}
