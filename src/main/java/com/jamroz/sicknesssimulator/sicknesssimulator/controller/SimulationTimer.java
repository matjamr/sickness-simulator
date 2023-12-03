package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static com.jamroz.sicknesssimulator.sicknesssimulator.util.Consts.SIMULATION_STEP;

@Data
@RequiredArgsConstructor
class SimulationTimer extends AnimationTimer {
    private final TextField timeLabel;
    private final SimulationController simulationController;
    public ChoiceBox<Integer> snapshotBox;
    private final long interval = 1000000000 / SIMULATION_STEP;
    private long last = 0;
    private Integer ticks = 0;
    private double time = 0.0;
    private final Pane pane;

    @Override
    public void handle(long now) {
        if(hasTimeElapsed(now)){
            step(time);
            last = now;
            time += 1.0 / SIMULATION_STEP;
            ticks ++;
            timeLabel.setText(String.format("%.2f", time));

            if(ticks % 25 == 0){
                simulationController.saveMemento(ticks/25);
                snapshotBox.getItems().add(ticks/25);
                snapshotBox.setValue(ticks/25);
            }
        }
    }

    private boolean hasTimeElapsed(long now) {
        return now - last > interval;
    }

    public void resetTime(){
        time = 0.0;
        timeLabel.setText(String.format("%.2f", time));
        ticks = 0;
    }

    @FXML
    public void step(double time) {
        simulationController.nextStep(time);
    }
}