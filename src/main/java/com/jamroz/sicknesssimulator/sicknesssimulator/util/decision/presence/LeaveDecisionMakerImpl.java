package com.jamroz.sicknesssimulator.sicknesssimulator.util.decision.presence;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.decision.IDecision;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.enums.DecisionChoice;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.decision.DecisionMaker;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LeaveDecisionMakerImpl implements DecisionMaker<DecisionChoice> {
    private final RandomGenerator randomGenerator;

    @Override
    public DecisionChoice decide(IDecision constraints) {
        // 50% chances
        var result = (int) randomGenerator.generate(2);

        return DecisionChoice.values()[result];
    }
}
