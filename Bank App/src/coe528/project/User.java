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

import java.io.*;
import java.util.Scanner;

public class User{
    private String username;
    private String password;
    private String role;
    private double balance;

    public User(String username) throws FileNotFoundException{
        File file = new File(username + ".txt");
        try (Scanner reader = new Scanner(file)) {
            this.username = reader.nextLine();
            this.password = reader.nextLine();
            this.role = reader.nextLine();
            this.balance = Double.parseDouble(reader.nextLine());
        }
    }
    
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }
    
    public void setBalance(double accountBalance){
        this.balance = accountBalance;
    }
    public double getBalance(){
        return balance;
    }

    public void updateAccount(){
        File file = new File("" + username + ".txt");
        try{
            FileWriter Write = new FileWriter(username + ".txt");
            Write.write(this.username);
            Write.write(System.getProperty( "line.separator" ));
            Write.write(this.password);
            Write.write(System.getProperty( "line.separator" ));
            Write.write("customer");
            Write.write(System.getProperty( "line.separator" ));
            Write.write("" + this.balance);
            Write.close();
        }
        catch(IOException e){}
    }
}
