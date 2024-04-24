import java.rmi.*;

interface Intf extends Remote{
    int count_vowels(String name) throws RemoteException;
};