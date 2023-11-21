
package com.mp3.models;

public class User {
    private String username, password;
    private int valid = -1;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public String toString(){
        return username + "";
    }
    
    // Checks if the object's username and password is equal with the passed
    // username and password
    public boolean validate(String uname, String pword){
        boolean matchUname = username.equals(uname);
        boolean matchPword = password.equals(pword);
        if (matchUname && matchPword){
            valid = 1;
            return true;
        } else {
            if (matchUname && !matchPword) valid = 0;
            if (!matchUname && matchPword) valid = -1;
            return false;
        } 
    }
    
    // Returns the 1 if username and password is a match
    // Returns 0 if the password is incorrect
    // Returns -1 if the username is incorrect
    public int isValid(){
        return this.valid;
    }
}
