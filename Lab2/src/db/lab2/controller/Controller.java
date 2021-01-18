package db.lab2.controller;

import db.lab2.model.Model;
import db.lab2.view.View;


public class Controller {
	public static void main(String[] args) {
		String db = "library";
		String login = "postgres";
		String password = "124/1/28";
		Model.connectionInitialization(db, login, password);
		
		menu();
		
		Model.connectionClose();
	}
	
	public static void menu() {
		int numberMenu = 0;
		while(true) {
			numberMenu = View.viewMenu();
			
			switch(numberMenu) {
			case 1: {
				String[] addRows = {"reader_id", "reader_name", "reader_surname", "reader_email"};
				String[] addData = View.inputAddData();
				String table = addData[addData.length - 1];
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
