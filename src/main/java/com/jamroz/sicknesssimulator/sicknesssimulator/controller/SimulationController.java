package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Position;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.HealtyState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.NoSymptomsState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.SymptomsState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import com.jamroz.sicknesssimulator.sicknesssimulator.state.SimulationCareTaker;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.generator.Generator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.HandicappedRandomGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static com.jamroz.sicknesssimulator.sicknesssimulator.util.Consts.PERSON_RADIUS;

@Data
public class SimulationController {
    private Population population;
    private boolean isStarted = false;
    private RandomGenerator randomGenerator = new HandicappedRandomGenerator();
    private Generator<Position> positionGenerator;
    private SicknessSpreadingController sicknessSpreadingController;
    private SicknessHealingController sicknessHealingController;
    private SimulationCareTaker simulationCareTaker;

    public void nextStep(double time) {
        move();
        checkMeet();
        draw();
        changeDirection();
        addPerson(time);
        recoverHealth();
    }

    private void move() {
        List<Person> peopleToBeRemoved = new ArrayList<>();
        population.getObservableList().forEach(person -> {
            if(!person.move(PERSON_RADIUS)) {
                peopleToBeRemoved.add(person);
            }
        });

        if(!peopleToBeRemoved.isEmpty())
            population.getObservableList().removeAll(peopleToBeRemoved);
    }

    private void checkMeet() {
        sicknessSpreadingController.check();
    }

    private void draw() {
        population.getObservableList().forEach(Person::update);
    }

    public void changeDirection(){
        population.getObservableList().forEach(Person::changeDirection);
    }

    private void addPerson(double time) {
        if((time - (int) time) > 0.95 ) {
            if(Math.random() <= 0.1) {
                if(Math.random() <= 0.5) {
                    population.getObservableList().add(new Person(positionGenerator.generate(), new SymptomsState(), Math.random() * (30 - 20) + 20));
                }
                else{
                    population.getObservableList().add(new Person(positionGenerator.generate(), new NoSymptomsState(), Math.random() * (30 - 20) + 20));
                }
            }
            else{
                population.getObservableList().add(new Person(positionGenerator.generate(), new HealtyState(), Math.random() * (30 - 20) + 20));
            }
        }
    }

    private void recoverHealth() {
        sicknessHealingController.checkHealing();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void reset() {
        if(Objects.nonNull(population))
            population.getObservableList().clear();
    }

    public void saveMemento(int number) {
        simulationCareTaker.save(number);
    }

    public void load(Integer value) {
        simulationCareTaker.load(value);
    }
}
