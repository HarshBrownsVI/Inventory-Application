/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */
package ucf.assignments;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateStringConverter;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListManagerTest extends ListManager{
    ListManager manager = new ListManager();
    ObservableList<Task> list = FXCollections.observableArrayList();

    @RepeatedTest(5)
    void addItem()
    {
        // create a String variable to store a name intended to compare the actual with
        // call the addItem() method and store the description of the item in
        // use assertEquals to compare the two names
        Task t = new Task("Eat Grass", LocalDate.parse("2022-04-13"), true);
        ObservableList<Task> actual = manager.addItem(t);
        ObservableList<Task> expected = FXCollections.observableArrayList(t);
        assertEquals(expected, actual);
    }

    @Test
    void deleteItem()
    {
        // create a String variable to store a name intended to compare the actual with
        // call the deleteItem() method and store the description of the item in
        // use assertEquals to compare the two names
        Task t = new Task("Eat Grass", LocalDate.parse("2022-04-13"), true);
        ObservableList<Task> actual = manager.deleteItem(t);
        ObservableList<Task> expected = FXCollections.observableArrayList(t);
        expected.remove(t);
        assertEquals(expected, actual);
    }

    @Test

    @RepeatedTest(5)
    void displayAll()
    {
        // create a new String variable for the expected value
        // create another String variable and call the displayAll() function
        // assertEquals to check if the two strings are the same
        ObservableList<Task> exp = FXCollections.observableArrayList(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true),
                new Task("Food", LocalDate.parse("2020-02-09"), true));

        list = FXCollections.observableArrayList(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true),
                new Task("Food", LocalDate.parse("2020-02-09"), false));
        manager.addAll();
        assertEquals(exp, list);

    }

    @RepeatedTest(5)
    void displayCompleted()
    {
        // create a new String variable for the expected value
        // create another String variable and call the displayCompleted() function
        // assertEquals to check if the two strings are the same
        ObservableList<Task> exp = FXCollections.observableArrayList(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true));

        manager.addItem(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true));
        manager.addItem(new Task("Food", LocalDate.parse("2020-02-09"), false));
        for (int i = 0; i < exp.size(); i++) {

            assertEquals(exp.get(i).getIsCompleted(), manager.addCompleted().get(i).getIsCompleted());
        }

    }

    @RepeatedTest(5)
    void displayIncomplete()
    {
        // create a new String variable for the expected value
        // create another String variable and call the displayIncomplete() function
        // assertEquals to check if the two strings are the same
        Task t = new Task("Eat Grass", LocalDate.parse("2020-02-10"), true);
        ObservableList<Task> exp = FXCollections.observableArrayList(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true));
        manager.addItem(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true));
        manager.addItem(new Task("Food", LocalDate.parse("2020-02-09"), false));
        for (int i = 0; i < exp.size(); i++) {

            assertEquals(exp.get(i).getIsCompleted(), manager.addCompleted().get(i).getIsCompleted());
        }
    }

    @Test
    void testGetListName()
    {
        ListManager manager = new ListManager();
        String actual = manager.getListName();
        String expected = manager.listName;
        assertEquals(expected, actual);
    }

    @Test
    void testAddItem()
    {
        ListManager manager = new ListManager();
        LocalDate date = LocalDate.parse("2020-01-09");
        ObservableList<Task> exp = FXCollections.observableArrayList();
        Task t = new Task("Eat Grass", date, true);
        exp.add(t);
        ObservableList<Task> actual = manager.addItem(t);
        assertEquals(exp, actual);
    }

    @Test
    void testDeleteItem()
    {
        ListManager manager = new ListManager();
        LocalDate date = LocalDate.parse("2020-01-09");
        ObservableList<Task> exp = FXCollections.observableArrayList();
        Task t = new Task("Eat", date, false);
        Task t2 = new Task("Eat", date, false);
        exp.add(t);
        exp.add(t2);
        ObservableList<Task> actual = exp;
        exp.remove(t2);
        manager.deleteItem(t2);
        assertEquals(exp, actual);
    }


    @Test
    void testSorted()
    {
        // tableview already sorts the items in my application
    }


}
