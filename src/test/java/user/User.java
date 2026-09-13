package user;


public class User {
    private final String user;
    private final String password;

    public User(String user, String password) {
        this.password = password;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
