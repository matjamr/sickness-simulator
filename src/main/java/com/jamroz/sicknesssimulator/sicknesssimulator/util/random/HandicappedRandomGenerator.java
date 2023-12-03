package com.jamroz.sicknesssimulator.sicknesssimulator.util.random;

import java.util.Random;

public class HandicappedRandomGenerator implements RandomGenerator {

    @Override
    public double generate(double number) {
        Random random = new Random();
        return random.nextDouble(number);
    }
}
