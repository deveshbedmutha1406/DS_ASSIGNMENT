import ReverseModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
public class Server{
    public static void main(String args[]){
        try{
            ORB orb = ORB.init(args, null);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            NamingContextExt nc_ref = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            ReverseImpl obj = new ReverseImpl();
            org.omg.CORBA.Object tmp_obj_ref = rootPOA.servant_to_reference(obj);
            Reverse h_ref = ReverseHelper.narrow(tmp_obj_ref);

            String name = "Server";
            NameComponent path[] = nc_ref.to_name(name);
            nc_ref.rebind(path, h_ref);
            orb.run();
            System.out.println("server initiated");
        }catch(Exception e){

        }
     
    }
}
