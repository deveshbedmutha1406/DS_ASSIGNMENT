import java.rmi.*;
import java.rmi.server.*;
public class Impl extends UnicastRemoteObject implements Intf{
    Impl() throws RemoteException{
        System.out.println("Object Created");
    }

    public int count_vowels(String name) throws RemoteException{
        int ans = 0;
        for(int i = 0;i<name.length(); i++){
            char tmp = name.charAt(i);
            if(tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u'){
                ans += 1;
            }
        }
        return ans;
    }

}