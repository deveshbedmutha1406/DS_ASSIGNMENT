import java.util.*;

public class Ring{
    int max_process;
    int cordinator;
    boolean process[];
    static ArrayList<Integer>Pid;

    Ring(int max){
        max_process = max;
        cordinator = max;
        process = new boolean[max];
        Pid = new ArrayList<Integer>();
        for(int i = 0;i<max; i++){
            process[i] = true;
            System.out.println("Process Created : " + (i + 1));
        }
    };

    public static void showArray(){
        System.out.print("[ ");
        for(Integer x: Pid){
            System.out.print(x + ", ");
        }
        System.out.print("]\n");
    };

    public void upProcess(int pid){
        if(process[pid - 1]){
            System.out.println("Already Active");
        }else{
            process[pid - 1] = true;
            System.out.println("Active Success: " + pid);
        }
    };

    public void downProcess(int pid){
        if(process[pid - 1]){
            process[pid - 1] = false;
            System.out.print("Deactivated " + pid);            
        }else{
            System.out.print("Already Deactivated");
        }
    };

    public void runElection(int pid){
        int temp = pid;
        Pid.add(temp);
        System.out.print("Process " + temp + " Sending: ");
        showArray();
        while(temp != pid - 1){
            if(process[temp]){
                Pid.add(temp+1);
                System.out.println("Process " + (temp+1) + " Sending: ");
                showArray();
            }
            temp = (temp + 1)%max_process;
        }
        cordinator = Collections.max(Pid);
        System.out.println("Cordinator is : " + cordinator);
        Pid.clear();
    }

    public static void displayMenu(){
        System.out.println("1. Create Processes");
        System.out.println("2. Down A Process");
        System.out.println("3. Up A Process");
        System.out.println("4. Run Election");
        System.out.println("5. Exit Program");
        System.out.print("Enter Choice : ");
    }

    public static void main(String args[]){
        int choice;
        int pid;
        Ring obj = null;
        Scanner sc = new Scanner(System.in);
        while(true){
            displayMenu();
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter N: ");
                    pid = sc.nextInt();
                    obj = new Ring(pid);
                    break;
                case 2:
                    System.out.print("Enter Pid: ");
                    pid = sc.nextInt();
                    obj.downProcess(pid);
                    break;
                case 3:
                    System.out.print("Enter Pid: ");
                    pid = sc.nextInt();
                    obj.upProcess(pid);
                    break;            
                case 4:
                    System.out.print("Enter N To Start election");
                    pid = sc.nextInt();
                    obj.runElection(pid);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Choice");
                    break;
                
            }
        }
    }

}