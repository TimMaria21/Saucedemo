package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemo.locked.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User notMatchPermission() {
        return new User(PropertyReader.getProperty("saucedemo.not.match.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }

    public static User emptyUsernamePermission() {
        return new User(PropertyReader.getProperty("saucedemo.empty.username"),
                PropertyReader.getProperty("saucedemo.password"));
    }
    public static User emptyPasswordPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.empty.password"));
    }
}
