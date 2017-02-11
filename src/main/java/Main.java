import java.util.Scanner;

/**
 * Created by User on 10.02.2017.
 */
public class Main {
    public static void main(String[] args){
        menu();
    }

    private static void menu(){
        DBManipulations dbManipulations = new DBManipulations();
        dbManipulations.createDBconnection();
        boolean flag = true;
        while(flag){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1: add new Order.");
            System.out.println("2: add new Client.");
            System.out.println("3: add new Product.");
            System.out.println("4: show info about your Order");
            System.out.println("5: Exit.");
            int index = scanner.nextInt();
            switch (index){
                case 1:
                    dbManipulations.addNewOrderDB(new Scanner(System.in));
                    break;
                case 2:
                    dbManipulations.addClientDB(new Scanner(System.in));
                    break;
                case 3:
                    dbManipulations.addProductDB(new Scanner(System.in));
                    break;
                case 4:
                    dbManipulations.getOrderList();
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
}
