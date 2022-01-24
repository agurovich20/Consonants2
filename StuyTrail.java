import java.io.*;
import java.util.*;

public class StuyTrail {

	private String name;
	private int healthB, turn, health, money, arrival, trainOdds, time, place, lost, hpchng;
	Scanner scannerString = new Scanner (System.in);
	Scanner scannerInt = new Scanner (System.in);
	ArrayList<String> inventory = new ArrayList<String>();

	public StuyTrail() {
		name = "";
		health = 30;
		money = 0;
		arrival = 0;
		time = 40;
		place = 0;
		hpchng = 0;
		turn = 0;
		healthB = 20;
	} //contructor

	public void startGame() {
		System.out.println( "\n\nWelcome to the StuyTrail: have fun getting to Stuyvesant!\n" );
		nameCharacter();
		home();
		train();
		walk();
		stuy();
	} //ends startGame()

	public String nameCharacter() {
		System.out.println( "What is your name?\n" );
		name = scannerString.nextLine();
		System.out.println("\nHello " + name + ", Your journey begins now!\n" );
		return name;
	} //ends nameCharacter()

	public void getBal() {
		String ret = "  You now have ";
		if ( money >= 10 )
			ret += "$" + money + " in your wallet. You're rich!";
		else if ( money > 0 )
			ret += "$" + money + " in your wallet.";
		else
			ret = "Well, You're broke now.";
		System.out.println( ret );
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
		health += chng;
		if( health <= 0 )
			death();
		else
			System.out.println( "  You have " + health + " health left." );

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
		for ( int i = 0; i < 3; i++ ) {
			for ( int p = 0; p < itemsHome.size(); p++ ) {
				System.out.println( p+1 + ": " +  itemsHome.get(p) );
			}
			System.out.println( "" );
			int itemIndex = Integer.parseInt( scannerInt.nextLine() );
			time();
			inventory.add( itemsHome.get( itemIndex - 1 ) );
			System.out.println("\nYou've added " + itemsHome.get(itemIndex - 1) + " to your inventory\n");
			itemsHome.remove( itemIndex - 1 );
		}
		System.out.println( "You have chosen: " + inventory + "\n" );
		System.out.println( "You also grab your wallet." );
		money = (int) ( Math.random() * 11 );
		getBal();
		System.out.println( "\nTime to go to the subway. You have " + time + " minutes to get to school.\n" );
	} //ends home()

        public void train() {
                System.out.println("-------------------------------------------------------");
		scannerString.nextLine(); //press enter to continue
		place = 0; //train is place = 0
		boolean hasC = false;//metrocard variable
		for ( int x = 0; x < inventory.size(); x++ ) {
			if ( inventory.get(x).equals("Student Metrocard") ) {
    				hasC = true;
    			}
		}
		if( hasC == true ) {//have card
    			System.out.println( "Good thing you've got your student metrocard" );
		} else {//dont have card
    			System.out.println( "Yikes, no card? gonna have to pay... or jump" );
    			System.out.println( "\n1: Jump \n2: Pay ($3)\n" );
	        	int ans3 = Integer.parseInt( scannerInt.nextLine() );
			if ( ans3 == 1 ) {
				System.out.println( "\nYou make the daring jump AND...\n\n" );
				disaster();
    			} else if ( ans3 == 2 ) {
				if ( money >= 3 ) {
					money -= 3;
					System.out.println( "\n(-$3) better to pay for the metrocard than miss the train..." );
					getBal();
				} else {
					System.out.println( "\nYou don't have enough to pay for the metrocard... Guess you'll have to jump." );
					disaster();
				}
			}
		}//end of metrocard scenario
		arrival = (int) ( Math.random() * 8 + 2 );
		place = 1;
                System.out.println( "\nYou've made it to the subway station! The next train arrives in " + arrival + " minutes.\n" );
		time = time - arrival;
		place = 2;
                disaster();//chance for delay or you get hit by the train
                place = 3;
                System.out.println( "\nYou see a 5 dollar bill lying at the edge of the station, do you reach out for it?" );
                System.out.println( "1: Yes \n2: No\n" );
                int answer = Integer.parseInt( scannerInt.nextLine() );
		if( answer == 1 ){
			disaster();//fiver odds
			time();
		}
                System.out.println( "\nThe train arrives and you get on. You have " + time + " minutes left." );
		scannerString.nextLine(); //press enter to continue

/*---------------------------------------------------------------- train arrives ------------------------------------------*/

		System.out.println( "\n\nWhile waiting in the train you spot an ad, it promotes a game to win actual money!" );
		boolean has = false;//cell phone variable
		for ( int i = 0; i < inventory.size(); i++ ) {
			if ( inventory.get(i).equals("Cell Phone") ){
    				System.out.println( "\nAnd you have your phone, play the game?" );
				System.out.println( "1: Yes \n2: No\n" );
	                	int ans2 = Integer.parseInt( scannerInt.nextLine() );
				if( ans2 == 1 )
                        		has = true;
			}
		} // ends checking loop
		if( has == false ) {
			System.out.println( "\nUnfortunately, you didn't have your phone. That game was probably a virus anyway..." );
			scannerString.nextLine(); //press enter to continue
		} else if ( has == true ) {
        		time();
        		game();
        	}//ends cell phone scenario

		//start charity encounter
		System.out.println("\nA man approaches you asking for some money, do you choose to be charitable? ($3)");
		System.out.println("\n1: Yes \n2: No\n");
		int ans2 = Integer.parseInt( scannerInt.nextLine() );
		if( ans2 == 1 ) {
			if( money >= 3 ){
				System.out.println( "\nYou give the man 2 dollars" );
				money -= 3;
				System.out.println( "\nThe karma from your charity got you 5 bonus minutes of time!" );
				time += 5;
			} else
                                System.out.println( "\ndamn dude you're broker than him, you don't have cash for that" );
		} else
			System.out.println( "\nWoooooooow, ok man, ur choice whatever" );
			//end charity encounter
			time();
	} //ends train()

	public void walk() {
		System.out.println( "-------------------------------------------------------" );
		System.out.println( "\nYou get to your stop and exit the train" );
		scannerString.nextLine(); //press enter to continue
		time();
		place = 4;
		disaster();
		System.out.println( "After walking the sacred two blocks from the train to Stuyvesant, you get to the bridge with only " + time + " minutes to spare, \nBut it isn't over yet!" );
	}

	public void stuy() {
		System.out.println( "-------------------------------------------------------" );
		scannerString.nextLine(); //press enter to continue
		System.out.println( "\nAs you approach the daunting scanner ladies, the question comes...\nAre you prepared?" );
		scannerString.nextLine(); //press enter to continue
		place = 5;
		boolean hasID = false;//metrocard variable
		for ( int l = 0; l < inventory.size(); l++ ) {
			if ( inventory.get(l).equals("Student ID") ) {
    				hasID = true;
    			}
		}
		if( hasID == true ) {//have card
    			System.out.println( "AND YES YOU ARE, you swipe with confidence and \n\nYOU. \nARE. \n\nIN" );
		} else {//dont have card
    			System.out.println( "Oh no.... you dont have your ID\n but you HAVE to get in, you've got a Calc test dude!!" );
			scannerString.nextLine(); //press enter to continue
			System.out.println( "\nSo make the choice." );
    			System.out.println( "\n1: FIGHT \n2: Pay, the cowards way out ($5)\n" );
	        	int ans6 = Integer.parseInt( scannerInt.nextLine() );
			if ( ans6 == 1 ) {
				System.out.println( "\nIt had to come to this. For DEATH or GLORY!" );
				FIGHT();
    			} else if ( ans6 == 2 ) {
				if ( ( money - 5 ) >= 0 ) {
					System.out.println( "\nok nerd, you get in. " );
				} else {
					System.out.println( "\nYou don't got the cash for that, looks like you're FIGHTING\n" );
					FIGHT();
				}
			}
		}
		System.out.println("\nWait... Was today a Gym day?");
		place = 9;
		disaster();
	}

	public void FIGHT() {
		System.out.println("You've got " + health + " health left.\nShe has 20, you can do it!\n");
		while( healthB > 0 ) {
			place = (int) ( Math.random() * 3 + 5 );
			disaster();
		}
		System.out.println("\n\n\n\nYouve done it. \n\nyou really made it. \n\nYou have passed the StuyTrail. Now can you pass your classes?");
		System.out.println("\nHow do you feel? \n1: Good \n2: Bad");
		int ans7 = Integer.parseInt( scannerInt.nextLine() );
		System.out.println("Cool, cool I don't care");
	}

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
		int Odds = (int) ( Math.random() * 100 + 1 );
		int damageLight = (int) ( Math.random() * 3 + 4 );
		int damageHeavy = (int) ( Math.random() * 5 + 6 );

		if ( place == 0 ) { //IN THE TRAIN STATION
			if ( Odds < 4 ) {
				System.out.println( "You died of dysentery." );
				death();
			} else if ( Odds <= 30 ) {
				System.out.println( "Oh yiiiiiiikes, yikes dude, oh my god your face, yikes." );
				System.out.println( "yeah uh, yeah health loss of 10 for that one." );
				healthchng(-10);
			} else {
				System.out.println( "Ayooooo you made it, free ride\n\n" );
			}
		} else if ( place == 2 ) { //WAITING FOR TRAIN
			if ( Odds < 4 ) {
				System.out.println( "You died of dysentery." );
				death();
			} else if ( Odds <= 10 ) {
				System.out.println( "You got hit by a train, must suck" );
				death();
			} else if ( Odds <= 20 ) {
				time -=5;
				System.out.println( "Your train has been delayed for 5 minutes! You only have " + time + " minutes before you are late." );
			}
		} else if ( place == 3 ) { //GETTING 5 DOLLAR BILL
			if ( Odds < 4 ) {
				System.out.println( "You died of dysentery." );
				death();
			} else if ( Odds <= 40 ) {
				System.out.println( "You got the fiver! Nice!" );
				money += 5;
				getBal();
			} else {
				lost = (int) (Math.random() * 3 + 5);
				System.out.println( "\nSomeone got to the fiver first, and in the kerfuffle you dropped your " + inventory.get(0) + "1" );
				inventory.remove(0);
				System.out.println( "\nYou also lost " + lost + " health." );
				healthchng( lost * -1 );
			}
		} else if ( place == 4 ) { //WALKING OUTSIDE
			if ( Odds < 4 ) {
				System.out.println( "You died of dysentery." );
				death();
			} else if ( Odds <= 14 ) {
				System.out.println("The sidewalks are really busy and that health line is filled with a whole lot of anti-maskers....");
				boolean hasM = false;
				for ( int b = 0; b < inventory.size(); b++ ) {//check for mask
					if ( inventory.get(b).equals("Mask") ) {
						hasM = true;
					}
				} //ends checking loop
				if ( hasM == true )
					System.out.println( "\nBut you've got your mask! Good choice." );
				else {
					System.out.println( "\nToday was NOT a good day to forget a mask for your travels." );
					System.out.println( "(-10 health) You start coughing in public and you get weird looks." );
					healthchng(-10);
				}
			}
		} else if ( place == 5 ) { //FIGHT
			if ( Odds <= 70 ) {
				System.out.println( "SHE THROWS A LEFT HOOK AT YOU FOR " + damageLight + " PTS OF DAMAGE" );
				healthchng( damageLight * -1 );
				System.out.println( "  She has " + healthB + " health left." );
			} else {
				System.out.println( "AND YOU GET HER BY SUPRISE, HEAVY RIGHT HOOK FOR " + damageHeavy + " PTS OF DAMAGE" );
				healthB -= damageHeavy;
				healthchng(0);
				System.out.println( "  She has " + healthB + " health left." );
			}
		} else if ( place == 6 ) { //FIGHT
			if ( Odds <= 70 ) {
				System.out.println( "SHE PREDICTS YOUR LEG SWEEP AND COUNTERS WITH A KNEE, A SLAMMING " + damageLight + " PTS OF DAMAGE TO YOU" );
				healthchng( damageLight * -1 );
				System.out.println( "  She has " + healthB + " health left." );
			} else {
				System.out.println( "YOU GO FOR A LEG SWEEP, SHE SLAMS ON HER BACK FOR " + damageLight + " PTS OF DAMAGE" );
				healthB -= damageLight;
				healthchng(0);
				System.out.println( "  She has " + healthB + " health left." );
			}
		} else if ( place == 7 ) { //FIGHT
			if ( Odds <= 60 ) {
				System.out.println( "YOU GO FOR THE HEADSLAM BUT SHE DUCKS AND UPPERCUTS YOU 5 FEET UP. THATS A CLASHING " + damageHeavy + " PTS OF DAMAGE" );
				healthchng( damageHeavy * -1 );
				System.out.println( "  She has " + healthB + " health left." );
			} else {
				System.out.println( "YOU GO FOR A HEADSLAM AND IT FLOWS THROUGH. THATS A CRUSHING " + damageHeavy + " PTS OF DAMAGE" );
				healthB -= damageHeavy;
				healthchng(0);
				System.out.println( "  She has " + healthB + " health left." );
			}
		} else if ( place == 9 ) { //SCHOOL (GYM DAY?)
			if ( Odds <= 50 ) {
				System.out.println( "Oh no, it wasnt, nevermind" );
				System.out.println( "The rest of the day goes without incident. (YOU WIN)" );
				System.out.println( "----------------------------------------------------------------------------------------------" );
			} else {
				System.out.println("It was.\n");
				boolean hasGS = false;
				for ( int q = 0; q < inventory.size(); q++ ) {//check for gym clothes
					if ( inventory.get(q).equals("Gym Clothes") )
						hasGS = true;
				} //ends checking loop
				if ( hasGS == true){
					System.out.println( "BUT YOU WERE PREPARED \n YOU HAVE NOW TRULY WON STUYTRAILS" );
					System.out.println( "----------------------------------------------------------------------------------------------" );
				} else { //hasGS == false
					System.out.println( "You go through your bag but you can't find your gym clothes." );
					System.out.println( "You ask a dude next to you if you can use his.\n" );
					if ( Odds <= 65 ) {
						System.out.println( "\nTo your surprise, he takes his shirt off and gives it to you.\nAn absolute hero. (YOU WIN)" );
						System.out.println( "----------------------------------------------------------------------------------------------" );
					} else {
						System.out.println( "He doesn't have his either. You're out of luck.\n" );
						System.out.println( "It's your third absence this semester.\nYou fail gym and you have to repeat the year. (YOU LOSE)" );
						System.out.println( "----------------------------------------------------------------------------------------------" );
					}
				}
			}
		}
	scannerString.nextLine(); //press enter to continue after every disaster
	}//close method disaster() (the whitespace and code was the disaster, tried to make it as readable as possible ~mw)

} //ends class
