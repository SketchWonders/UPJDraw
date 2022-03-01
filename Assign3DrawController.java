import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
//import javafx.collections.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Assign3DrawController {
	public Shape s;
	private String[] colorStrings = {"Full", "Light", "Empty"};
	private String colorChoice = colorStrings[0];
	private	ObservableList<String> colorList = FXCollections.observableArrayList(colorStrings);
	
    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private RadioButton lineRadioButton;

    @FXML
    private RadioButton circleRadioButton;

    @FXML
    private RadioButton rectangleRadioButton;

    @FXML
    private TextField errorMessagesTextField;
	
	@FXML
    private Pane displayPane;
	
	@FXML
    private ColorPicker colorPicker;

	public Color c;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button drawButton;
	
	private Boolean somethingIsPicked = false;

    @FXML
    void circleRadioButtonSelected(ActionEvent event) {
		somethingIsPicked = true;
		label1.setText("Center x");
		label2.setText("Center y");
		label3.setText("Radius");
		
		label4.setText("");
		label4.setVisible(false);
		textField4.setVisible(false);
		s = new Circle (); 
		
    }

    @FXML
    void colorPicked(ActionEvent event) {
		c = colorPicker.getValue();
		//this.setFill(colorPicker.getValue());
    }

    @FXML
    void comboBoxPicked(ActionEvent event) {
		//String selected = comboBox.getValue().toString();
		switch (colorChoice)  {
			case "Full" :	c = new Color (c.getRed(), c.getGreen(), c.getBlue(), 1);break;
			case "Light" :	c = new Color (c.getRed(), c.getGreen(), c.getBlue(), .5);break;
			case "Empty" :	c = new Color (c.getRed(), c.getGreen(), c.getBlue(), 0); break;
		}
		/*
		if(selected.equals("Dark")){
			c = c;
		}
		else if(selected.equals("Light")){
			//changes opacity
			c = new Color (c.getRed(), c.getGreen(), c.getBlue(), .5);
		}
		else{
			c = new Color(c.getRed(), c.getGreen(), c.getBlue(), .5);
		}
		*/
    }

    @FXML
    void drawButtonPressed(ActionEvent event) {
		try{
			if (!somethingIsPicked){
				throw (new NoOptionPickedException());
			}
			if(s instanceof Circle){
				((Circle)s).setCenterX(Double.valueOf(textField1.getText()));
				((Circle)s).setCenterY(Double.valueOf(textField2.getText()));
				((Circle)s).setRadius(Double.valueOf(textField3.getText()));
				((Circle)s).setFill(c);
				((Circle)s).setStroke(Color.BLACK);
				if((Double.valueOf(textField1.getText())- Double.valueOf(textField3.getText()))<0||((Double.valueOf(textField2.getText())- Double.valueOf(textField3.getText()))<0)){
					throw (new OutofWindowException());
				}
				if((Double.valueOf(textField1.getText())+ Double.valueOf(textField3.getText()))>600||((Double.valueOf(textField2.getText())+ Double.valueOf(textField3.getText()))>255)){
					throw (new OutofWindowException());
				}
			}
			else if(s instanceof Line){
				((Line)s).setStartX(Double.valueOf(textField1.getText()));
				((Line)s).setStartY(Double.valueOf(textField2.getText()));
				((Line)s).setEndX(Double.valueOf(textField3.getText()));
				((Line)s).setEndY(Double.valueOf(textField4.getText()));
				((Line)s).setFill(c);
				if((Double.valueOf(textField1.getText())<0)||((Double.valueOf(textField2.getText()))<0)||((Double.valueOf(textField3.getText()))<0)||((Double.valueOf(textField4.getText()))<0)){
					throw (new OutofWindowException());
				}
				if(Double.valueOf(textField1.getText())>600 ||Double.valueOf(textField2.getText())>255||Double.valueOf(textField3.getText())>600|| Double.valueOf(textField4.getText())>255){
					throw (new OutofWindowException());
				}
			}
			else if (s instanceof Rectangle){
				((Rectangle)s).setX(Double.valueOf(textField1.getText()));
				((Rectangle)s).setY(Double.valueOf(textField2.getText()));
				((Rectangle)s).setWidth(Double.valueOf(textField3.getText()));
				((Rectangle)s).setHeight(Double.valueOf(textField4.getText()));
				((Rectangle)s).setFill(c);
				((Rectangle)s).setStroke(Color.BLACK);
				if((Double.valueOf(textField1.getText())<0)||((Double.valueOf(textField2.getText()))<0)||((Double.valueOf(textField3.getText()))<0)||((Double.valueOf(textField4.getText()))<0)){
					throw (new OutofWindowException());
				}
				if((Double.valueOf(textField1.getText())+ Double.valueOf(textField3.getText()))>600||((Double.valueOf(textField2.getText())+ Double.valueOf(textField4.getText()))>255)){
					throw (new OutofWindowException());
				}
			}
			errorMessagesTextField.setText("");
			displayPane.getChildren().add(s);
		}
		catch (NullPointerException w){
			errorMessagesTextField.setText("Please enter values");
		}
		catch(NumberFormatException x){
			errorMessagesTextField.setText("Please enter valid values");
		}
		catch(NoOptionPickedException y){
			errorMessagesTextField.setText("Please pick a shape");
		}
		catch(OutofWindowException e){
			errorMessagesTextField.setText("Object does not fit in field");
		}
		catch(IllegalArgumentException u){
			errorMessagesTextField.setText("You already added that shape.");
		}
		catch(Exception z){
			errorMessagesTextField.setText("Something really bad happened...");
		}
    }

    @FXML
    void lineRadioButtonSelected(ActionEvent event) {
		somethingIsPicked = true;
		label1.setText("First Point x");
		label2.setText("First Point y");
		label3.setText("Second Point x");
		label4.setText("Second Point y");
		label4.setVisible(true);
		textField4.setVisible(true);
		
		s = new Line (); 
		
    }

    @FXML
    void rectangleRadioButtonSelected(ActionEvent event) {
		somethingIsPicked = true;
		label1.setText("Point x");
		label2.setText("Point y");
		label3.setText("length");
		label4.setText("height");
		label4.setVisible(true);
		textField4.setVisible(true);
		
		s = new Rectangle (); 
		
    }

	public void initialize() {

		comboBox.setItems(colorList);
		
		
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
        @Override 
			public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
				colorChoice = newValue;
			}    
		}
		);
		
		
	}
}


class OutofWindowException extends IllegalStateException
{
     public OutofWindowException()
     {
            super("Index not in window");
     }
}

class NoOptionPickedException extends IllegalStateException{
	public NoOptionPickedException(){
		super("Please pick a shape.");
	}
}