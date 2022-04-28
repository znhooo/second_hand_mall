import java.util.Random;
import java.util.UUID;

public class CreateOrderTest {
    public static void main(String[] args) {
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String id =String.valueOf(r1)+ r2 + now;// 订单ID
        System.out.println(id);
    }
}
