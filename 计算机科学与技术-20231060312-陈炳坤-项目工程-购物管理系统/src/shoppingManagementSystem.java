import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class shoppingManagementSystem {
    List<Clients> clients = new ArrayList<>();//储存已有客户账号
    Administrators administrator = new Manager("admin","ynuadmin"); //初始管理员账号
    Clients client;//登录后的账户对象
    List<Commodities> commodities = new ArrayList<>();//储存商品信息
    ProductManager productManager = new ProductManager();
    CustomerManager customerManager = new CustomerManager();
    ShoppingManagement shoppingManagement = new ShoppingManagement();


    //初始化账户
    public shoppingManagementSystem() {
        clients.add(new Customer(1,"cbk","2004D$sdfd","15223417890","1303307411@qq.com"));
        clients.add(new Customer(2,"aff","sdfDSA*6445","48959541771","592428555@qq.com"));
        clients.add(new Customer(3,"gfd","asd54881***","548459219","98811563277@qq.com"));
        clients.add(new Customer(4,"jyj","fasaw&**(548","844188922","789921158852@qq.com"));
        commodities.add(new Product(1,"苹果手机","苹果","2020-04-05","15pro",6000,8000,50));
        commodities.add(new Product(2,"华为手机","华为","2023-08-15","MatePro",3000,4085,100));
        commodities.add(new Product(3,"三星手机","三星","2018-02-07","max12",5000,12000,20));
        commodities.add(new Product(4,"小米手机","小米","2015-07-09","14",4999,9500,250));
    }

    /**
     * 登录界面
     */
    public void loginInterface() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;//登录失败次数
        do {
            System.out.println("---------------------------登录界面---------------------------");
            System.out.println("1.管理员账户登录 2.客户账户登录(重置密码) 3.客户账户注册 4.退出系统");
            System.out.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (Administrators.login(administrator)) {
                        administratorsMenu();
                    }
                    break;
                case 2:
                    if(count>=5){
                        System.out.println("登录失败次数过多，账户已锁定");
                        char judge;
                        while (true){
                            System.out.println("是否需要重置密码？y/n");
                            judge = scanner.next().charAt(0);
                            if(judge == 'y'||judge == 'n'){
                                break;
                            }
                        }
                        if(judge == 'y'){
                            Clients.resetPassword(clients);
                        }
                        break;
                    }
                    client = Clients.login(clients);
                    if (client != null) {
                        clientsMenu();
                    }
                    count++;
                    break;
                case 3:
                    Clients.register(clients);
                    break;
                case 4:
                    System.out.println("退出系统");
                    return;
            }
        } while (true);
    }

    /**
     * 管理员菜单
     */
    public void administratorsMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------------------------------------管理员-------------------------------------");
            System.out.println("1.密码管理 2.客户管理 3.商品管理 4.退出登录");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    administratorPasswordManagementMenu();
                    break;
                case 2:
                    administratorClientManagementMenu();
                    break;
                case 3:
                    administratorCommoditiesManagementMenu();
                    break;
                case 4:
                    System.out.println("退出登录");
                    return;
            }
        } while (true);
    }

    /**
     * 管理员密码管理菜单
     */
    public void administratorPasswordManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------------------------------管理员---------------------------------------");
            System.out.println("-------------------------------------密码管理界面-------------------------------------");
            System.out.println("1.修改密码 2.重置客户密码 3.退出界面");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    administrator.changePassword(administrator);
                    break;
                case 2:
                    customerManager.resetCustomerPassword(clients);
                    break;
                case 3:
                    System.out.println("退出界面");
                    return;
            }
        } while (true);
    }

    /**
     * 管理员用户管理菜单
     */
    public void administratorClientManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------------------------------管理员---------------------------------------");
            System.out.println("-------------------------------------用户管理界面------------------------------------");
            System.out.println("1.出示所有客户信息 2.删除客户信息 3.查询客户信息 4.退出界面");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    customerManager.outputClientsInformation(clients);
                    break;
                case 2:
                    customerManager.deleteClientsInformation(clients);
                    break;
                case 3:
                    System.out.print("请选择查询方式（1.客户ID 2.用户名）：");
                    int point = scanner.nextInt();
                    if(point==1){
                        customerManager.inquireClientsInformationByID(clients);
                    }else if (point == 2){
                        customerManager.inquireClientsInformationByAccountName(clients);
                    }

                    break;
                case 4:
                    System.out.println("退出界面");
                    return;
            }
        } while (true);

    }

    /**
     * 管理员商品管理界面
     */
    public void administratorCommoditiesManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("----------------------------------------管理员----------------------------------------------");
            System.out.println("--------------------------------------商品管理界面-------------------------------------------");
            System.out.println("1.出示所有商品信息 2.添加商品信息 3.修改商品信息 4.删除商品信息 5.查询商品信息 6.退出界面");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ProductManager.outputCommodityInformation(commodities);
                    break;
                case 2:
                    productManager.addProduct(commodities);
                    break;
                case 3:
                    productManager.changeCommodityInformation(commodities);
                    break;
                case 4:
                    productManager.deleteCommodityInformation(commodities);
                    break;
                case 5:
                    productManager.inquireCommodityInformation(commodities);
                    break;
                case 6:
                    System.out.println("退出");
                    return;
            }
        } while (true);
    }

    /**
     * 用户界面
     */
    public void clientsMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------------------客户---------------------------");
            System.out.println("1.修改密码 2.购物 3.退出登录");
            System.out.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if(client.changePassword()){
                        return;
                    }
                    break;
                case 2:
                    shoppingMenu();
                    break;
                case 3:
                    System.out.println("退出系统");
                    return;
            }
        } while (true);
    }

    /**
     * 用户购买界面
     */
    public void shoppingMenu(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---------------------------------------------------客户------------------------------------------------------");
            System.out.println("--------------------------------------------------购物界面----------------------------------------------------");
            System.out.println("1.展示购物车信息 2.将商品加入购物车 3.将商品移除购物车 4.修改购物车中商品数量 5.结账 6.查看购物历史 7.退出界面");
            System.out.print("请选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ProductManager.outputCommodityInformation(client.shoppingCart);
                    break;
                case 2:
                    shoppingManagement.addCommodity(commodities,client.shoppingCart);
                    break;
                case 3:
                    shoppingManagement.deleteCommodity(client.shoppingCart);
                    break;
                case 4:
                    shoppingManagement.changeCommodityNumber(client.shoppingCart,commodities);
                    break;
                case 5:
                    shoppingManagement.settleAccounts(client);
                    break;
                case 6:
                    System.out.println(client.shoppingHistory);
                    break;
                case 7:
                    System.out.println("退出界面");
                    return;
            }
        } while (true);
    }
}
