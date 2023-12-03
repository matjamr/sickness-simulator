package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.ImmuneState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.NoSymptomsState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.SymptomsState;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class SicknessHealingController {
    private Population population;


    public void checkHealing() {
        population.getObservableList().forEach(person -> {
            if(person.getState() instanceof NoSymptomsState || person.getState() instanceof SymptomsState){
                person.setSickTime(person.getSickTime() + 0.04);
                if(person.getSickTime() >= person.getHealTime()){
                    person.setState(new ImmuneState());
                }
            }
        });
    }
}
