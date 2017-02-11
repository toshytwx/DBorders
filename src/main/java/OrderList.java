import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.HashMap;

/**
 * Created by User on 10.02.2017.
 */
public class OrderList {
    private HashMap <Client, Product> orderList= new HashMap<Client, Product>();

    public OrderList() {
    }

    public void addOrder(Client client, Product product) {
        orderList.put(client,product);
    }


    public void OrderListInfo() {
        if(orderList.isEmpty()){
            System.out.println("You didn't order anything yet!");
        }else{
            System.out.println(orderList.toString());
        }
    }
}
