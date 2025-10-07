package calculator;

public class Subtract implements Operate {

	@Override
	
	public Double getResults(Double... numbers) {
		Double Results = numbers[0];
		
		for (int i =1; i < numbers.length; i++) {
			Results -= numbers[i];
		}
			
		
	return Results;
	}
	
}

