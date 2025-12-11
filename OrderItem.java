import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OrderItem {

    private final MenuItem product;
    private final int quantity;
    private final BeverageSize size;
    private final List<CustomizationOption> customizations;

    public OrderItem(MenuItem product, int quantity, BeverageSize size, List<CustomizationOption> customizations) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must greater than 0");
        }

        this.product = product;
        this.quantity = quantity;
        this.size = size;
        if(customizations == null || customizations.isEmpty()) {
            this.customizations = Collections.emptyList();
        } else {
            this.customizations = Collections.unmodifiableList(new ArrayList<>(customizations));
        }
    }

    public MenuItem getProduct() {
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public BeverageSize getSize() {
        return size;
    }

    public List<CustomizationOption> getCustomizations() {
        return customizations;
    }


    public BigDecimal getUnitPrice() {
        BigDecimal price = product.getBasePrice();
        for (CustomizationOption option : customizations) {
            price = price.add(option.getExtraCost());
        }
        return price;
    }

    public BigDecimal getLineTotal() {
        return getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }


}