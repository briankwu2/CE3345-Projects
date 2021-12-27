/* Project 5
CS 3345.002
Author: Brian Wu
Due Date: 12/04/21
// Uses BinaryHeap.java prioQueue from textbook source code.


*/

//Imports
import java.util.*;
import java.io.*;

public class Kruskals
{

    // Edge Class
    public static class Edge implements Comparable<Edge>
    {
        private int v1 = -1;
        private int v2 = -1;
        private int weight = -1;
        Edge()
        {
            
        }

        // Overloaded Constructor
        Edge(int v1, int v2, int weight)
        {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        // Getters
        public int getV1 ()
        {
            return v1;
        }
        
        public int getV2 ()
        {
            return v2;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge e) // Only care if the edge is equal or not equal
        {

            if (((this.v1 == e.getV1() &&  this.v2 == e.getV2()) || (this.v1 == e.getV2() || this.v2 == e.getV1())) && this.weight == e.getWeight())
            {
                return 0;
            }
            
                if (this.weight < e.getWeight())
                    return -1;
                else
                    return 1;
            

        } 



    }


    public static ArrayList<Edge> kruskals(ArrayList<Edge> edgeList, int numVertices)
    {
        DisjSets disjoint = new DisjSets(numVertices);
        Edge [] edgeArray = new Edge[edgeList.size()];
        edgeArray = edgeList.toArray(edgeArray);
        BinaryHeap<Edge> prioQueue = new BinaryHeap<Edge>(edgeArray);
        ArrayList<Edge> acceptEdges = new ArrayList<>();

        while (acceptEdges.size() != numVertices - 1)
        {
            Edge e = prioQueue.deleteMin();
            int v1Set = disjoint.find(e.getV1());
            int v2Set = disjoint.find(e.getV2());

            if (v1Set != v2Set)
            {
                // Accept the edge if there is not a cyclic route
                acceptEdges.add(e);
                disjoint.union(v1Set,v2Set);

            }
        }

        return acceptEdges;
    }

    public static void main(String[] args) throws FileNotFoundException
    {

        Scanner scanFile = new Scanner(new File("assn9_data.csv"));
        ArrayList<Edge> edgeList = new ArrayList<>();
        HashMap<Integer, String> intToCityHash = new HashMap<>();
        HashMap<String, Integer> cityToIntHash = new HashMap<>();
        
        // Create hashmap for cities and assign keys to each value.
        int counter = 0;
        while (scanFile.hasNext())
        {
            String line  = scanFile.nextLine();
            String [] lineArray = line.split(","); // Splits the line into an array
            intToCityHash.put(counter, lineArray[0]); // Hashes city name
            cityToIntHash.put(lineArray[0], counter); // Hashes int

            counter++; // Increment counter/key
        }

        scanFile.close();
        Scanner scanFile2 = new Scanner(new File("assn9_data.csv"));

        while (scanFile2.hasNext())
        {
            String line  = scanFile2.nextLine();
            String [] lineArray = line.split(","); // Splits the line into an array
            for (int i = 1; i < lineArray.length; i+=2) // Goes in pairs for weight and vertice
            {
                Edge e = new Edge(cityToIntHash.get(lineArray[0]),cityToIntHash.get(lineArray[i]), Integer.parseInt(lineArray[i+1])); // Creates edge with weight and vertices
                boolean multiple = false;
                for (int j = 0; j < edgeList.size();j++)
                {
                    if (edgeList.get(j).compareTo(e) == 0)
                    {
                        multiple = true;
                        break;
                    }
                }
                if (!multiple) // If there is no copy of the edge already in the edgelist, then add it
                    edgeList.add(e); // Adds the edge into the edge list
            }
        }

        ArrayList<Edge> minSpan = kruskals(edgeList, counter);
        int totalDistance = 0; 

        for (int i = 0; i < minSpan.size(); i++)
        {
            String v1, v2;
            v1 = intToCityHash.get(minSpan.get(i).getV1());
            v2 = intToCityHash.get(minSpan.get(i).getV2());
            int edgeWeight = minSpan.get(i).getWeight();
            System.out.println("("+ v1 + ", " + v2 + ")" + " has distance of " + edgeWeight);
            totalDistance += edgeWeight;
        }

        System.out.println("Total distance is: " + totalDistance);

        
    }
}