/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp2.models;

/**
 *
 * @author Richard Maru
 */
public class Login {
    private String username, password;
    private int valid = -1;
    
    // GETTERS and SETTERS for username and password
    public String getUsername(){
        return this.username;
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
