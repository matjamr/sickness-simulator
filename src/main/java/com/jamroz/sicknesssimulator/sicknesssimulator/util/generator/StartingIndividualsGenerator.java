package com.jamroz.sicknesssimulator.sicknesssimulator.util.generator;



import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Position;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.HealtyState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.IState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.ImmuneState;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.HandicappedRandomGenerator;
import com.jamroz.sicknesssimulator.sicknesssimulator.util.random.RandomGenerator;

import java.util.List;
import java.util.stream.Stream;

import static com.jamroz.sicknesssimulator.sicknesssimulator.util.Consts.INDIVIDUALS_NUMBER;


public class StartingIndividualsGenerator implements Generator<List<Person>> {
    private final RandomGenerator randomGenerator;
    private final double width;
    private final double height;
    private boolean cond;

    public StartingIndividualsGenerator(HandicappedRandomGenerator randomGenerator, double width, double height, boolean selected) {
        this.randomGenerator = randomGenerator;
        this.width = width;
        this.height = height;
        this.cond = selected;
    }

    @Override
    public List<Person> generate() {
        return Stream.generate(this::generateRandomIndividual)
                .limit(INDIVIDUALS_NUMBER)
                .toList();
    }

    private Person generateRandomIndividual() {
        IState state = (cond && Math.random() < 0.4) ? new ImmuneState() : new HealtyState();
        return new Person(new Position((width-480)*Math.random(), (height - 280)*Math.random() + 10), state, Math.random() * (30 - 20) + 20);
    }
}
