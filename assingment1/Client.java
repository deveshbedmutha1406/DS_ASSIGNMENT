import java.rmi.*;
import java.util.*;

public class Client{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            String serverUrl = "rmi://localhost/Server";
            ServerInt serverintf = (ServerInt)Naming.lookup(serverUrl); // check if object is present in rmi registry or not

            System.out.print("Enter first numbers: ");
            double a = sc.nextDouble();
            System.out.print("Enter second numbers: ");
            double b = sc.nextDouble();

            System.out.println("Addition: " + serverintf.Addition(a, b));
            System.out.println("Subtraction: " + serverintf.Subtraction(a, b));
            System.out.println("Multiplication: " + serverintf.Multiplication(a, b));
            System.out.println("Division: " + serverintf.Division(a, b));
        }catch(Exception e){
            System.out.println("Exception at Client class: " + e.getMessage());
        }
    }
}