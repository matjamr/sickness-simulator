package com.jamroz.sicknesssimulator.sicknesssimulator.model.vector;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Direction;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Position;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.movement.Updateable;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.state.*;
import com.jamroz.sicknesssimulator.sicknesssimulator.model.util.Movable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.*;

import static com.jamroz.sicknesssimulator.sicknesssimulator.util.Consts.PERSON_RADIUS;


@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain=true)
public class Person extends Circle implements Movable, Updateable {
    private UUID uuid;
    private Position position;
    private Direction direction;
    private IState state;
    private double sickTime = 0.0;
    private double healTime;

    public Person(Position position, IState iState, double healTime) {
        this.position = position;
        this.healTime = healTime;
        direction = new Direction();
        uuid = UUID.randomUUID();
        state = iState;

        setCenterX(position.getX());
        setCenterY(position.getY());
        setRadius(PERSON_RADIUS);
        setFill(state.getColor());
        setStroke(Color.BLACK);
    }

    @Override
    public boolean move(double radius) {
        return position.move(direction, PERSON_RADIUS);
    }

    @Override
    public void update() {
        setRadius(PERSON_RADIUS);
        setTranslateX(position.getX());
        setTranslateY(position.getY());
        setFill(state.getColor());
        setStroke(Color.BLACK);
    }

    public void changeDirection() {
        if(getStroke().equals(Color.BLACK)) {
            direction = new Direction();
        }
    }

    public void hide() {
        setRadius(0);
        setVisible(false);
        setFill(Color.TRANSPARENT);
        setStroke(Color.TRANSPARENT);
    }

    public boolean meets(Person other) {
        return position.meet(other.getPosition());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!Objects.equals(position, person.position)) return false;
        return Objects.equals(direction, person.direction);
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    public Person copy() {
        return new Person(position, state, healTime)
                .setUuid(uuid)
                .setDirection(direction)
                .setSickTime(sickTime);
    }
}
