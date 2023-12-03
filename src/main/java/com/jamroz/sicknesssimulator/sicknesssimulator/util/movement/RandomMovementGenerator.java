package com.jamroz.sicknesssimulator.sicknesssimulator.util.movement;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.IVector;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Vector2D;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomMovementGenerator implements MovementGenerator {
    private final RandomGenerator randomGenerator;

    @Override
    public IVector generateMovement() {
        var x = randomGenerator.generate(2.5);
        var y = randomGenerator.generate(2.5);
        return new Vector2D(x, y);
    }
}
