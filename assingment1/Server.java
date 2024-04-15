import java.rmi.*;

public class Server{
    public static void main(String[] args) {
        try{
            ServerImpl serverimpl = new ServerImpl();
            Naming.rebind("Server", serverimpl);    // we refer using Server
            System.out.println("Server is ready and running fine");
        }catch(Exception e){
            System.out.println("Exception at Server class: " + e.getMessage());
        }
    }
}