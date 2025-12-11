package com.example.model;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CustomizationOption {

    private final int Id;
    private final String name;
    private final BigDecimal extraCost;
    private final Map<String, Double> extraIngredientConsumption;


    public CustomizationOption(int Id, String name, BigDecimal extraCost, Map<String, Double> extraIngredientConsumption){
        this.Id = Id;
        this.name = name;
        this.extraCost = extraCost != null ? extraCost : BigDecimal.ZERO;

        if (extraIngredientConsumption == null) {
            this.extraIngredientConsumption = Collections.emptyMap();
        } else {
            this.extraIngredientConsumption = Collections.unmodifiableMap(new HashMap<>(extraIngredientConsumption));
        }
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExtraCost() {
        return extraCost;
    }
    

    public Map<String, Double> getExtraIngredientConsumption(){
        return extraIngredientConsumption;
    }

    @Override
    public String toString() {
        return "Customization Option{" + "Id = " + Id + "name = " + name + '\'' + "Extra Cost =" + extraCost + "}";
        }

    
   
    }