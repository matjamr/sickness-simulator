package com.jamroz.sicknesssimulator.sicknesssimulator.model.state;

import javafx.scene.paint.Color;

public class HealtyState implements IState{

    @Override
    public Color getColor(){
        return Color.CYAN;
    }
}
