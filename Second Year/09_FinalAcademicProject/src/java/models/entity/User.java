package models.entity;

import java.sql.Date;

public class User{
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String mobile;
    private String email;
    private String address;
    private String city;
    private String country;
    private String username;
    private String password;
    private String userType;
    private Date registeredAt;


    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
    private String zip_code;

    public String getZip_code() {
        return zip_code;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    //ID, FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, ZIP_CODE, USERNAME, USERTYPE, REGISTEREDAT   
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    
    public User(int id, String firstName, String lastName, String username, String password, String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    //FIRSTNAME, LASTNAME, BIRTHDAY, EMAIL, USERNAME
    public User(String firstName, String lastName, Date birthday, String email, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
    }
    //FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, USERNAME, USERTYPE
    public User(String firstName, String lastName, Date birthday, String mobile, String email, String address, String city, String country, String username, String userType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.username = username;
        this.userType = userType;
    }

    public User() {
    }
    
    public User(String firstName, String lastName, String username, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String mobile, String email, String address, String city, String country, String zip_code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip_code = zip_code;
    }

    public User(int id, String firstName, String lastName, Date birthday, String mobile, String email, String address, String city, String country, String username, String password, String userType, String zip_code) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.zip_code = zip_code;
    }
    //FIRSTNAME, LASTNAME, BIRTHDAY, USERNAME, PASSWORD, USERTYPE
     public User(String firstName, String lastName, String username, String password, String userType, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }
     
    //ID, FIRSTNAME, LASTNAME, BIRTHDAY, MOBILE, EMAIL, ADDRESS, CITY, COUNTRY, ZIP_CODE, USERNAME, USERTYPE, REGISTEREDAT
    public User(int id, String firstName, String lastName, Date birthday, String mobile, String email, String address, String city, String country, String zip_code, String username, String userType, Date registeredAt){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip_code = zip_code;
        this.username = username;
        this.userType = userType;
        this.registeredAt = registeredAt;
    }
    
}