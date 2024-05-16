package org.example.dao;

import org.example.Expense;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImpl implements ExpenseDAO{

    Connection con ;

    @Override
    public void connection() {

        String url = "jdbc:mysql://localhost/expense";
        String username = "root";
        String password = "Faizan@786";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException | SQLException s){
            System.out.println(s);
        } finally {
            System.out.println("connected");
        }
    }

    @Override
    public void addExpense(Expense expense) {
        String query = "insert into expense(description,amount,date) values(?,?,?)";

        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,expense.getDesc());
            pst.setDouble(2, expense.getAmount());
            pst.setDate(3, Date.valueOf(expense.getDate()));

            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s Affected");

        }catch(SQLException s){
            System.out.println(s);
        }
    }

    @Override
    public void getExpenseById(int id) {
        Expense expense = new Expense();

        String query = "select * from expense where id = ?";

        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            rs.next();
            System.out.println(rs.getInt("id") + " : " + rs.getString("description") + " : "
                             + rs.getDouble("amount") + " : " + rs.getDate("date")  );


        }catch(SQLException s){
            System.out.println(s);
        }

    }

    @Override
    public void updateExpense(int id,Expense expense) {
        String query = "update expense set description = ? , amount = ?, date = ? where id = ?";

        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,expense.getDesc());
            pst.setDouble(2, expense.getAmount());
            pst.setDate(3, Date.valueOf(expense.getDate()));
            pst.setInt(4,id);

            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s Affected");


        }catch(SQLException s){
            System.out.println(s);
        }
    }

    @Override
    public void deleteExpense(int id) {
        String query = "delete from expense where id = ? ";

        try{
            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1 , id);

            int rowAffected = pst.executeUpdate();
            System.out.println(rowAffected + " row/s Affected");

        }catch(SQLException s){
            System.out.println(s);
        }
    }

    @Override
    public List<Expense> getAllExpense() {

        List<Expense> expenses = new ArrayList<>();

        String query = "select * from expense";

        try{
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                expenses.add(new Expense(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDate(4).toLocalDate()));
//                System.out.println(rs.getInt("id") + " : " + rs.getString("desc") + " : "
//                        + rs.getDouble("amount") + " : " + rs.getDate("date")  );
            }
            return expenses;

        }catch(SQLException s){
            System.out.println(s);
        }
        return null;
    }

    @Override
    public void closeConnection() {
        try{
            con.close();
        }catch(SQLException s){
            System.out.println(s);
        } finally {
            System.out.println("disconnected");
        }
    }
}
