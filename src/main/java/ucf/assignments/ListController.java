package ucf.assignments;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.LocalDateStringConverter;


import java.beans.SimpleBeanInfo;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListController implements Initializable
{
    @FXML
    public ComboBox<String> listDropdown = new ComboBox<String>();
    @FXML
    public TableView<Task> itemTable = new TableView<>();

    @FXML
    private TableColumn descriptionCol = new TableColumn();
    @FXML
    private TableColumn dateCol = new TableColumn();
    @FXML
    private TableColumn completed = new TableColumn();

    // create add button, delete button, edit button
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;
    @FXML private Button enter;
    @FXML private Button addItemButton;
    @FXML private Button deleteItemButton;
    //@FXML private String title;
    // create a list view of ListManagers
    //@FXML
   // ListView<ListManager> listNames = new ListView<>();
    @FXML
    private TextField listNameView;
    @FXML
    private TextField descriptionBox;
    @FXML
    private String description;
    @FXML
    private DatePicker dueDate;
    @FXML
    private CheckBox isCompleted;

    // create a new FileChooser

    //create new buttons called open and cancel to use when loading list(s)
    // create an observable list
    // create a new list manager
    // create a new stage
    Stage p = new Stage();
    //create a new list model
    ListModel model = new ListModel();
    // create a new list manager
    ListManager manager = new ListManager();


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<String> choices = FXCollections.observableArrayList("All Items", "Completed Items", "Incomplete Items");
        listDropdown.setItems(choices);
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        itemTable.setEditable(true);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        completed.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));
        LocalDateStringConverter converter = new LocalDateStringConverter();
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t) {
                if (t==null) {
                    return "" ;
                } else {
                    return dateFormat.format(t);
                }
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    return LocalDate.parse(string, dateFormat);
                } catch (DateTimeParseException exc) {
                    return null ;
                }
            }

        }));
        completed.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));

    }

    @FXML
    public void addListClicked(javafx.event.ActionEvent event) throws IOException
    {
        //if the user clicks the + icon
        // create a new list model
        // set the list view to editable
        // set the cell factory to be a text field in order to edit it
        // navigate to the list view
        // set prompt text to "Enter Title"
        // if user presses enter on the keyboard after adding the title
        // add the list using the list model object
        if(event.getSource() == addButton)
        {
            listNameView.requestFocus();
            System.out.println("It works");

        }
    }


    @FXML
    public void Enter(javafx.event.ActionEvent event) throws IOException
    {
        System.out.println("The");
        manager.getListName();
        if (event.getSource() == enter)
        {
            System.out.println("ENTER pressed");
            if(listNameView != null)
            {
                manager.setListName(listNameView.getText());
                System.out.println("The name you entered is \"" + manager.getListName());
                model.addList(p);
            }
            else
                System.out.println("List view is null");
        }


    }

    @FXML
    public void deleteListClicked(javafx.event.ActionEvent event)
    {
        // If the user clicks on one of the lists
            // if the user clicks delete
            // call the deleteList() function from the ListModel class
    }
    @FXML
    public void editListClicked(javafx.event.ActionEvent event)
    {
        //if the user clicks the list edit button
        // show the list view of titles
        //set the editable ability to true for user to be able to edit the title
        // use setCellFactory to provide the editable cells using forListView
        // call the edit title method using the list manager object

    }

    @FXML
    public void addItemClicked(ActionEvent event)
    {

        // if the user clicks the add button
            //add item using add item function from the ListManager class
        if(event.getSource() == addItemButton)
        {
            description = descriptionBox.getText();

            Task t = new Task(description, dueDate.getValue(), isCompleted);
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            completed.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));
            itemTable.setItems(manager.addItem(t));
            System.out.println("Description:" + description);
            System.out.println("Due Date:" + dueDate.getValue());
            System.out.println("Completed:" + isCompleted.isSelected());
        }
    }

    @FXML
    public void deleteItemClicked(javafx.event.ActionEvent event)
    {
        // if the user clicks the delete button
            //add item using delete item function from the ListManager class
        if(event.getSource() == deleteItemButton)
        {
            Task t = new Task(description, dueDate.getValue(), isCompleted);
            itemTable.setItems(manager.deleteItem(t));
            itemTable.getItems().removeAll(itemTable.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void listDropdownClicked(javafx.event.ActionEvent event)
    {
        // If you clicked the dropdown button
        // create a new observable list with the options, "all items", "completed", and "incomplete"
        // create a new choice box with those options
        // get the value of the choice that is clicked by the user
        // if the value is all items, call the function displayAll()
        // else if the value is only completed items, call the function displayCompleted()
        // else call the function displayIncomplete()
       // ObservableList<String> choices = FXCollections.observableArrayList("All Items", "Completed Items", "Incomplete Items");
        //listDropdown.setItems(choices);
        if(event.getSource() == listDropdown)
        {

           /* listDropdown.valueProperty().addListener((obs, oldItem, newItem) -> {
                label.textProperty().unbind();
                if (newItem == null) {
                    label.setText("");
                } else {
                    label.textProperty().bind(newItem.detailsProperty());
                }
            });
            */
           /* ObservableList<Task> all = manager.addAll();
            ObservableList<Task> completed = manager.addCompleted();
            ObservableList<Task> incomplete = manager.addIncomplete();*/
            for(int i = 0; i < itemTable.getItems().size(); i++)
            {
                if (listDropdown.getSelectionModel().getSelectedItem() == "All Items") {
                    itemTable.setItems(manager.addAll());
                } else if (listDropdown.getSelectionModel().getSelectedItem() == "Completed Items") {
                    itemTable.setItems(manager.addIncomplete(itemTable.getItems().get(i)));
                } else if (listDropdown.getSelectionModel().getSelectedItem() == "Incomplete Items") {
                    itemTable.setItems(manager.addIncomplete(itemTable.getItems().get(i)));
                }
            }
        }

    }


    @FXML
    public void importListClicked(ActionEvent event)
    {
        // initialize the file chooser to open a new window showing a new stage
        // if the file is not null
        // if(file != null)
         // call the importList function from the ManageFiles class to import the list
    }

    @FXML
    public void exportListClicked(ActionEvent actionEvent)
    {
        // if the user clicks on one of the lists
            // if the user clicks export
                // call the exportList() function from the ManageFiles class
        // else if the user clicks on the Ctrl key and selects multiple lists
            // if the user clicks export
                // call the exportMultipleLists() from the ManageFiles class
    }
    @FXML
    public void editDescription()
    {
        descriptionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event)
            {
                Task t = (Task) event.getRowValue();
                t.setDescription((String) event.getNewValue());

            }
        });
    }

    @FXML
    public void editDueDate()
    {
        descriptionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event)
            {
                Task t = (Task) event.getRowValue();
                t.setDueDate( (LocalDate) event.getNewValue());

            }
        });
    }


    @FXML
    public void editCompleted()
    {
        descriptionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, CheckBox>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event)
            {
                Task t = (Task) event.getRowValue();
                t.setIsCompleted( (boolean) event.getNewValue());

            }
        });
    }





    public void sortItems(SortEvent<TableView> tableViewSortEvent)
    {

    }
}
