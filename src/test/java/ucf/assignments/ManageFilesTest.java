/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Sriharsha Aitharaju
 */
package ucf.assignments;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ManageFilesTest {


    @RepeatedTest(3)
    void importList() throws IOException {
        // create a new manager
        // fill up manager with tasks
        // create a second manager and a manage files
        // create a new file to compare it to
        // compare the contents of both managers
        ListManager manager = new ListManager();
        manager.addItem(new Task("Eat Grass", LocalDate.parse("2020-02-10"), true));
        manager.addItem(new Task("Food", LocalDate.parse("2020-02-09"), false));
        ListManager manager2 = new ListManager();
        ManageFiles files = new ManageFiles();
        files.importList(new File("C:\\Users\\harsh\\TaskApplication\\src\\main\\java\\ucf\\assignments\\xyz.txt"), manager2);
        for (int i = 0; i < manager.getList().size(); i++) {

            assertEquals(manager.getList().get(i).getDescription(),manager2.getList().get(i).getDescription());
        }
    }


    @RepeatedTest(3)
    void exportList()
    {
        // create a String variable that is the expected name of the file
        // create a new String variable and initialize it by calling the exportList() function from the ManageFiles class
        // use assertEquals() to compare if the filenames(Strings) are the same
        ManageFiles manageFiles = new ManageFiles();
        Task t =  new Task("Eat Grass", LocalDate.parse("2020-02-10"), true);
        String text = manageFiles.exportList(t);
        String exp = "Eat Grass,2020-02-10,yes\n";
        assertEquals(exp, text);

    }

}
