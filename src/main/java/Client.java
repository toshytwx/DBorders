/**
 * Created by User on 10.02.2017.
 */
public class Client {
    private int clientId;
    private String phone;
    private String name;

    @Override
    public String toString() {
        return "\n"+ "Client"+"\n" + "phone= " + phone + "\n" + "name= " + name + "\n";
    }

    public Client() {
    }

    public Client(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public  int getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
