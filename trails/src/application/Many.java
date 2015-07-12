package application;

import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class Many {
	@FXML
	private DatePicker fine;
	
	public void hello() {
		String s = fine.getValue().toString();
		System.out.println(s);
		Calendar c = Calendar.getInstance();
		System.out.println(c);
		Date d = new Date();
		System.out.println(d);
	}
}
