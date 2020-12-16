package com.vm.vendendingmachine;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {

	VendingMachine underTest;

	@Before
	public void setUp() {
		underTest = new VendingMachine();
	}

	@Test
	public void whenIdCoinIsPassedAQuarterItReturnsQuarter() {
		Quarter newQuarter = new Quarter();
		assertEquals("quarter", underTest.idCoin(newQuarter));
	}

	@Test
	public void whenIdCoinIsPassedANickleItReturnsNickel() {
		Nickel newNickle = new Nickel();
		assertEquals("nickel", underTest.idCoin(newNickle));
	}

	@Test
	public void whenIdCoinIsPassedADimeItReturnsDime() {
		Dime newDime = new Dime();
		assertEquals("dime", underTest.idCoin(newDime));
	}

	@Test
	public void whenIdCoinIsPassedAPennyItReturnsPenny() {
		Penny newPenny = new Penny();
		assertEquals("penny", underTest.idCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInCoinHold() {
		Quarter newCoin = new Quarter();
		underTest.idCoin(newCoin);
		underTest.acceptCoin(newCoin);
		assertTrue(underTest.checkHoldContainsCoin(newCoin));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsAQuarter() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertTrue(underTest.checkHoldContainsCoin(newQuarter));
		assertFalse(underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsANickel() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertTrue(underTest.checkHoldContainsCoin(newNickel));
		assertFalse(underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsADime() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertTrue(underTest.checkHoldContainsCoin(newDime));
		assertFalse(underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInCoinReturnIfItsAPenny() {
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertTrue(underTest.checkCoinReturnContainsCoin(newPenny));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointTwoFiveWhenAcceptCoinIsPassedOneQuarter() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		assertEquals(new BigDecimal("0.25"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointFiveWhenAcceptCoinIsPassedTwoQuarters() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertEquals(new BigDecimal("0.50"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointThreeWhenAcceptCoinIsPassedOneQuarterAndOneNickel() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		assertEquals(new BigDecimal("0.30"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointThreeFiveWhenAcceptCoinIsPassedOneQuarterAndOneDime() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		assertEquals(new BigDecimal("0.35"), underTest.calcMoneyInHold());
	}

	@Test
	public void stockItemAddssodaToStock() {
		Soda newSoda = new Soda();
		underTest.stockItem(newSoda);
		assertTrue(underTest.checkStockForItem(newSoda));
	}

	@Test
	public void stockItemAddspepsiToStock() {
		Pepsi newPepsi = new Pepsi();
		underTest.stockItem(newPepsi);
		assertTrue(underTest.checkStockForItem(newPepsi));
	}

	@Test
	public void stockItemAddscokeToStock() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		assertTrue(underTest.checkStockForItem(newCoke));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedcokeAndcokeItemIsStocked() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		assertTrue(underTest.checkStockForItemType("coke"));
	}

	@Test
	public void checkStockReturnsFalseWhenPassedsodaAndOnlycokeItemIsStocked() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		assertFalse(underTest.checkStockForItemType("soda"));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedcokeAndBothcokeItemAndsodaItemsAreStocked() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		Soda newSoda = new Soda();
		underTest.stockItem(newSoda);
		assertTrue(underTest.checkStockForItemType("coke"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedcokeAndWhenPassedsodaWhencokeItemAndsodaItemAreStocked() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		Soda newSoda = new Soda();
		underTest.stockItem(newSoda);
		assertTrue(underTest.checkStockForItemType("coke"));
		assertTrue(underTest.checkStockForItemType("soda"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedcokeAndWhenPassedsodaButFalseWhenPassedpepsiWhencokeItemAndsodaItemAreStocked() {
		Coke newCoke = new Coke();
		underTest.stockItem(newCoke);
		Soda newSoda = new Soda();
		underTest.stockItem(newSoda);
		assertTrue(underTest.checkStockForItemType("coke"));
		assertTrue(underTest.checkStockForItemType("soda"));
		assertFalse(underTest.checkStockForItemType("pepsi"));
	}

	@Test
	public void checkSufficientFundsReturnsTrueWhenPassedpepsiItemAndTwoQuartersAreAccepted() {
		Pepsi newPepsi = new Pepsi();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertTrue(underTest.checkSufficientFunds(newPepsi));
	}

	@Test
	public void checkSufficientFundsReturnsFalseWhenPassedpepsiItemAndOneQuarterIsAccepted() {
		Pepsi newPepsi = new Pepsi();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		assertFalse(underTest.checkSufficientFunds(newPepsi));
	}

	@Test
	public void checkSufficientFundsReturnsTrueWhenPassedpepsiItemAndThreeQuartersAreAccepted() {
		Pepsi newPepsi = new Pepsi();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertTrue(underTest.checkSufficientFunds(newPepsi));
	}

	@Test
	public void checkStockReturnsFalseWhensodaItemIsStockedAndThenDispenseItemIsPassedThatsodaItem() {
		Soda newSoda = new Soda();
		underTest.stockItem(newSoda);
		underTest.dispenseItem(newSoda);
		assertFalse(underTest.checkStockForItemType(newSoda.getType()));
	}

	@Test
	public void checkStockReturnsFalseWhenpepsiItemIsStockedAndThenDispenseItemIsPassedThatpepsiItem() {
		Pepsi newPepsi = new Pepsi();
		underTest.stockItem(newPepsi);
		underTest.dispenseItem(newPepsi);
		assertFalse(underTest.checkStockForItemType(newPepsi.getType()));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroWhenOneQuarterIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndCoinsInHoldArePutInBankWhenOneQuarterIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertTrue(underTest.checkBankContainsCoin(newQuarter));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndCoinsInHoldArePutInBankWhenOneQuarterAndOneDimeIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertTrue(underTest.checkBankContainsCoin(newQuarter));
		assertTrue(underTest.checkBankContainsCoin(newDime));
	}

	@Test
	public void makeChangeReturnsZeroPointTwoFiveWhenThreeQuartersAreAcceptedAndItsPassedApepsiItem() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Pepsi newPepsi = new Pepsi();
		assertEquals(new BigDecimal("0.40"), underTest.makeChange(newPepsi));
	}

	@Test
	public void makeChangeReturnsZeroPointZeroFiveWhenTwoQuartersAndTwoDimesAreAcceptedAndItsPassedAcokeItem() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		Coke newCoke = new Coke();
		assertEquals(new BigDecimal("0.45"), underTest.makeChange(newCoke));
	}

	@Test
	public void calcNumOfQuartersInChangeReturnsOneWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Pepsi newPepsi = new Pepsi();
		assertEquals(1, underTest.calcNumOfQuartersInChange(newPepsi));
	}

	@Test
	public void calcNumOfQuartersInChangeReturnsTwoWhenChangeEqualsZeroPointFiveZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Pepsi newPepsi = new Pepsi();
		assertEquals(2, underTest.calcNumOfQuartersInChange(newPepsi));
	}

	@Test
	public void calcNumOfQuartersInChangeReturnsThreeWhenChangeEqualsZeroPointSevenFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Pepsi newPepsi = new Pepsi();
		assertEquals(3, underTest.calcNumOfQuartersInChange(newPepsi));
	}

	@Test
	public void calcNumOfQuartersInChangeReturnsThreeWhenChangeEqualsZeroPointNineZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Pepsi newPepsi = new Pepsi();
		assertEquals(4, underTest.calcNumOfQuartersInChange(newPepsi));
	}

	@Test
	public void calcNumOfQuartersInChangeReturnsZeroWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Pepsi newPepsi = new Pepsi();
		assertEquals(1, underTest.calcNumOfQuartersInChange(newPepsi));
	}

	@Test
	public void calcNumOfDimesInChangeReturnsOneWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Pepsi newPepsi = new Pepsi();
		assertEquals(0, underTest.calcNumOfDimesInChange(newPepsi));
	}

	@Test
	public void calcNumOfDimesInChangeReturnsTwoWhenChangeEqualsZeroPointTwoZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		Pepsi newPepsi = new Pepsi();
		assertEquals(1, underTest.calcNumOfDimesInChange(newPepsi));
	}

	@Test
	public void calcNumOfDimesInChangeReturnsOneWhenChangeEqualsZeroPointThreeFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Pepsi newPepsi = new Pepsi();
		assertEquals(0, underTest.calcNumOfDimesInChange(newPepsi));
	}

	@Test
	public void calcNumOfDimesInChangeReturnsOneWhenChangeEqualsZeroPointSixFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Pepsi newPepsi = new Pepsi();
		assertEquals(0, underTest.calcNumOfDimesInChange(newPepsi));
	}

	@Test
	public void calcNumOfDimesInChangeReturnsZeroWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Pepsi newPepsi = new Pepsi();
		assertEquals(2, underTest.calcNumOfDimesInChange(newPepsi));
	}

	@Test
	public void calcNumOfNickelsInChangeReturnsOneWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Pepsi newPepsi = new Pepsi();
		assertEquals(0, underTest.calcNumOfNickelsInChange(newPepsi));
	}

	@Test
	public void calcNumOfNickelsInChangeReturnsZeroWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Pepsi newPepsi = new Pepsi();
		assertEquals(1, underTest.calcNumOfNickelsInChange(newPepsi));
	}

	@Test
	public void calcNumOfNickelsInChangeReturnsZeroWhenChangeEqualsZeroPointThreeFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Pepsi newPepsi = new Pepsi();
		assertEquals(0, underTest.calcNumOfNickelsInChange(newPepsi));
	}

	@Test
	public void calcNumOfNickelsInChangeReturnsOneWhenChangeEqualsZeroPointOneFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Pepsi newPepsi = new Pepsi();
		assertEquals(1, underTest.calcNumOfNickelsInChange(newPepsi));
	}

	@Test
	public void returnChangeTakesQuarterFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Pepsi newPepsi = new Pepsi();
		underTest.returnChange(newPepsi);
		assertFalse(underTest.checkBankContainsCoin(newQuarter));
		assertTrue(underTest.checkCoinReturnContainsCoin(newQuarter));
		assertEquals(1, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesDimeFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		Pepsi newPepsi = new Pepsi();
		underTest.returnChange(newPepsi);
		assertTrue(underTest.checkBankContainsCoin(newDime));
		assertFalse(underTest.checkCoinReturnContainsCoin(newDime));
		assertEquals(0, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesNickelFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Pepsi newPepsi = new Pepsi();
		underTest.returnChange(newPepsi);
		assertTrue(underTest.checkBankContainsCoin(newNickel));
		assertFalse(underTest.checkCoinReturnContainsCoin(newNickel));
		assertEquals(0, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesOneQuarterOneDimeAndOneNickelFromBankAndAddsThemToCoinReturnWhenChangeEqualsZeroPointFourZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Pepsi newPepsi = new Pepsi();
		underTest.returnChange(newPepsi);
		assertFalse(underTest.checkBankContainsCoin(newNickel));
		assertTrue(underTest.checkCoinReturnContainsCoin(newNickel));
		assertTrue(underTest.checkBankContainsCoin(newDime));
		assertFalse(underTest.checkCoinReturnContainsCoin(newDime));
		assertFalse(underTest.checkBankContainsCoin(newQuarter));
		assertTrue(underTest.checkCoinReturnContainsCoin(newQuarter));
		assertEquals(2, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void countQuartersInBankReturnsOneWhenOneQuarterIsInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(1, underTest.countQuartersInBank());
	}

	@Test
	public void countQuartersInBankReturnsTwoWhenTwoQuartersAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(2, underTest.countQuartersInBank());
	}

	@Test
	public void countQuartersInBankReturnsThreeWhenThreeQuartersAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(3, underTest.countQuartersInBank());
	}

	@Test
	public void countNickelsInBankReturnsOneWhenOneNickelIsInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(1, underTest.countNickelsInBank());
	}

	@Test
	public void countNickelsInBankReturnsTwoWhenTwoNickelsAreInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(2, underTest.countNickelsInBank());
	}

	@Test
	public void countNickelsInBankReturnsThreeWhenThreeNickelsAreInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(3, underTest.countNickelsInBank());
	}

	@Test
	public void countDimesInBankReturnsOneWhenOneDimeIsInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(1, underTest.countDimesInBank());
	}

	@Test
	public void countDimesInBankReturnsTwoWhenTwoDimesAreInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(2, underTest.countDimesInBank());
	}

	@Test
	public void countDimesInBankReturnsThreeWhenThreeDimesAreInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(3, underTest.countDimesInBank());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFiveQuartersFourNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertTrue(underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFiveQuartersFiveNickelsAndFourDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertTrue(underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFourQuartersFiveNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertTrue(underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsFalseWhenFiveQuartersFiveNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertFalse(underTest.checkExactChangeNeeded());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndQuarterIsInCoinReturnWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertTrue(underTest.checkCoinReturnContainsCoin(newQuarter));
	}

	@Test
	public void countQuartersInCoinReturnReturnsOneWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(1, underTest.countQuartersInCoinReturn());
	}

	@Test
	public void countQuartersInCoinReturnReturnsTwoWhenTwoQuartersAreAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(2, underTest.countQuartersInCoinReturn());
	}

	@Test
	public void countNickelsInCoinReturnReturnsOneWhenOneNickelIsAcceptedAndReturnCoinsIsRun() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.returnCoins();
		assertEquals(1, underTest.countNickelsInCoinReturn());
	}

	@Test
	public void countNickelsInCoinReturnReturnsTwoWhenTwoNickelsAreAcceptedAndReturnCoinsIsRun() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.acceptCoin(newNickel);
		underTest.returnCoins();
		assertEquals(2, underTest.countNickelsInCoinReturn());
	}

	@Test
	public void countDimesInCoinReturnReturnsOneWhenOneDimeIsAcceptedAndReturnCoinsIsRun() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		assertEquals(1, underTest.countDimesInCoinReturn());
	}

	@Test
	public void countDimesInCoinReturnReturnsTwoWhenTwoDimesAreAcceptedAndReturnCoinsIsRun() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		assertEquals(2, underTest.countDimesInCoinReturn());
	}

	@Test
	public void countPenniesInCoinReturnReturnsOneWhenOnePennyIsAccepted() {
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(1, underTest.countPenniesInCoinReturn());
	}

	@Test
	public void countPenniesInCoinReturnReturnsTwoWhenTwoPenniesAreAccepted() {
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		underTest.acceptCoin(newPenny);
		assertEquals(2, underTest.countPenniesInCoinReturn());
	}

	@Test
	public void emptyCoinReturnClearsCoinReturn() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		underTest.emptyCoinReturn();
		assertEquals(0, underTest.countDimesInCoinReturn());
	}

	@Test
	public void pickItemFromStockReturnsItemInStockOfSpecifiedType() {
		Pepsi newPepsi = new Pepsi();
		underTest.stockItem(newPepsi);
		assertEquals(newPepsi, underTest.pickItemFromStock("pepsi"));
	}

}
