import java.net.Socket;
import java.util.concurrent.TimeUnit;

import java.io.OutputStream;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;


public class client{

    private static void sendTime(Socket client){
        try{
            while(true){
                OutputStream os = client.getOutputStream();
                long current = System.currentTimeMillis() - 600000;
                String curr_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
                System.out.println("Time Send By " + client.getPort() + " " + curr_time);
                os.write(curr_time.getBytes());
                os.flush();
                TimeUnit.SECONDS.sleep(5);
            }
        }catch(Exception e){
            System.out.println("Error in sending time");
        }
    }
    
    private static void recvTime(Socket client){
        try{
            InputStream is = client.getInputStream();
            byte [] buff = new byte[1024];
            int byteread = is.read(buff);
            String recv_time = new String(buff, 0, byteread);

            String recv_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(recv_time)));
            System.out.println("Recv time from server " + recv_date);

        }catch(Exception e){
            System.out.println("Recv Time Error");

        }
    }

    private static void initiate(int port){
        try{
            Socket client = new Socket("127.0.0.1", port);
            System.out.println("Socket Initiated");

            // thread to send time
            Thread send_time_thread = new Thread(()->sendTime(client));
            send_time_thread.start();

            // thread to recv time
            Thread recv_time_thread = new Thread(()->recvTime(client));
            recv_time_thread.start();
        }catch(Exception e){
            System.out.println("Connection Attempt Failed");
        }
    }

    public static void main(String args[]){
        initiate(1050);
    }
}