//import java.io;
//import java.util;
import java.util.Scanner;

public class Woo {
	public static void main(String[] args) {
		StuyTrail run = new StuyTrail();

		run.startGame();

		Scanner in = new Scanner (System.in);
		String choice = in.nextLine(); //choice is their answer for scanner

		run.choiceInt(choice);
	}
}
