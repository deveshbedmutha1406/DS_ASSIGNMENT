import java.rmi.*;

public class server{
    public static void main(String args[]){
        try{
            Impl obj = new Impl();
            Naming.rebind("Server", obj);
            System.out.println("Server Loaded");
        }catch(Exception e){

        }
    }
}