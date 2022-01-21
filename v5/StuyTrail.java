import java.io.*;
import java.util.*;

public class StuyTrail {

        private String name;
        private int hp, money, trainOdds, time, place;
        private boolean playerD;

        Scanner scannerString = new Scanner (System.in);
        Scanner scannerInt = new Scanner (System.in);
        ArrayList<String> inventory = new ArrayList<String>();

        public StuyTrail() {
                name = "";
                hp = 10;
                money = ( (int) Math.random() * 20 ) + 5;;
                time = 0;
                place = 0;
                playerD = false;
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

        public void home() {
//<<<<<<< HEAD
                System.out.println( "You wake up and get out of bed, ready to start the day.\nChoose what to bring with you to school:" );
//=======
                //System.out.println( "You're in your home.\nWhat should you bring out with you?\nChoose Wisely, you only have 5 inventory slots: " );
//>>>>>>> 95a3ad18e326358a65a42955ce3d9ffd602e7707
                ArrayList<String> itemsHome = new ArrayList<String>();
                itemsHome.add("Student ID");
                itemsHome.add("Student Metrocard");
                itemsHome.add("Jacket");
                itemsHome.add("Gym Clothes");
                //ADD MORE ITEMS HERE
                for ( int i = 0; i < 2; i++ ) {
                        for ( int p = 0; p < itemsHome.size(); p++ ) {
                                System.out.println( p+1 + ": " +  itemsHome.get(p));//ASDASUDJASIUD ADD STUFF HERE 
                        }
                        int itemIndex = Integer.parseInt( scannerInt.nextLine() );
                        inventory.add( itemsHome.get( itemIndex - 1 ) );
                        System.out.println("\nYou've added " + itemsHome.get(itemIndex - 1) + " to your inventory\n");
                        itemsHome.remove( itemIndex - 1 );
                }
                System.out.println("You have chosen: " + inventory + "\n Time to go to the train");
        } //ends home()

        public void train() {
                System.out.println("----------------------------------------------------------------");
                System.out.println("\nYou arrive at the train");
                disaster();
                place = 3;
                System.out.println("You see a 5 dollar bill lying at the edge of the station, do you reach out for it?");
                System.out.println("1: Yes \n2: No");
                int answer = Integer.parseInt( scannerInt.nextLine() );
                disaster();
        } //ends train()

        public void disaster() {
                int Odds = ((int) (Math.random() * 100 ));
                if ( Odds < 2 ) {
                        System.out.println("You died of dysentery");
                        playerD = true;
                }
                if ( Odds < 5 ) {
                        if ( place == 2 ) {
                              System.out.println("You got hit by a train, must suck");
                                playerD = true;
                        }
                } else if ( Odds < 20 ) {
                        if( place == 2 ){
                                time = time - 5;
                                System.out.println( "Train 5 minutes late, time before late: " + time );
                                // train late
                        }
                } else if ( Odds < 30 ) {  // no jacket
                        if( place == 3 ) {
                                System.out.println("You got the fiver!");
                                money = money + 5;
                        }
                } else if (Odds >= 30 ) {
			if ( place == 3 ) {
				System.out.println("\nSomeone got to the fiver first, and in the kerfuffle you dropped " + inventory.get(0));
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
