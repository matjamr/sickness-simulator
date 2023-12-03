package com.jamroz.sicknesssimulator.sicknesssimulator.model.movement;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Vector2D;
import lombok.Data;

import static com.jamroz.sicknesssimulator.sicknesssimulator.util.Consts.*;

@Data
public class Position {
    private Vector2D position;

    public Position(double x, double y){
        position = new Vector2D(x, y);
    }

    public boolean move(Direction direction, double radius){
        position = new Vector2D(getX() + direction.getX(), getY() + direction.getY());
        if(getX() < LEFT_BOUND || getX() + radius > X_BOUND) {
            if(Math.random() <= 0.5) {
                direction.bounceX();
                position = new Vector2D(getX() + direction.getX(), getY());
            }
            else{
                return false;
            }
        }

        if(getY()  < LEFT_BOUND || getY() + radius > Y_BOUND) {
            if(Math.random() <= 0.5) {
                direction.bounceY();
                position = new Vector2D(getX(), getY() + direction.getY());
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean meet(Position other) {
        return distance(other) <= 2 * PERSON_RADIUS + 4;
    }

    public double distance(Position other) {
        double x = getX();
        double y = getY();
        double otherX = other.getX();
        double otherY = other.getY();

        double distanceX = x - otherX;
        double distanceY = y - otherY;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public double getX() {
        return position.getComponents()[0];
    }

    public double getY() {
        return position.getComponents()[1];
    }

}
