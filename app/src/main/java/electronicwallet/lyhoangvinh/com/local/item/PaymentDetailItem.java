package electronicwallet.lyhoangvinh.com.local.item;

import electronicwallet.lyhoangvinh.com.base.interfaces.Item;

public class PaymentDetailItem implements Item {
    private int count;

    private int money;

    public PaymentDetailItem(int count, int money) {
        this.count = count;
        this.money = money;
    }

    public int getCount() {
        return count;
    }

    public int getMoney() {
        return money;
    }
}
