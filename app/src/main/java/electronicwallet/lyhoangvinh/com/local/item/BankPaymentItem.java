package electronicwallet.lyhoangvinh.com.local.item;

import electronicwallet.lyhoangvinh.com.base.interfaces.Item;
import electronicwallet.lyhoangvinh.com.local.model.Bank;

public class BankPaymentItem implements Item {

    private Bank bank;

    public BankPaymentItem(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
