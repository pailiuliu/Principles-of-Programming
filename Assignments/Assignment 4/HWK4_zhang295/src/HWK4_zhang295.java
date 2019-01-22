/*
* Name: Richard Zhang, Ming Wang, Pai Liu	
* MacID: zhang295, wangm27, liup27
* Student Number: 001450206, 001408077, 001419475
* Description: This is our HWK4 program. It contains one main class, and 10 other classes.
*/


import java.io.BufferedReader; //imports IO Bufferred Reader library to class
import java.io.BufferedWriter; //imports IO Bufferred Writer library to class
import java.io.File; //imports IO File library to class
import java.io.FileReader; //imports IO File Reader library to class
import java.io.FileWriter; //imports IO Writer Reader library to class
import java.io.IOException; //imports IO Exception library to class
import java.io.PrintWriter; //imports IO PrintWriter library to class
import java.util.ArrayList; //imports ArrayList function to class
import java.util.Scanner; //imports Scanner function to class

public class HWK4_zhang295 { //Name of main class, correct file naming convention
	
	//if user inputs wrong thing make it so it doesnt crash ex.mistake in input
	//make it strictly dependent on whatever's in the text files 
	//if they order twice of the same thing, it doesnt print it out twice
	
	static ArrayList<ShoppingCart> UserList = new ArrayList<ShoppingCart>(); //declares ArrayList containing ShoppingCart class object
	static int currentUserIndex; //declares integer currentUserIndex
	static int ConfirmationIDCounter; //declares integer ConfirmationIDCounter

	public static void main(String[] args) { //main function of the program
		UserInterface UI = new UserInterface(); //declares UserInterface object called UI
		UI.changeCurrentPage(1); //change this to start on a specific page
		CreateExistingUsersAndShoppingCarts(); //activate function stated below
		UI.getAudioProducts(); //calls getAudioProducts function on UI
		UI.getReadables(); //calls getReadables function on UI
		createConfirmationNumberTextFile();//calls createConfirmationNumberTextFile function from below 
//blank line
//another blank line
		while (true){ //infinite while loop until break
			if (UI.getCurrentPage() == 1){ //if current page is page 1 in UserInterface
				String userInput = getUserInput(); //get string as user input
				while (!userInput.equals("1") && !userInput.equals("2")){ //prints error if user does NOT enter 1 or 2
					System.out.println("Please enter a valid option"); //prints statement stating error
					userInput = getUserInput(); //keep asking for user input until 1 or 2 is entered
				} //ending bracket
				if (userInput.equals("1")){ //if user input's string equals 1
					UI.changeCurrentPage(3); //change to page 3 in UserInterface
				} //ending bracket
				else if (userInput.equals("2")){ //if user input's string equals 2
					UI.changeCurrentPage(2); //change to page 2 in UserInterface
				} //ending bracket
			} //ending bracket
			else if(UI.getCurrentPage() == 2){ //if current page is page 2 in UserInterface
				String userInput = getUserInput(); //get string as user input
				if(userInput.contains(" ")){ //if user input contains blank/space
					System.out.println("Username cannot have spaces."); //Prints out to notify user of error
					System.out.println("Enter another username"); //Prints out asking string as user input
					userInput = getUserInput(); //get new string as user input
				}
				if(userInput.length() > 25){ //if user input string is longer than 25 characters
					System.out.println("Username cannot be longer than 25 characters."); //Prints out to notify user of eror
					System.out.println("Enter another username."); //Prints out asking string as user input
					userInput = getUserInput(); //get new string as user input
				}
				appendToFile(userInput, "Users.txt");  //if there are no errors, puts user input's string into file Users.txt
				System.out.println("Username successfully added"); //prints string to output
				System.out.println("Your username is " + userInput); //displays username entered by user
				UserList.add(new ShoppingCart(userInput)); //adds shopping object to UserList arraylist
				CreateShoppingCart(userInput); //calls CreateShoppingCart function defined by user input string
				wait(2500); //program waits 2500 milliseconds
				UI.changeCurrentPage(1); //change to page 1 in UserInterface
			}
			
			else if(UI.getCurrentPage() == 3){ //if current page is page 3 in UserInterface
					String userInput = getUserInput(); //get string as user input
					if (UsernameExists(userInput)){ //if input exists in Users.txt file
						for (int i = 0; i < UserList.size(); i++){ //loops through size of arraylist Userlist
				            if (UserList.get(i).getUsername().equals(userInput)){ //if user input is found in Userlist arraylist
				                currentUserIndex = i; //assign to currentUserIndex variable
				            }
				        }
						System.out.println("Hello Mr." + UserList.get(currentUserIndex).getUsername()); //prints out Mr. followed by username
						addExistingItemsToShoppingCarts(UserList.get(currentUserIndex)); //calls addExistingItemsToShoppingCarts function with parameter of username
						wait(1500); //program wait 1500 milliseconds
						UI.changeCurrentPage(5); //change to page 5 in UserInterface
					}
					else { //if user input does not exist as username
						System.out.println("No Access"); //prints out No Access
						wait(1500); //program wait 1500 milliseconds
						UI.changeCurrentPage(1); //change to page 1 in UserInterface
					}
				}
			else if(UI.getCurrentPage() == 5){ //if current page is page 5 in UserInterface
				String userInput = getUserInput(); //get string as user input
				while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")){ //prints error if user does NOT enter 1, 2, 3 or 4
					System.out.println("Please enter a valid option"); //prints out to user stating error
					userInput = getUserInput(); //get new string as user input
				}
				if (userInput.equals("1")){ //if user input equals 1
					UI.changeCurrentPage(6); //change to page 6 in UserInterface
				}
				else if (userInput.equals("2")){ //if user input equals 2
					UI.changeCurrentPage(7); //change to page 7 in UserInterface
				}
				else if (userInput.equals("3")){ //if user input equals 3
					UI.changeCurrentPage(1); //change to page 1 in UserInterface
				}
				else if (userInput.equals("4")){ //if user input equals 4
					UI.changeCurrentPage(11); //change to page 11 in UserInterface
				}
			}
			else if(UI.getCurrentPage() == 6){ //if current page is page 6 in UserInterface
				String userInput = getUserInput(); //get string as user input
				while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("-1")){ //prints error if user does NOT enter -1, 1, or 2
					System.out.println("Please enter a valid option"); //prints out to user stating error
					userInput = getUserInput(); //get new string as user input
				}
				if (userInput.equals("1")){ //if user input equals 1
					UI.changeCurrentPage(8); //change to page 8 in UserInterface
				}
				else if (userInput.equals("2")){ //if user input equals 2
					UI.changeCurrentPage(9); //change to page 9 in UserInterface
				}
				else if (userInput.equals("-1")){ //if user input equals -1
					UI.changeCurrentPage(5); //change to page 5 in UserInterface
				}
			}
			else if(UI.getCurrentPage() == 7){ //if current page is page 7 in UserInterface
				System.out.println(UserList.get(currentUserIndex).getContent()); //prints content of current user from UserList arraylist
				System.out.println("Type -1 to return to previous menu"); //prints out statement
				String userInput = getUserInput(); //get string as user input
				while (!userInput.equals("-1")){ //prints error if user does NOT enter -1
					System.out.println("Please enter a valid option"); //prints out to user stating error
					userInput = getUserInput(); //get new string as user input
				}
				if (userInput.equals("-1")){ //if user input equals -1
					UI.changeCurrentPage(5); //change to page 5 in UserInterface
				}
			}
			
			else if(UI.getCurrentPage() == 8){ //if current page is page 8 in UserInterface
				Boolean validInput = false;   //declares and initiates boolean value validInput to be False
				while(validInput == false){ //loops as long as validInput remains false
					String userInput = getUserInput(); //get string as user input
					while(!isNumeric(userInput)){ //if userinput is NOT a number
						System.out.println("Please enter a serial number."); //asks to enter serial number
						userInput = getUserInput(); //get new string as user input
					}
					if (userInput.equals("-1")){  //if user enters -1
						UI.changeCurrentPage(6); //change to page 6 in UserInterface
						validInput = true; //completes loop by changing conditional statement
					}
					for (int i = 0; i < UI.readables.length; i++){ //loops through the list of readables of the UI object
						if (UI.readables[i] != null){ //if the line read is NOT empty/null
							if(UI.readables[i].sNo == Integer.parseInt(userInput)){ //if serial number of readable item is equal to userInput
								validInput = true; //completes loop by changing conditional statement
								System.out.println("Enter quantity: "); //prints out asking for user to enter quantity
								String quantity = getUserInput(); //gets user input of a string
								while(!isNumeric(quantity) || Integer.parseInt(quantity) < 1 || Integer.parseInt(quantity) > UI.readables[i].quantity){
									//prints error if quantity is not a number, if quantity entered is less than 1 or if there is less quantity available in readables than asked for
									if (!isNumeric(quantity)){ //if quantity entered is NOT a number
										System.out.println("Please enter a quantity."); //prints out statement
										quantity = getUserInput(); //get new string as user input
									}
									else if (quantity.equals("-1")){ //if user input equals -1
										UI.changeCurrentPage(6); //change to page 6 in UserInterface
										break; //ends loop
									}
									else if (Integer.parseInt(quantity) < 0){ //if quantity entered is less than 0
										System.out.println("Please enter a positive number"); //prints out statement
										quantity = getUserInput();//get new string as user input
									}
									else if (Integer.parseInt(quantity) < 1){ //if quantity entered is less than 1
										System.out.println("Please enter a quantity that is not zero."); //prints out statement
										quantity = getUserInput();//get new string as user input
									}
									else if(Integer.parseInt(quantity) > UI.readables[i].quantity){ //if quantity entered is less than quantity available in readables than asked for
										System.out.println("There is not enough of that item in stock."); //prints out statement
										System.out.println("Please enter a valid quantity"); //prints out statement
										quantity = getUserInput(); //get new string as user input
									}
								}
								if (quantity.equals("-1")){ //if quantity entered equals -1
									break; //break from loop
								} 
								System.out.println(); //prints line
								UI.readables[i].quantity = UI.readables[i].quantity - Integer.parseInt(quantity); //takes quantity away from readables stock
								UserList.get(currentUserIndex).addItem(UI.readables[i], Integer.parseInt(quantity)); //make it so that it actually changes the associate text document
								changeReadableItemQuantity(UI.readables[i]); //adds quantity to user's shopping cart
								System.out.println("sNo:" + UI.readables[i].sNo + " " + UI.readables[i].Name + " has been successfully added to your cart"); //prints statement 
								System.out.println("Quantity added: " + Integer.parseInt(quantity)); //prints quantity added to shopping cart to verify
								System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut:"); //print statement
								userInput = getUserInput(); //get new string as user input
								while (!userInput.equals("-2") && !userInput.equals("0")){ //if quantity entered does NOT equal to -2 or 0
									System.out.println("Please enter a valid option"); //prints statement asking for valid input
									userInput = getUserInput(); //get new string as user input
								}
								if (userInput.equals("-2")){ //if user enters -2
									UI.changeCurrentPage(6); //change to page 6 in UserInterface
								}
								else if (userInput.equals("0")){ //if user enters 0
									UI.changeCurrentPage(10); //change to page 0 in UserInterface
								}
							}	
						}
					}
					if(validInput == false){ //if validInput is false from the start
						System.out.println("The imputted value is not an available serial number."); //prints statement
						System.out.println("Enter another serial number."); //prints statement
					}
				}
			}

			else if(UI.getCurrentPage() == 9){ //if current page is page 9 in UserInterface
				Boolean validInput = false;  //declares and initiates boolean value validInput to be False
				while(validInput == false){ //loops as long as validInput remains false
					String userInput = getUserInput(); //get string as user input
					while(!isNumeric(userInput)){ //if userinput is NOT a number
						System.out.println("Please enter a serial number.");  //asks to enter serial number
						userInput = getUserInput(); //get new string as user input
					}
					if (userInput.equals("-1")){ //if user input equals -1
						UI.changeCurrentPage(6); //change to page 6 in UserInterface
						validInput = true; //change validInput boolean to True
					}
					for (int i = 0; i < UI.audioProducts.length; i++){ //loops through length of audio entries
						if (UI.audioProducts[i] != null){ //if entry is NOT empty/null
							if(UI.audioProducts[i].sNo == Integer.parseInt(userInput)){ //if serial number entered is equal to user input
								validInput = true; //completes loop
								System.out.println("Enter quantity: "); //print statement asking for quantity
								String quantity = getUserInput(); //declare new string as user input
								while(!isNumeric(quantity) || Integer.parseInt(quantity) < 1 || Integer.parseInt(quantity) > UI.audioProducts[i].quantity){
									//prints error if quantity is not a number, if quantity entered is less than 1 or if there is less quantity available in audios than asked for
									if (!isNumeric(quantity)){ //if quantity entered is NOT a number
										System.out.println("Please enter a quantity"); //prints statement
										quantity = getUserInput(); //get new string as user input
									}
									else if (quantity.equals("-1")){ //if user input equals -1
										UI.changeCurrentPage(6); //change to page 6 in UserInterface
										break; //stops loop
									}
									else if (Integer.parseInt(quantity) < 0){ //if quantity entered is less than 0
										System.out.println("Please enter a positive number"); //prints statement stating error
										quantity = getUserInput();//get new string as user input
									}
									else if (Integer.parseInt(quantity) < 1){ //if quantity entered is less than 1
										System.out.println("Please enter a quantity that is not zero."); //prints statement stating error
										quantity = getUserInput(); //get new string as user input
									}
									else if(Integer.parseInt(quantity) > UI.audioProducts[i].quantity){ //if there is less quantity available in audios than asked for
										System.out.println("There is not enough of that item in stock."); //prints statement stating error
										System.out.println("Please enter a valid quantity"); //prints statement
										quantity = getUserInput(); //get new string as user input
									}
								}
								if (quantity.equals("-1")){ //if user inputs -1
									break; //breaks from loop
								} 
								System.out.println(); //prints line
								UI.audioProducts[i].quantity = UI.audioProducts[i].quantity - Integer.parseInt(quantity); //takes quantity away from audio product's entries quantity
								UserList.get(currentUserIndex).addItem(UI.audioProducts[i], Integer.parseInt(quantity)); //make it so that it actually changes the associate text document
								changeAudioItemQuantity(UI.audioProducts[i]); //adds quantity into user's shopping cart
								System.out.println("sNo:" + UI.audioProducts[i].sNo + " " + UI.audioProducts[i].Name + " has been successfully added to your cart"); //prints statement on item added 
								System.out.println("Quantity added: " + Integer.parseInt(quantity)); //prints statement on quantity added
								System.out.println("Press -2 to Continue Shopping or Press 0 to CheckOut:"); //prints statement
								userInput = getUserInput(); //get string as user input
								while (!userInput.equals("-2") && !userInput.equals("0")){ //if user input is NOT equal to -2 and 0
									System.out.println("Please enter a valid option"); //print statement
									userInput = getUserInput(); //get string as user input
								}
								if (userInput.equals("-2")){ //if user input equals -2
									UI.changeCurrentPage(6); //change to page 6 in UserInterface
								}
								else if (userInput.equals("0")){ //if user input equals 0
									UI.changeCurrentPage(10); //change to page 10 in UserInterface
								}
							}	
						}
					}
					if(validInput == false){ //if validInput remains false from the start 
						System.out.println("The imputted value is not an available serial number."); //print statement stating error
						System.out.println("Enter another serial number."); //print statement
					}
				}
			}
			
			else if(UI.getCurrentPage() == 10){ //if current page is page 9 in UserInterface
				double priceOfCDsAndBooks = 0; //declares and initiates the double variable priceOfCDsAndBooks
				double TotalPrice = 0; //declares and initiates the double variable TotalPrice
				double environmentTax = 0; //declares and initiates the double variable environmentTax
				double HST = 0; //declares and initiates the double variable HST
				double Shipping = 0; //declares and initiates the double variable Shipping
				
				ShoppingCart user = UserList.get(currentUserIndex); //assigns username to ShoppingCart object user
				System.out.println("Billing Information: "); //print statement
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "Name","","Quantity", "Price")); //creates the string format for entries
				for (int i = 0; i < user.itemList.length; i++){ //iterates through list of items purchased by user
					if (user.itemList[i] != null){ //as long as the entry is NOT empty/null
						System.out.println(String.format("%-27s %-5s %-10s %-10s", user.itemList[i].Name ,"", user.itemList[i].quantityOrdered, user.itemList[i].getPrice())); //prints entry with formatting
						TotalPrice += user.itemList[i].quantityOrdered*user.itemList[i].getPrice(); //sums up total for the total price of all item purchased
						if(user.itemList[i].Type.equals("CD") || user.itemList[i].Type.equals("Book")){ //if item contains a CD or books
							priceOfCDsAndBooks = user.itemList[i].quantityOrdered * user.itemList[i].getPrice() * 0.1; //calculates shipping of each item  by multiplying quantity by price time 10%
							Shipping += priceOfCDsAndBooks; //adds to total shipping
						}
					}
				}
				System.out.println(); //prints line
				environmentTax = TotalPrice*0.02; //calculates evironmental tax that applies to CD and books
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "Environment Tax" ,"2%", "", environmentTax)); //sets printout format
				HST = TotalPrice*0.13; //sets value for HST
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "HST" ,"13%", "", HST)); //prints statement formatted to standards
				System.out.println(); //print lines
				/*Shipping = priceOfCDsAndBooks*0.1;*/
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "Shipping" ,"10%", "", Shipping));  //prints statement formatted to standards
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "" ,"", "", "_________"));//prints statement formatted to standards
				TotalPrice += environmentTax + HST + Shipping; //calculates total price, with HST tax, and shipping
				System.out.println(String.format("%-27s %-5s %-10s %-10s", "Total:" ,"", "", TotalPrice));//prints statement formatted to standards
				System.out.println("Are you sure you want to pay? yes or no."); //prints statement
				String userInput = getUserInput(); //ask for string user input
				userInput = userInput.toLowerCase(); //change to lowercase only
				while (!userInput.equals("yes") && !userInput.equals("no")){ //if user input is NOT the strings "yes" or "no"
					System.out.println("Please enter a valid option"); //prints error
					userInput = getUserInput(); //ask for new string user input
					userInput = userInput.toLowerCase(); //change input to all lowercase
				}
				if (userInput.equals("yes")){ //if user input is "yes"
					System.out.println("Confirmation ID: " + " U" + ConfirmationIDCounter); //prints statement formatted to standards
					ConfirmationIDCounter++; //increment confirmation counter
					writeToFile(Integer.toString(ConfirmationIDCounter), "ConfirmationNumber.txt"); //writes the previous value to the new file ConfirmationNumber.txt
					System.out.println("Items shipped to Mr. " + user.getUsername()); //prints statement formatted to standards
					emptyShoppingCart(user); //shopping cart for user is cleared
					wait(4000); //wait 4 seconds
					UI.changeCurrentPage(5); //change to page 5 in UserInterface
				}
				if (userInput.equals("no")){ //if user input is "no"
					UI.changeCurrentPage(5); //change to page 5 in UserInterface
				}
				
			}
			else if(UI.getCurrentPage() == 11){ //change to page 10 in UserInterface
				showItemsBought(); //call showItemsBought function
				System.out.println("Type -1 to return to previous page."); //print statement
				String userInput = getUserInput(); //ask for string user input 
				while (!userInput.equals("-1")){ //if user unput is NOT -1
					System.out.println("Please enter a valid option"); //ask for valid input
					userInput = getUserInput(); //ask new for string user input
				}
				if (userInput.equals("-1")){ //if user input equal -1
					UI.changeCurrentPage(5); //change to page 5 in UserInterface
				}
			}
		}
	}
	
	public static void showItemsBought(){ //creates function showItemsBought
		Boolean Content = false; //declares and initiates boolean variable Content as False
		try //try structure
		{
			File file = new File("ItemsBought.txt"); //creates new text file called ItemsBought.txt
			BufferedReader reader = new BufferedReader(new FileReader(file)); //creates new reader
			String line = ""; //creates empty string 
			while((line = reader.readLine()) != null) //as long as line is NOT empty/null
			{
				Content = true; //Content is assigned True value
				break; //break from loop
			}
			reader.close(); //close the file reader
		}
		catch (IOException ioe) //catch
		{
			System.out.println(""); //print empty string
		}
		
		try //try structure
		{
			File file = new File("ItemsBought.txt"); //creates new text file called ItemsBought.txt
			BufferedReader reader = new BufferedReader(new FileReader(file)); //creates new reader
			String line = ""; //creates empty string 
			if (Content){ //Content if True
				System.out.println(String.format("%-20s %-25s %-10s", "Confirmation ID" ,"Product Name", "Total")); //creates format of print out
				System.out.println(); //print line
				while((line = reader.readLine()) != null) //while the line being read is not empty/null
				{
					String[] ar; //declares string array ar
					if( (ar = line.split(",")).length == 3){ //if ar split by the comma becomes length of 3
						System.out.println(String.format("%-20s %-25s %-10s", ar[0] ,ar[1], ar[2])); //print out correct format of line
					}
					if( (ar = line.split(",")).length == 2){ //if ar split by the comma becomes length of 2
						System.out.println(String.format("%-20s %-25s %-10s", "" ,ar[0], ar[1])); //print out correct format of line
					}
					if( (ar = line.split(",")).length == 1){ //if ar split by the comma becomes length of 1
						System.out.println(String.format("%-20s %-25s %-10s", "" ,"", "________")); //print out correct format of line
						System.out.println(String.format("%-20s %-25s %-10s", "" ,"", ar[0])); //print out correct format of line
						System.out.println(); //print line
					}
				}
			}
			else{ //otherwise
				System.out.println("There are no previous purchases"); //print statement
			}
			reader.close(); //close reader
		} 
		catch (IOException ioe) //catch structure for IOException
		{
			System.out.println("There are no previous purchases"); //prints error
		}
			
	}
	
	public static void emptyShoppingCart(ShoppingCart user){ //creates function emptyShoppingCart with parameter of object ShoppingCart
		//% is used to split the string when reading the text file
		double totalPrice = 0; //declares and initiates totalPrice to be 0
		Boolean Bool = true; //declares and initiates boolean Bool to be True
		for (int i = 0; i < user.itemList.length; i++){ //iterates through list of items from user
			if (user.itemList[i] != null){ //if entry is NOT empty/null
				if (Bool){ //if Bool is True
					if(user.itemList[i].Type.equals("CD") || user.itemList[i].Type.equals("Book")){ //if the types are CDs and Books
						appendToFile("U" + Integer.toString(ConfirmationIDCounter) + "," + user.itemList[i].Name + "," + (user.itemList[i].quantityOrdered*user.itemList[i].getPrice())*1.25,"ItemsBought.txt"); 
						//add the information of books/cd to file with specific formatting
						totalPrice += user.itemList[i].quantityOrdered * user.itemList[i].getPrice()*1.25; //find total price of tax and shipping
						Bool = false; //change Bool to false to end loop
					}
					else{ //otherwise
						appendToFile("U" + Integer.toString(ConfirmationIDCounter) + "," + user.itemList[i].Name + "," + (user.itemList[i].quantityOrdered * user.itemList[i].getPrice())*1.15, "ItemsBought.txt");
						//add the information of books/cd to file with specific formatting
						totalPrice += user.itemList[i].quantityOrdered * user.itemList[i].getPrice()*1.15; //find total price of tax and shipping
						Bool = false; //change Bool to false to end loop
					}
				}else{ //otherwise
					if(user.itemList[i].Type.equals("CD") || user.itemList[i].Type.equals("Book")){ //if the types are CDs and Books
						appendToFile(user.itemList[i].Name + "," + (user.itemList[i].quantityOrdered * user.itemList[i].getPrice())*1.25,"ItemsBought.txt"); 
						//add the information of books/cd to file with specific formatting
						totalPrice += user.itemList[i].quantityOrdered * user.itemList[i].getPrice()*1.25; //find total price of tax and shipping
					}
					else{ //otherwise
						appendToFile(user.itemList[i].Name + "," + (user.itemList[i].quantityOrdered * user.itemList[i].getPrice())*1.15, "ItemsBought.txt");
						//add the information of books/cd to file with specific formatting
						totalPrice += user.itemList[i].quantityOrdered * user.itemList[i].getPrice()*1.15; //find total price of tax and shipping
					}
				}
			}
		}
		appendToFile(Double.toString(totalPrice), "ItemsBought.txt"); //add the price to the file ItemBought.txt
		writeToFile("","Cart_[" + user.getUsername() + "].txt"); //makes corresponding shoppingcart text file blank 
	}
	
	public static void appendToFile(String input, String file){ //creates function called appendToFile with two parameters, input string and the file to input into
		try{ //try
			FileWriter fw = new FileWriter(file, true); //Example: "Users.txt", creates new file writer
			BufferedWriter buffer = new BufferedWriter(fw); //creates new buffered writer
			PrintWriter pw = new PrintWriter(buffer);  //creates new Print writer
			pw.println(input); //print the string input into the file
			pw.close(); //close the file
		} catch (IOException e){ //catch IOException error
			System.out.println("ERROR"); //print statement
		}
	}
	
	public static void appendToFileOnSameLine(String input, String file){  //creates function called appendToFileOnSameLine with two parameters, input string and the file to input into
		try{ //try
			FileWriter fw = new FileWriter(file, true); //Example: "Users.txt", creates new file writer
			BufferedWriter buffer = new BufferedWriter(fw); //creates new buffered writer
			PrintWriter pw = new PrintWriter(buffer); //creates new Print writer
			pw.print(input); //print the string input into the file
			pw.close(); //close the file
		} catch (IOException e){ //catch IOException error
			System.out.println("ERROR"); //print statement
		}
	}
	
	public static void writeToFile(String input, String file){ //creates function called writeToFile with two parameters, input string and the file to input into
		try //try
		{
			FileWriter writer = new FileWriter(file); //creates new filewriter object
			writer.write(input); //write input into file writer
			writer.close(); //close object
		}
		catch (IOException ioe) //catch IOException error
		{
			ioe.printStackTrace(); //prints to display error
		}
	}
	
	public static void createConfirmationNumberTextFile(){ //create function called createConfirmationNumberTextFile
		File f = new File("ConfirmationNumber.txt"); //creates new text file ConfirmationNumber.txt
		if(f.exists() && !f.isDirectory()) {  //if file f exist and if f is NOT in directory
			try{ //try structure
				FileReader fr = new FileReader("ConfirmationNumber.txt"); //creates new file reader reading through ConfirmationNumber.txt
				BufferedReader br = new BufferedReader(fr); //creates new buffered reader
				String str; //declares string str
				while((str = br.readLine())!= null){ //loops while str equals the read line and does NOT equal null
					str.trim(); //trim the string str
					ConfirmationIDCounter = Integer.parseInt(str); //sets ConfirmationIDCounter to strimmed string parsed to integer
				}			
				br.close(); //close buffered reader
			} catch (IOException e){ //catch IOExcpetion error 
				System.out.println("File not found"); // prints statement
			}
		}else{ //otherwise
			writeToFile("1000", "ConfirmationNumber.txt"); //write string "1000" to text file
			ConfirmationIDCounter = 1000; //set ConfirmationIDCounter to 1000
		}
	}
	
	public static String getUserInput(){ //create function getUserInput that returns a string 
		Scanner scanner = new Scanner(System.in); //creates new scanner 
		String userInput = scanner.next(); //get user input string to put into userInput
		return userInput.replace(".",""); //returns string, removes period at the end of number "ex. changes 1. to 1"
	}
	
	
	public static boolean UsernameExists(String username){ //create function that take in a string and returns a boolean, test if the username exist in Users.txt
		try{ //try
			FileReader fr = new FileReader("Users.txt"); //creates file reader object through text file Users.txt
			BufferedReader br = new BufferedReader(fr); //creates new buffered reader
			String str; //declares new string str
			while((str = br.readLine())!= null){ //loops while string equal the read line and is NOT empty/null
				if (str.equals(username)){ //if the string equal username
					return true; //return True as boolean
				}
			}			
			br.close(); //close buffered reader
		} catch (IOException e){ //catch IOException error
			System.out.print(""); //print blank 
		}
		return false; //return False as boolean
	}
	
	public static void wait(int milliseconds){ //create a function that pauses the program for a certain period of time
		try { //try
			Thread.sleep(milliseconds); //program sleeps for certain milliseconds
		} catch (InterruptedException e) { //catch InterruptedException error
			// TODO Auto-generated catch block
			e.printStackTrace(); //print error statement
		}
	}
	
	public static void CreateExistingUsersAndShoppingCarts(){ //create function that creates existing users and shopping carts
		try{ //try
			FileReader fr = new FileReader("Users.txt"); //create new file reader object reading through Users.txt
			BufferedReader br = new BufferedReader(fr); //create buffered reader
			String str; //declares string str
			while((str = br.readLine())!= null){ //loops while string equal the read line and is NOT empty/null
				UserList.add(new ShoppingCart(str)); //add shopping cart onto each user in UserList arraylist
				CreateShoppingCart(str); //create shopping cart
			}			
			br.close(); //close buffered reading
		} catch (IOException e){ //catch IOException error
			System.out.print(""); //print blank
		}
	}
	
	public static void CreateShoppingCart(String username){ //create function that creates shopping carts with parameter of username
		try{ //try
			FileWriter fw = new FileWriter("Cart_[" + username + "].txt", true);  //creates file writer object and write into a new text file
			BufferedWriter buffer = new BufferedWriter(fw); //creates read buffered writer object
			PrintWriter pw = new PrintWriter(buffer); //creates read print writer object
			pw.close(); //close print writer object
		} catch (IOException e){ //catch IOException error
			System.out.println("ERROR"); //print statement
		}
	}
	
	public static void addExistingItemsToShoppingCarts(ShoppingCart User){ //create function that adds items to shopping carts with parameter of username
		try{
			FileReader fr = new FileReader("Cart_[" + User.getUsername() + "].txt"); //creates file writer object and write into a new text file
			BufferedReader br = new BufferedReader(fr); //creates read buffered writer object
			String str; //create string
			while((str = br.readLine())!= null){ //loops while string equal the read line and is NOT empty/null
				String[] ar = str.split(","); //split string by commas
				 //lines to get rid of white space because if you don't you'll get an error when converting from string to integer
				ar[0] = ar[0].replaceAll("\\s+",""); //modifies array element
				ar[1] = ar[1].replaceAll("\\s+",""); //modifies array element
				ar[2] = ar[2].trim(); //removes leading or trailing spaces
				ar[3] = ar[3].replaceAll("\\s+",""); //modifies array element
				ar[4] = ar[4].trim(); //modifies array element
				ar[5] = ar[5].replaceAll("\\s+",""); //modifies array element
				ar[6] = ar[6].trim(); //modifies array element
				
				if(ar[6].equals("Book")){ //if index 6 of ar equal string Book
/*					Book book = new Book();
					book.sNo = Integer.parseInt(ar[1]);
					book.Name = ar[2];
					book.price = Integer.parseInt(ar[3]);
					book.datePurchased = ar[4];
					book.quantityOrdered = Integer.parseInt(ar[5]);
					book.Type = ar[6];	
					User.addItem(book, Integer.parseInt(ar[5]));*/
					
					User.itemList[User.counter] = new Book(); //create new Book
					User.itemList[User.counter].sNo = Integer.parseInt(ar[1]); //implements serial number as index 1 of ar
					User.itemList[User.counter].Name = ar[2]; //implements name as index 2 of ar
					User.itemList[User.counter].price = Integer.parseInt(ar[3]); //implements price as index 3 of ar
					User.itemList[User.counter].datePurchased = ar[4]; ////implements date purchased as index 4 of ar
					User.itemList[User.counter].quantityOrdered = Integer.parseInt(ar[5]);//implements quantity ordered as index 5 of ar
					User.itemList[User.counter].Type = ar[6];	//implements type as index 6 of ar
					User.counter++; //increments counter
				}
				else if(ar[6].equals("eBook")){ //if index 6 of ar equal string eBook
					User.itemList[User.counter] = new eBook(); //create new eBook
					User.itemList[User.counter].sNo = Integer.parseInt(ar[1]); //implements serial number as index 1 of ar
					User.itemList[User.counter].Name = ar[2]; //implements name as index 2 of ar
					User.itemList[User.counter].price = Integer.parseInt(ar[3]);//implements price as index 3 of ar
					User.itemList[User.counter].datePurchased = ar[4];//implements date purchased as index 4 of ar
					User.itemList[User.counter].quantityOrdered = Integer.parseInt(ar[5]);//implements quantity ordered as index 5 of ar
					User.itemList[User.counter].Type = ar[6];	//implements type as index 6 of ar
					User.counter++; //increments counter
					/*eBook book = new eBook();
					book.sNo = Integer.parseInt(ar[1]);
					book.Name = ar[2];
					book.price = Integer.parseInt(ar[3]);
					book.datePurchased = ar[4];
					book.quantityOrdered = Integer.parseInt(ar[5]);
					book.Type = ar[6];	
					User.addItem(book, Integer.parseInt(ar[5]));*/
				}
				else if(ar[6].equals("CD")){ //if index 6 of ar equal string CD
				/*	CD book = new CD();
					book.sNo = Integer.parseInt(ar[1]);
					book.Name = ar[2];
					book.price = Integer.parseInt(ar[3]);
					book.datePurchased = ar[4];
					book.quantityOrdered = Integer.parseInt(ar[5]);
					book.Type = ar[6];	
					User.addItem(book, Integer.parseInt(ar[5]));*/
					User.itemList[User.counter] = new CD(); //create new CD
					User.itemList[User.counter].sNo = Integer.parseInt(ar[1]); //implements serial number as index 1 of ar
					User.itemList[User.counter].Name = ar[2];//implements name as index 2 of ar
					User.itemList[User.counter].price = Integer.parseInt(ar[3]);//implements price as index 3 of ar
					User.itemList[User.counter].datePurchased = ar[4];//implements date purchased as index 4 of ar
					User.itemList[User.counter].quantityOrdered = Integer.parseInt(ar[5]);//implements quantity ordered as index 5 of ar
					User.itemList[User.counter].Type = ar[6];	//implements type as index 6 of ar
					User.counter++; //increments counter
				}
				else if(ar[6].equals("MP3")){ //if index 6 of ar equal string MP3
					/*MP3 book = new MP3();
					book.sNo = Integer.parseInt(ar[1]);
					book.Name = ar[2];
					book.price = Integer.parseInt(ar[3]);
					book.datePurchased = ar[4];
					book.quantityOrdered = Integer.parseInt(ar[5]);
					book.Type = ar[6];	
					User.addItem(book, Integer.parseInt(ar[5]));*/
					User.itemList[User.counter] = new MP3(); //create new MP3
					User.itemList[User.counter].sNo = Integer.parseInt(ar[1]); //implements serial number as index 1 of ar
					User.itemList[User.counter].Name = ar[2];//implements name as index 2 of ar
					User.itemList[User.counter].price = Integer.parseInt(ar[3]);//implements price as index 3 of ar
					User.itemList[User.counter].datePurchased = ar[4];//implements date purchased as index 4 of ar
					User.itemList[User.counter].quantityOrdered = Integer.parseInt(ar[5]);//implements quantity ordered as index 5 of ar
					User.itemList[User.counter].Type = ar[6];	//implements type as index 6 of ar
					User.counter++; //increments counter
				}
				
			}		
			br.close(); //buffered read close
		} catch (IOException e){ //catch IOException error
			System.out.println("File not found"); //print statement
		}	
		
		/*for (int j = 0; j < User.itemList.length; j++){
			if (User.itemList[j] != null){
				System.out.println(String.format("%-27s %-5s %-10s %-10s", User.itemList[j].Name ,"", User.itemList[j].quantityOrdered, User.itemList[j].getPrice()));
			}
		}*/
	}
	
	public static void changeReadableItemQuantity(Readable readable){ //creates function that changes quantity
		if (readable.Type.equals("Book")){ //if readable type is Book
			updateTextDocument(readable,"Books.txt"); //updateTextDocument function is called on it
		}
		if (readable.Type.equals("eBook")){//if readable type is eBook
			updateTextDocument(readable,"eBooks.txt"); //updateTextDocument function is called on it
		}
	}
	
	public static void changeAudioItemQuantity(Audio audio){ //creates function that changes quantity
		if (audio.Type.equals("CD")){//if readable type is CD
			updateTextDocument(audio,"CDs.txt");//updateTextDocument function is called on it
		}
		if (audio.Type.equals("MP3")){//if readable type is MP3
			updateTextDocument(audio,"MP3.txt");//updateTextDocument function is called on it
		}
	}
	
	public static void updateTextDocument(Item item,String Filename){ //creates function that updates the text document with new quantity
		String string; //declares string
		String newString; //declares string
		try{ //try
			FileReader fr = new FileReader(Filename); //creates file reader object using filename
			BufferedReader br = new BufferedReader(fr); //creates new buffered reader
			String str; //declares string str
			while((str = br.readLine())!= null){ //loops while string equal the read line and is NOT empty/null
				String[] ar = str.split(","); //splits string by commas
				ar[0] = ar[0].trim(); //trim array
				ar[0] = ar[0].replaceAll("\\s+",""); //replace sections by spaces
				if (item.sNo == Integer.parseInt(ar[0])){ //if the serial number is equal to the first number on the line
					ar[4] = Integer.toString(item.quantity); //change element 4 to string
					newString = ar[0] + "," + ar[1] + "," + ar[2] + "," + ar[3] + "," + ar[4];  //creates new string with the array elements
					ReplaceLine(Filename, str, newString); //replace file's str value with new string
				}
			}			
			br.close(); //close buffered reader
		} catch (IOException e){ //catch IOException error
			System.out.println("File not found"); //print statement
		}
	}
	
	public static void ReplaceLine(String FileName, String Line, String newLine){ //creates replace line function with multiple parameters
		{
	         try //try
	             {
	             File file = new File(FileName); //creates new file
	             BufferedReader reader = new BufferedReader(new FileReader(file)); //creates new buffered reader object
	             String line = "", oldtext = ""; //declares two string
	             while((line = reader.readLine()) != null) //loops while string equal the read line and is NOT empty/null
	                 {
	                 oldtext += line + "\r\n"; //add \r\n to the line
	             }
	             reader.close(); //close reader boject

	             //To replace a line in a file
	             String newtext = oldtext.replace(Line, newLine); //replaces oldline with new line
	            
	             FileWriter writer = new FileWriter(FileName); //creates file writer object
	             writer.write(newtext); //write newtext onto the file
	             writer.close(); //close writer object
	         } 
	         catch (IOException ioe) //catch IOException error
	             {
	             ioe.printStackTrace(); //prints error statement
	         }
	     }
	}

	public static boolean isNumeric(String str)  //creates function that takes in a string to test if it's a number and returns a bool
	{  
	  try  //try 
	  {  
	    int d = Integer.parseInt(str);  //tries to parse the string
	  }  
	  catch(NumberFormatException nfe)  //catch error if string can NOt be converted into number
	  {  
	    return false;  //return bool False
	  }  
	  return true;  //if it can convert without exception, then return true
	}
	

}
