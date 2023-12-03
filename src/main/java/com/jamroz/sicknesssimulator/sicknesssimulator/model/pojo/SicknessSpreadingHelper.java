package com.jamroz.sicknesssimulator.sicknesssimulator.model.pojo;

import com.jamroz.sicknesssimulator.sicknesssimulator.model.vector.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class SicknessSpreadingHelper {
    private Person sickPerson;
    private List<PersonTimePojo> affectedPeople;

    @Data
    @AllArgsConstructor
    public static class PersonTimePojo {
        private Person person;
        private double affectedTime;
    }
}
