package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<String> Citycmb;

    @FXML
    private Button ShowReportBtn;

    @FXML
    private Button ExitBtn;

    @FXML
    private TextField NumOfMaps;

    @FXML
    private TextField NumOfPurchases;

    @FXML
    private TextField NumOfSubscriptions;

    @FXML
    private TextField NumOfViews;

    @FXML
    private TextField NumOfRenewals;

    @FXML
    private TextField NumOfDownloads;

    @FXML
    void Exit(ActionEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void ShowReport(ActionEvent event) {
    	String CityForReport=new String();
   	    CityForReport=(String)this.Citycmb.getSelectionModel().getSelectedItem();//gets the selected city
   	    try {
			Main.client.sendToMyCLient(CityForReport);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       	try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ArrayList<String> CityReport=new ArrayList<String>();
    	CityReport=Main.client.RecieveCityReport();
    	if(CityReport.get(0).equals("NoReport"))
    		System.out.println("no report for this city");
    	else {	
        NumOfMaps.setText(CityReport.get(0));
       NumOfPurchases.setText(CityReport.get(1));
       NumOfSubscriptions.setText(CityReport.get(2));
        NumOfRenewals.setText(CityReport.get(3));
        NumOfViews.setText(CityReport.get(4));
        NumOfDownloads.setText(CityReport.get(5));
    }}
    @FXML
    void citycmb(ActionEvent event) {

    }
    @FXML
    void initialize() {
    	 assert Citycmb != null : "fx:id=\"Citycmb\" was not injected: check your FXML file 'ReportGui.fxml'.";
         try {
			Main.client.sendToMyCLient("GetCitiesForComboBox");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//incase the job is to get city names for combobox
         try {
 			Thread.currentThread().sleep(3000);
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         ObservableList<String> list;
         list = FXCollections.observableArrayList(Main.client.RecieveCityNames());
 		Citycmb.setItems(list);//update the ComboxBox with the list of cities 
        assert ShowReportBtn != null : "fx:id=\"ShowReportBtn\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert ExitBtn != null : "fx:id=\"ExitBtn\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfMaps != null : "fx:id=\"NumOfMaps\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfPurchases != null : "fx:id=\"NumOfPurchases\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfSubscriptions != null : "fx:id=\"NumOfSubscriptions\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfViews != null : "fx:id=\"NumOfViews\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfRenewals != null : "fx:id=\"NumOfRenewals\" was not injected: check your FXML file 'ReportGui.fxml'.";
        assert NumOfDownloads != null : "fx:id=\"NumOfDownloads\" was not injected: check your FXML file 'ReportGui.fxml'.";
       
      
    }
}
