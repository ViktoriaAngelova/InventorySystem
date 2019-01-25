package core;

public final class DefaultProduct implements Product {

	private String name;
	private String barcode;
	private double price;
	private double quantity;

	public DefaultProduct(final String name, final String barcode, final double price, final double quantity) {
		this.name = name;
		this.barcode = barcode;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getBarcode() {
		return barcode;
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	@Override
	public double getStockedValue() {
		return price * quantity;
	}

	@Override
	public void sell(double quantity) {
		if (quantity > this.quantity) {
			throw new IllegalArgumentException("Not enough quantity!");
		}
		this.quantity -= quantity;
	}

	@Override
	public void store(double quantity) {
		this.quantity += quantity;
	}

	@Override
	public String toString() {
		return name + " (" + barcode + ")";
	}
}
