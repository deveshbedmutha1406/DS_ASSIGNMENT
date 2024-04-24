import ReverseModule.ReversePOA;
import java.lang.String;

public class ReverseImpl extends ReversePOA{
    ReverseImpl(){
        super();
        System.out.println("Reverse Impl Object Created");
    }

    public String reverse_string(String name){
        StringBuffer obj = new StringBuffer(name);
        
        return (("Response From Server " + obj.reverse()));
    }
}