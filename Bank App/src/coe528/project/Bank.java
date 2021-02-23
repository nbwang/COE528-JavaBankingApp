/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Wang
 */
public class Bank extends Application{
    private Customer customer;
    private Manager manager;
    private TextField Value;
    private TextField cusUser;
    private TextField cusPass;
    private Button send;
    private Label user;
    private Label pass;
    private Label currentValue;
    private Label footer;
    private Scene loginScreen;
    private Scene customerScreen;
    private Scene managerScreen;
    
    @Override
    public void start(Stage primaryStage){     
        primaryStage.setTitle("Bank Application");
        
        //Login Screen Scene
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        Label errorMessage = new Label("");    
        Button enter = new Button("Login");
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(username, password, enter, errorMessage);
        loginScreen = new Scene(layout1, 700, 300);
        enter.setOnAction((ActionEvent action) -> {
            if (username.getText().equals("admin")){
                try{
                    manager = new Manager(username.getText(), password.getText());
                }
                catch(Exception e){}
                if(manager.getLogin() == true){
                    
                    //Manager Post Login Scene
                    Button addCus = new Button("Add Customer");
                    Button delCus = new Button("Delete Customer");
                    Button logout = new Button("Logout");
                    Label errorMessage2 = new Label("");
                    cusUser = new TextField();
                    cusPass = new TextField();
                    send = new Button("Enter");
                    user = new Label("");
                    pass = new Label("");
                    cusUser.setDisable(true);
                    cusUser.setVisible(false);
                    cusPass.setDisable(true);
                    cusPass.setVisible(false);
                    send.setDisable(true);
                    send.setVisible(false);
                    user.setDisable(true);
                    user.setVisible(false);
                    pass.setDisable(true);
                    pass.setVisible(false);
                    BorderPane border  = new BorderPane();
                    HBox hbox = new HBox(addCus, delCus, logout, errorMessage2);
                    HBox bottom = new HBox(cusUser, cusPass, send, user, pass);
                    border.setTop(hbox);
                    border.setLeft(bottom);
                    managerScreen = new Scene(border, 700, 300);
                    primaryStage.setScene(managerScreen);
                    primaryStage.show();

                    addCus.setOnAction(e -> {
                        cusUser.setDisable(false);
                        cusUser.setVisible(true);
                        send.setDisable(false);
                        send.setVisible(true);
                        cusPass.setDisable(false);
                        cusPass.setVisible(true);
                        user.setDisable(true);
                        user.setVisible(false);
                        pass.setDisable(true);
                        pass.setVisible(false);
                        cusUser.setPromptText("Enter a Username");
                        cusPass.setPromptText("Enter a Password");
                        send.setOnAction(button  ->{
                            if(manager.addCustomer(cusUser.getText(), cusPass.getText())){
                                manager.addCustomer(cusUser.getText(), cusPass.getText()); 
                           }
                            else{
                                errorMessage2.setText("That user already exists");
                            }
                        });
                    });

                    delCus.setOnAction(e -> {
                        cusUser.setDisable(false);
                        cusUser.setVisible(true);
                        cusPass.setDisable(true);
                        cusPass.setVisible(false);
                        send.setDisable(false);
                        send.setVisible(true);
                        user.setDisable(true);
                        user.setVisible(false);
                        pass.setDisable(true);
                        pass.setVisible(false);
                        cusUser.setPromptText("Enter a Username");
                        send.setOnAction(button  ->{
                        if(manager.deleteCustomer(cusUser.getText())){
                            manager.deleteCustomer(cusUser.getText());
                        }
                        else{
                            errorMessage2.setText("That user already exists");
                        }
                        });
                    });
                    
                    logout.setOnAction(e -> {
                    primaryStage.setScene(loginScreen);
                    manager.logout();
                    });  
                }
                else{
                    errorMessage.setText("Password is incorrect");
                }
            }
            else{
                try{
                    customer = new Customer(username.getText(), password.getText());
                    customer.login(username.getText(), password.getText());
                }
                catch(FileNotFoundException e){
                    errorMessage.setText("Account does not exist");
                }
                if(customer.getLogin() == true){              
                    
                    //Customer Post Login Screen
                    Button deposit = new Button("Deposit money");
                    Button withdrawMoney = new Button("Withdraw money");
                    Button purchase = new Button("Online Purchase");
                    Button logout = new Button("Logout");
                    Label errorMessage2 = new Label("");
                    Value = new TextField();
                    send = new Button("Enter");
                    currentValue = new Label("");
                    footer = new Label("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr());
                    Value.setDisable(true);
                    send.setDisable(true);
                    Value.setVisible(false);
                    send.setVisible(false);
                    currentValue.setDisable(true);
                    currentValue.setVisible(false);
                    BorderPane border  = new BorderPane();
                    HBox hbox = new HBox(deposit, withdrawMoney, purchase, logout, errorMessage2);
                    HBox bottom = new HBox(Value, send, currentValue);
                    HBox floor = new HBox(footer);
                    border.setTop(hbox);
                    border.setLeft(bottom);
                    border.setBottom(floor);
                    customerScreen = new Scene(border, 700, 300);
                    primaryStage.setScene(customerScreen);
                    primaryStage.show();

                    deposit.setOnAction(e -> {
                        Value.setDisable(false);
                        send.setDisable(false);
                        Value.setVisible(true);
                        send.setVisible(true);
                        currentValue.setDisable(true);
                        currentValue.setVisible(false);
                        Value.setPromptText("Deposit Amount");
                        send.setOnAction(button  ->{
                            customer.deposit(Double.parseDouble(Value.getText()));
                            footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr());
                        });
                    });

                    withdrawMoney.setOnAction(e -> {
                        Value.setDisable(false);
                        send.setDisable(false);
                        Value.setVisible(true);
                        send.setVisible(true);
                        currentValue.setDisable(true);
                        currentValue.setVisible(false);
                        Value.setPromptText("Withdraw Amount");
                        send.setOnAction(button  ->{
                            if(Double.parseDouble(Value.getText()) <= customer.getBalance()){
                                customer.withdraw(Double.parseDouble(Value.getText()));
                                footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr());
                            }
                            else{
                                errorMessage2.setText("You don't have enough Money!");
                            }
                        });

                    });

                    purchase.setOnAction(e -> {
                        Value.setDisable(false);
                        send.setDisable(false);
                        Value.setVisible(true);
                        send.setVisible(true);
                        currentValue.setDisable(true);
                        currentValue.setVisible(false);
                        Value.setPromptText("Purchase Cost");
                        send.setOnAction(button  ->{
                            customer.onlinePurchase(Double.parseDouble(Value.getText()));
                            if (((Double.parseDouble(Value.getText())) + customer.getLevel().getFee()) < customer.getBalance()){
                                customer.onlinePurchase(Double.parseDouble(Value.getText()));
                                footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr());
                            }
                            else{
                                errorMessage2.setText("You don't have enough Money!");
                            }
                        });
                    });
                    logout.setOnAction(e -> {
                    primaryStage.setScene(loginScreen);
                    customer.logout();
                    });     
                }
                else{
                    errorMessage.setText("Password or username is incorrect!");
                }
            }
        });
            primaryStage.setScene(loginScreen);
            primaryStage.show();            
    }
        
    public static void main(String[] args){
        launch(args);
    }
}

