package db.lab2.view;

import db.lab2.model.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class View {
	public static void printTable(ResultSet rs, int size) {
		try {		
 			while (rs.next()) {
				for(int i = 1; i <= size; i++) {
					System.out.printf("%-50s", rs.getString(i));
				}
				System.out.printf("\n");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void printExplain(ResultSet rs) {
		try {
			String time;
			while(rs.next()) {
				if((time = rs.getString(1)).startsWith("Execution Time:")) {
					System.out.println(time);
				}
			}
		}catch (SQLException e) {
		System.out.println(e);

		}
	}
	
	public static void performOperation(boolean bool) {
		if(bool) {
			System.out.println("\nOperation is performed");
		} else {
			System.out.println("\nOperation is not performed");
		}
	}
	
	public static int viewMenu() {
		String menu = "1. Add data\n"
					+ "2. Delete data\n"
					+ "3. Update data\n"
					+ "4. Input random data\n"
					+ "5. Search for books by author's name\n"
					+ "6. Search for readers with the largest number of books in a given period\n"
					+ "7. Search for the most popular authors for a certain period\n"
					+ "8. View table\n"
					+ "9. View table columns name\n"
					+ "10. Exit";
		Scanner in = new Scanner(System.in);
		System.out.println("\n" + menu);
		System.out.print("Enter the menu number: ");
		int numberMenu = in.nextInt();
		
		return numberMenu;
	}
	
	public static String[] inputAddData() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input table: ");
		String table = in.nextLine();
		
		int numberColumns = Model.countColumns(table);
		String[] result = new String[numberColumns + 1];
		
		String[] columnsName = Model.getColumnsName(table);
		
		for(int i = 0; i < numberColumns; i++) {
			System.out.print("Input " + columnsName[i] + ": ");
			result[i] = in.nextLine();
		}
		
		result[numberColumns] = table;
		
		return result;
	}
	
	public static String[] inputDeleteData() {
		Scanner in = new Scanner(System.in);
		
		String[] result = new String[3];
		
		System.out.print("Input table: ");
		result[0] = in.nextLine();
		
		System.out.print("Input the name of the column by which the data will be displayed(if delete all data in table input 'null'): ");
		result[1] = in.nextLine();
		
		System.out.print("Input value column: ");
		result[2] = in.nextLine();
		
		return result;
	}
	
	public static String[] inputUpdateData() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input table: ");
		String table = in.nextLine();
		
		int numberColumns = Model.countColumns(table);
		String[] result = new String[numberColumns + 3];
		
		String[] columnsName = Model.getColumnsName(table);
		
		result[numberColumns] = table;
		
		System.out.print("Input the name of the column in which the data changes: ");
		result[numberColumns + 1] = in.nextLine();
		
		System.out.print("Input value column: ");
		result[numberColumns + 2] = in.nextLine();
		
		for(int i = 0; i < numberColumns; i++) {
			System.out.print("Input " + columnsName[i] + ": ");
			result[i] = in.nextLine();
		}
		
		return result;
	}
	
	public static String[] inputRandomData() {
		Scanner in = new Scanner(System.in);
		
		String[] result = new String[2];
		
		System.out.print("Input table( all tables, input 'all'): ");
		result[0] = in.nextLine();
		
		System.out.print("Input the amount of random data: ");
		result[1] = in.nextLine();
		
		return result;
	}
	
	public static String inputSearchByName() {
		Scanner in = new Scanner(System.in);
			
		System.out.print("Input name: ");
		String name = in.nextLine();
			
		return name;
	}
	
	public static String inputCountBookInReader() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input date(YYYY-MM-DD): ");
		String date = in.nextLine();
			
		return date;
	}
	
	public static String inputCountPopularAuthor() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input date(YYYY-MM-DD): ");
		String date = in.nextLine();
			
		return date;
	}
	
	public static String inputTable() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Input table: ");
		String table = in.nextLine();
			
		return table;
	}
	
	public static void printMatrix(String[][] matrix) {
		int numberRows = matrix.length;
		int numberColumns = matrix[0].length;
		
		for(int i = 0; i < numberRows; i++) {
			for(int j = 0; j < numberColumns; j++) {
				System.out.printf("%-20s", matrix[i][j]);
			}
			System.out.println();
		}
	}
}
