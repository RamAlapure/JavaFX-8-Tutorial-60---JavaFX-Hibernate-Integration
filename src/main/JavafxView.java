package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import beans.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavafxView extends Application {

	private TextField fName, lName, email, mobileNo;
	private DatePicker date;
	private Button savebtn;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX 8 Tutorial 60 - JavaFX Hibernate Integration");
		
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		
		fName = new TextField();
		fName.setTooltip(new Tooltip("Enter First Name"));
		fName.setFont(Font.font("SanSerif", 15));
		fName.setPromptText("First Name");
		fName.setMaxWidth(200);
		
		lName = new TextField();
		lName.setTooltip(new Tooltip("Enter Last Name"));
		lName.setFont(Font.font("SanSerif", 15));
		lName.setPromptText("Last Name");
		lName.setMaxWidth(200);
		
		email = new TextField();
		email.setTooltip(new Tooltip("Enter Email"));
		email.setFont(Font.font("SanSerif", 15));
		email.setPromptText("Email");
		email.setMaxWidth(200);
		
		mobileNo = new TextField();
		mobileNo.setTooltip(new Tooltip("Enter Mobile Number"));
		mobileNo.setFont(Font.font("SanSerif", 15));
		mobileNo.setPromptText("Mobile No");
		mobileNo.setMaxWidth(200);
		
		date = new DatePicker();
		date.setTooltip(new Tooltip("Enter Date of Birth"));		
		date.setPromptText("Date of Birth");
                date.setMaxWidth(200);
                date.setStyle("-fx-font-size:15");
		
                //Hibernate Configuration
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
					
		User user = new User();
		
		savebtn = new Button("Save");
		savebtn.setTooltip(new Tooltip("Save the User Details"));
		savebtn.setFont(Font.font("SanSerif", 15));
		savebtn.setOnAction(e ->{
			user.setFirstName(fName.getText());
			user.setLastName(lName.getText());
			user.setEmail(email.getText());
			user.setDate(date.getEditor().getText());
			user.setMobileNo(mobileNo.getText());
			
			Session session = sf.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			
			clearFields();
			
		});
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(fName, lName, date, email, mobileNo, savebtn);
		vbox.setPadding(new Insets(10));		
		root.getChildren().add(vbox);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void clearFields() {
		// TODO Auto-generated method stub
		fName.clear();
		lName.clear();
		email.clear();
		mobileNo.clear();
		date.getEditor().setText(null);	
		date.setValue(null);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
