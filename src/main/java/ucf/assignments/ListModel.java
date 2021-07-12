/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */
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
    // create a new textfield of the name of the list
    // create an observable list of of ListManager objects`
    @FXML
    private TextField listNameView = new TextField();
    ListView<ListManager> listNames = new ListView<>();

    public void addList(Stage primary) throws IOException {
        // load the item.fxml file to load the table
        // create new scene of the root
        // set the title of the value in the textfield
        // set the scene to the item.fxml and show the scene
        Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
        Scene scene = new Scene(root, 600, 500);
        primary.setTitle(listNameView.getText());
        primary.setScene(scene);
        //Displaying the stage
        primary.show();

    }


}
