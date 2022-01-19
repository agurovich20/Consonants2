import java.io.*;
import java.util.*;

public class StuyTrail {
	private String name;
	

	public String startGame() {
		return "Welcome to the StuyTrail: have fun getting to Stuyvesant!\nPress enter to continue.";
	}

	public String nameCharacter( Scanner input ) {

		System.out.println( "What is your name?" );
		name = input.nextLine();
		
		return name;
	}
}
