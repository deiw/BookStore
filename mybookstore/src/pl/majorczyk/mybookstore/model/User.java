package pl.majorczyk.mybookstore.model;

public class User {
    private long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String street;
    private String city;

    public User(){}
    public User(User user){
        this.id=user.id;
        this.firstname=user.firstname;
        this.lastname=user.lastname;
        this.password=user.password;
        this.email=user.email;
        this.street=user.street;
        this.city=user.city;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
