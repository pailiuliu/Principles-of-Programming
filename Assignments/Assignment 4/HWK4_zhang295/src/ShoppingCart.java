import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ShoppingCart extends User{
	
	Item[] itemList = new Item[100];
	int counter = 0;
	
	ShoppingCart(String username) {
		super(username);
	}
	
	public void addItem(Item item, int quantityOrdered){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(); // needed to print out date	
		
		itemList[counter] = item;
		itemList[counter].quantityOrdered = quantityOrdered;
		try{
			FileWriter fw = new FileWriter("Cart_[" + this.getUsername() + "].txt", true); 
			BufferedWriter buffer = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffer); 
			pw.println((counter + 1) +  "," + item.sNo + "," + item.Name + "," + item.price + "," + dateFormat.format(date) + "," + item.quantityOrdered + "," + item.Type);  //add date
			pw.close();
		} catch (IOException e){
			System.out.println("ERROR");
		}
		this.counter++;
	}
	
	public String getContent(){
		String string = "";
		int counter1 = 0;
		try{
			FileReader fr = new FileReader("Cart_[" + this.getUsername() + "].txt");
			BufferedReader br = new BufferedReader(fr);
			String tempstr;
			while((tempstr = br.readLine())!= null){
				String[] ar = tempstr.split(",");
				 //lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].trim();
				ar[2] = ar[2].trim(); //removes leading or trailing spaces
				ar[4] = ar[4].trim();
				ar[5] = ar[5].trim();
				string += ar[0] + "," + ar[2] + "," + ar[4] + "," + ar[5] + "\n";
				counter1 += 1;
			}
			if (counter1 == 0){
				System.out.println("There are no items in your shopping cart.");
			}
			br.close();
		} catch (IOException e){
			System.out.println("There are no items in your shopping cart.");
		}
		return string;
	}
	
	/*ArrayList<Item> itemList = new ArrayList<Item>();

	ShoppingCart(String username) {
		super(username);
	}
	
	public void addItem(Item item, int quantity){
		
		itemList.add(item);
		try{
			FileWriter fw = new FileWriter("Cart_[" + this.getUsername() + "].txt", true); 
			BufferedWriter buffer = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffer); 
			pw.println(itemList.size() + "," + item.Name + "," + quantity);  //add date
			pw.close();
		} catch (IOException e){
			System.out.println("ERROR");
		}
	}
	
	public String getContent(){
		String string = "";
		int counter = 0;
		try{
			FileReader fr = new FileReader("Cart_[" + this.getUsername() + "].txt");
			BufferedReader br = new BufferedReader(fr);
			String tempstr;
			while((tempstr = br.readLine())!= null){
				string += tempstr + "\n";
				counter += 1;
			}
			if (counter == 0){
				System.out.println("There are no items in your shopping cart.");
			}
			br.close();
		} catch (IOException e){
			System.out.println("There are no items in your shopping cart.");
		}
		return string;
	}*/
	
}
