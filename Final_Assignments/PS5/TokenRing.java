import java.util.*;

public class TokenRing{

    public static void display(){
        System.out.println("1. Initialize");
        System.out.println("2. Perform Token Ring");
        System.out.println("3. Exit");
    }

    public static void performToken(int process, int st, int en){
        Scanner sc = new Scanner(System.in);
        int token = 0;  // initially token at 0th process.
        for(int i = 0;i<st;i++){
            System.out.println("Token Passing From " + i + " to " + (i + 1));
            token += 1;
        }
        System.out.println("Process " + st + " Recv Token");
        System.out.println("Enter Data To Be Shared : ");
        String data = sc.nextLine();
        for(int i = st; i != en; i = (i + 1)%process){
            System.out.println("Data Transfer From " + i + " to " + ((i + 1)%process));
        }
        System.out.println("Data Recv By Process " + en + " " + data);
    }

    public static void displayProcess(int p){
        for(int i = 0;i<p;i++){
            System.out.println("Process Created : " + i);
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n;
        int process = 0;
        while(true){
            display();
            n = sc.nextInt();
            switch(n){
                case 1:
                    System.out.print("Enter Process: ");
                    process = sc.nextInt();
                    displayProcess(process);
                    break;
                case 2:
                    System.out.println("Enter St and En Numbers");
                    int st = sc.nextInt();
                    int end = sc.nextInt();
                    performToken(process, st, end);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Valid Option");
                    break;
            }
        }

    }
}