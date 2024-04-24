import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;

public class Client{
    public static void main(String args[]){
        try{
            ORB orb = ORB.init(args, null);
            NamingContextExt nc_ref = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            Reverse obj = (Reverse)nc_ref.resolve_str("Server");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            System.out.println(obj.reverse_string(name));
        }catch(Exception e){

        }
    }
}