import java.rmi.*;

interface ServerInt extends Remote{
    public double Addition(double a, double b) throws RemoteException;
    public double Subtraction(double a, double b) throws RemoteException;
    public double Multiplication(double a, double b) throws RemoteException;
    public double Division(double a, double b) throws RemoteException;
}