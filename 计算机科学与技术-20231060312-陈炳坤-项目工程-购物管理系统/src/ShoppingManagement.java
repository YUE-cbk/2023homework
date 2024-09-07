import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShoppingManagement {


    /**
     * 将商品加入购物车
     */
    public void addCommodity(List<Commodities> commodities, List<Commodities> shoppingCart) {
        Scanner scanner = new Scanner(System.in);
        //输出商品列表
        ProductManager.outputCommodityInformation(commodities);
        System.out.print("请输入您要购买的商品编号：");
        int choice = scanner.nextInt();
        //判断购物车中是否已添加该物品
        if (shoppingCart != null) {
            for (Commodities product : shoppingCart) {
                if (choice == product.getSerialNumber()) {
                    product.setNumber(product.getNumber() + 1);
                    for (Commodities commodity:commodities){
                        if(commodity.getSerialNumber() == choice){
                            commodity.setNumber(commodity.getNumber()-1);
                        }
                    }
                    System.out.println("添加商品成功");
                    return;
                }
            }
        }
        for (Commodities product : commodities) {
            if (product.getSerialNumber() == choice) {
                //判断能否添加商品
                if (product.getNumber() <= 0) {
                    System.out.println("该商品库存不足，添加失败");
                }
                Commodities newProduct = new Product();
                newProduct.setName(product.getName());
                newProduct.setSerialNumber(product.getSerialNumber());
                newProduct.setManufacturers(product.getManufacturers());
                newProduct.setProductionDate(product.getProductionDate());
                newProduct.setVersion(product.getVersion());
                newProduct.setRetailPrice(product.getRetailPrice());
                newProduct.setNumber(1);
                //将商品加入购物车
                if (shoppingCart != null) {
                    shoppingCart.add(newProduct);
                }
                //库存数量-1
                product.setNumber(product.getNumber() - 1);
                System.out.println("添加商品成功");
            }
        }
    }

    /**
     * 将商品移出购物车
     */
    public void deleteCommodity(List<Commodities> shoppingCart) {
        Scanner scanner = new Scanner(System.in);
        //输出购物车列表
        ProductManager.outputCommodityInformation(shoppingCart);
        System.out.print("请输入您要移除的商品编号：");
        int choice = scanner.nextInt();
        char option;
        while (true) {
            System.out.println("删除后无法恢复，请确认是否继续删除操作？y/n");
            option = scanner.next().charAt(0);
            if (option == 'y' || option == 'n') {
                break;
            }
        }
        if (option == 'y') {
            //判断购物车中是否已添加该物品
            for (Commodities product : shoppingCart) {
                if (choice == product.getSerialNumber()) {
                    shoppingCart.remove(product);
                    System.out.println("移除商品成功");
                    return;
                }
            }

            System.out.println("该商品不存在，请重新输入");
        }
        System.out.println("取消删除操作");
    }

    /**
     * 修改购物车中商品的数量
     */
    public void changeCommodityNumber(List<Commodities> shoppingCart,List<Commodities> commodities) {
        Scanner scanner = new Scanner(System.in);
        //输出购物车列表
        ProductManager.outputCommodityInformation(shoppingCart);
        System.out.print("请输入您要修改的商品编号：");
        int choice = scanner.nextInt();
        System.out.print("请输入您想要的数量：");
        //判断购物车中是否已添加该物品
        for (Commodities product : shoppingCart) {
            if (choice == product.getSerialNumber()) {
                int num = -1;
                do {
                    num = scanner.nextInt();
                    if(num>=0||num<=product.getNumber()){
                        break;
                    }
                    System.out.println("输入范围为0~"+product.getNumber());
                }while (true);
                if (num<=0) {
                    shoppingCart.remove(product);
                }
                for (Commodities commodity:commodities){
                    if(commodity.getSerialNumber() == choice){
                        commodity.setNumber(commodity.getNumber()+product.getNumber()-num);
                    }
                }
                product.setNumber(num);

                System.out.println("修改成功");
                return;
            }
        }
        System.out.println("该商品不存在，请重新输入");
    }

    /**
     * 结账
     */
    public void settleAccounts(Clients customer) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        String tempShoppingHistory = customer.getShoppingHistory();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        for(Commodities product : customer.getShoppingCart()){
            sum += product.getNumber()*product.getRetailPrice();
            tempShoppingHistory += "\n商品名称：" + product.getName() +"\t购买数量: " + product.getNumber()+"\t购买时间: "+ simpleDateFormat.format(new Date());
        }
        char option;
        while (true) {
            System.out.println("您的付款金额为："+sum);
            System.out.println("请确认是否付款？y/n");
            option = scanner.next().charAt(0);
            if (option == 'y' || option == 'n') {
                break;
            }
        }
        if (option == 'y') {
            System.out.println("成功付款!");
            customer.setTotalConsumingAmount(customer.getTotalConsumingAmount()+sum);
            customer.setShoppingHistory(tempShoppingHistory);
        }
    }
}
