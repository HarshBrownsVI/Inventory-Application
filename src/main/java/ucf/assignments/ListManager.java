/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListManager {
    // MANAGES ONE LIST OF TASKS
    // create an observable list of tasks
    // create a list view to view the list items
    String listName;
    //create a textfield field for the title of the list

    @FXML
    private String description;
    @FXML
    private LocalDate dueDate;
    @FXML
    private CheckBox isCompleted;
    //Create a TableView

    @FXML
    public ObservableList<Task> list = FXCollections.observableArrayList();
    Serializable serializedData = new ArrayList<>(list);

    @FXML
    public ObservableList<Task> completed = FXCollections.observableArrayList();

    @FXML
    public ObservableList<Task> incomplete = FXCollections.observableArrayList();


    public ListManager() {

    }

    public ListManager(String listName) {
        // set this title equal to the parameter title
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        // set the parameter name to be equal to the field name
        this.listName = listName;
    }

    public ObservableList<Task> addItem(Task task) {
        // add the task parameter to the list
        // return the list
        list.add(task);
        return list;
    }

    public ObservableList<Task> deleteItem(Task task) {
        // remove the task parameter from the list
        // return the list
        list.remove(task);
        return list;
    }

    public void editDescription(String des) {
        // create a new string that gets the selected value by the user
        // use the function setDescription() from the Task class to set it to the new value
        // using getNewValue() from the table column
        description = des;

    }

    public DatePicker editDueDate() {
        // create a new DatePicker that gets the selected value by the user
        // use the function setDueDate() from the Task class to set it to the new value
        // using getNewValue() from the table column
        return new DatePicker();
    }

    public boolean editIsCompleted() {
        // create a new CheckBox that gets the selected value by the user
        // use the function setIsCompleted from the Task class to set it to the new value
        // using getNewValue() from the table column
        return true;
    }

    @FXML
    public ObservableList<Task> addAll() {
        // create a for each loop of Task objects
        // add all of the tasks in the list to the tableview
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        return list;
    }

    @FXML
    public ObservableList<Task> addCompleted()
    {
        // use the checked property to check if the item in the list is checked or not
        // for(List list: O
        // if the item is checked by the user
        //add the task in the list to the table view
        completed.clear();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsCompleted() == true) {
                completed.add(count, list.get(i));
                count++;
            }
        }
        return completed;
    }

    @FXML
    public ObservableList<Task> addIncomplete() {
        // use the checked property to check if the item in the list is checked or not
        // create a for each loop that goes through the entire list
        // if the item is not checked by the user
        //add the task in the list to the table view
        incomplete.clear();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getIsCompleted()) {
                incomplete.add(count, list.get(i));
                count++;
            }
            }
            return incomplete;
    }
}
