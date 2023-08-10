package com.davidmwangi.protobuf;

import com.davidmwangi.models.Address;
import com.davidmwangi.models.Car;
import com.davidmwangi.models.Person;

import java.util.ArrayList;
import java.util.List;

public class CompositeProto {
    public static void main(String[] args) {

        Address address = Address.newBuilder()
                .setCity("Nairobi")
                .setStreet("Luthuli")
                .setPostbox(12)
                .build();

        Car legacy = Car.newBuilder()
                .setYear(2023)
                .setMake("Subaru")
                .setModel("Legacy")
                .build();

        Car outback = Car.newBuilder()
                .setYear(2023)
                .setMake("Subaru")
                .setModel("Outback")
                .build();

        List<Car> carList = new ArrayList<>();
        carList.add(outback);
        carList.add(legacy);

        Person david =  Person.newBuilder()
                .setName("David")
                .setAge(10)
                .setAddress(address)
                .addAllCar(carList)
                .build();

        System.out.println(david);
    }
}
