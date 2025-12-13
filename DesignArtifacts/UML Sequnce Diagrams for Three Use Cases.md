# UML Sequnce Diagrams for Three Use Cases

i. Three use cases for UML sequence diagram

- Use Case 1: Customer Places Order
- Use Case 2: Barista Updates Order Status
- Use Case 3: Manager Restocks Inventory

ii. Three UML Sequence Diagrams

- All images will be included in .zip file

iii. Activity Diagram

- Image will be included in .zip file

iv.

- For the "Customer Places Order" sequence diagram, I separated each step into different classes so it follows the Single Responsibility Principle. The controller will only coordinate the workflow. The MenuService checks to see if the items are valid, the InventoryService checks how much stock ther is, the PaymentGateway handles everything to do with the payment, and the OrderRepository saves the final order. This makes the design easier to update and change later if needed. As a quick example, if the payment method were to need a change, only the PaymentGateway class needs to be updated. Also, using an OrderFactory also makes sense because it centralizes how order objects are created, making the system more consistent.

- In the "Barista Updates Order Status" sequence diagram, once again, I made the it so the controller just forwards the request and keeps everything organized. The OrderService handles all the logic for updating the order, while the repository is responsible for fetching and saving the data. This separation uses the object-oriented idea encapsulation, because each class hides its internal work and shows only simple methods. It also makes sure that business logic is not mixed inside the controller.

- For the "Manager Restocks Inventory" sequence diagram, I used a similar structure. The InventoryService handles all the logic, the InventoryRepository interacts with the stored data, and the AuditLog keeps track of restock events that occur. Adding the AuditLog is good design practice and works for this because it separates logging from the actual business operations, which keeps the core code cleaner and easier to maintain.

- For the activity diagram, I focused on showing the overall workflow of placing an order, including the decisions and possible failure points. This makes the process easier to understand at a high level. The diagram shows the logical path the system follows-validating items, checking availability, handling payment, and confirming the order. This demonstrates how the workflow connects to the steps shown in the sequence diagram but in a simpler, more narrative style.
