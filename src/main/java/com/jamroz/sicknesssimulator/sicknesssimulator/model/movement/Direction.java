package com.jamroz.sicknesssimulator.sicknesssimulator.model.movement;


import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Vector2D;

public class Direction {
    private double speed;
    private Vector2D direction;

    public Direction(double x, double y, double speed){
        direction = new Vector2D(x, y);
        this.speed = speed;
    }

    public Direction(){
        double randomDirection = Math.random() * Math.PI * 2;
        direction = new Vector2D(Math.sin(randomDirection), Math.cos(randomDirection));
        speed =  Math.random() * 2.5;
    }

    public double getX(){
        return direction.getX();
    }
    public double getY(){
        return direction.getY();
    }
    public double getSpeed(){
        return speed;
    }

    public void bounceX(){
        direction = new Vector2D(-direction.getX(), direction.getY());
    }

    public void bounceY(){
        direction = new Vector2D(direction.getX(), -direction.getY());
    }

}
