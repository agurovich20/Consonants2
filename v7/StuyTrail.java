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
                money = ( (int) Math.random() * 20 ) + 5;;
                time = 40;
                place = 0;
		hpchng = 0;
		turn = 0;
        } //contructor

        public void startGame() {
                System.out.println( "Welcome to the StuyTrail: have fun getting to Stuyvesant!" );
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
                for ( int i = 0; i < inventory.size(); i++ ) {
                        System.out.println( "\t" + inventory.get(i) );
                }
        }

        public void death() {
                System.out.println("-----------------------GAME-OVER-----------------------");
                        System.exit(0);
        }//end death()

        public void healthchng(int chng){
		if(chng > 0)
                        	health = health + chng;
                else if (health - chng <= 0)
                       	 	death();
                else
                health = health - chng;

        }//end healthchng
	public void time(){
		time = time - 2;
		if (time <=0){
			System.out.println("YOU RAN OUT OF TIME, YOU ARE LATE");
			death();
		}
	}

	public void game(){
		GuessNumber game = new GuessNumber(1, 30);

                game.play();
	}

        public void home() {
//<<<<<<< HEAD
                System.out.println( "You wake up and get out of bed, ready to start the day.\nChoose two items to bring with you to school:" );
//=======
                //System.out.println( "You're in your home.\nWhat should you bring out with you?\nChoose Wisely, you only have 5 inventory slots: " );
//>>>>>>> 95a3ad18e326358a65a42955ce3d9ffd602e7707
                ArrayList<String> itemsHome = new ArrayList<String>();
                itemsHome.add("Student ID");
                itemsHome.add("Student Metrocard");
                itemsHome.add("Jacket");
                itemsHome.add("Gym Clothes");
                itemsHome.add("Cell Phone");
                itemsHome.add("Mask");
                //ADD MORE ITEMS HERE
                for ( int i = 0; i < 2; i++ ) {
                        for ( int p = 0; p < itemsHome.size(); p++ ) {
                                System.out.println( p+1 + ": " +  itemsHome.get(p));//ASDASUDJASIUD ADD STUFF HERE 
                        }
                        int itemIndex = Integer.parseInt( scannerInt.nextLine() );
			time();
                        inventory.add( itemsHome.get( itemIndex - 1 ) );
                        System.out.println("\nYou've added " + itemsHome.get(itemIndex - 1) + " to your inventory\n");
                        itemsHome.remove( itemIndex - 1 );
                }
                System.out.println("You have chosen: " + inventory + "\nTime to go to the subway. You have " + time + " minutes to get to school.\n");
        } //ends home()
	
        public void train() {
                System.out.println("-------------------------------------------------------");

		arrival = (int)(Math.random() *8);
		place = 1;
                System.out.println("You arrive safely at the subway station! There is a train in " + arrival + " minutes.\n");
		time = time - arrival;
		place = 2;
                disaster();
                place = 3;
                System.out.println("You see a 5 dollar bill lying at the edge of the station, do you reach out for it?");
                System.out.println("1: Yes \n2: No");
                int answer = Integer.parseInt( scannerInt.nextLine() );
              if( answer == 1){
                        disaster();
			time();
		}
                System.out.println("\nThe train arrives and you get on. You have " + time + " minutes left.");
		System.out.println("\nWhile waiting in the train you spot an ad, it promotes a game to win actual money!");
		for ( int i = 0; i < inventory.size(); i++ ) {
		  if ( inventory.get(i).equals("Cell Phone") ){
    			System.out.println("\nAnd you have your phone, play the game?");
	                System.out.println("1: Yes \n2: No");
	                int ans2 = Integer.parseInt( scannerInt.nextLine() );
        		      if( ans2 == 1){
                        	time();
				game();
				}
		  }
		else System.out.println("\n\nwell maybe if you had your cell phone....");
        	}
	} //ends train()

        public void disaster() {
                int Odds = ((int) (Math.random() * 100 ));
                if ( Odds < 5 ) {
                        System.out.println("You died of dysentery");
			health = 0;
                        death();
                }
                if ( Odds < 10 ) {
			if (place == 2){
                        	System.out.println("You got hit by a train, must suck");
                        	health = 0;
                        	death();
			}
                } else if ( Odds < 20 ) {
                        if( place == 2 ){
                                time = time - 5;
                                System.out.println( "Your train has been delayed by 5 minutes! You only have " + time + " before you are late.");
                                // train late
                        }
                } else if ( Odds < 30 ) {  // no jacket
			System.out.println("test");
		//	if (place == 1) {
		//		System.out.println("looks like its cold out today, a jacket would've helped");
		//		System.out.println("health loss of 5 per turn");
		//		healthchng( 5 );
		//	}
                        if ( place == 3 ) {
                                System.out.println("You got the fiver!");
                                money = money + 5;
                        }

                } else if (Odds >= 30 ) {
			if ( place == 3 ) {
				lost = 5;
				System.out.println("\nSomeone got to the fiver first, and in the kerfuffle you dropped your " + inventory.get(0) + "!");
				System.out.println("\nYou also lost " + lost + "% of your health.");
				healthchng(5);
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
