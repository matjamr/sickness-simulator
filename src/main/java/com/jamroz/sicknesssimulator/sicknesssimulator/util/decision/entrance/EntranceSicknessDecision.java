package com.jamroz.sicknesssimulator.sicknesssimulator.util.decision.entrance;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.decision.IDecision;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.enums.EntranceSicknessEnum;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.decision.DecisionMaker;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntranceSicknessDecision implements DecisionMaker<EntranceSicknessEnum> {
    private final RandomGenerator randomGenerator;

    @Override
    public EntranceSicknessEnum decide(IDecision constraints) {
        // 50% chances
        var result = (int) randomGenerator.generate(2);

        return EntranceSicknessEnum.values()[result];
    }
}
