package it.polito.tdp.dizionariograph;

import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model = new Model();
	
	public void setModel(Model model) {
		//Bottonidisabilitati all'avvio
	    btnGradoMax.setDisable(true);
	    btnTrovaVicini.setDisable(true);
	    txtCercare.setDisable(true);
	    
		this.model=model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtCercare;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGradoMax(ActionEvent event) {
    	int gradomax=model.findMaxDegree();
    	txtResult.appendText("Grado massimo del grafo = "+gradomax);
    }

    @FXML
    void doGrafo(ActionEvent event) {

    try {
    	
    model.createGraph( Integer.parseInt(txtLettere.getText()));
    
    //Abilito i bottoni
    btnGradoMax.setDisable(false);
    btnTrovaVicini.setDisable(false);
    txtCercare.setDisable(false);
    }catch(NumberFormatException e) {
    	txtResult.appendText("Inserire un valore corretto!");
    }
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	//Disabilito i bottoni quando resetto
    	btnGradoMax.setDisable(true);
	    btnTrovaVicini.setDisable(true);
	    txtCercare.setDisable(true);
	    
	    //Cancello campi di testo
    	txtResult.clear();
    	txtLettere.clear();
    	txtCercare.clear();
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	txtResult.clear();

    	List<String> vicini = model.displayNeighbours(txtCercare.getText());
    	
    	if (vicini.isEmpty()) txtResult.appendText("Nessun risultato trovato!");
    	for(String s : vicini) txtResult.appendText(s+"\n");
    	txtResult.appendText("Grado del nodo = "+vicini.size()+"\n");
    	
    }

    @FXML
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtCercare != null : "fx:id=\"txtCercare\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
}

