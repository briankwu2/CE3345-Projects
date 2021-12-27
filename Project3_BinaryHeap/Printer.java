// Project 3 Printer file
// Author: Brian Wu


// Another class called Printer should read an input file and create objects
//    for each entry.  These objects should be added to a priority queue
//    using the textbook's BinaryHeap class (unmodified).  

//    The input file contains each job to print on a separate line, with tabs between 
//    the fields.  The fields are name, user priority, pages, and a flag indicating 
//    inside or outside job (I or O).

//    Once the file is read and the print jobs have been added to the binary heap, 
//    the Printer object should deleteMin each job and print its user's name, user priority, 
//    and pages to the screen.  OutsidePrintjobs should also show their cost.
/*
Steps for finishing this project.
1. Test reading and separating a line of input text. [X]
    a. Make sure that all the variables are properly converted into their respective types. [X]
2. Create Printerjob or OutsidePrinterjob objects as needed, and insert them into a BinaryHeap structure. [X]
    a. Double check that the compareTo method works for both Printerjob and OutsidePrinterjob objects. [X]
    b. Double check that insert and deleteMin methods work properly for the BinaryHeap structure.[X]
3. Loop deleteMin until the binaryHeap is empty.
    a. For each object returned, print userName, userPriority, numPages, and for outsidePrintjobs, print cost. [X}]





*/

import java.util.*;
import java.io.*;

public class Printer
{
    public static void main(String[] args) {
        // Primary testing for classes
        // Printjob p1 = new Printjob("bob", 5, 2);
        // p1.printInfo();
        // System.out.println("--------------------------");

        // Printjob p2 = new Printjob("dude", 2, 1);
        // p2.printInfo();
        // System.out.println("--------------------------");

    
        // if (p1.compareTo(p2) > 0)
        // {
        //     System.out.println("p1 is bigger than p2");
        // }
        // else if (p1.compareTo(p2) < 0)
        // {
        //     System.out.println("p1 is less than p2");
        // }

        // if (p1.compareTo(p1) == 0)
        //     System.out.println("p1 is p1");

        // OutsidePrintjob op1 = new OutsidePrintjob("sally", 2, 5);
        // op1.printInfo();


        // System.out.println("op1 compared to p1 is: " + op1.compareTo(p1));
        // System.out.println("op1 compared to p2 is: " + op1.compareTo(p2));
        // -----------------------------------------------------------------------
        
        BinaryHeap<Printjob> printerQueue = new BinaryHeap<>(); // Creates a printer queue structure

        // Reading in printer queue.
        try
        {
            System.out.println("Reading in input file...");
            // Read in file
            Scanner scan = new Scanner(new File("input.txt"));
            while (scan.hasNext())
            {
                String nameOfUser = scan.next();
                int priorityOfUser = Integer.parseInt(scan.next());
                int numPagesOfUser = Integer.parseInt(scan.next());
                char classFlag =  scan.next().charAt(0);

                // If the classflag is a regular printjob.
                if (classFlag == 'I') 
                {
                    Printjob tempPJ = new Printjob(nameOfUser, priorityOfUser, numPagesOfUser);
                    // tempPJ.printInfo(); For testing purposes
                    printerQueue.insert(tempPJ); // Insert the object into the printerQueue
                }
                // If the classflag is a outside printjob.
                else if (classFlag == 'O')
                {
                    OutsidePrintjob tempOutsidePJ = new OutsidePrintjob(nameOfUser, priorityOfUser, numPagesOfUser);
                    // tempOutsidePJ.printInfo();
                    printerQueue.insert(tempOutsidePJ); // Insert the object into the printerQueue
                }
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }

        // Emptying binary heap/printer queue
        System.out.println("Beginning print queue order from highest priority to lowest priority");
        System.out.println("-----------------------");
        


        while (!printerQueue.isEmpty())
        {
            Printjob temp = printerQueue.deleteMin();
            temp.printInfo(); // Prints the info
            System.out.println("-----------------------");
        }
       
        System.out.println("Done!");
        
        
            


    }

}