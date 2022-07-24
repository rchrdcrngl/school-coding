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
public class ProfileInfo {
    private String name;
    private String info;
    
    // Constructor where the values of the fields are hardcoded into the method
    // and is set depending on the passed parameter value
    public ProfileInfo(String code){
        if (code.equals("caringal")){
            name = "Richard Maru Caringal";
            info = "Computer Science Student at UST";
        } else if (code.equals("pagulayan")){
            name = "Christian Charles Pagulayan";
            info = "Computer Science Student, Gamer";
        }
    }
    
    // Getter for profile name
    public String getName(){
        return this.name;
    }
    
    // Getter for profile info
    public String getInfo(){
        return this.info;
    }
}
