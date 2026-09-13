package enums;

public enum ErrorMessages {
    LOCKED_USER("Epic sadface: Sorry, this user has been locked out."),
    EMPTY_USERNAME("Epic sadface: Username is required"),
    EMPTY_PASSWORD("Epic sadface: Password is required"),
    INVALID_CREDENTIALS("Epic sadface: Username and password do not match any user in this service");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
