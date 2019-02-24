package electronicwallet.lyhoangvinh.com.local.model;

import java.util.List;

import electronicwallet.lyhoangvinh.com.base.interfaces.Item;

public class BankItem implements Item {

    private String title;
    private String name;
    private List<Bank> banks;

    public BankItem(String title, String name, List<Bank> banks) {
        this.title = title;
        this.name = name;
        this.banks = banks;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}
