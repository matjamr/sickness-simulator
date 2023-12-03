package com.jamroz.sicknesssimulator.sicknesssimulator.model.vector;

public interface IVector {
    double abs();
    double cdot(IVector iVector);
    double[] getComponents();
}
