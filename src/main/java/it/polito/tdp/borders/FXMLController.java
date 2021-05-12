
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> cmbBoxNazioni;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	if(txtAnno.getText() == null || txtAnno.getText().equals(""))
    		txtResult.setText("Devi inserire l'anno!");
    	
    	int annoMax;
    	
    	try {
    		annoMax = Integer.parseInt(txtAnno.getText());
    	} catch(NumberFormatException nfe) {
    		throw new RuntimeException("Errore Formato Anno!", nfe);
    	}
    	
    	model.creaGrafo(annoMax);
    	
    	txtResult.appendText(model.getStati());
    	
    	cmbBoxNazioni.getItems().addAll(model.getNodi());
    	
    }

    @FXML
    void doStatiRaggiungibili(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBoxNazioni != null : "fx:id=\"cmbBoxNazioni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
