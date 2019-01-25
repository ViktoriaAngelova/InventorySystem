package core;

public interface Product {

	public String getName();

	public String getBarcode();

	public double getQuantity();

	public double getStockedValue();

	public void sell(double quantity);

	public void store(double quantity);

}
