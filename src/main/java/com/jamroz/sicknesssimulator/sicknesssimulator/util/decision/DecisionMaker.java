package com.jamroz.sicknesssimulator.sicknesssimulator.util.decision;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.decision.IDecision;

public interface DecisionMaker<T> {
    T decide(IDecision constraints);
}
