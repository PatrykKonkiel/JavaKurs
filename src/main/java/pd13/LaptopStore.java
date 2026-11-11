package pd13;

import lombok.Getter;

@Getter
public class LaptopStore {

    private int availableQuantity;
    private int soldQuantity;

    public LaptopStore(int availableQuantity) {
        this.availableQuantity = availableQuantity;
        this.soldQuantity = 0;
    }

    public synchronized boolean tryPurchase(int quantity) {
        if (quantity <= 0 || quantity > availableQuantity) {
            return false;
        }
        availableQuantity -= quantity;
        soldQuantity += quantity;
        return true;
    }
}
