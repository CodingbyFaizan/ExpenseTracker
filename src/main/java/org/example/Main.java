package org.example;

import org.example.dao.ExpenseDAOImpl;
import java.time.LocalDate;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);

        ExpenseDAOImpl dao = new ExpenseDAOImpl();

        dao.connection();

        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("              +----------------------------------------+");
            System.out.println("                 WELCOME TO EXPENSE TRACKER SYSTEM");
            System.out.println("              +----------------------------------------+");

            System.out.println();
            System.out.println("                      +----------------------+");
            System.out.println("                         FEATURES AVAILABLE");
            System.out.println("                      +----------------------+");

            System.out.println();
            System.out.println("------------------------");
            System.out.println("1. ADD A EXPENSE");
            System.out.println("2. DELETE A EXPENSE");
            System.out.println("3. UPDATE A EXPENSE");
            System.out.println("4. GET ALL EXPENSE");
            System.out.println("5. GET EXPENSE BY ID");
            System.out.println("6. EXIT");
            System.out.println("------------------------");

            System.out.println();

            System.out.println("ENTER YOUR CHOICE : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the description ");
                    String desc = sc.nextLine();
                    System.out.println("Enter the amount");
                    Double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter a date in format (YYYY-MM-DD)");
                    String date = sc.nextLine();

                    LocalDate convertedDate = LocalDate.parse(date);
                    Expense expense1 = new Expense(desc, amount,convertedDate);
                    dao.addExpense(expense1);
                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    System.out.println("Enter a expense id you want to delete");
                    System.out.println("check the expense id present in the DB");
                    int id = sc.nextInt();
                    dao.deleteExpense(id);

                    System.out.println("Expense Deleted successfully!");
                    break;

                case 3:
                    System.out.println("Enter a expense id you want to update!");
                    int expenseIdForUpdate = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter new details for the expense!");
                    System.out.println("Enter the description ");
                    String updatedDesc = sc.nextLine();
                    System.out.println("Enter the amount");
                    Double updatedAmount = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter a date in format (YYYY-MM-DD)");
                    String updatedDate = sc.nextLine();

                    Expense updatedExpense = new Expense(updatedDesc, updatedAmount, LocalDate.parse(updatedDate));
                    dao.updateExpense(expenseIdForUpdate,updatedExpense);
                    System.out.println("Expense Updated Successfully!");
                    break;

                case 4:
                    for (Expense expense : dao.getAllExpense()) {
                        System.out.println(expense.getId() + " : " + expense.getDesc() + " : " +
                                expense.getAmount() + " : " + expense.getDate());
                    }
                    break;

                case 5:
                    System.out.println("Enter the Expense Id : ");
                    int expenseId = sc.nextInt();

                    dao.getExpenseById(expenseId);
                    break;

                case 6:
                    sc.close();
                    dao.closeConnection();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! ");
                    System.out.println("choice should be between (1-6)");
                    break;
            }

        }
    }
}
