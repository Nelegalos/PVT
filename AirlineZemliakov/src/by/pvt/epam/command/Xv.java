package by.pvt.epam.command;

public class Xv {

	public static void main(String[] args) {

		int savings = 40000;
		int month = 0;
		while (savings > 0) {
			savings = savings + (savings * 6 / 100) / 12
					- (500 - (savings * 6 / 100) / 12);
			month++;
		}
		int year = month / 12;
		month = month % 12;
		System.out.println(year + " years and " + month + " months");

	}
}
