package db.lab2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.lab2.view.View;


public class Model {
	private static Connection connection = null;
	private static String database = null;
	
	public static void connectionInitialization(String db, String login, String password) {
		try {
			database = db;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/" + database;
			connection = DriverManager.getConnection(url, login, password);
        
			if(connection != null) {
				System.out.println("The connection to the database is established\n");
			} else {
				System.out.println("No connection to database\n");
				return;
			}
		}catch (Exception e) {
				System.out.println(e);
		}
	}
	
	
	public static void connectionClose() {
		try {
			connection.close();
		}catch (SQLException e) {
			postgresError(e);
		}
	}
	
	
	public static boolean addData(String table, String[] columns, String[] data) {
		try {
			int numberColumn = countColumns(table);
			String insertData = "INSERT INTO " + table + " (";
			for(int i = 0; i < numberColumn; i++) {
				insertData += columns[i];
				if(i != numberColumn - 1) {
					insertData += ",";
				}
			}
			insertData += ") VALUES (";
			for(int i = 0; i < numberColumn; i++) {
				insertData += "'" + data[i] + "'";
				if(i != numberColumn - 1) {
					insertData += ",";
				}
			}
			insertData += ")";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(insertData);
			
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	public static boolean deleteData(String table, String row, String value) {
		try {
			String deleteData;
			if(row == null) deleteData = "DELETE FROM " + table;
			else deleteData = "DELETE FROM " + table + " WHERE " + row + " = " + "'" + value + "'";
		
			Statement statement = connection.createStatement();
			statement.executeUpdate(deleteData);
			
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		
		return true;
	}
	
	public static boolean updateData(String table, String[] newRow, String[] newData, String oldRow, String oldData) {
		try {
			int numberCoulumns = countColumns(table);
			String updateData = "UPDATE " + table + " SET ";
			for(int i = 0; i < numberCoulumns; i++) {
				updateData += newRow[i] + " = " + "'" + newData[i] + "'"; 
				if(i != numberCoulumns - 1) {
					updateData += ",";
				}
		}
		
		updateData += " WHERE " + oldRow + " = " + "'" + oldData + "'";
		Statement statement = connection.createStatement();
		statement.executeUpdate(updateData);
		
		statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	public static boolean randomData(String table, int numberColumns) {
		switch(table) {
		case "reader":
			return ramdomReader(numberColumns);
		case "author":
			return ramdomAuthor(numberColumns);
		case "book":
			return ramdomBook(numberColumns);
		case "reader_ticket":
			return ramdomReaderTicket(numberColumns);
		case "all":
			ramdomReader(numberColumns);
			ramdomAuthor(numberColumns);
			ramdomBook(numberColumns);
			return ramdomReaderTicket(numberColumns);
		default:
			return false;
		}
	}
	
	private static boolean ramdomReader(int numberColumns) {
		String ramdomReader = "INSERT INTO reader (reader_id, reader_name, reader_surname, reader_email)"
				+ "SELECT trunc(10 + random()*10000000)::int, "
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int),"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int),"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int)"
				+ "FROM generate_series(1, " + numberColumns + ")"; 
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(ramdomReader);
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	private static boolean ramdomAuthor(int numberColumns) {
		String ramdomAuthor = "INSERT INTO author (author_id, author_name, author_surname, author_email)"
				+ "SELECT trunc(10 + random()*10000)::int, "
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int),"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int),"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int)"
				+ "FROM generate_series(1, " + numberColumns + ")"; 
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(ramdomAuthor);
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	private static boolean ramdomBook(int numberColumns) {
		String ramdomBook = "INSERT INTO book (book_id, book_name, author_id, year_publication, count_books)\r\n"
				+ "(SELECT trunc(random()*10000)::int,\r\n"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||\r\n"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int) ||\r\n"
				+ "chr(trunc(65+random()*25)::int) || chr(trunc(65+random()*25)::int),\r\n"
				+ "author_id,\r\n"
				+ "trunc(1960 + random()*150)::int,\r\n"
				+ "trunc(random()*10000)::int\r\n"
				+ "FROM author tablesample BERNOULLI(100)\r\n"
				+ "ORDER BY random())  LIMIT " + numberColumns;
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(ramdomBook);
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	private static boolean ramdomReaderTicket(int numberColumns) {
		String ramdomReaderTicket = "INSERT INTO reader_ticket (reader_id, book_id, date_issued, validity)\r\n"
				+ "(SELECT reader_id, book_id,\r\n"
				+ "date '2012-01-01' + trunc(random() * 100 * (date '2020-05-20' - date '2012-01-01'))::int,\r\n"
				+ "trunc(15 + random()*10000)::int\r\n"
				+ "FROM reader, book tablesample BERNOULLI(100)\r\n"
				+ "ORDER BY random())  LIMIT " + numberColumns;
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(ramdomReaderTicket);
			statement.close();
		}catch (SQLException e) {
			postgresError(e);
			return false;
		}
		return true;
	}
	
	// return books by author name
	public static void searchByName(String name) {
		String joinText = "SELECT a.author_name, a.author_surname, book_name\r\n"
				+ "FROM book  AS b INNER JOIN\r\n"
				+ "(SELECT author_id, author_name, author_surname \r\n"
				+ "FROM author WHERE author_name LIKE '" + name + "') \r\n"
				+ "AS a ON b.author_id = a.author_id";
		String explainJoinText = "EXPLAIN ANALYZE (" + joinText + ")";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(joinText);
			View.printTable(rs, 3);
			rs = statement.executeQuery(explainJoinText);
			View.printExplain(rs);
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
		}
	}
	
	// return number book, which reader take for a certain period
	public static void countBookInReader(String date) {
		String countBookInReader = "SELECT reader_name, reader_surname, count(*) AS ticket_count \r\n"
				+ "FROM reader AS r INNER JOIN\r\n"
				+ "(SELECT reader_id, date_issued FROM reader_ticket WHERE date_issued > '" + date + "')\r\n"
				+ "AS rt ON r.reader_id = rt.reader_id\r\n"
				+ "GROUP BY reader_name, reader_surname\r\n"
				+ "ORDER BY ticket_count desc";
		String explainCountBookInReader = "EXPLAIN ANALYZE (" + countBookInReader + ")";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(countBookInReader);
			View.printTable(rs, 3);
			rs = statement.executeQuery(explainCountBookInReader);
			View.printExplain(rs);
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
		}
	}
	
	// return the most popular authors and their books
	public static void countPopularAuthor(String date) {
		String countPopularAuthor = "SELECT author_name, author_surname, count(*) AS ticket_count\r\n"
				+ "FROM author AS a INNER JOIN\r\n"
				+ "(SELECT rt.book_id, book_name, author_id, count(*) AS ticket_count\r\n"
				+ "FROM book AS b INNER JOIN\r\n"
				+ "(SELECT book_id, date_issued FROM reader_ticket WHERE date_issued > '2015-10-10')\r\n"
				+ "AS rt ON b.book_id = rt.book_id\r\n"
				+ "GROUP BY rt.book_id, b.book_name, author_id\r\n"
				+ "ORDER BY ticket_count desc)\r\n"
				+ "AS res ON res.author_id = a.author_id\r\n"
				+ "GROUP BY author_name, author_surname\r\n"
				+ "ORDER BY ticket_count desc";
		String explainCountPopularAuthor = "EXPLAIN ANALYZE (" + countPopularAuthor + ")";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(countPopularAuthor);
			View.printTable(rs, 3);
			rs = statement.executeQuery(explainCountPopularAuthor);
			View.printExplain(rs);
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
		}
	}
	
	
	public static String[] getColumnsName(String table) {
		String[] result = null;
		try {
			String getColumns = "SELECT column_name FROM information_schema.columns WHERE table_name = '" + table + "'";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getColumns);
			int size = countColumns(table);
			result = new String[size];
			
			for(int i = 0; rs.next(); i++) {
				result[i] = rs.getString(1);
			}
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
			return null;
		}
		
		return result;
	}
	
	public static String[] getColumnsType(String table) {
		String[] result = null;
		try {
			String getType = "SELECT data_type FROM information_schema.columns WHERE table_name = '" + table + "'";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(getType);
			
			result = new String[countColumns(table)];
			
			for(int i = 0; rs.next(); i++) {
				result[i] = rs.getString(1);
			}
			
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
			return null;
		}
		
		return result;
	}
	
	public static int countColumns(String table) {
		int result = 0;
		
		String countCoulumns = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_catalog = '" + 
				database + "'" + "AND table_name = '" + table + "'";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(countCoulumns);
			if(rs.next()) result = rs.getInt(1);
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
			return 0;
		}
		return result;
	}
	
	public static void printTable(String table) {
		try {
			String printTable = "SELECT * FROM " + table;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(printTable);
			int size = countColumns(table);
			View.printTable(rs, size);
			statement.close();
			rs.close();
		}catch (SQLException e) {
			postgresError(e);
		}
	}
	
	private static void postgresError(SQLException e) {
		System.out.println("\nError: Incorrect data entered");
	}
	
	public static void printColumnsNameType(String table) {
		String[] columnsName = getColumnsName(table);
		String[] columnsType = getColumnsType(table);
		
		int size = columnsName.length;
		
		String[][] columnsNameType = new String[size][2];
		
		for(int i = 0; i < size; i++) {
			columnsNameType[i][0] = columnsName[i];
			columnsNameType[i][1] = columnsType[i];
		}
		
		View.printMatrix(columnsNameType);
	}
}
