import java.io.*;
import java.util.*;

public class StuyTrail {

	private String name;
	private int turn, health, money, arrival, trainOdds, time, place, lost, hpchng;

	Scanner scannerString = new Scanner (System.in);
	Scanner scannerInt = new Scanner (System.in);
	ArrayList<String> inventory = new ArrayList<String>();

	public StuyTrail() {
		name = "";
		health = 100;
		money = 0;
		time = 40;
		place = 0;
		hpchng = 0;
		turn = 0;
	} //contructor

	public void startGame() {
		System.out.println( "Welcome to the StuyTrail: have fun getting to Stuyvesant!\n" );
		nameCharacter();
		home();
		train();
	} //ends startGame()

	public String nameCharacter() {
		System.out.println( "What is your name?" );
		name = scannerString.nextLine();
		System.out.println("\nHello " + name + ", Your journey begins now!\n" );
		return name;
	} //ends nameCharacter()

	public void getBal() {
		String ret = "You now have ";
		if ( money >= 50 )
			ret += "$" + money + " in your wallet. You're rich!";
		else if ( money >= 20 )
			ret += "$" + money + " in your wallet. Nice!";
		else if ( money > 0 )
			ret += "$" + money + " in your wallet.";
		else
			ret = "Well, You're broke now.";
		System.out.println( ret + "\n" );
	} //ends getBal()

	public void getInv() {
		System.out.println( "You reach into your bag and find..." );
		for ( int i = 0; i < inventory.size(); i++ ) {
			System.out.println( "\t" + inventory.get(i) );
		}
	}

	public void death() {
		System.out.println("\n-----------------------GAME-OVER-----------------------");
		System.exit(0);
	}//end death()

	public void healthchng( int chng ) {
		if( chng > 0 )
			health = health + chng;
		else if ( ( health - chng ) <= 0 )
			death();
		else
			health = health - chng;

        }//end healthchng

	public void time(){
		time = time - 2;
		if ( time <= 0 ){
			System.out.println( "YOU RAN OUT OF TIME, YOU ARE LATE" );
			death();
		}
	}

	public void home() {
		System.out.println( "You wake up and get out of bed, ready to start the day.\nChoose two items to bring with you to school:" );
		ArrayList<String> itemsHome = new ArrayList<String>();
		itemsHome.add( "Student ID" );
		itemsHome.add( "Student Metrocard" );
		itemsHome.add( "Jacket" );
		itemsHome.add( "Gym Clothes" );
		itemsHome.add( "Cell Phone" );
		itemsHome.add( "Mask" );
		for ( int i = 0; i < 2; i++ ) {
			for ( int p = 0; p < itemsHome.size(); p++ ) {
				System.out.println( p+1 + ": " +  itemsHome.get(p) );
			}
			int itemIndex = Integer.parseInt( scannerInt.nextLine() );
			time();
			inventory.add( itemsHome.get( itemIndex - 1 ) );
			System.out.println("\nYou've added " + itemsHome.get(itemIndex - 1) + " to your inventory\n");
			itemsHome.remove( itemIndex - 1 );
		}
		System.out.println( "You have chosen: " + inventory + "\n" );
		System.out.println( "You also grab your wallet." );
		money = ( (int) Math.random() * 21 ) + 5;
		getBal();
		System.out.println( "\nTime to go to the subway. You have " + time + " minutes to get to school.\n" );
	} //ends home()

        public void train() {
                System.out.println("-------------------------------------------------------");
		boolean hasC = false;//metrocard variable
		for ( int x = 0; x < inventory.size(); x++ ) {
			if ( inventory.get(x).equals("Student Metrocard") ) {
    				hasC = true;
    			}
		}
		if( hasC == true ) {//have card
    			System.out.println( "good thing you've got your student metrocard" );
		} else {//dont have card
    			System.out.println( "yikes, no card? gonna have to pay... or jump" );
    			System.out.println( "\n1: Jump \n2: Pay ($3)" );
	        	int ans3 = Integer.parseInt( scannerInt.nextLine() );
			if ( ans3 == 1 ) {
				System.out.println( "\nyou make the daring jump AND...\n\n" );
				disaster();
    			} else if ( ans3 == 2 ) {
				money -= 3;
				System.out.println( "\n(-$3) better to pay for the metrocard than miss the train..." );
				getBal();
			}
		}//end of metrocard scenario
		arrival = ( (int) Math.random() * 8 ) + 1;
		place = 1;
                System.out.println( "You arrive safely at the subway station! The next train arrives in " + arrival + " minutes.\n" );
		time = time - arrival;
		place = 2;
                disaster();//chance for delay or you get hit by the train
                place = 3;
                System.out.println( "You see a 5 dollar bill lying at the edge of the station, do you reach out for it?" );
                System.out.println( "1: Yes \n2: No" );
                int answer = Integer.parseInt( scannerInt.nextLine() );
		if( answer == 1 ){
			disaster();//fiver odds
			time();
		}
                System.out.println( "\nThe train arrives and you get on. You have " + time + " minutes left." );

/*---------------------------------------------------------------- train arrives ------------------------------------------*/

		System.out.println( "\nWhile waiting in the train you spot an ad, it promotes a game to win actual money!" );
			boolean has = false;//cell phone variable
		for ( int i = 0; i < inventory.size(); i++ ) {
			if ( inventory.get(i).equals("Cell Phone") ){
    				System.out.println("\nAnd you have your phone, play the game?");
	                	System.out.println("1: Yes \n2: No");
	                	int ans2 = Integer.parseInt( scannerInt.nextLine() );
				if( ans2 == 1 ) {
                        		has = true;
				}
			}
		} // ends checking loop
		if( has == false ){
			System.out.println( "\nthat game was probably a virus anyway..." );
        	} else if ( has == true ) {
        		time();
        		game();
        	}//ends cell phone scenario

	} //ends train()

	public void game() {
		GuessNumber game = new GuessNumber(1, 30);
		System.out.println( "Guess the number in 3 tries or less" );
		game.play();
		if( game._guessCtr < 4 ) {
			money = money + 5;
			System.out.println( "you win the game and get 5 bucks!" );
			getBal();
		} else {
			System.out.println( "oof, too many tries there, no money for you" );
			getBal();
		}
	}

	public void disaster() {
		int Odds = ( (int) ( Math.random() * 100 ) + 1 );
		if ( Odds < 4 ) {
			System.out.println( "You died of dysentery" );
			health = 0;
			death();
		}
		if ( Odds < 10 ) {
			if ( place == 2 ) {
				System.out.println( "You got hit by a train, must suck" );
				health = 0;
				death();
			}
		} else if ( Odds < 20 ) {
			if( place == 2 ){
				time = time - 5;
				System.out.println( "Your train has been delayed by 5 minutes! You only have " + time + " before you are late." );
				// train late
			}
		} else if ( Odds < 30 ) {  // no jacket
		/*	if ((place == 1) && has4 = false) {
				System.out.println("looks like its cold out today, a jacket would've helped");
				System.out.println("\nhealth loss of 5");
				healthchng( 5 );
			}*/
			if ( place == 3 ) {
				System.out.println( "You got the fiver! Nice!" );
				money = money + 5;
			}
			else if (place == 0) {
				System.out.println( "Oh Yiiiiiiikes, yikes dude, oh my god your face, yikes." );
				System.out.println( "yeah uh, yeah health loss of 10 for that one" );
				health = health - 10;
			}
		} else if ( Odds >= 30 ) {
			if ( place == 3 ) {
				lost = 5;
				System.out.println( "\nSomeone got to the fiver first, and in the kerfuffle you dropped your " + inventory.get(0) + "!" );
				System.out.println( "\nYou also lost " + lost + " of your health." );
				healthchng(5);
			} else if( place == 0 ) {
				System.out.println( "Ayooooo you made it, free ride" );
			}
		}

                // freezing
                // subtract health
//      else if ( Odds < 40 ) 
                //lost ID_card
//      else
                // train on time
        } //ends disaster()

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
} //ends class
