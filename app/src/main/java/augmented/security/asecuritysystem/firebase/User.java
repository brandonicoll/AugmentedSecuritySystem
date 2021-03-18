package augmented.security.asecuritysystem.firebase;

public class User { //ISPY CORP -- This class is used to remember user information and store it so it can be accessed later

    public String fullName, email;

    public User(){

    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

