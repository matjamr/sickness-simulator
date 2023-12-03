package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import com.jamroz.sicknesssimulator.sicknesssimulator.state.SimulationCareTaker;
import com.jamroz.sicknesssimulator.sicknesssimulator.state.SimulationMemento;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.generator.PositionGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.generator.StartingIndividualsGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.listener.PersonListener;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.HandicappedRandomGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;
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
    public TextField timeLabel;
    @FXML
    public CheckBox immunityBox;
    @FXML
    public ChoiceBox<Integer> snapshotBox;
    @FXML
    public Button mementoButton;

    private final SimulationController simulationController = new SimulationController();
    private ListChangeListener<Person> personListener;
    private SimulationTimer clock;
    private Population population;
    private boolean restart = false;

    @FXML
    public void initialize() {
        clock = new SimulationTimer(timeLabel, simulationController, pane);
        clock.setSnapshotBox(snapshotBox);
        personListener = new PersonListener(pane);
        simulationController.setPositionGenerator(new PositionGenerator(pane));
    }

    public void reset() {
        simulationController.reset();
        clock.resetTime();
        snapshotBox.getItems().clear();
        restart = true;
    }

    public void start() {
        if(isNull(population)) {
            generatePopulation();
            simulationController.setPopulation(population);
            simulationController.setSicknessSpreadingController(new SicknessSpreadingController(population));
            simulationController.setSicknessHealingController(new SicknessHealingController(population));
            simulationController.setSimulationCareTaker(new SimulationCareTaker(population));
        }

        if(restart) {
            updatePopulation();
            restart = false;
        }

        clock.start();
        simulationController.setStarted(true);

        stopButton.setDisable(false);
        resetButton.setDisable(true);
        immunityBox.setDisable(true);
        mementoButton.setDisable(true);
        snapshotBox.setDisable(true);
    }

    private void updatePopulation() {
        var generator = new StartingIndividualsGenerator(new HandicappedRandomGenerator(), pane.getHeight(), pane.getWidth(), immunityBox.isSelected());
        population.getObservableList().addAll(generator.generate());
    }

    private void generatePopulation() {
        var generator = new StartingIndividualsGenerator(new HandicappedRandomGenerator(), pane.getHeight(), pane.getWidth(), immunityBox.isSelected());
        population = new Population(generator.generate(), personListener);
    }

    public void stop() {
        clock.stop();
        simulationController.setStarted(false);

        stopButton.setDisable(true);
        resetButton.setDisable(false);
        immunityBox.setDisable(false);
        mementoButton.setDisable(false);
        snapshotBox.setDisable(false);
    }

    public void load() {
        simulationController.load(snapshotBox.getValue());
        clock.setTime(snapshotBox.getValue());
        clock.setTicks(snapshotBox.getValue() * 25);
        if (snapshotBox.getItems().size() > snapshotBox.getValue()) {
            snapshotBox.getItems().subList(snapshotBox.getValue(), snapshotBox.getItems().size()).clear();
        }

        start();
    }
}