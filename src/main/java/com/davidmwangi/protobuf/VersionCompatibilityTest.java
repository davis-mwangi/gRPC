package com.davidmwangi.protobuf;

import com.davidmwangi.models.Television;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VersionCompatibilityTest {

    public static void main(String[] args) {
        Television teleV1 = Television.newBuilder()
                .setYear(2023)
                .setBrand("Sony")
                .build();

        Path pathV1 = Paths.get("tv.v1");
        try {
            Files.write(pathV1,teleV1.toByteArray());
            byte[]bytes = Files.readAllBytes(pathV1);
            System.out.println(Television.parseFrom(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}
