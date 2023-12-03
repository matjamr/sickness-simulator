package com.jamroz.sicknesssimulator.sicknesssimulator.model.state;

import javafx.scene.paint.Color;

public class ImmuneState implements IState{

    @Override
    public Color getColor(){
        return Color.BLUE;
    }
}
