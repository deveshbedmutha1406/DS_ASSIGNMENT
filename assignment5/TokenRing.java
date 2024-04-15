import java.util.*;
public class TokenRing{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes you want in the ring: ");
        int n = sc.nextInt();

        System.out.println("Ring Formed Is As Below: ");
        for(int i = 0;i<n; i++){
            System.out.print(i + " ");
        }
        System.out.println("0");
        
        int choice = 0;

        do{
            System.out.print("Enter Sender Node: ");
            int sender = sc.nextInt();

            System.out.print("Enter Receiver Node: ");
            int receiver = sc.nextInt();   

            System.out.print("Enter Message: ");
            String message = sc.next();

            int token = 0;
            System.out.println("Token Passing: ");
            for(int i = token; i<sender; i++){
                System.out.println(i + " -> " + (i+1));
                token = i+1;
            }
            System.out.println("Sender: " + sender + " Sending Data: "+message);
            for(int i = sender; i != receiver; i = (i+1)%n){
                System.out.println("Data Forwarding from: "+i + " -> " + (i+1)%n);
                token = (i+1)%n;
            }
            System.out.println("Receiver " + receiver + " Received Data: "+ message);

            token = sender; // update value to sender once data is received

            System.out.println("Do you want to send more data? (1/0): ");
            choice = sc.nextInt();
        }while(choice == 1);
    }
}