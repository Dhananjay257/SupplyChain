package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
   public static final int width = 700, height=600,headerBar=50;
    Pane bodyPane=new Pane();
    Pane root;
   Login login = new Login();
   SignUp signUp=new SignUp();
   ProductDetails productDetails = new ProductDetails();
   Button globalLoginButton;
   Button globalSignUpButton;
   Label customerEmailLable=null;
   Pane footerBarPane;
   String customerEmail;
   String userName;
   private  GridPane headerBar()
   {
       TextField searchText = new TextField();
       Button searchButton = new Button("Search");
       searchButton.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent actionEvent) {
               String productName = searchText.getText();
               //clear body and put this new pane
               bodyPane.getChildren().clear();
               bodyPane.getChildren().add(productDetails.getProductsByName(productName));
           }
       });

       globalLoginButton = new Button("Log In");
       customerEmailLable = new Label("Welcome User");
       globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               bodyPane.getChildren().clear();
               bodyPane.getChildren().add(loginPage());
               globalLoginButton.setDisable(true);

           }
       });
       globalSignUpButton=new Button("SignUp");
       globalSignUpButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               bodyPane.getChildren().clear();
               bodyPane.getChildren().add(signUpPage());


           }
       });

       customerEmailLable= new Label("Welcome User");
       GridPane gridPane = new GridPane();
       gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
       gridPane.setVgap(5);
       gridPane.setHgap(5);
       gridPane.setAlignment(Pos.CENTER);
       gridPane.add(searchText,0,0);
       gridPane.add(searchButton,1,0);
       gridPane.add(globalLoginButton,2,0);
       gridPane.add(customerEmailLable,4,0);
       gridPane.add(globalSignUpButton,3,0);
       return gridPane;
   }
   private GridPane loginPage()
   {
       root.getChildren().remove(footerBarPane);
       Label emailLabel = new Label("Email");
       Label passwordLabel = new Label("Password");
       Label messageLabel= new Label("Please Login !");
       TextField emailTextField = new TextField();
       PasswordField passwordField = new PasswordField();
       Button loginButton = new Button("Login");
       loginButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {

               String email= emailTextField.getText();
               String password = passwordField.getText();
               //messageLabel.setText(email+"=="+password);
               if(login.customerlogin(email,password))
               {
                   messageLabel.setText("Login Successfull !!");
                   customerEmail=email;
                   globalLoginButton.setDisable(true);
                   customerEmailLable.setText("Welcome :"+customerEmail);
                   bodyPane.getChildren().clear();
                   bodyPane.getChildren().add(productDetails.getAllProducts());
                   root.getChildren().addAll(footerBarPane);
               }
               else {
                   messageLabel.setText("Login Failed !!");
               }
           }
       });
       GridPane gridPane = new GridPane();
       gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
       gridPane.setVgap(5);
       gridPane.setHgap(5);
       gridPane.setStyle("-fx-background-color: #C0C0C0");
       gridPane.setAlignment(Pos.CENTER);
       gridPane.add(emailLabel,0,0);
       gridPane.add(emailTextField,1,0);
       gridPane.add(passwordLabel,0,1);
       gridPane.add(passwordField,1,1);
       gridPane.add(loginButton,0,2);
       gridPane.add(messageLabel,1,2);
       return gridPane;
   }

    private GridPane signUpPage()
    {
        root.getChildren().remove(footerBarPane);
        Label firstnameLabel = new Label("First Name");
        Label lastnameLabel = new Label("Last Name");
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label numberLabel = new Label("Phone number ");
        Label messageLabel = new Label("Please signup");
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        TextField numberTextField = new TextField();
        // String customerName = String.format("SELECT name FROM customer WHERE email = '%s'",customerEmail);
        Button signupButton = new Button("Sign Up !");
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(footerBarPane);
                String firstname = firstNameTextField.getText();
                String lastname = lastNameTextField.getText();
                String email= emailTextField.getText();
                String password = passwordField.getText();
                String number = numberTextField.getText();
                //messageLabel.setText(email+"=="+password);
                if(signUp.addCustomer(email,password,firstname,lastname,number)){
                    messageLabel.setText("SignUp Successful");
                    globalSignUpButton.setDisable(true);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().addAll(productDetails.getAllProducts());
                    root.getChildren().addAll(footerBarPane);
                  //  root.getChildren().addAll(headerBar(),bodyPane,footerBarPane);
                }else{
                    messageLabel.setText("SignUp Failed");
                }
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.add(firstnameLabel,0,0);
        gridPane.add(firstNameTextField,1,0);
        gridPane.add(lastnameLabel,0,1);
        gridPane.add(lastNameTextField,1,1);
        gridPane.add(emailLabel,0,2);
        gridPane.add(emailTextField,1,2);
        gridPane.add(passwordLabel,0,3);
        gridPane.add(passwordField,1,3);
        gridPane.add(numberLabel,0,4);
        gridPane.add(numberTextField,1,4);
        gridPane.add(signupButton,0,5);
        gridPane.add(messageLabel,1,5);
        return gridPane;
    }

    private  GridPane footerBar()
    {
        Button addToCartButton = new Button("Add to cart");
        Button buyNowButton = new Button("Buy Now");
        Label messageLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct = productDetails.getSelectedProduct();
                if(Order.placeOrder(customerEmail,selectedProduct))
                {
                    messageLabel.setText("Ordered");
                }
                else {
                    messageLabel.setText("Order Failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(50);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar+height+5);
        gridPane.add(addToCartButton,0,0);
        gridPane.add(buyNowButton,1,0);
        gridPane.add(messageLabel,2,0);
        return gridPane;
    }
    private Pane createContent(){
        root = new Pane();
        root.setPrefSize(width,height+2*headerBar);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
       footerBarPane=footerBar();
      //  bodyPane.getChildren().addAll(loginPage());
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(),bodyPane,footerBarPane);
        //root.getChildren().remove(footerBarPane);
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SupplyChain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
