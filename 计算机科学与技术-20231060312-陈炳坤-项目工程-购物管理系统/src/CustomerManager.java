import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    /**
     * 重置客户密码
     */
    public void resetCustomerPassword(List<Clients> clients){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------重置客户密码------------------------");
        System.out.print("请输入客户ID：");
        int ID = scanner.nextInt();
        for (Clients client:clients){
            if (ID == client.getClientID()){
                char choice;
                while (true){
                    System.out.println("是否确定重置该客户密码？y/n");
                    choice = scanner.next().charAt(0);
                    if(choice == 'y'||choice == 'n'){
                        break;
                    }
                }
                if(choice == 'y'){
                    System.out.print("请输入新密码：");
                    String newPassword = scanner.next();
                    if(client.setPassword(newPassword)){
                        System.out.println("重置客户密码成功");
                    }
                    return;
                }
            }
        }
        System.out.println("该用户不存在，请检查ID是否正确");
    }

    /**
     * 输出所有客户信息
     * @param clients 储存客户对象的集合
     */
    public void outputClientsInformation(List<Clients> clients){
        for (Clients client : clients){
            System.out.println("------------------------客户信息-----------------------");
            System.out.println(client.toString());
        }
    }

    /**
     * 删除客户信息
     * @param clients 储存客户对象的集合
     */
    public void deleteClientsInformation(List<Clients> clients){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要删除客户的ID：");
        int ID = scanner.nextInt();
        for (Clients client : clients){
            if(ID == client.getClientID()){
                char choice;
                while (true){
                    System.out.println("是否确定删除该客户信息？y/n");
                    choice = scanner.next().charAt(0);
                    if(choice == 'y'||choice == 'n'){
                        break;
                    }
                }
                if(choice == 'y'){
                    clients.remove(client);
                    System.out.println("成功删除该客户信息");
                }
                return;
            }
        }
        System.out.println("该客户ID不存在，请检查是否输入正确");
    }

    /**
     * 根据ID查询客户信息
     * @param clients 储存客户对象的集合
     */
    public void inquireClientsInformationByID(List<Clients> clients){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入客户的ID：");
        int ID = scanner.nextInt();
        for (Clients client : clients){
            if(ID == client.getClientID()){
                System.out.println("-------------------------客户信息--------------------------");
                System.out.println(client);
                return;
            }
        }
        System.out.println("该客户ID不存在，请检查是否输入正确");
    }

    /**
     * 根据用户名查询客户信息
     * @param clients 储存客户对象的集合
     */
    public void inquireClientsInformationByAccountName(List<Clients> clients){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入客户的用户名：");
        String account = scanner.next();
        for (Clients client : clients){
            if(client.getAccount().equals(account)){
                System.out.println(client);
                return;
            }
        }
        System.out.println("该客户名不存在，请检查是否输入正确");
    }
}
