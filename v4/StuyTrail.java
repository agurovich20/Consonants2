import java.io.*;
import java.util.*;

public class StuyTrail {
	private String name;
	private int hp;
	private int money;
	private int trainOdds;

	Scanner scannerString = new Scanner (System.in);
	Scanner scannerInt = new Scanner (System.in);
	ArrayList<String> inventory = new ArrayList<String>();

	public StuyTrail() {
		name = "";
		hp = 10;
		money = ( (int) Math.random() * 20 ) + 5;;
	} //contructor

	public void startGame() {
		System.out.println( "Welcome to the StuyTrail: have fun getting to Stuyvesant!" );
		nameCharacter();
		home();
	} //ends startGame()

	public String nameCharacter() {
		System.out.println( "What is your name?" );
		name = scannerString.nextLine();
		System.out.println("\nHello " + name + ", Your journey begins now\n" );
		return name;
	} //ends nameCharacter()

	public void getBal() {
		String ret = "";
		if ( money >= 50 )
			ret = "You have $" + money + " in your wallet. You're rich!";
		else if ( money >= 20 )
			ret = "You have $" + money + " in your wallet. Nice!";
		else if ( money > 0 )
			ret = "You have $" + money + " in your wallet.";
		else
			ret = "You're broke.";
		System.out.println( ret );
	} //ends getBal()

	public void getInv() {
		System.out.println( "You reach into your bag and find..." );
	}

	public void home() {
		System.out.println( "You're in your home.\nWhat should you bring out with you?\nChoose Wisely, you only have 5 inventory slots: " );
		ArrayList<String> itemsHome = new ArrayList<String>();
		itemsHome.add("Student ID");
		itemsHome.add("Student Metrocard");
		itemsHome.add("Jacket");
		itemsHome.add("Gym Clothes");
		for ( int i = 0; i < 5; i++ ) {
			for ( int p = 0; p < itemsHome.size(); p++ ) {
				System.out.println( p+1 + ":" +  itemsHome.get(p));//ASDASUDJASIUD ADD STUFF HERE 
			}
			int itemIndex = Integer.parseInt( scannerInt.nextLine() );
		 	inventory.add( itemsHome.get( itemIndex - 1 ) );
			System.out.println("\nYou've added, " + itemsHome.get(itemIndex - 1) + ", to your inventory\n");
			itemsHome.remove( itemIndex - 1 );

		}
	} //ends home()

	/*public void train() {
		trainOdds = ((int) Math.random() * 100 );
		if ( trainOdds < 5 )
			//hit by train
		else if ( trainOdds < 20 )
			// train late add time
		else
			// train on time
		}
	}*/
}
