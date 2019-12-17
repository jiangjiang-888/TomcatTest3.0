package main;

public class ItemBean {

	private String itemId;
	private String itemName;
	private double itemPrice;
	private int itemStock;
	private String buyer;
	private String buydate;
	private String filename;
	private double totalprice;
	private int itemSale;
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
