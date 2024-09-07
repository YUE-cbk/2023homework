import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public abstract class Clients {
    public int clientID;
    public String account;
    public String password;
    public String clientLevel;
    public Date registrationDate;
    public double totalConsumingAmount;
    public String phoneNumber;
    public String emailAddress;
    public List<Commodities> shoppingCart;
    public String shoppingHistory;


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public String getShoppingHistory() {
        return shoppingHistory;
    }

    public void setShoppingHistory(String shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }

    public List<Commodities> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Commodities> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public String getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(String clientLevel) {
        this.clientLevel = clientLevel;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getTotalConsumingAmount() {
        return totalConsumingAmount;
    }

    public void setTotalConsumingAmount(double totalConsumingAmount) {
        this.totalConsumingAmount = totalConsumingAmount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
            return true;
        } else {
            System.out.println("密码无效，请重新输入");
            return false;
        }
    }


    public Clients(int clientID, String account, String password, String phoneNumber, String emailAddress) {
        this.clientID = clientID;
        this.account = account;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.registrationDate = new Date();
        this.clientLevel = "铜牌客户";
        shoppingCart = new ArrayList<>();
        shoppingHistory = "-----------------------------------购物历史------------------------------------";
    }

    public Clients() {
    }

    public Clients(String account, String password, List<Clients> clients) {
        this.account = account;
        this.password = password;
        this.clientID = clients.size() + 1;
        this.registrationDate = new Date();
        shoppingCart = new ArrayList<>();
        this.clientLevel = "铜牌客户";
        shoppingHistory = "-----------------------------------购物历史------------------------------------";
    }


    /**
     * 验证密码是否合法
     * @param password 需要验证的密码
     * @return 返回是否合法
     */
    public static boolean isValidPassword(String password) {
        // 检查长度
        if (password == null || password.length() <= 8) {
            return false;
        }

        // 跟踪是否包含大小写字母、数字和标点符号
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        // 遍历密码中的每个字符
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            // 检查大写字母
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            // 检查小写字母
            else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            // 检查数字
            else if (Character.isDigit(c)) {
                hasNumber = true;
            }
            // 检查标点符号（这里简单地认为非字母非数字的ASCII字符是标点符号）
            else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
            // 如果已经找到了所有类型的字符，可以提前返回true
            if (hasUppercase && hasLowercase && hasNumber && hasSpecialChar) {
                return true;
            }
        }

        // 检查是否所有类型的字符都被找到了
        return hasUppercase && hasLowercase && hasNumber && hasSpecialChar;
    }

    /**
     * 修改自身密码
     */
    public boolean changePassword() {
        System.out.println("-------------------------密码修改------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入原密码：");
        String password = scanner.next();
        System.out.print("请输入新密码：");
        String newPassword01 = scanner.next();
        System.out.print("请再次输入新密码：");
        String newPassword02 = scanner.next();
        if(!password.equals(getPassword())){
            System.out.println("原密码错误，请重新输入");
            return false;
        }
        if(!newPassword01.equals(newPassword02)){
            System.out.println("新密码不一致，请重新输入");
            return false;
        }
        if(password.equals(newPassword01)){
            System.out.println("新密码与原密码相同，请重新输入");
            return false;
        }
        setPassword(newPassword01);
        System.out.println("成功修改密码");
        return true;
    }

    /**
     * 重置密码
     */
    public static void resetPassword(List<Clients> clients) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入账户名：");
        String account = scanner.next();
        System.out.print("请输入注册时所使用的邮箱：");
        String emailAddress = scanner.next();
        for (Clients client : clients) {
            if (account.equals(client.getAccount()) && emailAddress.equals(client.getEmailAddress())) {
                if (client.setPassword(generateSecurePassword())) {
                    System.out.println("重置后密码为：" + client.getPassword());
                    return;
                }
            }
        }
        System.out.println("未找到账户，请检查是否输入错误");
    }

    /**
     * 客户重置密码时随机生成一个密码
     * @return 返回生成的密码
     */
    public static String generateSecurePassword() {
        SecureRandom random = new SecureRandom();
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}:\"<>?";
        final int PASSWORD_LENGTH = 12;
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }


    /**
     * 展示购物信息
     */
    public void showShoppingHistoryInformation() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (shoppingCart.isEmpty()) {
            System.out.println("------------------------------------------购物车----------------------------------------------");
            for (Commodities product : shoppingCart) {
                System.out.println("商品编号：" + product.getSerialNumber() + "\t商品名称：" + product.getName() + "\t商品版本：" + product.getVersion() + "\n生产厂家：" + product.getManufacturers() + "\t生产日期：" + simpleDateFormat.format(product.getProductionDate()) + "\t购买数量：" + product.getNumber());
            }
        } else {
            System.out.println("购物车为空");
        }
    }

    /**
     * 客户登录
     * @param clients 储存客户对象的集合
     * @return 返回目标对象
     */
    public static Clients login(List<Clients> clients) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------客户登录---------------------------");
        System.out.print("请输入您的账户：");
        String account = scanner.next();
        System.out.print("请输入您的密码：");
        String password = scanner.next();
        for (Clients client : clients) {
            if (password.equals(client.getPassword()) && account.equals(client.getAccount())) {
                System.out.println("成功登录账户！");
                return client;
            }
        }
        System.out.println("账户或密码错误，请重新输入");
        return null;
    }

    /**
     * 注册账户
     * @param clients 储存账户对象的集合
     */
    public static void register(List<Clients> clients) {
        System.out.println("---------------------------客户账户注册---------------------------");
        Scanner scanner = new Scanner(System.in);
        Clients client = new Customer();
        System.out.print("请输入用户名：");
        client.setAccount(scanner.next());
        System.out.print("请输入密码：");
        if (!client.setPassword(scanner.next())) {
            return;
        }
        System.out.print("请输入手机号：");
        client.setPhoneNumber(scanner.next());
        System.out.print("请输入邮箱号：");
        client.setEmailAddress(scanner.next());
        client.setClientID(clients.getLast().getClientID() + 1);
        client.setClientLevel("铜牌客户");
        client.setTotalConsumingAmount(0);
        client.setRegistrationDate(new Date());
        clients.add(client);
        System.out.println("创建账户成功，请前往登录");
    }


    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "用户ID：" + clientID +
                "\n用户名：" + account +
                "\n用户级别：" + clientLevel  +
                "\n注册日期：" + simpleDateFormat.format(registrationDate) +
                "\n消费总额：" + totalConsumingAmount +
                "\n电话号码：" + phoneNumber +
                "\n邮箱地址：" +  emailAddress ;
    }


}
