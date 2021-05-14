package com.epam.engx.cleancode.naming.task3;

public class HarshadNumber {

	private static final long SEQUENCE_LIMITER = 200;

	public String main() {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= SEQUENCE_LIMITER; i++) {
			if (i % calculateSumOfNumberDigits(i) == 0) {
				result.append(i).append("\n");
			}
		}
		return result.toString();
	}

	private int calculateSumOfNumberDigits(int number) {
		int sum = 0;
		while (number != 0) {
			sum += number % 10;
			number = number / 10;
		}
		return sum;
	}
}
