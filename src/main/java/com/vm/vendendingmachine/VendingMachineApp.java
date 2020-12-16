package com.vm.vendendingmachine;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineApp {

	public static Scanner input = new Scanner(System.in);
	public static String userResponse;

	public static void main(String[] args) {

		VendingMachine myMachine = new VendingMachine();

		Quarter setUpQuarter = new Quarter();
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		Nickel setUpNickel = new Nickel();
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		Dime setUpDime = new Dime();
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);

		myMachine.bankCoins();

		Soda stockedSoda = new Soda();
		myMachine.stockItem(stockedSoda);
		myMachine.stockItem(stockedSoda);
		Pepsi stockedPepsi = new Pepsi();
		myMachine.stockItem(stockedPepsi);
		myMachine.stockItem(stockedPepsi);
		Coke stockedCoke = new Coke();
		myMachine.stockItem(stockedCoke);
		myMachine.stockItem(stockedCoke);

		while (1 < 2) {

			System.out.println("This machine sells Soda for $0.45, Pepsi for $0.35, and Coke for $0.25.");
			System.out.println("Press Q to insert a quarter.");
			System.out.println("Press N to insert a nickel.");
			System.out.println("Press D to insert a dime.");
			System.out.println("Press P to insert a penny");
			System.out.println("Press C to return coins.");
			System.out.println("Press R to reset.");
			System.out.println("Press T to take coins/change.");
			System.out.println("Press 1 to choose Soda.");
			System.out.println("Press 2 to choose Pepsi.");
			System.out.println("Press 3 to choose Coke.");

			BigDecimal moneyInserted = myMachine.calcMoneyInHold();
			boolean exactChangeNeeded = myMachine.checkExactChangeNeeded();
			if (moneyInserted.equals(new BigDecimal("0.00")) && exactChangeNeeded) {
				System.out.println("EXACT CHANGE ONLY");
			} else if (moneyInserted.equals(new BigDecimal("0.00"))) {
				System.out.println("INSERT COIN");
			} else {
				System.out.println("$" + moneyInserted);
			}

			userResponse = input.next();

			if (userResponse.equalsIgnoreCase("Q")) {
				Quarter userQuarter = new Quarter();
				myMachine.acceptCoin(userQuarter);
			} else if (userResponse.equalsIgnoreCase("N")) {
				Nickel userNickel = new Nickel();
				myMachine.acceptCoin(userNickel);
			} else if (userResponse.equalsIgnoreCase("D")) {
				Dime userDime = new Dime();
				myMachine.acceptCoin(userDime);
			} else if (userResponse.equalsIgnoreCase("P")) {
				Penny userPenny = new Penny();
				myMachine.acceptCoin(userPenny);
			} else if (userResponse.equalsIgnoreCase("C")) {
				myMachine.returnCoins();
			} else if (userResponse.equalsIgnoreCase("R")) {
				myMachine.returnCoins();
			} else if (userResponse.equalsIgnoreCase("T")) {
				System.out.println(myMachine.countQuartersInCoinReturn() + " quarters taken.");
				System.out.println(myMachine.countNickelsInCoinReturn() + " nickels taken.");
				System.out.println(myMachine.countDimesInCoinReturn() + " dimes taken.");
				System.out.println(myMachine.countPenniesInCoinReturn() + " pennies taken.");
				myMachine.emptyCoinReturn();
			} else if (userResponse.equalsIgnoreCase("1")) {
				boolean sodaInStock = myMachine.checkStockForItemType("soda");
				Item chosenItem = myMachine.pickItemFromStock("soda");
				if (sodaInStock) {
					boolean sufficientFunds = myMachine.checkSufficientFunds(chosenItem);
					if (sufficientFunds) {
						myMachine.dispenseItem(chosenItem);
						myMachine.returnChange(chosenItem);
						myMachine.bankCoins();
						System.out.println("Soda dispensed.");

					} else {
						System.out.println("$" + chosenItem.getPrice());
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else if (userResponse.equalsIgnoreCase("2")) {
				boolean pepsiInStock = myMachine.checkStockForItemType("pepsi");
				Item chosenItem = myMachine.pickItemFromStock("pepsi");
				if (pepsiInStock) {
					boolean sufficientFunds = myMachine.checkSufficientFunds(chosenItem);
					if (sufficientFunds) {
						myMachine.dispenseItem(chosenItem);
						myMachine.returnChange(chosenItem);
						myMachine.bankCoins();
						System.out.println("Pepsi dispensed.");

					} else {
						System.out.println("$" + chosenItem.getPrice());
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else if (userResponse.equalsIgnoreCase("3")) {
				boolean cokeInStock = myMachine.checkStockForItemType("coke");
				Item chosenItem = myMachine.pickItemFromStock("coke");
				if (cokeInStock) {
					boolean sufficientFunds = myMachine.checkSufficientFunds(chosenItem);
					if (sufficientFunds) {
						myMachine.dispenseItem(chosenItem);

						myMachine.bankCoins();
						System.out.println("Coke dispensed.");


					} else {
						System.out.println("$" + chosenItem.getPrice());
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else {
				System.out.println("That is not a valid response.");
			}
		}

	}

}
