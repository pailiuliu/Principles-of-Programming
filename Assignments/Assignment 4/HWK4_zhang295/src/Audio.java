import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Audio extends Item{

	protected String artistName;
	
	public String getInfo(){
		return sNo + "," + Name + "," + artistName + "," + price + "," + quantity + "," + Type;
	}
	
	@Override
	public int getPrice() { //this method will get overrided
		return price;
	}

	
}
