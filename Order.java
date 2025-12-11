import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {


    private final String Id;
    private final String customerName;
    private final LocalDateTime createdAt;
    private OrderStatus status;
    private final List<OrderItem> items;

    public Order(String customerName, List<OrderItem> items){
        this(UUID.randomUUID().toString(), customerName, LocalDateTime.now(), OrderStatus.PENDING, items);
    }

     public Order(String Id,
                 String customerName,
                 LocalDateTime createdAt,
                 OrderStatus status,
                 List<OrderItem> items) {
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("customerName must not be null/blank");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.id = id != null ? Id : UUID.randomUUID().toString();
        this.customerName = customerName;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.status = status != null ? status : OrderStatus.PENDING;
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
    }

    public String getId() {
        return Id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getLineTotal());
        }
        return total;
    }

     public String toString() {
        return "Order{" +
                "Id='" + Id + '\'' + " customerName='" + customerName + '\'' + " createdAt=" + createdAt + " status=" + status + " items=" + items.size() + '}';
     }

}