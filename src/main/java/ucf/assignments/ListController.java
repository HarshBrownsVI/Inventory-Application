
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */
package ucf.assignments;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.w3c.dom.Text;


import javax.swing.event.ChangeListener;
import java.beans.SimpleBeanInfo;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ListController implements Initializable
{
    // create a new combo box for the options, tableview, and table columns
    @FXML
    public ComboBox<String> listDropdown = new ComboBox<String>();
    @FXML
    public TableView<Task> itemTable = new TableView<>();

    @FXML
    private TableColumn<Task, String> descriptionCol = new TableColumn("Description");
    @FXML
    private TableColumn<Task, LocalDate> dateCol = new TableColumn<Task, LocalDate>("Due Date");
    @FXML
    private TableColumn<Task, String> completed = new TableColumn("Completed?");

    // create add button, delete button, edit button
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button editButton;
    @FXML private Button enter;
    @FXML private Button addItemButton;
    @FXML private Button deleteItemButton;
    @FXML private Button exportList;
    @FXML private Button importList;


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
    FileChooser chooser = new FileChooser();

    //create new buttons called open and cancel to use when loading list(s)
    // create an observable list
    // create a new list manager
    // create a new stage
    Stage p = new Stage();
    //create a new list model
    ListModel model = new ListModel();
    // create a new list manager
    ListManager manager = new ListManager();
    // create a new file manager
    ManageFiles files = new ManageFiles();
    // create a new anchor pane for the error messages
    @FXML
    private AnchorPane anchorPane = new AnchorPane();


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<String> choices = FXCollections.observableArrayList("All Items", "Completed Items", "Incomplete Items");
        listDropdown.setItems(choices);
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        itemTable.setEditable(true);
        LocalDateStringConverter converter = new LocalDateStringConverter();
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate t)
            {
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
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        completed.setCellFactory(TextFieldTableCell.forTableColumn());
        completed.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));

        itemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void addListClicked(javafx.event.ActionEvent event) throws IOException
    {
        //if the user clicks the + icon
        //move the cursor to the textbox
        if(event.getSource() == addButton)
        {
            listNameView.requestFocus();
        }
    }


    @FXML
    public void Enter(javafx.event.ActionEvent event) throws IOException
    {
        //if the user clicks the enter button
        // if the name of the list is given
        // set the list name to the value in the text
        // add the list as per the add list function in the model class
        if (event.getSource() == enter) {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Item.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
                if (listNameView != null) {
                    //manager.setListName(listNameView.getText());
                    p.setScene(root.getScene());
                    p.setTitle(listNameView.getText());
                    model.addList(p);
                }
            }
        }

    }


    @FXML
    public void addItemClicked(ActionEvent event) throws IOException {

        // if the user clicks the add button
        //add item using add item function from the ListManager class
        // update the description variable to be what is entered in the text box
        // set the cell value factories of the 3 field to enable their properties
        // add values to the item table using the addItem function in the ListManager class
        if(event.getSource() == addItemButton)
        {
            description = descriptionBox.getText();

            Task t = new Task(description, dueDate.getValue(), isCompleted.isSelected());
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            completed.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));
            //p = (Stage)anchorPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
            Scene scene = new Scene(root);
            p.setScene(scene);
            Alert alert = new Alert(Alert.AlertType.ERROR, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(p);
            if(t.getDescription().length() < 1 || t.getDescription().length() > 256 || t.getDescription().contains(","))
            {
                alert.getDialogPane().setContentText("Invalid Description!! Must be between 1 and 256 Characters and have no commas!!");
                alert.getDialogPane().setHeaderText("Invalid Item");
                alert.showAndWait();
            }
            else {
                itemTable.setItems(manager.addItem(t));
            }
        }
    }

    @FXML
    public void deleteItemClicked(javafx.event.ActionEvent event)
    {
        // if the user clicks the delete button
        //create a new Task which is the user input for the 3 values
        // remove all of the selected items
        if(event.getSource() == deleteItemButton)
        {
            Task t = new Task(description, dueDate.getValue(), isCompleted.isSelected());
            itemTable.setItems(manager.deleteItem(t));
            itemTable.getItems().removeAll(itemTable.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void listDropdownClicked(javafx.event.ActionEvent event)
    {
        // If you clicked the dropdown button
        // check each item in the list Dropdown
        // if the user selects all items
        // set the table to show all items
        // if the user selected only the completed items
        // set the table to show only the completed items
        // if the user selected only the incomplete items
        // set the table to show only the incomplete items
        if(event.getSource() == listDropdown)
        {
            if (listDropdown.getSelectionModel().getSelectedItem() == "All Items")
            {
                itemTable.setItems(manager.addAll());
            }
            else if (listDropdown.getSelectionModel().getSelectedItem() == "Completed Items")
            {
                itemTable.setItems(manager.addCompleted());
            }
            else if (listDropdown.getSelectionModel().getSelectedItem() == "Incomplete Items")
            {
                itemTable.setItems(manager.addIncomplete());
            }
        }
    }


    @FXML
    public void importListClicked(ActionEvent event) throws IOException
    {
        // initialize the file chooser to open a new window showing a new stage
        // if the file is not null
        // if(file != null)
        // call the importList function from the ManageFiles class to import the list
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        //Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle(chooser.getTitle());
        stage.setWidth(300);
        stage.setHeight(500);
        importList.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                try
                {
                    File selectedFile = chooser.showOpenDialog(stage);
                    ObservableList<Task> list = files.importList(selectedFile, manager);
                    System.out.println("File Name: " + selectedFile);
                    if (selectedFile != null)
                    {
                        itemTable.setEditable(true);
                        itemTable.setItems(list);
                        itemTable.getColumns().setAll(descriptionCol, dateCol, completed);
                        for(int i = 0; i < manager.getList().size();i++)
                            System.out.println(manager.getList().get(i).getDescription());
                        //stage.setScene(scene);
                        // stage.show();


                    }


                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }



    @FXML
    public void exportListClicked(ActionEvent actionEvent)
    {
        // create a new stage
        // create an extension to display on the screen for the .txt file
        // create a try catch
        // if the user clicks on the export list button
        // open up a dialog window to save a txt file
        // if the file is not null
        // get the filName that the user saves it as
        // create a new filewriter
        //create a for loop to go through the entire table and call the exportList function
        // close the writer
        // catch the exception and printStackTrace

        Stage stage = new Stage();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        exportList.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                try
                {
                    File file = chooser.showSaveDialog(stage);

                    if(file != null)
                    {
                        File fileName = file.getAbsoluteFile();
                        FileWriter writer = new FileWriter(fileName);

                        for (Task t : itemTable.getItems())
                        {
                            writer.append(files.exportList(t));
                        }

                        writer.flush();
                        writer.close();
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });

    }
    @FXML
    public void editDescription()
    {
        // create an on edit commit action with an event handler
        // create a new task that gets the row value
        // load the fxml file
        // create a try catch for the file
        // create a scene and an alert to display in error if an invalid field is entered
        // if the description is invalid
            // display an error saying that it is invalid and make them reenter another description
        //if it is valid, display the new value changed by the user
        Task t = itemTable.getSelectionModel().getSelectedItem();
        descriptionCol.setEditable(true);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        System.out.println("yayayayaya!!!!!!!!!!!!!!!!!!!!!");
        descriptionCol.setOnEditCommit(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
                Scene scene = new Scene(root);
                p.setScene(scene);
                Alert alert = new Alert(Alert.AlertType.ERROR, "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(p);


                ((Task) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDescription((String) event.getNewValue());
                String newVal = (String) event.getNewValue();
                if (newVal.length() < 1 || newVal.length() > 256 || newVal.contains(",")) {
                    System.out.println("New Value: " + newVal);
                    alert.getDialogPane().setContentText("Invalid Description!! Must be between 1 and 256 Characters and have no commas!!");
                    alert.getDialogPane().setHeaderText("Invalid Item");
                    alert.showAndWait();

                } else {
                    System.out.println("New Value: " + newVal);
                    ((Task) event.getTableView().getItems().get(event.getTablePosition().getRow())).setDescription((String) event.getNewValue());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        });
    }

    @FXML
    public void editDueDate()
    {
        // create an on edit commit action with an event handler
        // create a new task that gets the row value
        // load the fxml file
        // create a try catch for the file
        // create a scene and an alert to display in error if an invalid due date is entered
        // if the due date is invalid
        // display an error saying that it is invalid and make them reenter another date
        //else if it is valid, display the new value changed by the user
        dateCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, LocalDate>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task, LocalDate> event)
            {
                Task t = event.getRowValue();
                LocalDate newVal = event.getNewValue();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("Item.fxml"));

                    Scene scene = new Scene(root);
                    p.setScene(scene);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(p);

                    if (newVal == null || newVal.equals(DateTimeFormatter.ofPattern("yyyy-mm-dd"))) {
                        System.out.println("New Value: " + newVal);
                        alert.getDialogPane().setContentText("Invalid Date!! Must be between in the format YYYY-MM-DD!!");
                        alert.getDialogPane().setHeaderText("Invalid Item");
                        alert.showAndWait();
                    } else {
                        System.out.println("New Value: " + newVal);
                        (event.getTableView().getItems().get(event.getTablePosition().getRow())).setDueDate(event.getNewValue());
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }



    @FXML
    public void editCompleted()
    {
        // create an on edit commit action with an event handler
        // create a new task that gets the row value
        // load the fxml file
        // create a try catch for the file
        // create a scene and an alert to display in error if an invalid field is entered
        // if the completed field is not yes or no
        // display an error saying that it is invalid and make them reenter the correct value of whether it is completed or not
        //else if it is valid, display the new value changed by the user
        completed.setCellFactory(TextFieldTableCell.forTableColumn());
        completed.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent event)
            {
                Task t = (Task) event.getRowValue();
                String newVal = (String) event.getNewValue();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("Item.fxml"));

                    Scene scene = new Scene(root);
                    p.setScene(scene);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(p);

                    if (!newVal.equalsIgnoreCase("yes") && !newVal.equalsIgnoreCase("no")) {
                        System.out.println("New Value: " + newVal);
                        alert.getDialogPane().setContentText("Invalid Completed Item!! Only input yes or no based on whether the item is completed or not!");
                        alert.getDialogPane().setHeaderText("Invalid Item");
                        alert.showAndWait();
                    } else {
                        System.out.println("New Value: " + newVal);
                        ((Task) event.getTableView().getItems().get(event.getTablePosition().getRow())).setIsCompleted((String) event.getNewValue());
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

}
