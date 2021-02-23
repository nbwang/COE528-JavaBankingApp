/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.*;

/**
 *
 * @author Nicolas Wang
 * 
 * Overview: Customer class records the username, password, methods to deposit, withdraw and purchase and customer level
 * The class is mutable
 * 
 * Abstract Function AF(c) = Customer c
 * c.level, c.username, c.password, c.balance, c.purchase
 * 
 * RepInv RV(c) = true if c.username != null
 *                        c.balance >=0
 *                        c.purchase >=50
 *                        c.balance - c.purchase - fee >=0
 *otherwise false
 */

public class Customer{
    protected double balance;
    protected boolean login;
    protected User user;
    protected Level level;
  
    public Customer(String username, String password)throws FileNotFoundException{
        user = new User(username);
        this.balance = 0;
        deposit(user.getBalance());
        login = false;
    }
    
    public void setLevel(Level level){
        this.level = level;
    }
    public Level getLevel(){
        return level;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return user.getBalance();
    }
    
    public boolean getLogin(){
        return this.login;
    }
    
    public void logout(){
        login = false;
    }
    
    public void login(String username, String password){
        /*
            Requires: Username and password input as Strings
            Modifies: nothing
            Effects: sets the login flag as true so the user is allowed into the account     
        */
        if(this.user.getUsername().equals(username) && this.user.getPassword().equals(password)){
            login = true;
        }
    }
    
    public void deposit(double amount){
    /*
        Requires: deposit amount input from the user as a double
        Modifies: Balance
        Effects: adds the double amount onto the balance, and returns true if input is a double value
    */
        if(RepOk(amount, 1) == true){
            this.balance += amount;
            user.setBalance(this.balance);
            user.updateAccount();
            if (balance < 10000){
            level = new Silver();
            }
            else if(balance >= 10000 && balance < 20000){
                level = new Gold();
            }
            else if(balance >= 20000){
                level = new Platinum();
            }
        }
    }
    
    public void withdraw(double amount){
    /*
        Requires: withdraw amount input from the user as a double
        Modifies: Balance
        Effects: subtracts the withdraw amount from the balance and returns true if withdraw amount is less than the balance
    */
        if (RepOk(amount, 2) == true){
            this.balance -= amount;
            user.setBalance(this.balance);
            user.updateAccount(); 
            if (balance < 10000){
            level = new Silver();
            }
            else if(balance >= 10000 && balance < 20000){
                level = new Gold();
            }
            else if(balance >= 20000){
                level = new Platinum();
            }
        }
    }
    
    public void onlinePurchase(double amount){
    /*
        Requires: Cost of the purchase
        Modifies: Balance, fee
        Effects: subtracts the cost of the purchase and the fee depending on the customer level
    */
        if(RepOk(amount, 3) == true){
            this.balance -= (amount + level.getFee());
            user.setBalance(this.balance);
            user.updateAccount();      
            if (balance < 10000){
            level = new Silver();
            }
            else if(balance >= 10000 && balance < 20000){
                level = new Gold();
            }
            else if(balance >= 20000){
                level = new Platinum();
            }
        }
    }
    
    private boolean RepOk(double amount, int cases){
        boolean rep = false;
        switch(cases){
            case 1:
            //Despositing money into the account
                if(amount > 0){
                    rep = true;
                    break;
                }            
                else{
                    break;
                }
            case 2:
            //Withdrawing money from the account
                if(amount > 0 && this.balance - amount >=0){
                    rep = true;
                    break;
                }
                else{
                    break;
                }
            case 3:
            //Online purchasing
                if(amount > 0 && this.balance - amount - level.getFee() > 0){
                    rep = true;
                    break;
                }
                else{
                    break;
                }
        }
        return rep;
    }
}