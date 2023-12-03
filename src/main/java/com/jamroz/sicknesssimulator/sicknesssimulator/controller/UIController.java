package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import com.jamroz.sicknesssimulator.sicknesssimulator.state.SimulationCareTaker;
import com.jamroz.sicknesssimulator.sicknesssimulator.state.SimulationMemento;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.generator.PositionGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.generator.StartingIndividualsGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.listener.PersonListener;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.HandicappedRandomGenerator;
import javafx.animation.AnimationTimer;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import static java.util.Objects.isNull;

public class UIController {
    @FXML
    public Pane pane;
    @FXML
    public Button resetButton;
    @FXML
    public Button startButton;
    @FXML
    public Button stopButton;
    @FXML
    public Button stepButton;
    @FXML
    public TextField timeLabel;
    @FXML
    public CheckBox immunityBox;
    @FXML
    public ChoiceBox snapshotBox;
    @FXML
    private Label welcomeText;

    private final SimulationController simulationController = new SimulationController();
    private ListChangeListener<Person> personListener;
    private SimulationTimer clock;
    private Population population;

    @FXML
    public void initialize() {
        clock = new SimulationTimer(timeLabel, simulationController, pane);
        clock.setSnapshotBox(snapshotBox);
//        pane.setStyle("-fx-background-color: #ff00ff");
        personListener = new PersonListener(pane);
        simulationController.setPositionGenerator(new PositionGenerator(pane));
    }

    public void reset() {
        simulationController.reset();
        clock.resetTime();
    }

    public void start() {
        if(isNull(population)) {
            var generator = new StartingIndividualsGenerator(new HandicappedRandomGenerator(), pane.getHeight(), pane.getWidth());

            population = new Population(generator.generate(), personListener);
            simulationController.setPopulation(population);
            simulationController.setSicknessSpreadingController(new SicknessSpreadingController(population));
            simulationController.setSicknessHealingController(new SicknessHealingController(population));
            simulationController.setSimulationCareTaker(new SimulationCareTaker(population));

        }


        clock.start();
        simulationController.setStarted(true);
    }

    public void stop() {
        clock.stop();
        simulationController.setStarted(false);
    }

    public void step(ActionEvent actionEvent) {
        simulationController.nextStep(clock.getTime() + 0.04);
    }
}