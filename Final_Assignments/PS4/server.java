import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.InputStream;
import java.io.OutputStream;
public class server{

    private static final Map<String, HashMap<String, Object>> obj = new HashMap<>();

    private static void startRecv(Socket slave, String addr){
        try{
            while(true){
                InputStream is = slave.getInputStream();
                byte[]buff = new byte[1024];
                int byteread = is.read(buff);
                String recv_time = new String(buff,0,byteread);
                Date curr_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(recv_time);
                System.out.println("Recv Time From " + addr + " " + curr_time);

                long time_diff = System.currentTimeMillis() - curr_time.getTime();

                HashMap<String, Object> m1 = new HashMap<>();
                m1.put("time_diff", time_diff);
                m1.put("conn", slave);

                obj.put(addr, m1);

                TimeUnit.SECONDS.sleep(5);
            }
        }catch(Exception e){

        }
    }

    private static void acceptConn(ServerSocket master){
        try{
            while(true){
                Socket slave = master.accept();
                String addr = slave.getInetAddress().getHostAddress() + ":" + slave.getPort();
                System.out.println("Connecetion recve from " + addr);
                Thread t1 = new Thread(()->startRecv(slave, addr));
                t1.start();
                TimeUnit.SECONDS.sleep(5);
            }
        }catch(Exception e){
            System.out.println("inside accepting connection");
        }
    }

    private static long getAvg(){
        long ans = 0;
        for(HashMap<String, Object>o: obj.values()){
            ans += (long)o.get("time_diff");
        }
        return (ans/(obj.size() + 1));
    }

    private static void syncClocks(){
        try{
            while(true){
                System.out.println("New sync Cycle Started clients : " + obj.size());
                if(!obj.isEmpty()){
                    long getval = getAvg();
                    System.out.println("Avg Value is " + getval);
                    long time_to_send = System.currentTimeMillis() + getval;
                    System.out.println("send in milli seconds " + time_to_send);
                    String formated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((time_to_send)));
                    System.out.println("Actial Time To Send " + formated);

                    for(Map.Entry<String, HashMap<String, Object>> o1: obj.entrySet()){
                        System.out.println("Sending Time To " + o1.getKey());
                        Socket slave = (Socket) o1.getValue().get("conn");
                        OutputStream os = slave.getOutputStream();
                        os.write(String.valueOf(time_to_send).getBytes());
                        os.flush();
                    }
                }else{
                    System.out.println("empty object no connections");
                }
                TimeUnit.SECONDS.sleep(5);
            }
        }catch(Exception e){
            System.out.println("Err in sync clocks");
        }
    }

    private static void start(int port){
        try{
            ServerSocket master = new ServerSocket(port);
            System.out.println("Server initated");

            // thread to accept connection
            Thread accept_conn = new Thread(()->acceptConn(master));
            accept_conn.start();

            Thread sync_clocks = new Thread(()->syncClocks());
            sync_clocks.start();
        }catch(Exception e){
            System.out.println("error in start");
        }
    }


    public static void main(String args[]){
        start(1050);
    }
}