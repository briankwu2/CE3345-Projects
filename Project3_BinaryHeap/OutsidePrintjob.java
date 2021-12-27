// Subclass of Printjob
// Adds a printcost associated with the printjob.
// Author: Brian Wu



public class OutsidePrintjob extends Printjob{
    // Print cost
    private double printCost;

    // Default Constructor
    OutsidePrintjob()
    {
        super();
        printCost = 0;
    }
    
    //Overloaded Constructor
    OutsidePrintjob(String userName, int userPriority, int numPages)
    {
        super(userName,userPriority,numPages); // calls the Printjob constructor
        this.printCost = numPages * 0.10; // Calculates the print cost
    }

    //Print all info
    @Override
    public void printInfo()
    {
        super.printInfo();
        System.out.print("printCost is: $");
        System.out.printf("%.2f", printCost);
        System.out.println();
    }


}
