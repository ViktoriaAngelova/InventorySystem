package core;

import java.util.Comparator;

public final class NameComparator implements Comparator<Product> {

	public NameComparator() {

	}

	@Override
	public int compare(final Product product1, final Product product2) {
		return product1.getName().compareToIgnoreCase(product2.getName());
	}

}
