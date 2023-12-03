package com.jamroz.sicknesssimulator.sicknesssimulator.model.decision;

import javafx.util.Pair;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StandardDoubleConstraints implements IDecision {
    private final double left;
    private final double right;

    @Override
    public Pair<Double, Double> getLeftRightConstraints() {
        return new Pair<>(left, right);
    }
}
