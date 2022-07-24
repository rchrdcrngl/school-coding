/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mp2.models;

import java.util.*;

/**
 *
 * @author Richard Maru
 */
public class HobbyList {
    
    // Returns hardcoded list of hobbies depending on passed parameter value
    
    public List getHobbies(String name){
        List<String> hobbies = new ArrayList<>();
        if(name.equals("caringal")){
            hobbies.add("Watching TV series");
            hobbies.add("Listening to Lo-Fi music");
            hobbies.add("Reading books");
        }
        else if(name.equals("pagulayan")){
            hobbies.add("Playing video games");
            hobbies.add("Listening to music");
            hobbies.add("Solving puzzles");
        }
        return hobbies;
    }
}
