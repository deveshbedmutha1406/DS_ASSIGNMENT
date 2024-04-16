import java.util.*;

public class Bully{
    int coodinator;
    int max_processes;
    boolean processes[];

    public Bully(int n){
        max_processes = n;
        processes = new boolean[max_processes];
        coodinator = n;

        System.out.println("Creating Processes: ");
        for(int i =0;i<n; i++){
            processes[i] = true;
            System.out.println("Process "+ (i + 1)+ " created");
        }
        System.out.println("Process P "+ coodinator+ " is the coordinator");
    }

    void displayProcesses(){
        for(int i =0;i<max_processes; i++){
            if(processes[i]){
                System.out.println("Process "+ (i + 1)+ " is active");
            }else{
                System.out.println("Process "+ (i + 1)+ " is inactive");
            }
        }
        System.out.println("Process P "+ coodinator+ " is the coordinator");
    }

    void upProcess(int pid){
        if(!processes[pid-1]){
            processes[pid - 1] = true;
            System.out.println("Process "+ pid+ " is now active");
        }else{
            System.out.println("Process "+ pid+ " is already active");
        }
    }

    void downProcess(int pid){
        if(processes[pid-1]){
            processes[pid - 1] = false;
            System.out.println("Process "+ pid+ " is now inactive");
        }else{
            System.out.println("Process "+ pid+ " is already inactive");
        }
    }

    void runElection(int pid){
        coodinator = pid;
        boolean keepGoing = true;

        for(int i = pid; i<max_processes && keepGoing; i++){
            System.out.println("Process "+pid + " to process " + (i+1) + ": Are you alive?");
            if(processes[i]){
                keepGoing = false;
                runElection(i+1);
            }
        }
    }

    public static void main(String args[]){
        Bully bully = null;
        int max_processes = 0, pid=0;
        int choice = 0;

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Bully Algorithm");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice:- ");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Enter the number of processes you want to create: ");
                    max_processes = sc.nextInt();
                    bully = new Bully(max_processes);
                    break;
                case 2:
                    bully.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter the process id you want to up: ");
                    pid = sc.nextInt();
                    bully.upProcess(pid);
                    break;
                case 4:
                    System.out.print("Enter the process id you want to down: ");
                    pid = sc.nextInt();
                    bully.downProcess(pid);
                    break;
                case 5:
                    System.out.print("Enter the process id you want to start election from: ");
                    pid = sc.nextInt();
                    bully.runElection(pid);
                    bully.displayProcesses();
                    break;
                case 6:
                    System.out.println("Exiting Program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

}