package electronicwallet.lyhoangvinh.com.events;

import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.Total;

public class MoneyEvent {
    private Money money;
    private Total total;

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
}
