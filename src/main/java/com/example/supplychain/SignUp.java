package com.example.supplychain;

public class SignUp {

    public boolean addCustomer(String email,String password,String first_name,String lastname,String mobile ){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        String query=String.format("insert into customer (email,password,first_name,lastname,mobile) values ('%s','%s','%s','%s','%s')",
                email,password,first_name,lastname,mobile);
        int rowsAffected=0;
        try{
            rowsAffected=databaseConnection.executeUpdateQuery(query);

        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected!=0;

    }
}
