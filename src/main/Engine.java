package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import core.*;

public final class Engine {

	private static List<Product> stockedProducts;
	private static List<Product> outOfStockProducts;
	private static Comparator<Product> nameComparator;

	static {
		stockedProducts = new ArrayList<>();
		outOfStockProducts = new ArrayList<>();
		nameComparator = new NameComparator();
	}

	private Engine() {
		throw new UnsupportedOperationException();
	}

	public static void run() {
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();
		String[] inputArray;

		while (!userInput.equalsIgnoreCase("close")) {
			inputArray = userInput.trim().split(" ");
			switch (inputArray[0].toLowerCase()) {
			case "sell":
				sellProduct(inputArray[1], Double.parseDouble(inputArray[2]));
				break;
			case "add":
				addProduct(inputArray[1], inputArray[2], Double.parseDouble(inputArray[3]),
						Double.parseDouble(inputArray[4]));
				break;
			case "update":
				storeProduct(inputArray[1], Double.parseDouble(inputArray[2]));
			case "printa":
				printStockedProductsAlphabetically();
				break;
			case "printu":
				printOutOfStockProductsAlphabetically();
				break;
			case "printd":
				printAllProductsByQuantityDecreasing();
				break;
			case "calculate":
				System.out.println(calculateStockProductsValue());
				break;
			default:
				System.out.println("Invalid comand!");
			}
			userInput = input.nextLine();
		}
		input.close();
	}

	private static Product getProduct(final String barcode) {
		for (Product product : stockedProducts) {
			if (product.getBarcode().equalsIgnoreCase(barcode)) {
				return product;
			}
		}
		throw new NoSuchElementException("Product does not exist!");
	}

	private static List<Product> getStockedProductsAlphabeticallySorted() {
		List<Product> stockedProductsAlphabeticallySorted = new ArrayList<>(stockedProducts);
		Collections.sort(stockedProductsAlphabeticallySorted, nameComparator);
		return stockedProductsAlphabeticallySorted;
	}

	private static List<Product> getOutOfStockProductsAlphabeticallySorted() {
		List<Product> outOfStockProductsAlphabeticallySorted = new ArrayList<>(outOfStockProducts);
		Collections.sort(outOfStockProductsAlphabeticallySorted, nameComparator);
		return outOfStockProductsAlphabeticallySorted;
	}

	private static List<Product> getAllProductsSortedByQuantityDecreasing() {
		List<Product> allProductsSortetByQuantityDecreasing = new ArrayList<>(stockedProducts);
		allProductsSortetByQuantityDecreasing.addAll(outOfStockProducts);
		Comparator<Product> comparator = new QuantityComparatorDecreasing();
		Collections.sort(allProductsSortetByQuantityDecreasing, comparator);
		return allProductsSortetByQuantityDecreasing;
	}

	private static void sellProduct(final String barcode, final double quantity) {
		Product product = null;
		try {
			product = getProduct(barcode);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		if (product != null) {
			try {
				product.sell(quantity);

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void addProduct(final String barcode, final String name, final double price, final double quantity) {
		stockedProducts.add(new DefaultProduct(name, barcode, price, quantity));
	}

	private static void storeProduct(final String barcode, final double quantity) {
		Product product = null;
		try {
			product = getProduct(barcode);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		if (product != null) {
			product.store(quantity);
		}
	}

	private static double calculateStockProductsValue() {
		double stockProductsValue = 0;
		for (Product product : stockedProducts) {
			stockProductsValue += product.getStockedValue();
		}
		return stockProductsValue;
	}

	private static void printStockedProductsAlphabetically() {
		for (Product product : getStockedProductsAlphabeticallySorted()) {
			System.out.println(product);
		}
	}

	private static void printOutOfStockProductsAlphabetically() {
		for (Product product : getOutOfStockProductsAlphabeticallySorted()) {
			System.out.println(product);
		}
	}

	private static void printAllProductsByQuantityDecreasing() {
		for (Product product : getAllProductsSortedByQuantityDecreasing()) {
			System.out.println(product);
		}
	}

}
