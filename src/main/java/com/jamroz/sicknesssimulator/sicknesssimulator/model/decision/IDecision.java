package com.jamroz.sicknesssimulator.sicknesssimulator.model.decision;

import javafx.util.Pair;

public interface IDecision {
    Pair<Double, Double> getLeftRightConstraints();
}
