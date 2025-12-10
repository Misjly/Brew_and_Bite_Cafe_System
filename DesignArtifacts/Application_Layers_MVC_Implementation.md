# Architectural layers
## View
The view layer handles interactions with user: input and display of the data through the user interface. It changes based on the information that the Model layer provides it with.
## Controller
The Controller layer handles the input that the View provides, parses it and uses the resulting data to update model by manipulating higher level functionality.
## Model
The Model layer stores data needed for the application to run and handles business logic which includes lower level functionality and notification of the View layer of any updates.
## Data access
Data access layer handles the persistence state of data for when the application won't be able to store it due to inactivity. It handles both the input and the output of the data between application and the storage.
# MVC pattern implementation
* The View layer is implemented primarily with JavaFX that provides a graphical user interface for easier user interaction. JavaFX utilizes fxml files that help render the view screens for the GUI. Those represent the View layer but they are used by the controller classes themselves to render.
* The Controller layer is implemented in the similarly called classes: BaristaController, CustomerController and ManagerController. They handle the input and manipulate the Model layer classes along with being a part of an Observer pattern.
* The Model layer is implemented with domain model classes that handle the business logic, for example UserAccout, Beverage, Pastry, etc.
* The Data access layer is implemented to preserve the state of the application for future use through JSON files. The authentication details, inventory, product catalog and orders are saved through the serialization and pulled from the external storage through deserialization
# MVC design decisions and layering
The layers divide classes into separate resposibilities to ensure low coupling and high cohesion. They don't interact between layers more than nessesary to enhance maintainability, modularity and scalability, and are grouped under similar functionality to enhance readability, reliability and make debugging easier by isolating different systems.
1. The View only displays information and accepts user interactions while only passing that information to the Controller.
2. Controller is only busy with parsing that input and passing it to the Model classes through interfaces that prevent excessive dependance.
3. Model only handles the data that Controller passed to it through its business logic to pass it back to the View without getting reliant on the View's classes.
4. Data access layer's only responsibility is to take data that Model stores during runtime and save it for future loading into the Model which doesn't have to worry where this data is saved because of this layer.