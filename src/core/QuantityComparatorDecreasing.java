package core;

import java.util.Comparator;

public final class QuantityComparatorDecreasing implements Comparator<Product> {

	public QuantityComparatorDecreasing() {

	}

	@Override
	public int compare(final Product product1, final Product product2) {
		return Double.compare(product2.getQuantity(), product1.getQuantity());
	}

}
