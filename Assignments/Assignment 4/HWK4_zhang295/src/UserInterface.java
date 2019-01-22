import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {
	
	Audio[] audioProducts = new Audio[100]; // set max size to 100
	Readable[] readables = new Readable[100]; // set max size to 100
	private int currentPage;
	
	public int getCurrentPage(){
		return currentPage;
	}
	public void changeCurrentPage(int page){
		this.currentPage = page;
		for(int clear = 0; clear < 100; clear++) { //adds empty lines creating the illusion of a new page
		    System.out.println("\n") ;
		}
		if (page == 1){
			System.out.println("1.Sign in");
			System.out.println("2.Sign up");
			System.out.println("Choose your option:");
		}
		if (page == 2){
			System.out.println("Choose your username: ");
		}
		if (page == 3){
			System.out.println("Enter your username:");
		}
		
		if (page == 5){
			System.out.println("1.View Items By Category");
			System.out.println("2.View Shopping Cart ");
			System.out.println("3.Sign Out ");
			System.out.println("4.View Previous Orders ");
			System.out.println("Choose your option:");
		}
		
		if (page == 6){
			System.out.println("1.Readables");
			System.out.println("2.Audio");
			System.out.println("Choose your option:");
			System.out.println("Press -1 to return to previous menu");
		}
		if (page == 8){
			showReadables();
			System.out.println();
			System.out.println("Choose your option by typing in the serial number:");
			System.out.println("Press -1 to return to previous menu.");
		}
		if (page == 9){
			showAudioProducts();
			System.out.println("Choose your option by typing in the serial number:");
			System.out.println("Press -1 to return to previous menu.");
		}
	}
	
	public void getReadables(){
		int counter = 0;
		try{
			FileReader fr = new FileReader("Books.txt");
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!= null){
				String[] ar = str.split(",");
				 //lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].replaceAll("\\s+","");
				ar[1] = ar[1].trim(); //removes leading or trailing spaces
				ar[2] = ar[2].trim();
				ar[3] = ar[3].replaceAll("\\s+","");
				ar[4] = ar[4].replaceAll("\\s+","");
				readables[counter] = new Readable();
				readables[counter].sNo = Integer.parseInt(ar[0]);
				readables[counter].Name = (ar[1]);
				readables[counter].authorName = (ar[2]);
				readables[counter].price = Integer.parseInt(ar[3]);
				readables[counter].quantity = Integer.parseInt(ar[4]);
				readables[counter].Type = "Book";			
				counter++;
			}			
			br.close();
		} catch (IOException e){
			System.out.println("File not found");
		}
		try{
			FileReader fr = new FileReader("Ebooks.txt");
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!= null){
				String[] ar = str.split(",");
				//lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].replaceAll("\\s+","");
				ar[1] = ar[1].trim(); //removes leading or trailing spaces
				ar[2] = ar[2].trim();
				ar[3] = ar[3].replaceAll("\\s+","");
				ar[4] = ar[4].replaceAll("\\s+","");
				readables[counter] = new Readable();
				readables[counter].sNo = Integer.parseInt(ar[0]);
				readables[counter].Name = (ar[1]);
				readables[counter].authorName = (ar[2]);
				readables[counter].price = Integer.parseInt(ar[3]);
				readables[counter].quantity = Integer.parseInt(ar[4]);
				readables[counter].Type = "eBook";			
				counter++;
			}			
			br.close();
		} catch (IOException e){
			System.out.println("File not found");
		}
	}
	
	public void getAudioProducts() {
		int counter = 0;
		try{
			FileReader fr = new FileReader("Cds.txt");
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!= null){
				String[] ar = str.split(",");
				 //lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].replaceAll("\\s+","");
				ar[1] = ar[1].trim(); //removes leading or trailing spaces
				ar[2] = ar[2].trim();
				ar[3] = ar[3].replaceAll("\\s+","");
				ar[4] = ar[4].replaceAll("\\s+","");
				audioProducts[counter] = new Audio();
				audioProducts[counter].sNo = Integer.parseInt(ar[0]);
				audioProducts[counter].Name = (ar[1]);
				audioProducts[counter].artistName = (ar[2]);
				audioProducts[counter].price = Integer.parseInt(ar[3]);
				audioProducts[counter].quantity = Integer.parseInt(ar[4]);
				audioProducts[counter].Type = "CD";			
				counter++;
			}			
			br.close();
		} catch (IOException e){
			System.out.println("File not found");
		}
		try{
			FileReader fr = new FileReader("MP3.txt");
			BufferedReader br = new BufferedReader(fr);
			String str;
			while((str = br.readLine())!= null){
				String[] ar = str.split(",");
				//lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].replaceAll("\\s+","");
				ar[1] = ar[1].trim(); //removes leading or trailing spaces
				ar[2] = ar[2].trim();
				ar[3] = ar[3].replaceAll("\\s+","");
				ar[4] = ar[4].replaceAll("\\s+","");
				audioProducts[counter] = new Audio();
				audioProducts[counter].sNo = Integer.parseInt(ar[0]);
				audioProducts[counter].Name = (ar[1]);
				audioProducts[counter].artistName = (ar[2]);
				audioProducts[counter].price = Integer.parseInt(ar[3]);
				audioProducts[counter].quantity = Integer.parseInt(ar[4]);
				audioProducts[counter].Type = "MP3";			
				counter++;
			}			
			br.close();
		} catch (IOException e){
			System.out.println("File not found");
		}
		
	}
	public void showReadables(){
		System.out.print(String.format("%-5s %-25s %-10s %-10s %-20s %-5s", "S.No", "Name of the Book", "Author", "Price($)", "Quantity in Store", "Type"));
		System.out.println();
		for (int i = 0; i < readables.length; i++){
			if (readables[i] != null){
				String readableInfo = readables[i].getInfo();
				String[] ar = readableInfo.split(",");
				/*for (int j = 0; j < ar.length; j++){
					System.out.println(ar[j]);
				}*/
				System.out.print(String.format("%-5s %-25s %-10s %-10s %-20s %-5s", ar[0], ar[1], ar[2], ar[3], ar[4],  ar[5]));
				System.out.println();
			}
		}
	}
	
	public void showAudioProducts(){
		System.out.print(String.format("%-5s %-25s %-10s %-10s %-20s %-5s", "S.No", "Name", "Artist", "Price($)", "Quantity in Store", "Type"));
		System.out.println();
		for (int i = 0; i < audioProducts.length; i++){
			if (audioProducts[i] != null){
				String audioInfo = audioProducts[i].getInfo();
				String[] ar = audioInfo.split(",");
				/*for (int j = 0; j < ar.length; j++){
					System.out.println(ar[j]);
				}*/
				System.out.print(String.format("%-5s %-25s %-10s %-10s %-20s %-5s", ar[0], ar[1], ar[2], ar[3], ar[4],  ar[5]));
				System.out.println();
			}
		}
	}
	
}