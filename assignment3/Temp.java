import java.io.*;
import mpi.*;
public class Temp{
    public static void main(String args[]) throws Exception{
        MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // Define the array of numbers
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Example array
        int n = array.length;
        
        // Broadcast the size of the array to all processes
        int[] nArray = new int[] {n};
        MPI.COMM_WORLD.Bcast(nArray, 0, 1, MPI.INT, 0);
        n = nArray[0];
        
          // Divide the array among processes
        int localArraySize = n / size;
        int[] localArray = new int[localArraySize];

        MPI.COMM_WORLD.Scatter(array, 0, localArraySize, MPI.INT, localArray, 0, localArraySize, MPI.INT, 0);

          // Compute partial sum
        int localSum = 0;
        for (int i = 0; i < localArraySize; i++) {
            localSum += localArray[i];
            System.out.println("Process " + me + " local element: " + localArray[i]);
        }

            // Gather partial sums to root process
        int[] partialSums = new int[size];
        MPI.COMM_WORLD.Gather(new int[] {localSum}, 0, 1, MPI.INT, partialSums, 0, 1, MPI.INT, 0);
          // Compute total sum on root process
        if (me == 0) {
            int totalSum = 0;
            for (int partialSum : partialSums) {
                totalSum += partialSum;
            }
            System.out.println("Total sum: " + totalSum);
        }



        MPI.Finalize();
    }
}