package ucf.assignments;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageFiles
{
    // initialize todo list
    @FXML
    public ObservableList<Task> list = FXCollections.observableArrayList();
    // Create a new file chooser

    public ObservableList<Task> importList(File filename, ListManager manager) throws IOException
    {
        // Create a new ObservableList of Tasks
        // create a try catch
            // create a new bufferedReader of the filename to read the file
            // create a new string to read the line
            // while there are more lines to read
            // read the line and split the data by comma
            // add the data to the list by creating a new Task of elements of the array
        //catch
        // if the path is not found, throw an exception
        // use the method printStackTrace() to print an error message
        // return the list
        //ObservableList<Task> tasks = FXCollections.observableArrayList();
        manager.getList().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))

        {
            boolean b;
            String line;
            while ((line = br.readLine()) != null)
            {
                // process the line
                System.out.println(line);
                String data[] = line.split(",");
                boolean checked = false;
                checked = data[2].equals("yes") ? true : false;
                Task t = new Task(data[0], LocalDate.parse(data[1]), checked);
               manager.addItem(t);
            }

        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }


        return manager.getList();
        }
       

    public String exportList(Task t)
    {
       // create a new String variable
        // initialize the variable to the data in each row(description, due date, completed?)
       // return the string variable
        String text = "";

        text = t.getDescription() + "," + t.getDueDate() + "," + t.getIsCompleted() + "\n";
        list.add(t);
        return text;
    }


}
