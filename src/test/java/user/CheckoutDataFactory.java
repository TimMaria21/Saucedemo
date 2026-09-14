package user;

import utils.PropertyReader;

public class CheckoutDataFactory {
    public static CheckoutData withValidData() {
        return new CheckoutData(
                PropertyReader.getProperty("saucedemo.first.name"),
                PropertyReader.getProperty("saucedemo.last.name"),
                PropertyReader.getProperty("saucedemo.postal.code"));
    }
}
