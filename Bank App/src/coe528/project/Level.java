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
public abstract class Level 
{
    protected String levelStr;
    protected int fee;

    public abstract String getLevelStr();
    
    public abstract int getFee();
    
    public abstract void setLevel(Customer customer);
}
