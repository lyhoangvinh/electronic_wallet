package electronicwallet.lyhoangvinh.com.events;

import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.Total;

public class MoneyEvent {
    private Money money;
    private Total total;
    private String bankName;

    public MoneyEvent() {
    }

    public MoneyEvent(Money money, Total total) {
        this.money = money;
        this.total = total;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public String getBankName() {
        return bankName;
    }
}
