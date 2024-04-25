import java.util.*;
public class Bully{
    int cordinator;
    int  max_process;
    boolean processes[];

    Bully(int max){
        System.out.println("Initializing Process");
        cordinator = max;
        max_process = max;
        processes = new boolean[max];
        for(int i =0;i<max_process;i++){
            processes[i] = true;
            System.out.println("Process " + (i + 1) + " Created ");
        }

    }

    public void displayProcess(){
        for(int i =0;i<max_process;i++){
            if(processes[i]){
                System.out.println("Process " + (i + 1) + " Active ");
            }else{
                System.out.println("Process " + (i + 1) + " Down ");
            }
        }
        System.out.println("Cordinator is " + cordinator);
    }

    public void downProcess(int pid){
        if(processes[pid-1]){
            System.out.println("Process Made Down");
            processes[pid - 1] = false;
        }else{
            System.out.println("Already down");
        }
    }

    public void upProcess(int pid){
        if(processes[pid - 1]){
            System.out.println("Already Up");
        }else{
            processes[pid - 1] = true;
            System.out.println("Made Up");
        }
    }

    public void performElection(int pid){
        cordinator = pid;
        boolean flag = true;

        for(int i = pid; flag != false && i<max_process; i++){
            System.out.println("Process " + pid + " sending to " + (i + 1));
            if(processes[i]){
                flag = false;
                performElection(i + 1);
            }
        }
    }

    public static void displayMenu(){
        System.out.println("1. Init");
        System.out.println("2. Down A Process");
        System.out.println("3. Up A Process");
        System.out.println("4. Perform Election");
        System.out.println("5. Exit");
        System.out.println("Please Select From Above Option");
    }

    public static void main(String args[]){
        int choice = 0;
        Bully obj = null;
        int pid = 0;
        int max_process = 0;
        Scanner sc = new Scanner(System.in);
        while(true){
            displayMenu();
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Max Process : ");
                    max_process = sc.nextInt();
                    obj = new Bully(max_process);
                    break;
                case 2:
                    System.out.print("Enter Process To DOwn: ");
                    pid = sc.nextInt();
                    obj.downProcess(pid);
                    break;
                case 3: 
                    System.out.print("Enter Process To up: ");
                    pid = sc.nextInt();
                    obj.upProcess(pid);
                    break;
                case 4:
                    System.out.print("Where to start election : ");
                    pid = sc.nextInt();
                    obj.performElection(pid);
                    obj.displayProcess();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Valid Option");
                    break;
            }
        }
    }
}