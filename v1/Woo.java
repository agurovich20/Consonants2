import java.io.*;
import java.util.*;
//import java.util.Scanner;

public class Woo {
	public static void main(String[] args) {
		StuyTrail game = new StuyTrail();

		System.out.println( game.startGame() );

		Scanner scannerString = new Scanner (System.in);
		Scanner scannerInt = new Scanner (System.in);

		game.nameCharacter( scannerString );
		

	}
}
