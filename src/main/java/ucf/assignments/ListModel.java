package ucf.assignments;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListModel
{
    private Object ObservableList;
    //MANAGES A LIST OF LISTMANAGERS
    // create an observable list of of ListManager objects`

    @FXML
    private TextField listNameView;
    ListView<ListManager> listNames = new ListView<>();
    // set the prompt text to prompt for the title of the list
    public void addList(Stage primary) throws IOException {
        // Create a new Scene
        // Create a new fxml loader
        // Create new tableview for list
        // Create columns w header names (Description, Due Date, Completed?)
        // Create a new Text object called title
        // set the title as the text object
        // return title
        Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
        Scene scene = new Scene(root, 600, 500);
        //primary.setTitle(listNameView.getName());
        primary.setScene(scene);
        //Displaying the stage
        primary.show();

    }

    public ObservableList deleteList()
    {
        //click on the list that you wish to delete
        //remove the list from the observable list
        //remove the list from the list view
        //return the list title contents
        return (javafx.collections.ObservableList) ObservableList;
    }



    public String editTitle()
    {
        // create a new String variable to hold the title
        // intialize - use the getNewValue() method of the listview class to overwrite the previous value
        // return the variable
        return "";
    }



    public String toString()
    {
        // create a String that gets the title of the list
        //return the String
        return "";
    }


}
