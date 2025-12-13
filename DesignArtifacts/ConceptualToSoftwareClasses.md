# Key Conceptual classes
## In order of noun extraction
### Customer
Represents a user that can place orders.
### Barista
Represents a user that can view placed orders and progress them.
### Manager
Represents a user that can manage inventory.
### Item
Represents available items to order.
### Order
Represents all items ordered by one customer. 
### Beverage
Represents type of item that can be defined as beverage.
### Pastry
Represents type of item that can be defined as pastry.
### Inventory
Represents a collection of ingredients.
### Menu
Represents a collection of items that can be ordered with their possible customizations.
### ProductType
Represents item types like beverage and pastry.
### UserRole
Represents an abstract role that user can take while logging in.
### Catalog
Represents a collection of available products.
### BeverageType
Represents types of beverages.
### Size
Represents possible sizes for a beverage. 
### Customization
Represents additions to the products that entail additional costs.
### PastryType
Represents types of pastry.
### MenuItem
Represents a single item from a menu.
### Ingredient
Represents an item from inventory.
### System
Represents a main class that organizes and manages application flow.
# Major Software classes
## How they are translated from conceptual classes and what are their responsibilities.
### Role
This is an enum **UserRole** that combines **Customer**, **Barista** and **Manager** to not hardcode these roles with String type.
### UserAccount
A class that combines **Customer**, **Barista** and **Manager** into one, by using **UserRole**.
### CafeModel
**System** class that will manage other classes like controllers, services and the ones that hold different collections.
### MenuItem
Model class that defines conceptual class **MenuItem**.
### MenuService
Class that represents **Menu** and holds factories to produce **Beverage**, **Pastry** and **MenuItem**
### ProductCatalog
**Catalog** class that holds a map of **MenuItem** instances.
### Order
Model class that defines conceptual class **Order** which holds a list of **OrderItem**.
### OrderItem
Class that represents a conceptual class **Item**, it holds a **MenuItem** and specifies conrete choices.
### CustomizationOption
Model class that defines conceptual class **Customization**.
### BeverageSize
An enum of a conceptual class **Size**.
### Category
An enum of a conceptual class **ProductType**.
### Beverage
Model class that defines conceptual class **Beverage**.
### Pastry
Model class that defines conceptual class **Pastry**.
### Inventory
Split from conceptual class **Inventory**. Represents a map of **IngredientStock**.
### IngredientStock
Split from conceptual class **Inventory**. Represents a stock of each separate **Ingredient**.
### Ingredient
Model class that defines conceptual class **Ingredient**.
### Not Included
**BeverageType** and **PastryType** could become enums to prevent String types of the variants. But the variants should be more dynamic and can be modified by managers.