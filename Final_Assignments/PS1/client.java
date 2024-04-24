import java.rmi.*;
import java.util.*;
import java.io.*;
import java.lang.String;
public class client{
    public static void main(String args[]){
        try{
            String url = "rmi://localhost/Server";
            Intf obj = (Intf)Naming.lookup(url);
            List<Thread>client_threads =  new ArrayList<>();
            for(int i = 0;i<3;i++){
              
                Thread t1 = new Thread(()->{
                    try{
                        System.out.print("Enter String: ");
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        String tmp = br.readLine();
                        System.out.println("Server Res : " + obj.count_vowels(tmp));
                    }catch(Exception e){

                    }
                                
                });
                client_threads.add(t1);
            }

            for(Thread x: client_threads){
                x.start();
            }
            for(Thread x: client_threads){
                x.join();
            }

        }catch(Exception e){

        }
    }
}