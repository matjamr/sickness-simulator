package com.jamroz.sicknesssimulator.sicknesssimulator.state;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import lombok.Data;

import java.util.List;


@Data
public class SimulationMemento {
    private List<Person> people;

    public SimulationMemento(List<Person> people) {
        this.people = people.stream()
                .map(Person::copy)
                .toList();
    }
}
