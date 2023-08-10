package com.davidmwangi.protobuf;

import com.davidmwangi.models.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {
    public static void main(String[] args) throws IOException {
//        Person david = Person.newBuilder()
//                .setName("David")
//                .setAge(20)
//                .build();
        Path path = Paths.get("david.ser");
//        Files.write(path, david.toByteArray());

       byte [] bytes =  Files.readAllBytes(path);
       Person newSam =  Person.parseFrom(bytes);
        System.out.println(newSam);

    }
}
