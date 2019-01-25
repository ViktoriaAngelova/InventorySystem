package main;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import core.*;

public final class Engine {

	private static List<Product> stockedProducts;
	private static List<Product> outOfStockProducts;

	static {
		stockedProducts = new ArrayList<>();
		outOfStockProducts = new ArrayList<>();
	}

	private Engine() {
		throw new UnsupportedOperationException();
	}

	private static Product getProduct(final String barcode) {
		for (Product product : stockedProducts) {
			if (product.getBarcode().equalsIgnoreCase(barcode)) {
				return product;
			}
		}
		throw new NoSuchElementException("Product does not exist!");
	}
	
}
