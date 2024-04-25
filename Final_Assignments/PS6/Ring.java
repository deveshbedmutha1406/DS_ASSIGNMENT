import java.util.*;

public class Ring{
    int cordinator;
    int max_process;
    boolean processes[];
    ArrayList<Integer>arr;
    Ring(int max){
        cordinator = max;
        max_process = max;
        processes = new boolean[max];
        for(int i =0;i<max_process;i++){
            System.out.println("Process " + (i + 1) + " created");
            processes[i] = true;
        }
        System.out.println("Cordinator is : " + cordinator);
        arr = new ArrayList<>();
    }   

    public static void displayMenu(){
        System.out.println("1. Init");
        System.out.println("2. Down A Process");
        System.out.println("3. Up A Process");
        System.out.println("4. Perform Election");
        System.out.println("5. Exit");
        System.out.println("Please Select From Above Option");
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

    public void showList(){
        System.out.print("[ ");
        for(Integer e: arr){
            System.out.print(e + ", ");
        }
        System.out.print(" ]\n");
    }

    public void runElection(int pid){
        int temp = pid;
        arr.add(pid);
        System.out.println("Process " + temp + " Sending ");
        showList();
        while(temp != pid - 1){
            if(processes[temp]){
                arr.add(temp + 1);
                System.out.println("Process " + (temp + 1));
                showList(); 
            }
            temp = (temp + 1)%max_process;
        }
        cordinator = Collections.max(arr);
        System.out.println("Cordinator is " + cordinator);
        arr.clear();
    }

    public static void main(String args[]){
        int pid = 0;
        int choice = 0;
        Ring obj = null;
        Scanner sc = new Scanner(System.in);

        while(true){
            displayMenu();
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Processes : ");
                    pid = sc.nextInt();
                    obj = new Ring(pid);
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
                    System.out.print("where to start election : ");
                    pid = sc.nextInt();
                    obj.runElection(pid);
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