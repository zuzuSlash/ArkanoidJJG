/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.model;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;



public class Block implements  Serializable {

    private String value;
    
    public Block(){
        this.value=null;
    }
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value=value;
    }
    public boolean isEmpty(){
        return this.value==null;
    }
    @Override
    public String toString(){
        return this.value;
    }
}
