/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */
package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Task
{
    // create a String to store the description
    SimpleStringProperty description;
    // create a date picker variable to the due date
    LocalDate dueDate;
    // create a boolean variable to store whether the task is completed or not
    // is completed or not
    boolean isCompleted;

    public Task(String description, LocalDate dueDate, boolean isCompleted)
    {
        // set the description parameter equal to the description field
        this.description = new SimpleStringProperty(description);
        // set the dueDare parameter equal to the dueDate field
       this.dueDate = dueDate;
        // set the isCompleted parameter to be equal the field is Completed
        this.isCompleted = isCompleted;
    }
    public void setDescription(String des)
    {
        // set this.description equal to the field in the class
        description.set(des);
    }
    public void setDueDate(LocalDate dueDate){
        // set the due date equal to the due date field in the class
        this.dueDate = dueDate;
    }

    public String getDescription(){
        // return the description field
        return description.get();
    }
    public LocalDate getDueDate(){
        // return the date field
            return dueDate;
    }
    
    // return yes if the task is completed, otherwise no
    public String getIsCompleted()
    {
        if(isCompleted)
            return "yes";
        return "no";
    }
   
    // check to see whether the date is valid or not
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(dueDate.toString());
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    // return the item 
    public String toString(){
        return description + "," + dueDate + "," + isCompleted;
    }


}
