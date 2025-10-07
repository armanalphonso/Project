package calculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	


	public static void main(String[] args) {
		final String inputExp = Readinput .read();
		
		Queue<String> operation;
		Queue<String> numbers;
// 4+5*3/2
		String numbersArr[] = inputExp.split("[-+*/]");
		String operArr[] = inputExp.split("[0-9]+");
		
		numbers = new LinkedList<String>(Arrays.asList(numbersArr));
		operation = new LinkedList<String>(Arrays.asList(operArr));
		
		Double res = Double.parseDouble(numbers.poll());
		
		while (!numbers.isEmpty()) {
			String opr = operation.poll();
			
			
			Operate operate;
			switch (opr) {
			case "+":
				operate = new Add();
				break;
				
			case "-":
				operate = new Subtract(); 
				break;
			case "*":
				operate = new Multiply();
				break;
				
			case "/":
				operate  = new  Divi();
				break;
				default:
					continue;
			}		
			  Double num = Double.parseDouble(numbers.poll());
			  res = operate.getResults(res, num);
        }

        System.out.println(res);
    }
}
