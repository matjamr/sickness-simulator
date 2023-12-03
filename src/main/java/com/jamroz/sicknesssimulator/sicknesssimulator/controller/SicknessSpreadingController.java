package com.jamroz.sicknesssimulator.sicknesssimulator.controller;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.Population;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.pojo.SicknessSpreadingHelper;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.HealtyState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.NoSymptomsState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.SymptomsState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class SicknessSpreadingController {
    private final Population population;
    private final Map<UUID, SicknessSpreadingHelper> sicknessMap = new HashMap<>();
    private static final Predicate<Person> isSickPersonPredicate = person -> person.getState() instanceof SymptomsState || person.getState() instanceof NoSymptomsState;

    public void check() {
        List<Person> sickPeople = getSickPeople();
        List<Person> newSickPeople = getNewSickPeople(sickPeople);

        addNewSickPeople(newSickPeople);
        checkTheRest();
    }

    private void checkTheRest() {
        sicknessMap.forEach((key, value) -> {
            Person person = value.getSickPerson();
            List<SicknessSpreadingHelper.PersonTimePojo> closeHealthyPeople = findCloseHealthyPeople(person);
            List<SicknessSpreadingHelper.PersonTimePojo> newest = getNewestAffectedPeople(closeHealthyPeople, value.getAffectedPeople());

            checkTimes(newest);

            value.setAffectedPeople(newest);
            sicknessMap.put(key, value);
        });
    }

    private void checkTimes(List<SicknessSpreadingHelper.PersonTimePojo> newest) {
        newest.stream()
                .filter(pojo -> pojo.getAffectedTime() >= 3.0)
                .forEach(this::processAffectedByTime);
    }

    private void processAffectedByTime(SicknessSpreadingHelper.PersonTimePojo pojo) {
        if(pojo.getPerson().getState() instanceof NoSymptomsState) {
            if(Math.random() <= 0.5) {
                if(Math.random() <= 0.5) {
                    System.out.println("NO SYMPTOMS");
                    pojo.getPerson().setState(new NoSymptomsState());
                }
                else{
                    System.out.println("SYMPTOMS");
                    pojo.getPerson().setState(new SymptomsState());
                }
            }
        }
        else{
            if(Math.random() <= 0.5) {
                System.out.println("SYMPTOMS");
                pojo.getPerson().setState(new NoSymptomsState());
            }
            else {
                System.out.println("NO SYMPTOMS");
                pojo.getPerson().setState(new SymptomsState());
            }
        }
    }

    private List<SicknessSpreadingHelper.PersonTimePojo> getNewestAffectedPeople(List<SicknessSpreadingHelper.PersonTimePojo> closeHealthyPeople,
                                                                               List<SicknessSpreadingHelper.PersonTimePojo> affectedPeople) {
        return closeHealthyPeople.stream()
                .filter(Objects::nonNull)
                .map(personTimePojo -> Optional.ofNullable(personExists(personTimePojo, affectedPeople))
                            .map(pojo -> new SicknessSpreadingHelper.PersonTimePojo(personTimePojo.getPerson(), pojo.getAffectedTime() + 0.04))
                            .orElse(new SicknessSpreadingHelper.PersonTimePojo(personTimePojo.getPerson(), 0.0)))
                .toList();
    }

    private SicknessSpreadingHelper.PersonTimePojo personExists(SicknessSpreadingHelper.PersonTimePojo personTimePojo, List<SicknessSpreadingHelper.PersonTimePojo> affectedPeople) {
        return affectedPeople.stream()
                .filter(pojo -> pojo.getPerson().getUuid().equals(personTimePojo.getPerson().getUuid()))
                .findFirst()
                .orElse(null);
    }

    private void addNewSickPeople(List<Person> newSickPeople) {
        newSickPeople.forEach(person -> {
            List<SicknessSpreadingHelper.PersonTimePojo> closeHealthyPeople = findCloseHealthyPeople(person);
            sicknessMap.put(person.getUuid(), new SicknessSpreadingHelper(person, closeHealthyPeople));
        });
    }

    private List<SicknessSpreadingHelper.PersonTimePojo> findCloseHealthyPeople(Person person) {
        return population.getObservableList().stream()
                .filter(p -> p.getStroke().equals(Color.BLACK))
                .filter(p -> p.getState() instanceof HealtyState)
                .filter(person::meets)
                .map(p -> new SicknessSpreadingHelper.PersonTimePojo(p, 0.0))
                .toList();
    }

    private List<Person> getNewSickPeople(List<Person> sickPeople) {
        var tmpKeySet = sicknessMap.keySet();
        return sickPeople.stream()
                .filter(person -> !tmpKeySet.contains(person.getUuid()))
                .toList();
    }

    private List<Person> getSickPeople() {
        return population.getObservableList().stream()
                .filter(person -> person.getStroke().equals(Color.BLACK))
                .filter(isSickPersonPredicate)
                .toList();
    }
}
