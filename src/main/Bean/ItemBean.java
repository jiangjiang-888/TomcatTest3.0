package main.Bean;

public class ItemBean {

    private String itemId;   	//编号
    private String itemName;	//名称
    private double itemPrice;	//单价
    private int itemStock;		//库存
    private String buyer;		//购买者id
    private String buydate;		//购买时间
    private String filename;	//图片地址
    private double totalprice;	//总价
    private int itemSale;		//售出数量
	private String itemClass;	//类别
	private String itemIfo;		//简介
    public String getItemIfo() {
        return itemIfo;
    }

    public void setItemIfo(String itemIfo) {
        this.itemIfo = itemIfo;
    }


	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}


	public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getBuydate() {
        return buydate;
    }

    public void setBuydate(String buydate) {
        this.buydate = buydate;
    }

    public int getItemSale() {
        return itemSale;
    }

    public void setItemSale(int itemSale) {
        this.itemSale = itemSale;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
