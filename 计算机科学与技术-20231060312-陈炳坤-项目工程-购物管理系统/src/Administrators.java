import java.util.Scanner;

public abstract class Administrators {
     public String account;
     public String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Administrators(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /**
     * 管理员账户登录
     * @param administrator 管理员账户
     * @return 返回是否成功登录
     */
    public static boolean login(Administrators administrator){
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------管理员登录---------------------------");
        System.out.print("请输入您的账户：");
        String account = scanner.next();
        System.out.print("请输入您的密码：");
        String password = scanner.next();
        if(password.equals(administrator.getPassword())&&account.equals(administrator.getAccount())){
            System.out.println("成功登录管理员账户！");
            return true;
        }
        System.out.println("账号或密码错误，请重新输入");
        return false;
    }

    /**
     * 修改自身密码
     */
    public void changePassword(Administrators administrator){
        System.out.println("-------------------------密码修改------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入原密码：");
        String password = scanner.next();
        System.out.print("请输入新密码：");
        String newPassword01 = scanner.next();
        System.out.print("请再次输入新密码：");
        String newPassword02 = scanner.next();
        if(!password.equals(administrator.getPassword())){
            System.out.println("原密码错误，请重新输入");
            return;
        }
        if(!newPassword01.equals(newPassword02)){
            System.out.println("新密码不一致，请重新输入");
            return;
        }
        if(password.equals(newPassword01)){
            System.out.println("新密码与原密码相同，请重新输入");
            return;
        }
        administrator.setPassword(newPassword01);
        System.out.println("成功修改密码");
    }
}
