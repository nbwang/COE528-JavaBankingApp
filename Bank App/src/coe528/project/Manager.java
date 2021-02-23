/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Nicolas Wang
 */
public class Manager {
    protected boolean login;
    protected User user;
    
    public Manager(String username, String password){
        login = username.equals("admin") && password.equals("admin");
    }
    
    public void logout(){
        login = false;
    }
    
    public boolean getLogin(){
        return login;
    }
    
    protected boolean addCustomer(String username, String password){
        if (RepOk(100, username, 1) == true){
            try{
                File file = new File(username + ".txt");
                if(file.exists()){
                    return false;
                }
                else{
                    file.createNewFile();
                    try (FileWriter Write = new FileWriter(username + ".txt")) {
                        Write.write(username);
                        Write.write(System.getProperty( "line.separator" ));
                        Write.write("" + password);
                        Write.write(System.getProperty( "line.separator" ));
                        Write.write("customer");
                        Write.write(System.getProperty( "line.separator" ));
                        Write.write("100.0");
                    }
                }
            }
            catch(IOException e){}
            return true;
        }
        else{
        return false;
        }
    }
    
    protected boolean deleteCustomer(String user){
        if (RepOk(0, user, 2)){
            File file = new File(user + ".txt");
            if(file.exists()){
                file.delete();
                return true;
            }
            else{
            return false;
            }
        }
        else{
            return false;
        }
    }
       
    private boolean RepOk(double balance, String user, int cases){
        boolean rep = false;
        switch(cases){
            case 1://Adding a new Customer
                if(balance >= 100 && !user.equals("admin")){
                    rep = true;
                    break;
                }
                else{
                    break;
                }
            case 2://Deleting a customer
                if(!user.equals("admin")){
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
