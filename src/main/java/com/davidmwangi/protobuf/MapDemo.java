package com.davidmwangi.protobuf;

import com.davidmwangi.models.BodyStyle;
import com.davidmwangi.models.Car;
import com.davidmwangi.models.Dealer;

public class MapDemo {

    public static void main(String[] args) {
        Car legacy = Car.newBuilder()
                .setYear(2023)
                .setMake("Subaru")
                .setModel("Legacy")
                .build();

        Car outback = Car.newBuilder()
                .setYear(2023)
                .setMake("Subaru")
                .setModel("Outback")
                .setBodyStyle(BodyStyle.SUV)
                .build();

        Dealer dealer = Dealer.newBuilder()
                .putModel(2023, legacy)
                .putModel(2004, outback)
                .build();

        System.out.println(dealer.getModelMap());
        System.out.println("*******************");
        System.out.println(dealer.getModelOrDefault(2004, null));

    }

}
