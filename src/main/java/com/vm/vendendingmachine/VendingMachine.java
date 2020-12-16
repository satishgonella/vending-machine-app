package com.vm.vendendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingMachine {

	private ArrayList<Coin> coinHold = new ArrayList<Coin>();
	private ArrayList<Coin> coinReturn = new ArrayList<Coin>();
	private ArrayList<Coin> bank = new ArrayList<Coin>();
	private ArrayList<Item> stock = new ArrayList<Item>();

	public String idCoin(Coin coin) {
		if (coin.getSize() == 4 && coin.getWeight() == 4) {
			return "quarter";
		}
		if (coin.getSize() == 3 && coin.getWeight() == 3) {
			return "nickel";
		}
		if (coin.getSize() == 2 && coin.getWeight() == 2) {
			return "penny";
		}
		if (coin.getSize() == 1 && coin.getWeight() == 1) {
			return "dime";
		}
		return "";
	}

	public void acceptCoin(Coin coin) {
		String coinType = idCoin(coin);

		if (coinType.equals("quarter") || coinType.equals("nickel") || coinType.equals("dime")) {
			coinHold.add(coin);
		} else {
			coinReturn.add(coin);
		}
	}

	public BigDecimal calcMoneyInHold() {
		BigDecimal moneyInHold = new BigDecimal("0.00");

		for (Coin coin : coinHold) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.25"));
			}
			if (coinType.equals("nickel")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.05"));
			}
			if (coinType.equals("dime")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.10"));
			}
		}
		return moneyInHold;
	}

	public boolean checkHoldContainsCoin(Coin coin) {
		boolean coinInHold = false;

		if (coinHold.contains(coin)) {
			coinInHold = true;
		}

		return coinInHold;
	}

	public void stockItem(Item newItem) {
		stock.add(newItem);
	}

	public boolean checkStockForItemType(String itemType) {
		boolean typeInStock = false;

		for (Item item : stock) {
			if (item.getType().equals(itemType)) {
				typeInStock = true;
				break;
			}
		}
		return typeInStock;
	}

	public boolean checkStockForItem(Item itemToCheck) {
		boolean itemInStock = false;

		if (stock.contains(itemToCheck)) {
			itemInStock = true;
		}

		return itemInStock;
	}

	public boolean checkSufficientFunds(Item selectedItem) {
		boolean sufficientFunds = false;

		if (selectedItem.getPrice().compareTo(calcMoneyInHold()) <= 0) {
			sufficientFunds = true;
		}
		return sufficientFunds;
	}

	public void dispenseItem(Item item) {
		stock.remove(item);
	}

	public Item pickItemFromStock(String chosenType) {
		Item chosenItem = null;

		for (Item item : stock) {
			String itemType = item.getType();
			if (itemType.equals(chosenType)) {
				chosenItem = item;
				break;
			}
		}
		return chosenItem;
	}

	public void bankCoins() {
		for (Coin coin : coinHold) {
			bank.add(coin);
		}
		coinHold.clear();
	}

	public void putCoinDirectlyInBank(Coin coin) {
		bank.add(coin);
	}

	public boolean checkBankContainsCoin(Coin coin) {
		boolean coinInBank = false;

		if (bank.contains(coin)) {
			coinInBank = true;
		}

		return coinInBank;
	}

	public BigDecimal makeChange(Item chosenItem) {
		BigDecimal change = calcMoneyInHold().subtract(chosenItem.getPrice());
		return change;
	}

	public int calcNumOfQuartersInChange(Item chosenItem) {
		int numOfQsInChange = (int) (makeChange(chosenItem).doubleValue() / 0.25);
		return numOfQsInChange;

	}

	public int calcNumOfDimesInChange(Item chosenItem) {
		BigDecimal changeMinusQuarters = makeChange(chosenItem)
				.subtract(new BigDecimal(calcNumOfQuartersInChange(chosenItem)).multiply(new BigDecimal("0.25")));
		int numOfDsInChange = (int) (changeMinusQuarters.doubleValue() / 0.10);
		return numOfDsInChange;
	}

	public int calcNumOfNickelsInChange(Item chosenItem) {
		BigDecimal changeMinusQuarters = makeChange(chosenItem)
				.subtract(new BigDecimal(calcNumOfQuartersInChange(chosenItem)).multiply(new BigDecimal("0.25")));
		BigDecimal changeMinusQuartersAndDimes = changeMinusQuarters
				.subtract(new BigDecimal(calcNumOfDimesInChange(chosenItem)).multiply(new BigDecimal("0.10")));
		int numOfNsInChange = (int) (changeMinusQuartersAndDimes.doubleValue() / 0.05);
		return numOfNsInChange;
	}

	public void returnChange(Item chosenItem) {
		int numOfQuartersInChange = calcNumOfQuartersInChange(chosenItem);
		int numOfDimesInChange = calcNumOfDimesInChange(chosenItem);
		int numOfNickelsInChange = calcNumOfNickelsInChange(chosenItem);

		for (int i = 0; i <= numOfQuartersInChange - 1; i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("quarter")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
		for (int i = 0; i <= numOfDimesInChange - 1; i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("dime")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
		for (int i = 0; i <= numOfNickelsInChange - 1; i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("nickel")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
	}

	public int countQuartersInBank() {
		int countQuarters = 0;

		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				countQuarters++;
			}
		}
		return countQuarters;
	}

	public int countNickelsInBank() {
		int countNickels = 0;

		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("nickel")) {
				countNickels++;
			}
		}
		return countNickels;
	}

	public int countDimesInBank() {
		int countDimes = 0;

		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("dime")) {
				countDimes++;
			}
		}
		return countDimes;
	}

	public boolean checkExactChangeNeeded() {
		int countQuarters = countQuartersInBank();
		int countNickels = countNickelsInBank();
		int countDimes = countDimesInBank();
		boolean exactChangeNeeded = false;

		if (countQuarters < 5 || countNickels < 5|| countDimes < 5) {
			exactChangeNeeded = true;
		}
		return exactChangeNeeded;
	}

	public void returnCoins() {
		for (Coin coin : coinHold) {
			coinReturn.add(coin);
		}
		coinHold.clear();
	}

	public boolean checkCoinReturnContainsCoin(Coin coin) {
		boolean coinInReturn = false;

		if (coinReturn.contains(coin)) {
			coinInReturn = true;
		}

		return coinInReturn;
	}

	public int getSizeOfCoinReturn() {
		return coinReturn.size();
	}

	public int countQuartersInCoinReturn() {
		int countQuarters = 0;

		for (Coin coin : coinReturn) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				countQuarters++;
			}
		}
		return countQuarters;
	}

	public int countNickelsInCoinReturn() {
		int countNickels = 0;

		for (Coin coin : coinReturn) {
			String coinType = idCoin(coin);
			if (coinType.equals("nickel")) {
				countNickels++;
			}
		}
		return countNickels;
	}

	public int countDimesInCoinReturn() {
		int countDimes = 0;

		for (Coin coin : coinReturn) {
			String coinType = idCoin(coin);
			if (coinType.equals("dime")) {
				countDimes++;
			}
		}
		return countDimes;
	}

	public int countPenniesInCoinReturn() {
		int countPennies = 0;

		for (Coin coin : coinReturn) {
			String coinType = idCoin(coin);
			if (coinType.equals("penny")) {
				countPennies++;
			}
		}
		return countPennies;
	}

	public void emptyCoinReturn() {
		coinReturn.clear();
	}

}
