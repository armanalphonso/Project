package calculator;
import java.util.Scanner;

public class Readinput {
	
	public static String read() {
		
		Scanner Scanner = new Scanner(System.in);
		System.out.println("Input expression (for eg: 4*3/2)");
		
		String inputLine =Scanner.nextLine();
		
		Scanner.close();
		return inputLine;
	}

	

}