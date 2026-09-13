package user;

public class CheckoutData {
    private final String firstName;
    private final String lastName;
    private final  String zip;

    public CheckoutData(String firstName, String lastName, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZip() {
        return zip;
    }
}
