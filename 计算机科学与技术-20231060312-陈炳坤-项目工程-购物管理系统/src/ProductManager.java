import java.text.SimpleDateFormat;
import java.util.*;

public class ProductManager {
    /**
     * 添加商品信息
     * @param commodities 储存商品的集合
     */
    public void addProduct(List<Commodities> commodities) {
        System.out.println("---------------------------商品信息输入---------------------------");
        Scanner scanner = new Scanner(System.in);
        Commodities commodity = new Product();
        System.out.print("请输入商品名称：");
        commodity.setName(scanner.next());
        System.out.print("请输入生产厂家：");
        commodity.setManufacturers(scanner.next());
        System.out.print("请输入产品型号：");
        commodity.setVersion(scanner.next());
        System.out.print("请输入进货价：");
        commodity.setPurchasePrice(scanner.nextDouble());
        System.out.print("请输入零售价：");
        commodity.setRetailPrice(scanner.nextDouble());
        System.out.print("请输入商品数量：");
        int num ;
        while (true) {
            try {
                // 尝试读取一个整数
                 num = scanner.nextInt();
                // 如果成功读取，则跳出循环
                break;
            } catch (InputMismatchException e) {
                // 如果输入不匹配，则捕获异常
                System.out.println("输入的不是整数，请重新输入！");
                // 清除错误输入，以便下次读取
                scanner.next(); // 读取并忽略错误输入
            }
        }
        commodity.setNumber(num);
        System.out.print("请输入商品的生产时间（yyyy-MM-dd）：");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            commodity.setProductionDate(simpleDateFormat.parse(scanner.next()));
        } catch (Exception exception) {
            System.out.println("你输入的日期格式不正确，请正确输入");
        }
        commodity.setSerialNumber(commodities.getLast().getSerialNumber() + 1);
        commodities.add(commodity);
    }

    /**
     * 输出所有商品信息
     *
     * @param commodities 储存商品的集合
     */
    public static void outputCommodityInformation(List<Commodities> commodities) {
        for (Commodities commodity : commodities) {
            System.out.println("----------------------商品信息-----------------------");
            System.out.println(commodity.toString());
        }
    }

    /**
     * 修改商品的信息
     *
     * @param commodities 储存商品的集合
     */
    public void changeCommodityInformation(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("你需要修改的信息是：1.商品编号2.商品名称3.生产厂家4.生产日期5.型号6.进货价7.零售价格8.数量 9；退出界面");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateSerialNumber(commodities);
                    break;
                case 2:
                    updateName(commodities);
                    break;
                case 3:
                    updateManufacturers(commodities);
                    break;
                case 4:
                    updateProductionDate(commodities);
                    break;
                case 5:
                    updateVersion(commodities);
                    break;
                case 6:
                    updatePurchasePrice(commodities);
                    break;
                case 7:
                    updateRetailPrice(commodities);
                    break;
                case 8:
                    updateNumber(commodities);
                    break;
                case 9:
                    System.out.println("退出界面");
                    return;
            }
        } while (true);

    }

    /**
     * 更新商品编号
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateSerialNumber(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        int newSerialNumber;
        for (Commodities commodity:commodities){
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的编号：");
                newSerialNumber = scanner.nextInt();
                for (Commodities product : commodities) {
                    if (newSerialNumber == product.getSerialNumber()) {
                        System.out.println("该编号已经存在，请重新输入");
                        return;
                    }
                }

                if (serialNumber == newSerialNumber) {
                    System.out.println("新的商品编号与原商品编号相同，请重新输入");
                    return;
                }
                commodity.setSerialNumber(newSerialNumber);
                System.out.println("成功更新商品编号");
                return;
            }
        }

        System.out.println("需要修改商品的编号不存在，请重新输入");

    }

    /**
     * 更新商品名称
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateName(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        String newName;
        for (Commodities commodity:commodities){
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的商品名称：");
                newName = scanner.next();
                for (Commodities product : commodities) {
                    if (newName.equals(product.getName())) {
                        System.out.println("该商品名称已经存在，请重新输入");
                        return;
                    }
                }
                if (commodity.getName().equals(newName)) {
                    System.out.println("新的商品名称与原商品名称相同，请重新输入");
                    return;
                }
                commodity.setName(newName);
                System.out.println("成功更新商品名称");
                return;
            }
        }

        System.out.println("需要修改商品名称不存在，请重新输入");
    }

    /**
     * 更新生产厂家
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateManufacturers(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        String manufacturers;
        for (Commodities commodity:commodities){
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的生产厂家：");
                manufacturers = scanner.next();
                if (commodity.getManufacturers().equals(manufacturers)) {
                    System.out.println("新的生产厂家与原生产厂家相同，请重新输入");
                    return;
                }
                commodity.setManufacturers(manufacturers);
                System.out.println("成功更新生产厂家");
                return;
            }
        }

        System.out.println("需要修改商品名称不存在，请重新输入");
    }

    /**
     * 更新生产日期
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateProductionDate(List<Commodities> commodities) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        Date date = new Date();
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        String productionDate;
        for (Commodities commodity:commodities){
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的生产日期：");
                productionDate = scanner.next();
                if (simpleDateFormat.format(commodity.getProductionDate()).equals(productionDate)) {
                    System.out.println("新的生产日期与原生产日期相同，请重新输入");
                    return;
                }
                try {
                    date = simpleDateFormat.parse(productionDate);
                }catch (Exception ex){
                    System.out.println("时间的输入格式错误，请重新输入");
                }
                commodity.setProductionDate(date);
                System.out.println("成功更新生产日期");
                return;
            }
        }

        System.out.println("需要修改商品名称不存在，请重新输入");
    }

    /**
     * 更新商品型号
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateVersion(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        String version;
        for (Commodities commodity:commodities) {
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的商品型号：");
                version = scanner.next();
                if (commodity.getVersion().equals(version)) {
                    System.out.println("新的商品型号与原商品型号相同，请重新输入");
                    return;
                }
                commodity.setVersion(version);
                System.out.println("成功更新商品型号");
                return;
            }
        }
    }

    /**
     * 更新进货价
     *
     * @param commodities 储存商品对象的集合
     */
    public void updatePurchasePrice(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        double purchasePrice;
        for (Commodities commodity:commodities) {
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的进货价：");
                purchasePrice = scanner.nextDouble();
                if (commodity.getPurchasePrice() == purchasePrice) {
                    System.out.println("新的进货价与原进货价相同，请重新输入");
                    return;
                }
                commodity.setPurchasePrice(purchasePrice);
                System.out.println("成功更新进货价");
                return;
            }
        }
    }

    /**
     * 更新零售价
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateRetailPrice(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        double retailPrice;
        for (Commodities commodity:commodities) {
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的零售价：");
                retailPrice = scanner.nextDouble();
                if (commodity.getRetailPrice() == retailPrice) {
                    System.out.println("新的零售价与原零售价相同，请重新输入");
                    return;
                }
                commodity.setRetailPrice(retailPrice);
                System.out.println("成功更新零售价");
                return;
            }
        }
    }

    /**
     * 更新商品数量
     *
     * @param commodities 储存商品对象的集合
     */
    public void updateNumber(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改商品的编号：");
        int serialNumber = scanner.nextInt();
        int number;
        for (Commodities commodity:commodities) {
            if (commodity.getSerialNumber() == serialNumber) {
                System.out.print("请输入新的商品数量：");
                while (true) {
                    try {
                        // 尝试读取一个整数
                        number = scanner.nextInt();
                        // 如果成功读取，则跳出循环
                        break;
                    } catch (InputMismatchException e) {
                        // 如果输入不匹配，则捕获异常
                        System.out.println("输入的不是整数，请重新输入！");
                        // 清除错误输入，以便下次读取
                        scanner.next(); // 读取并忽略错误输入
                    }
                }
                if (commodity.getNumber() == number) {
                    System.out.println("新的商品数量与原商品数量相同，请重新输入");
                    return;
                }
                commodity.setNumber(number);
                System.out.println("成功更新商品数量");
                return;
            }
        }
    }

    /**
     * 删除商品信息
     *
     * @param commodities 储存商品的集合
     */
    public void deleteCommodityInformation(List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要删除商品的编号：");
        int serialNumber = scanner.nextInt();
        for (Commodities commodity : commodities) {
            if (serialNumber == commodity.getSerialNumber()) {
                char choice;
                while (true) {
                    System.out.println("是否确定删除该商品？y/n");
                    choice = scanner.next().charAt(0);
                    if (choice == 'y' || choice == 'n') {
                        break;
                    }
                }
                if (choice == 'y') {
                    commodities.remove(commodity);
                    System.out.println("成功删除该商品信息");
                }
                return;
            }
        }
        System.out.println("该商品编号不存在，请检查是否输入正确");
    }

    /**
     * 查询商品信息
     */
    public void inquireCommodityInformation(List<Commodities> products) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入需要查询的商品名字(若无则输入null)：");
        String name = scanner.next();

        System.out.print("请输入需要查询的商品生产厂家(若无则输入null)：");
        String manufacturer = scanner.next();

        Double minPrice = null;
        System.out.print("请输入需要查询的商品的最低零售价(若无则输入非数字，如null或空格后回车)：");
        if (scanner.hasNextDouble()) {
            minPrice = scanner.nextDouble();
        } else {
            scanner.nextLine(); // 读取并忽略整行非数字输入
        }

        List<Commodities> result = new ArrayList<>();
        for (Commodities product : products) {
            if ((name.equals("null")  || name.equals(product.getName()))
                    && (manufacturer.equals("null")  || manufacturer.equals(product.getManufacturers()))
                    && (minPrice == null || product.getRetailPrice() >= minPrice)) {
                result.add(product);
            }
        }

        System.out.println("查询结果：");
        if (result.isEmpty()) {
            System.out.println("没有找到符合条件的商品。");
        } else {
            for (Commodities product : result) {
                System.out.println("-------------------商品信息--------------------");
                System.out.println(product);
            }
        }
    }
}