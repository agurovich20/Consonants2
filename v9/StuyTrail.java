
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
		health = 25;
		money = 0;
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
		if( chng < 0 )
			health = health + chng;
		else if ( ( health + chng ) <= 0 )
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
                System.out.println( "You've made it to the subway station! The next train arrives in " + arrival + " minutes.\n" );
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

		System.out.println( "\n\nWhile waiting in the train you spot an ad, it promotes a game to win actual money!" );
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
			System.out.println( "\nUnfortunately, you didn't bring your phone. That game was probably a virus anyway..." );
        	} else if ( has == true ) {
        		time();
        		game();
        	}//ends cell phone scenario

		//start charity encounter
		System.out.println("\n\nA man approaches you asking for some money, do you choose to be charitable? ($3)");
		System.out.println("\n1: Yes \n2: No");
		int ans2 = Integer.parseInt( scannerInt.nextLine() );
		if( ans2 == 1 ) {
			if( money >= 3 ){
				System.out.println( "\nYou give the man 2 dollars" );
				money -= 3;
				System.out.println( "\nThe karma from your charity got you 5 bonus minutes of time!" );
				time += 5;
			} else
                                System.out.println( "damn dude you're broker than him, you don't have cash for that" );
		} else
			System.out.println( "\nWoooooooow, ok man, ur choice whatever" );
			//end charity encounter
			time();
	} //ends train()

	public void walk() {
		System.out.println("-------------------------------------------------------");
		System.out.println("\nYou get to your stop and exit the train");
		time();
		place = 4;
		disaster();
		System.out.println("After walking the sacred two blocks from the train to Stuyvesant, you get to the bridge with a only " + time + " to spare, \nBut it isn't over yet!");
	}

	public void stuy() {
		System.out.println("-------------------------------------------------------");
		System.out.println("\nAs you approach the daunting scanner ladies, the question comes. \n\nAre you prepared?");
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
    			System.out.println( "Oh no.... you dont have your ID\n but you HAVE to get in, you've got a Calc test dude!!\n\n So make the choice." );
    			System.out.println( "\n1: FIGHT \n2: Pay, the cowards way out ($5)" );
	        	int ans6 = Integer.parseInt( scannerInt.nextLine() );
			if ( ans6 == 1 ) {
				System.out.println( "It had to come to this" );
				FIGHT();
    			} else if ( ans6 == 2 ) {
				if((money - 5) > 0){
				System.out.println( "ok nerd, you get in. " );
				}
				else {
				System.out.println("you don't got the cash for that, looks like you're FIGHTING");
				FIGHT();
				}
			}
		}
		System.out.println("\nWait\nWas today a Gym day?");
		place = 9;
		disaster();
	}

	public void FIGHT() {
		System.out.println("you've got " + health + " health left \n She has 20, you can do it");
		while(healthB > 0){
			for(int c = 5; c < 8; c++){
				place = c;
				disaster();
			}
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
		int Odds = ( (int) ( Math.random() * 100 ) + 1 );
		if ( Odds < 4 ) {
			if (place < 5){
			System.out.println( "You died of dysentery" );
			healthchng(-50);
			}
		}//close odds 3
		if ( Odds < 10 ) {
			if ( place == 2 ) {
				System.out.println( "You got hit by a train, must suck" );
				healthchng(-2000);
			}//close p 2
			else if ( place == 4){
				System.out.println("The sidewalks are really busy and that health line is filled with a whole lot of anti-maskers....");
				boolean hasM = false;
				for ( int b = 0; b < inventory.size(); b++ ) {//check for mask
					if ( inventory.get(b).equals("Mask") ){
                       				 hasM = true;
					}
				} // ends checking loop

				if (hasM == true){
					System.out.println("\nBut you've got your mask! Good choice.");
				}
				else{
					System.out.println("\nToday was NOT a good day to forget a mask for your travel.");
					System.out.println("Ur getting sick so 10 health loss for that");
					healthchng(-10);
				}
			}//close p4
			else if (place == 5 ){
				System.out.println("AND YOU GET HER BY SUPRISE, HEAVY RIGHT HOOK \n 10 PTS OF DAMAGE");
					healthB -= 10;
			}//close p5

		}//close Odds 10
		else if ( Odds < 20 ) {
			if( place == 2 ){
				time = time - 5;
				System.out.println( "Your train has been delayed by 5 minutes! You only have " + time + " before you are late." );
				// train late
			}//close p2
			else if( place == 7){
				System.out.println("YOU GO FOR A HEADSLAM AND IT FLOWS THROUGH \n THATS A CRUSHING 10 PTS OF DAMAGE");
				healthB -= 10;
			}//close p7
		} //close odds 20 
		else if ( Odds < 30 ) {
			if ( place == 3 ) {
				System.out.println( "You got the fiver! Nice!" );
				money = money + 5;
			}//close p3
			else if (place == 0) {
				System.out.println( "Oh Yiiiiiiikes, yikes dude, oh my god your face, yikes." );
				System.out.println( "yeah uh, yeah health loss of 10 for that one" );
				healthchng(-10);
			}//close p0
			else if ( place == 6){
				System.out.println("YOU SWEEP THE LEG, SHE SLAMS ON HER BACK \nITS 5 PTS OF DAMAGE");
				healthB -= 5;
			}//clsoe p6
		}//close odds 30
		else if( Odds < 50){
			if ( place == 9){
				System.out.println("It was\n");
				boolean hasGS = false;
				for ( int q = 0; q < inventory.size(); q++ ) {//check for mask
					if ( inventory.get(q).equals("Gym Clothes") ){
                       				 hasGS = true;
					}
				} // ends checking loop
				if ( hasGS == true){
					System.out.println("BUT YOU WERE PREPARED \n YOU HAVE NOW TRULY WON STUYTRAILS");
					System.out.println("----------------------------------------------------------------------------------------------");
				}
			}//ends p 9
		} //close odds 50
		else if( Odds >= 50 ) {
			if ( place == 9){
				System.out.println("Oh no, it wasnt, nevermind");
			}//close p9
		} //close odds 50
		else if ( Odds >= 30 ) {
			if ( place == 3 ) {
				lost = 5;
				System.out.println( "\nSomeone got to the fiver first, and in the kerfuffle you dropped your " + inventory.get(0) + "!" );
				System.out.println( "\nYou also lost " + lost + " of your health." );
				healthchng(5);
			} //close p3
			else if( place == 0 ) {
				System.out.println( "Ayooooo you made it, free ride\n\n" );
			}//close p 0
			  else if (place == 6){
			  	System.out.println("SHE PREDICTS YOYUR LEG SWEEP AND COUNTERS WITH A KNEE \nA SLAMMING 5 PTS OF DAMAGE TO YOU");
			  	healthchng(-5);
			} // close p6
		} //close odds 70
		else if (Odds >= 20){
			if (place == 7){
				System.out.println("YOU GO FOR THE HEADSLAM BUT SHE DUCKS AND UPPERCUTS YOU 5 FEET UP \n THATS A CLASHING 10 PTS OF DAMAGE");
				healthchng(-10);
			}//close p7
		} //close odds 80
		else if (Odds >= 10){
			if (place == 5){
				System.out.println("BUT SHE SAW THIS COMING, SWING ON YOU FOR 5 PTS OF DAMAGE");
					healthchng(-5);
			}//close p5
		}//close odds 90
	}//close method
} //ends class
