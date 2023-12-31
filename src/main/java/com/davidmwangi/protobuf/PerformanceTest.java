package com.davidmwangi.protobuf;

import com.davidmwangi.models.Person;
import com.davidmwangi.protobuf.json.JPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Int32Value;

public class PerformanceTest {
    public static void main(String[] args) {

        //json
        JPerson person = new JPerson();
        person.setName("David");
        person.setAge(10);
        ObjectMapper  mapper =  new ObjectMapper();


        Runnable json = () -> {
            try{
                byte[]bytes = mapper.writeValueAsBytes(person);
//                System.out.println("JSON:"+bytes.length);
                JPerson person1 = mapper.readValue(bytes, JPerson.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        };

       //protobuf
       Person david =  Person.newBuilder()
                .setName("David")
                .setAge(Int32Value.newBuilder().setValue(10).build())
                .build();



        Runnable proto = () -> {
            try{
                byte[] bytes =  david.toByteArray();
//                System.out.println("PROTO:"+bytes.length);
                Person person1 = Person.parseFrom(bytes);
            }catch (Exception e){
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5 ; i++) {
            runPerformanceTest(json, "JSON");
            runPerformanceTest(proto, "PROTO");
        }


    }
    private static  void runPerformanceTest(Runnable runnable,String method){
       long time1 =  System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            runnable.run();
        }

        long time2 =  System.currentTimeMillis();

        System.out.println(
                method + " : " + (time2 - time1) + " ms"
        );
    }
}
