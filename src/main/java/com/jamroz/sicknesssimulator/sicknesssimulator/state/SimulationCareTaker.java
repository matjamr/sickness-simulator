package com.jamroz.sicknesssimulator.sicknesssimulator.state;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SimulationCareTaker {
    private final Map<Integer, SimulationMemento> simulationMementoMap;
    private final Population population;

    public SimulationCareTaker(Population population){
        simulationMementoMap = new HashMap<>();
        this.population = population;
    }

    public void save(Integer second){
        System.out.println(simulationMementoMap.size());
        simulationMementoMap.put(second, new SimulationMemento(population.getObservableList()));
    }

//    public boolean load(Integer second){
//        if(simulationMementoMap.containsKey(second)){
//            SimulationMemento snapshot = simulationMementoMap.get(second);
//            population.setObservable(snapshot.getPopulation().getObservableList());
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
}
