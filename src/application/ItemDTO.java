package application;

public class ItemDTO {
	private int itemId;
	private String name;
	private String image;
	private int price;
	
	public ItemDTO(int itemId, String name, String image, int price) {
		this.itemId = itemId;
		this.name = name;
		this.image = image;
		this.price = price;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
