/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Nicolas Wang
 */
public class Silver extends Level{
    public Silver(){
        levelStr = "Silver";
        fee  = 20;
    }

    @Override
    public String getLevelStr(){
        return levelStr;
    }

    @Override
    public int getFee(){
        return fee;
    }

    @Override
    public void setLevel(Customer customer){
        customer.level = new Silver();
    }
}
