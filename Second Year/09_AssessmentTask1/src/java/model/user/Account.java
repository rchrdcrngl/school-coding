package model.user;

public class Account{
    private String name;
    private String username;
    private String password;
    private int IDNO;
    private String type;

    public String getType() {
        return type;
    }
    
    public String getName(){
        return name;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public void setName(String name){
        this.name = name;
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
}