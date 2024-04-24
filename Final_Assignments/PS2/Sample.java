import mpi.*;

public class Sample{
    public static void main(String args[]){
        MPI.Init(args); // initialize
        int me = MPI.COMM_WORLD.Rank(); // core number
        int size = MPI.COMM_WORLD.Size();   // number of cores.
        int noElements = 20;

        int[] arr = new int[noElements];   // actual array
        if(me == 0){
            for(int i = 1;i<=noElements;i++){
                arr[i-1] = i;
            }
        }
        int n = arr.length;
        int[] narray = new int[]{n};

        MPI.COMM_WORLD.Bcast(narray, 0, 1, MPI.INT, 0); // broadcast size to all
        int n1 = narray[0];

        int localArraySize = n1/size;
        int []localArray = new int[localArraySize];

        MPI.COMM_WORLD.Scatter(arr, 0, localArraySize, MPI.INT, localArray, 0, localArraySize, MPI.INT, 0); // scatter data
        int localSum = 0;
        for(int i =0;i<localArraySize; i++){
            System.out.println("Process " + me + " Element " + localArray[i]);
            localSum += localArray[i];
        }
        System.out.println("Local SUm BY Process " + me + " " + localSum);

        int []partialSum = new int[size];
        MPI.COMM_WORLD.Gather(new int[]{localSum}, 0, 1, MPI.INT, partialSum, 0, 1, MPI.INT, 0);    // collect back sum
        if(me == 0){
            int final_sum = 0;
            for(int i =0;i<size;i++){
                final_sum += partialSum[i];
            }   
            System.out.println("Final Sum : " + final_sum); // show sum;
        }
    }
}