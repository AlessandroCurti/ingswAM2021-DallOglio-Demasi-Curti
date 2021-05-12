package it.polimi.ingsw.model.actions.normalAction.marketAction;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.actions.Actions;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.playerboard.PlayerBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageResources extends Actions {

    private final ArrayList<ArrayList<Resource>> resources;
    private final Map<Resource,Integer> newResources;
    private final Map<Resource,Integer> discResources;
    private final boolean initial;

    public ManageResources(Map<Integer,ArrayList<Resource>> resources, Map<Resource, Integer> res, Map<Resource, Integer> discResources, boolean initial) {
        this.resources = new ArrayList<ArrayList<Resource>>(){{
           for (int i=0;i<resources.size();i++){
               add(i,resources.get(i+1));
           }
        }};
        this.newResources = res;
        this.discResources = discResources;
        this.initial = initial;
    }

    /**
     * set the new warehouse configuration
     * @param playerBoard of the current player
     */
    @Override
    public void doAction(PlayerBoard playerBoard) throws InvalidActionException {
        if(!validAction(playerBoard)) throw new InvalidActionException();
        else{
            manageResources(playerBoard);
        }
    }

    /**
     * check the validity of the action
     * @param playerBoard of the current player
     * @return true if the action is valid
     */
    @Override
    public boolean validAction(PlayerBoard playerBoard) {
        if (!initial) {
            HashMap<Resource, Integer> totalRes = new HashMap<>();
            for (Resource resource : Resource.values()) {
                totalRes.put(resource, playerBoard.getWarehouse().storedResources().get(resource) + newResources.get(resource));
            }

            //if the array.size is wrong
            if (playerBoard.getWarehouse().getDepots().size() < resources.size()) return false;
            for (int i = 0; i < resources.size(); i++) {

                //no cheat
                if ((resources.get(i).size() + discResources.get(resources.get(i).get(0))) != totalRes.get(resources.get(i).get(0)))
                    return false;

                //if the depots have the same resources
                if (i != 0) {
                    if (resources.get(0).get(0) == resources.get(i).get(0)) return false;
                }

                //if the capacity is wrong
                if (playerBoard.getWarehouse().getDepots().get(i).getCapacity() < resources.get(i).size()) return false;

                //if the resources of the depot are wrong
                for (int j = 0; j < resources.size(); j++) {
                    if (resources.get(i).get(0) != resources.get(i).get(j)) return false;
                }

            }

        }
        return true;
    }


    /**
     * clears the depots and fills them with the new organization
     * @param playerBoard of the current player
     */
    public void manageResources(PlayerBoard playerBoard) {
        //empty depots
        if (!initial){
            for (int i=0;i<playerBoard.getWarehouse().getDepots().size();i++) {
                playerBoard.getWarehouse().getDepots().get(i).removeResource(playerBoard.getWarehouse().getDepots().get(i).getOccupied());
            }
        }
        //add resources
        for (int i=0;i< resources.size();i++){
            for (int j=0;j<resources.get(i).size();j++) {
                playerBoard.getWarehouse().getDepots().get(i).addResources(resources.get(i).get(j));
            }
        }
    }
}
