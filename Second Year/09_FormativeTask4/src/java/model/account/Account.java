package model.account;

public class Account{
    private String lastname;
    private String firstname;
    private String username;
    private String password;
    private int IDNO;
    private String type;

    public String getType() {
        return type;
    }
    
    public String getLastName(){
        return lastname;
    }
    
    public String getFirstName(){
        return firstname;
    }
    
    public String getFullName(){
        return firstname + " " + lastname;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public void setLastName(String name){
        this.lastname = name;
    }
    
    public void setFirstName(String name){
        this.firstname = name;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIDNO() {
        return IDNO;
    }

    public void setIDNO(int IDNO) {
        this.IDNO = IDNO;
    }
    
    
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public Account(String firstname, String lastname, String username, String password, String type){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.type = type;
    }
}