import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Commodities {
    public int serialNumber;
    public String name;
    public String manufacturers;
    public Date productionDate;
    public String version;
    public double purchasePrice;
    public double retailPrice;
    public int number;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String manufacturers) {
        this.manufacturers = manufacturers;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Commodities(int serialNumber, String name, String manufacturers, String productionDate, String version, double purchasePrice, double retailPrice, int number) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.manufacturers = manufacturers;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.productionDate = simpleDateFormat.parse(productionDate);
        }catch (Exception ex){
            System.out.println("你输入的日期格式不正确，请正确输入");
        }
        this.version = version;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.number = number;
    }

    public Commodities() {
    }


    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "商品序号：" + serialNumber +
                "\t商品名称：" + name +
                "\t生产厂家：" + manufacturers +
                "\t生产日期：" + simpleDateFormat.format(productionDate) +
                "\t商品型号：" + version +
                "\t成本价：" + purchasePrice +
                "\t零售价：" + retailPrice +
                "\t库存数量：" + number ;

    }
}
