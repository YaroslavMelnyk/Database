package db.lab2.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import db.lab2.model.Model;
import db.lab2.view.View;

/*
 * ретьим звеном данной цепи €вл€етс€ контроллер. ¬ нем хранитс€ код, который 
 * отвечает за обработку действий пользовател€ (любое действие пользовател€ в системе обрабатываетс€ в контроллере). 
 * ќсновное предназначение  онтроллера Ч обрабатывать действи€ пользовател€. 
 * »менно через  онтроллер пользователь вносит изменени€ в модель. “очнее в данные, которые хран€тс€ в модели.
 * 
 * */

public class Controller {
	public static void main(String[] args) {
		String db = "library";
		String login = "postgres";
		String password = "124/1/28";
		Model.connectionInitialization(db, login, password);
		
		String tableReader = "reader";
		String tableReaderTicket = "reader_ticket";
		String tableAuthor = "author";
		String tableBook = "book";
		int numberRow = 4;
		
		String[] addRows = {"reader_id", "reader_name", "reader_surname", "reader_email"};
		String[] addData = {"2", "name2", "surname2", "nn2@nn.com"};
		//Model.addData(tableReader, numberRow, addRows, addData);
		
		String deleteRow = "reader_id";
		String deleteValue = "11";
		//Model.deleteData(tableReader, deleteRow, deleteValue);
		
		String[] updateRows = {"reader_id", "reader_name", "reader_surname", "reader_email"};
		String[] updateData = {"10", "name1", "surname1", "aa@aa.com"};
		String oldRow = "reader_id";
		String oldData = "9";
		//Model.updateData(table, numberRow, updateRows, updateData, oldRow, oldData);
		
		int numberData = 20;
		//Model.randomData(tableReaderTicket, numberData);
		
		String joinText = "ƒжоан";
		//Model.joinText(joinText);
		
		String date = "2015-01-01";
		//Model.countBookInReader(date);
		
		//Model.countPopularAuthor(date);
		
		menu();
		
		Model.connectionClose();
	}
	
	public static void menu() {
		int numberMenu = 0;
		Scanner in = new Scanner(System.in);
		while(true) {
			numberMenu = View.viewMenu();
			//System.out.println("Number menu: " + numberMenu);
			
			switch(numberMenu) {
			case 1: {
				String[] addRows = {"reader_id", "reader_name", "reader_surname", "reader_email"};
				String[] addData = View.inputAddData();
				String table = addData[addData.length - 1];
				//System.out.println("Table: " + table);
				View.performOperation(Model.addData(table, addRows, addData));
				break;
			}
			case 2:{
				String[] deleteData = View.inputDeleteData();
				View.performOperation(Model.deleteData(deleteData[0], deleteData[1], deleteData[2]));
				break;
			}
			case 3:{
				String[] updateRows = {"reader_id", "reader_name", "reader_surname", "reader_email"};
				String[] updateData = View.inputUpdateData();
				int length = updateData.length;
				View.performOperation(Model.updateData(updateData[length - 3], updateRows, updateData, updateData[length - 2], updateData[length - 1]));
				break;
			}
			case 4:{
				String[] randomData = View.inputRandomData();
				View.performOperation(Model.randomData(randomData[0], Integer.parseInt(randomData[1])));
				break;
			}
			case 5:{
				String searchName = View.inputSearchByName();
				Model.searchByName(searchName);
				break;
			}
			case 6:{
				String date = View.inputCountBookInReader();
				Model.countBookInReader(date);
				break;
			}
			case 7:{
				String date = View.inputCountPopularAuthor();
				Model.countPopularAuthor(date);
				break;
			}
			case 8:{
				String table = View.inputTable();
				Model.printTable(table);
				break;
			}
			case 9:{
				String table = View.inputTable();
				Model.printColumnsNameType(table);
				break;
			}
			case 10: {
				return;
			}
				
			}
		}
	}
}
