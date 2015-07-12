package application;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	Button	button = new Button ("Select");
	Label	index = new Label ();
	Label	selectedCountry = new Label ();
	int state=0;
	String[] arrayCountries = {"- no country selected - ", 
			"Abkhazia", "Afghanistan", "Albania", "Algeria", "Andorra", 
			"Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", 
			"Austria", "Azerbaijan", "Bahamas, The", "Bahrain", "Bangladesh", 
			"Barbados", "Belarus", "Belgium", "Belize", "Benin", 
			"Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", 
			"Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", 
			"Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic", 
			"Chad", "Chile", "China", "China (Taiwan), Republic of ", "Colombia", 
			"Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the", "Cook Islands", "Costa Rica", 
			"Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
			"Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador",
			"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Estonia", "Ethiopia", "Fiji", "France", "Gabon",
			"Gambia, The", "Georgia", "Germany", "Ghana", "Greece",
			"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Honduras", "Hungary", "Iceland", "India",
			"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
			"Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South",
			"Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
			"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
			"Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi",
			"Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
			"Mauritania", "Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova",
			"Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
			"Myanmar (Burma)", "Nagorno-Karabakh ", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
			"Niue", "Northern Cyprus ", "Norway", "Oman", "Pakistan",
			"Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Poland", "Portugal", "Qatar",
			"Romania", "Russia", "Rwanda", "Sahrawi Arab Democratic Republic", "Saint Kitts and Nevis",
			"Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "São Tomé and Príncipe",
			"Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"Somaliland", "South Ossetia", "South Sudan", "Spain", "Sri Lanka",
			"Sudan", "Sudan, South", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand",
			"Timor-Leste", "Togo", "Tonga", "Transnistria", "Trinidad and Tobago",
			"Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda",
			"Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
			"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
			"Yemen", "Zambia", "Zimbabwe"}; 
	ArrayList<String> listCountries = new ArrayList<String>(Arrays.asList(arrayCountries));
	ObservableList<String> countries = FXCollections.observableArrayList(listCountries);

	@Override public void start(Stage stage) {
		stage.setTitle("ComboBox");
		Scene scene = new Scene(new Group(), 450, 250);

		final ComboBox<String> emailComboBox = new ComboBox<String>();
		emailComboBox.getItems().addAll(countries);
		emailComboBox.getSelectionModel().selectFirst();

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (emailComboBox.getValue() != null && 
						!emailComboBox.getValue().toString().isEmpty()){
					String c = emailComboBox.getValue();
					if(c.equals("- no country selected - ")){
						int i=1;
						String s;
						do{
							s = emailComboBox.getItems().get(i);
							emailComboBox.getItems().remove(1);
						}while(!s.equals("------------------------"));
					}
					else{
						index.setText("Index: "+indexOf(c));
						selectedCountry.setText("Selected Country: "+c);
						addToComboBox(c);
						emailComboBox.getSelectionModel().clearAndSelect(0);
					}
				}
			}

			private void addToComboBox(String c) {
				// TODO Auto-generated method stub
				if(state == 0){
					emailComboBox.getItems().add(0, "- no country selected - ");
					emailComboBox.getItems().set(1, "------------------------");
				}
				int i = 1;
				boolean add = true;
				while(!emailComboBox.getItems().get(i).equals("------------------------")){
					if(emailComboBox.getItems().get(i).equals(c))
						add = false;
					i++;
				}

				if(add){
					if(state < 3){
						state++;
						emailComboBox.getItems().add(1, c);
					}
					else{
						emailComboBox.getItems().set(3, emailComboBox.getItems().get(2));
						emailComboBox.getItems().set(2, emailComboBox.getItems().get(1));
						emailComboBox.getItems().set(1, c);
		
					}

				}
			}

			private int indexOf(String value) {
				// TODO Auto-generated method stub
				for(int i=0; i<listCountries.size(); i++)
					if(listCountries.get(i).equals(value))
						return i;
				return -1;
			}
		});

		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(10);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(emailComboBox, 5, 5);
		grid.add(button, 5, 6);
		grid.add (index, 5, 7);
		grid.add (selectedCountry, 5, 8);

		Group root = (Group)scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}

}
