package com.jamroz.sicknesssimulator.sicknesssimulator.util.generator;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Position;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class PositionGenerator implements Generator<Position> {
    private final Pane pane;

    @Override
    public Position generate() {
        var ret = switch (new Random().nextInt(5 - 1) + 1) {
            case 1 -> new Position((pane.getWidth()-480), (pane.getHeight()- 400) * Math.random() + 10);
            case 2 -> new Position((pane.getWidth()-480) * Math.random(), pane.getHeight() - 400);
            case 3 -> new Position(10, (pane.getHeight()- 400) * Math.random() + 10);
            case 4 -> new Position((pane.getWidth()-480) * Math.random(), 10);
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(1, 5));
        };

        return ret;
    }
}
