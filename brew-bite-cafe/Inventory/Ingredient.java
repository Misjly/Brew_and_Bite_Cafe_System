import java.util.Objects;

public class Ingredient {

    private final String name;
    private final String unit;

    /**
     * Creates a new ingredient.
     *
     * @param name the name of the ingredient
     * @param unit the unit of measurement (e.g., "oz", "ml", "grams")
     * @throws IllegalArgumentException if name or unit is null or empty
     */
    public Ingredient(String name, String unit) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or empty");
        }
        if (unit == null || unit.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingredient unit cannot be null or empty");
        }

        this.name = name;
        this.unit = unit;
    }

    /**
     * Gets the ingredient name.
     *
     * @return the ingredient name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unit of measurement.
     *
     * @return the unit of measurement
     */
    public String getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }

    @Override
    public String toString() {
        return name + " (" + unit + ")";
    }
}
