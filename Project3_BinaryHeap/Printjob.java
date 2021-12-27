// Printjob class
// Used for Project 3 of CE 3345.002
// Author: Brian Wu




public class Printjob implements Comparable<Printjob> {

    private String userName;
    private int userPriority;
    private int numPages;

    // Default Constructor 
    Printjob()
    {
        userName = null;
        userPriority = 0;
        numPages = 0;
    }
    
    // Overloaded constructor
    Printjob(String userName, int userPriority, int numPages)
    {
        this.userName = userName;
        this.userPriority = userPriority;
        this.numPages = numPages;
    }

    //PrintMethod
    public void printInfo()
    {
        System.out.println("userName is: " + userName);
        System.out.println("userPriority is: " + userPriority);
        System.out.println("numPages is: " + numPages);
    }
    // Override compareTo for use in BinaryHeap.
    @Override
    public int compareTo(Printjob job2)
    {
        int thisJobPriority = this.numPages * this.userPriority;
        int jobPriority = job2.numPages * job2.userPriority;

        // Returns < 0 if this(printjob) is less than job2
        if (thisJobPriority < jobPriority)
        {
            return -1;
        }
        // Returns > 0 if this(printjob) is greater than job2
        else if (thisJobPriority > jobPriority)
        {
            return 1;
        }
        else // If they are equal return 0.
        {
            return 0;
        }

    }


}
