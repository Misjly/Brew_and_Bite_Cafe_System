import java.math.BigDecimal;
import java.util.Map;


public abstract class MenuItemFactory {

    public abstract MenuItem createMenuItem(String id, String name, Category category, BigDecimal basePrice, Map<String, Double> baseIngredientConsumption);

}