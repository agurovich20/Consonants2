import java.io.*;
import java.util.*;

public class StuyTrail {
	private String name;
	private int hp;
	private int money;
	private String[5] inventory;
	private int Odds;

	Scanner scannerString = new Scanner (System.in);
	Scanner scannerInt = new Scanner (System.in);

	public StuyTrail {
		name = "";
		hp = 10;
		money = ((int) Math.random() * 20 ) + 5;
	}

	public void startGame() {
		System.out.println( "Welcome to the StuyTrail: have fun getting to Stuyvesant!" );
		nameCharacter();
		home();
	}

	public String nameCharacter() {
		System.out.println( "What is your name?" );
		name = scannerString.nextLine();
		//System.out.println( name );
		return name;
	}

	public String getBal() {
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
	}

	public void home() {
		System.out.println( "You're in your home.\nChoose what to bring:" );
		String[] itemsHome = new String[10];
		itemsHome = { "Student ID", "Student Metrocard", "Jacket", "Gym Clothes", "" }
		for ( int i = 0; i < 5; i++ ) {
			
			
	}

	public void disaster() {
	Odds = ((int) Math.random() *100 );
	if ( Odds < 5 )
		// hit by train
	else if ( Odds < 20 )
		// train late
	else if ( Odds < 30 ) && // no jacket
		// freezing
		// subtract health
	else if ( Odds < 40 ) 
		//lost ID_card
	else
		// train on time
}
	}
}
