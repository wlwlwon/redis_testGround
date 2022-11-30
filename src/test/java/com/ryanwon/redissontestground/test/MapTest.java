package com.ryanwon.redissontestground.test;

import com.ryanwon.redissontestground.test.dto.Student;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMapReactive;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;

public class MapTest extends BaseTest{

    @Test
    public void mapTest1(){
        RMapReactive<String, String> map = this.client.getMap("user:1", StringCodec.INSTANCE);
        Mono<String> name = map.put("name", "sam");
        Mono<String> age = map.put("age", "10");
        Mono<String> city = map.put("ciry", "seoul");

        StepVerifier.create(name.concatWith(age).concatWith(city).then())
                .verifyComplete();
    }

    @Test
    public void mapTest2(){
        RMapReactive<String, String> map = this.client.getMap("user:2", StringCodec.INSTANCE);
        Map<String, String> javaMap = Map.of(
                "name", "jake",
                "age", "30",
                "city", "busan"
        );

        StepVerifier.create(map.putAll(javaMap).then())
                .verifyComplete();
    }

    @Test
    public void mapTest3(){
        //Map<Integer, Student>
        TypedJsonJacksonCodec typedJsonJacksonCodec = new TypedJsonJacksonCodec(Integer.class, Student.class);
        RMapReactive<Integer, Student> map = this.client.getMap("users", typedJsonJacksonCodec);

        Student student1 = new Student("sam", 10, "seoul", List.of(1, 2, 3));
        Student student2 = new Student("jake", 20, "busan", List.of(10, 20, 30));

        Mono<Student> mono1 = map.put(1, student1);
        Mono<Student> mono2 = map.put(2, student2);

        StepVerifier.create(mono1.concatWith(mono2).then())
                .verifyComplete();

    }
}
