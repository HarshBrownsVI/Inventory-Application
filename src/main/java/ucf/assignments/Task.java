package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Task
{
    // create a String to store the description
    String description;
    // create a date picker variable to the due date
    LocalDate dueDate;
    // create a boolean variable to store whether the task is completed or not
    // is completed or not
    CheckBox isCompleted;

    public Task(String description, LocalDate dueDate, CheckBox isCompleted)
    {
        // set the description parameter equal to the description field
        this.description = description;
        // set the dueDare parameter equal to the dueDate field
       this.dueDate = dueDate;
        // set the isCompleted parameter to be equal the field is Completed
        this.isCompleted = isCompleted;
    }
    public void setDescription(String description)
    {
        // set this.description equal to the field in the class
        this.description = description;
    }
    public void setDueDate(LocalDate dueDate){
        // set the due date equal to the due date field in the class
        this.dueDate = dueDate;
    }
    /*public StringProperty descriptionProperty() {
        if (description == null) description = new SimpleStringProperty(this, "firstName");
        return description;
    }*/
    public String getDescription(){
        // return the description field
        return description;
    }
    public LocalDate getDueDate(){
        // return the date field
            return dueDate;
    }
    public boolean getIsCompleted()
    {
         return isCompleted.isSelected();
    }
    public void setIsCompleted(boolean completed)
    {
        // set the completed parameter equal to the isCompleted field
        this.isCompleted = isCompleted;
    }
    public String toString(){
        return description + " " + dueDate;
    }


}
