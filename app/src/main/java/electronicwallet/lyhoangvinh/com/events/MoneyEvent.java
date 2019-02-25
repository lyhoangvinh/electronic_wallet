package electronicwallet.lyhoangvinh.com.events;

import electronicwallet.lyhoangvinh.com.local.model.Bank;
import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.Total;


public class MoneyEvent {
    private Money money;
    private Total total;
    private Bank bank;

    public MoneyEvent() {
    }

    public MoneyEvent(Money money, Total total) {
        this.money = money;
        this.total = total;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
