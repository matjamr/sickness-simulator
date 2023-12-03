//package com.jamroz.sicknesssimulator.sicknesssimulator.state;
//
//import com.kluczynskidamian.virusspread.model.Object.model.Object.Person;
//import com.kluczynskidamian.virusspread.model.Object.model.State.HealtyState;
//import com.kluczynskidamian.virusspread.model.Object.model.State.ImmuneState;
//import com.kluczynskidamian.virusspread.model.Object.model.State.NoSymptomsState;
//import com.kluczynskidamian.virusspread.model.Object.model.State.SymptomsState;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//
//import java.util.ArrayList;
//
//public class Simulation {
//    private ArrayList<Person> personArrayList;
//    private final SimulationCareTaker simulationCareTaker;
//    private final Pane pane;
//
//    public Simulation(Pane pane, int population, boolean immune){
//        this.pane = pane;
//
//        personArrayList = new ArrayList<>();
//        if(immune){
//            for(int person = 0; person < population; person++){
//                if(Math.random() < 0.4){
//                    personArrayList.add(new Person(new ImmuneState(), pane, true));
//                }
//                else{
//                    personArrayList.add(new Person(new HealtyState(), pane, true));
//                }
//            }
//        }
//        else{
//            for(int person = 0; person < population; person++){
//                personArrayList.add(new Person(new HealtyState(), pane, true));
//            }
//        }
//        draw();
//        simulationCareTaker = new SimulationCareTaker(this);
//    }
//
//    public void move(){
//        for(Person person: personArrayList){
//            if(!person.move()){
//                person.setState(new ImmuneState());
//                person.undraw();
//            }
//        }
//    }
//
//    public void recoverHealth(){
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                person.recoverHealth();
//            }
//        }
//    }
//
//    public void changeDirection(){
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                person.changeDirection();
//            }
//        }
//    }
//
//    public ArrayList<Person> getPersonArrayList(){
//        return personArrayList;
//    }
//    public void setPersonArrayList(ArrayList<Person> personArrayList){
//        this.personArrayList = personArrayList;
//    }
//    public void addPerson(Pane pane){
//        if(Math.random() <= 0.1){
//            if(Math.random() <= 0.5){
//                personArrayList.add(new Person(new SymptomsState(), pane, false));
//            }
//            else{
//                personArrayList.add(new Person(new NoSymptomsState(), pane, false));
//            }
//        }
//        else{
//            personArrayList.add(new Person(new HealtyState(), pane, false));
//        }
//    }
//    public void draw(){
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                person.draw();
//            }
//        }
//    }
//
//    public SimulationMemento snapshot(){
//        return new SimulationMemento();
//    }
//
//    public void checkMeet(){
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                for(Person other: personArrayList){
//                    if(person != other){
//                        if(other.getCircle().getStroke().equals(Color.BLACK)){
//                            person.checkSurrounding(other);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void step(Pane pane){
//        move();
//        checkMeet();
//        draw();
//        changeDirection();
//        addPerson(pane);
//        recoverHealth();
//    }
//
//    public void saveSimulationMemento(Integer second){
//        simulationCareTaker.save(second);
//    }
//
//    public void saveMemento(Integer second){
//        saveSimulationMemento(second);
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                person.saveMemento(second);
//            }
//        }
//    }
//
//    public void loadMemento(Integer second){
//        simulationCareTaker.load(second);
//        for(Person person: personArrayList){
//            if(person.getCircle().getStroke().equals(Color.BLACK)){
//                person.load(second);
//                pane.getChildren().add(person.getCircle());
//            }
//        }
//    }
//
//
//}
