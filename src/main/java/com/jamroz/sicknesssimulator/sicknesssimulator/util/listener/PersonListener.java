package com.jamroz.sicknesssimulator.sicknesssimulator.util.listener;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonListener implements ListChangeListener<Person> {
    private final Pane pane;

    @Override
    public void onChanged(Change<? extends Person> change) {
        while(change.next()) {
            if(change.wasAdded()) {
//                System.out.println("Adding " + change);
                pane.getChildren().addAll(change.getAddedSubList());
            }
            if(change.wasRemoved()){
//                System.out.println("Removing " + change.getRemoved());
                change.getRemoved().forEach(this::onRemoved);
            }
        }
    }

    // TODO fix
    private void onRemoved(Person person) {
        person.hide();
//        pane.getChildren().stream().filter(p -> p.equals(person)).map(Person.class::cast).forEach(person1 -> person1.);
    }


}
