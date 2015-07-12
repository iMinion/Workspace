/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minigoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author YROOPA
 */
public class Client implements Initializable {

	@FXML
	private Label label;
	@FXML
	private TextField searchingText;
	@FXML
	private ListView<String> urlsList;

	Socket sock;
	PrintStream PS;
	BufferedReader in;
	public void initialize(URL url, ResourceBundle rb) {
		try {
			sock=new Socket("127.0.0.1",5555);
			PS=new PrintStream(sock.getOutputStream());
			//PS.println("Hello to Server from client");

			in =new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO
	} 
	public void displayUrls(){
		try{

			//InputStreamReader IR=new InputStreamReader(System.in);
			//BufferedReader BR=new BufferedReader(IR);
			//				String keyword;
			//				while((keyword = searchingText.getText())!=null){
			System.out.println("Beta");
			String keyword=searchingText.getText();
			String str=keyword;
			str.trim();
			if(str.length()>0){
			System.out.println(keyword+" keyword ");
			PS.println(keyword);
			String result=in.readLine();
			System.out.println(result+" rrrrr ");
			if(!result.equalsIgnoreCase("null" )) {
				String[] s=result.split(",");
				System.out.println("Result: " + result);
				ArrayList<String> list=new ArrayList<String>();
				for(int i=0;i<s.length;i++){
					if(!list.contains(s[i]))
						list.add(s[i]);
				}
				ObservableList<String> obserlist=FXCollections.observableArrayList(list);
				urlsList.setItems(obserlist);
				System.out.println("Alpha");
			}
			else {
				String s = "No results found";
				System.out.println(s);
				ArrayList<String> list=new ArrayList<String>();
				list.add(s);
				ObservableList<String> obserlist=FXCollections.observableArrayList(list);
				urlsList.setItems(obserlist);				
			}
			}
			else{
				String s = "Please enter a keyword";
				System.out.println(s);
				ArrayList<String> list=new ArrayList<String>();
				list.add(s);
				ObservableList<String> obserlist=FXCollections.observableArrayList(list);
				urlsList.setItems(obserlist);
				
			}
			//				}
		} catch(Exception e){}




	}
	public void clear(){
		searchingText.clear();
		urlsList.getItems().clear();
	}
	public void closeApplication(){
		System.exit(0);
	}

}
