import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInt{
    public ServerImpl() throws RemoteException{
    }
    public double Addition(double a, double b) throws RemoteException{
        return a + b;
    }

    public double Subtraction(double a, double b) throws RemoteException{
        return a - b;
    }

    public double Multiplication(double a, double b) throws RemoteException{
        return a * b;
    }
    public double Division(double a, double b) throws RemoteException{
        if(b != 0){
            return a / b;
        }
        System.out.println("Division by zero");
        return 1/1;
    }
}