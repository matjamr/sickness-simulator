package com.jamroz.sicknesssimulator.sicknesssimulator.model;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.listener.PersonListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Population {
    private final List<Person> peopleList;
    private ObservableList<Person> observableList;
    private ListChangeListener<Person> listener;

    public Population(List<Person> people, ListChangeListener<Person> listener) {
        this.peopleList = people;
        this.listener = listener;

        observableList = FXCollections.observableArrayList();
        observableList.addListener(listener);
        observableList.addAll(people);
    }
}
