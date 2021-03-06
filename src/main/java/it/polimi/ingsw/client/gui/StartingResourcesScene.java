package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.model.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * scene that allows the player to select the starting resources
 */
public class StartingResourcesScene {

    @FXML
    public Label errorMessage;
    @FXML
    private Label amount;
    @FXML
    private Button servants;
    @FXML
    private Button shields;
    @FXML
    private Button stones;
    @FXML
    private Button coins;
    @FXML
    private Button depot1;
    @FXML
    private Button depot2;
    @FXML
    private Button depot3;

    private static View clientView;
    private static GUI gui;

    private Map<Integer, ArrayList<Resource>> newWarehouse;
    private Resource currentRes;
    private int numOfRes;

    public static void setGui(GUI gui) {
        StartingResourcesScene.gui = gui;
        StartingResourcesScene.clientView = gui.getClientView();
    }

    public void setError(String error){
        this.errorMessage.setText(error);
    }

    public void setAmount(int amount){
        newWarehouse = new HashMap<>();
        for(int i=0; i<3; i++){
            newWarehouse.put(i, new ArrayList<>());
        }
        resButtonUpdate(false);
        depotButtonUpdate(false);
        numOfRes=amount;
        if(amount==0) send();
        depotButtonUpdate(true);
        this.amount.setText("You must choose " + numOfRes + " resource");
    }

    public void addShields() {
        currentRes = Resource.SHIELDS;
        resButtonUpdate(true);
        depotButtonUpdate(false);
    }

    public void addServants() {
        currentRes = Resource.SERVANTS;
        resButtonUpdate(true);
        depotButtonUpdate(false);
    }

    public void addStones() {
        currentRes = Resource.STONES;
        resButtonUpdate(true);
        depotButtonUpdate(false);
    }

    public void addCoins() {
        currentRes = Resource.COINS;
        resButtonUpdate(true);
        depotButtonUpdate(false);
    }

    public void addResToDepot1(){
        numOfRes--;
        newWarehouse.get(0).add(currentRes);
        resButtonUpdate(false);
        depotButtonUpdate(true);
        send();
    }

    public void addResToDepot2(){
        numOfRes--;
        newWarehouse.get(1).add(currentRes);
        resButtonUpdate(false);
        depotButtonUpdate(true);
        send();
    }

    public void addResToDepot3(){
        numOfRes--;
        newWarehouse.get(2).add(currentRes);
        resButtonUpdate(false);
        depotButtonUpdate(true);
        send();
    }

    public void send(){
        if(numOfRes==0){
            resButtonUpdate(true);
            depotButtonUpdate(true);
            clientView.sendStartingResources(newWarehouse);
        }
    }

    private void resButtonUpdate(boolean change){
        servants.setDisable(change);
        shields.setDisable(change);
        stones.setDisable(change);
        coins.setDisable(change);
    }

    private void depotButtonUpdate(boolean change){
        depot1.setDisable(change);
        depot2.setDisable(change);
        depot3.setDisable(change);
    }
}
