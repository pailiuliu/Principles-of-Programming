

public class Readable extends Item {

	protected String authorName;
	
	public String getInfo(){
		return Integer.toString(sNo) + "," + Name + "," + authorName + "," + price + "," + quantity + "," + Type;
	}

	@Override
	public int getPrice(){ 
		return price;
	}
}
