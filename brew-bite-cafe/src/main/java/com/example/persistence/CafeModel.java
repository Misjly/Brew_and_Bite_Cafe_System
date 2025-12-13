package com.example.persistence;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.enums.BeverageSize;
import com.example.enums.Category;
import com.example.enums.OrderStatus;
import com.example.model.AuthService;
import com.example.model.CustomizationOption;
import com.example.model.Ingredient;
import com.example.model.IngredientStock;
import com.example.model.InventoryService;
import com.example.model.MenuItem;
import com.example.model.MenuService;
import com.example.model.Order;
import com.example.model.OrderBoard;
import com.example.model.OrderItem;
import com.example.model.OrderService;
import com.example.model.ProductCatalog;
import com.example.model.UserAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class CafeModel {
    private final AuthService authService;
    private final InventoryService inventoryService;
    private final ProductCatalog catalog;
    private final MenuService menuService;
    private final OrderBoard orderBoard;
    private final OrderService orderService;


    private final Gson gson;
    private final Path usersFile;
    private final Path inventoryFile;
    private final Path catalogFile;
    private final Path ordersFile;


    public CafeModel(AuthService authService, InventoryService inventoryService, ProductCatalog catalog, MenuService menuService, OrderBoard orderBoard, OrderService orderService, Path usersFile, Path inventoryFile, Path catalogFile, Path ordersFile) {
        if(authService == null || inventoryService == null || catalog == null || menuService == null || orderBoard == null || orderService == null) {
            throw new IllegalArgumentException("All services and domain objects must not be null");
        }

        this.authService = authService;
        this.inventoryService = inventoryService;
        this.catalog = catalog;
        this.menuService = menuService;
        this.orderBoard = orderBoard;
        this.orderService = orderService;

        this.usersFile = usersFile;
        this.inventoryFile = inventoryFile;
        this.catalogFile = catalogFile;
        this.ordersFile = ordersFile;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }


    public Optional<UserAccount> login(String username, String password) {
        return authService.login(username, password);
    }

    public MenuItem addBeverage(String id, String name, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        return menuService.addBeverage(id, name, basePrice, baseIngredientConsumption);
    }

    public MenuItem addPastry(String id, String name, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
        return menuService.addPastry(id, name, basePrice, baseIngredientConsumption);
    }

    public Collection<MenuItem> getAllMenuItems() {
        return menuService.getAllItems();
    }

    public List<MenuItem> getMenuItemsByCategory(Category category) {
        return menuService.getItemsByCategory(category);
    }

    public Map<String, IngredientStock> getAllInventoryStocks() {
        return inventoryService.getAllStocks();
    }

    public Order createAndPlaceOrder(String customerName, List<OrderItem> items) {
        Order order = new Order(customerName, items);
        if(!inventoryService.canFulfill(order)) {
            throw new IllegalArgumentException("Insufficient inventory to place the order");
        }

        inventoryService.consumeFor(order);
        orderService.placeOrder(order);
        return order;
    }

    public List<Order> getPendingOrders() {
        return orderBoard.getPendingOrders();
    }

    public List<Order> getFulfilledOrders() {
        return orderBoard.getFulfilledOrders();
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        orderBoard.updateStatus(orderId, newStatus);
    }

    public void restockIngredient(String name, String unit, double amount) {
        Ingredient ingredient = new Ingredient(name, unit);
        inventoryService.addStock(ingredient, amount);
    }



    // loading the application from the JSON files and any missing files will give "no data yet"
    public void LoadState() throws IOException {
        loadUsers();
        loadInventory();
        loadCatalog();
        loadOrders();
    }

    // saving the application state to the JSON files
    public void SaveState() throws IOException {
        saveUsers();
        saveInventory();
        saveCatalog();
        saveOrders();
    }

    private void saveUsers() throws IOException {
        if(usersFile == null) return;

        Map<String, UserAccount> accounts = authService.getAccounts();

        try(Writer writer = Files.newBufferedWriter(usersFile)) {
            gson.toJson(accounts, writer);
        }
    }
    
    private void loadUsers() throws IOException {
        if (usersFile == null || !Files.exists(usersFile)) return;

        Type type = new TypeToken<Map<String, UserAccount>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(usersFile)) {
            Map<String, UserAccount> loaded = gson.fromJson(reader, type);
            if (loaded != null) {

            }
        }
    }


     private void saveInventory() throws IOException {
        if (inventoryFile == null) return;

        List<InventoryEntryDTO> dtoList = new ArrayList<>();
        for (IngredientStock stock : inventoryService.getAllStocks().values()) {
            Ingredient ing = stock.getIngredient();
            dtoList.add(new InventoryEntryDTO(ing.getName(), ing.getUnit(), stock.getQuantityAvailable()));
        }
        try(Writer writer = Files.newBufferedWriter(inventoryFile)) {
            gson.toJson(dtoList, writer);
        }
     }

    private void loadInventory() throws IOException {
        if (inventoryFile == null || !Files.exists(inventoryFile)) return;

        Type type = new TypeToken<List<InventoryEntryDTO>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(inventoryFile)) {
            List<InventoryEntryDTO> dtoList = gson.fromJson(reader, type);
            if (dtoList != null) {
                for (InventoryEntryDTO dto : dtoList) {
                    Ingredient ing = new Ingredient(dto.name, dto.unit);
                    inventoryService.addStock(ing, dto.quantity);
                }
            }
        }
    }

     private void saveCatalog() throws IOException {
        if (catalogFile == null) return;

        List<MenuItemDTO> dtoList = new ArrayList<>();
        for (MenuItem item : catalog.getAllItems()) {
            dtoList.add(new MenuItemDTO(item.getId(), item.getName(), item.getCategory(), item.getBasePrice(), item.getBaseIngredientConsumption()));
        }

        try (Writer writer = Files.newBufferedWriter(catalogFile)) {
            gson.toJson(dtoList, writer);
        }
    }


    private void loadCatalog() throws IOException {
        if (catalogFile == null || !Files.exists(catalogFile)) return;

        Type type = new TypeToken<List<MenuItemDTO>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(catalogFile)) {
            List<MenuItemDTO> dtoList = gson.fromJson(reader, type);
            if (dtoList != null){
                for(MenuItemDTO dto : dtoList) {
                    if (dto.category == Category.BEVERAGE){
                        menuService.addBeverage(dto.id, dto.name, dto.basePrice, dto.baseIngredientConsumption);
                    }
                }
            }
        }
    }



    private void saveOrders() throws IOException {
        if (ordersFile == null) return;

        OrdersStateDTO dto = new OrdersStateDTO();

        dto.pending = new ArrayList<>();
        for (Order ord : orderBoard.getPendingOrders()) {
            dto.pending.add(OrderDTO.fromOrder(ord));
        }

        dto.fulfilled = new ArrayList<>();
        for (Order ord : orderBoard.getFulfilledOrders()) {
            dto.fulfilled.add(OrderDTO.fromOrder(ord));
        }

        try (Writer writer = Files.newBufferedWriter(ordersFile)) {
            gson.toJson(dto, writer);
        }
    }

    private void loadOrders() throws IOException {
        if (ordersFile == null || !Files.exists(ordersFile)) return;

        try (Reader reader = Files.newBufferedReader(ordersFile)) {
            OrdersStateDTO dto = gson.fromJson(reader, OrdersStateDTO.class);
            if (dto == null) return;

            // Pending orders
            if (dto.pending != null) {
                for (OrderDTO odto : dto.pending) {
                    Order order = odto.toOrder(catalog);
                    orderBoard.addOrder(order);
                }
            }

            // Fulfilled orders
            if (dto.fulfilled != null) {
                for (OrderDTO odto : dto.fulfilled) {
                    Order order = odto.toOrder(catalog);
                    order.setStatus(OrderStatus.FULFILLED);
                    orderBoard.addFulfilled(order); 
                }
            }
        }
    }


    private static class InventoryEntryDTO {
        String name;
        String unit;
        double quantity;

        InventoryEntryDTO(String name, String unit, double quantity) {
            this.name = name;
            this.unit = unit;
            this.quantity = quantity;
        }
    }

    private static class MenuItemDTO {
        String id;
        String name;
        Category category;
        BigDecimal basePrice;
        Map<String, Double> baseIngredientConsumption;

        MenuItemDTO(String id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.basePrice = basePrice;
            this.baseIngredientConsumption = baseIngredientConsumption;
        }
    }

    private static class OrdersStateDTO {
        List<OrderDTO> pending;
        List<OrderDTO> fulfilled;
    }

    private static class OrderDTO {
        String id;
        String customerName;
        String createdAt;
        OrderStatus status;
        List<OrderItemDTO> items;

        static OrderDTO fromOrder(Order order) {
            OrderDTO dto = new OrderDTO();
            dto.id = order.getId();
            dto.customerName = order.getCustomerName();
            dto.createdAt = order.getCreatedAt().toString();
            dto.status = order.getStatus();
            dto.items = new ArrayList<>();
            for (OrderItem oi : order.getItems()) {
                dto.items.add(OrderItemDTO.fromOrderItem(oi));
            }
            return dto;
        }

        Order toOrder(ProductCatalog catalog) {
            List<OrderItem> rebuiltItems = new ArrayList<>();
            if(items != null) {
                for (OrderItemDTO i : items) {
                    MenuItem product = catalog.findById(i.productId).orElseThrow(() -> new IllegalStateException("Unkown product id in saved order:" + i.productId));
                    List<CustomizationOption> customizations = new ArrayList<>();

                    OrderItem rebuilt = new OrderItem(product, i.quantity, i.size, customizations);
                    rebuiltItems.add(rebuilt);
                }
            }

            return new Order(id, customerName, LocalDateTime.parse(createdAt), status, rebuiltItems);
        }
    }

    private static class OrderItemDTO {
        String productId;
        int quantity;
        BeverageSize size;
        List<String> customizationIds;

        static OrderItemDTO fromOrderItem(OrderItem item) {
            OrderItemDTO dto = new OrderItemDTO();
            dto.productId = item.getProduct().getId();
            dto.quantity = item.getQuantity();
            dto.size = item.getSize();
            dto.customizationIds = new ArrayList<>();
            for (CustomizationOption opt : item.getCustomizations()) {
                dto.customizationIds.add(opt.getName());
            }
            return dto;
        }
    }
}
