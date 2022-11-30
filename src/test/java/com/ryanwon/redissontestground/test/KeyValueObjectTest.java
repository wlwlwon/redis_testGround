package com.ryanwon.redissontestground.test;

import com.ryanwon.redissontestground.test.dto.Student;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

public class KeyValueObjectTest extends BaseTest{

    /**
     * redis 객체 생성
     */

    @Test
    public void KeyValueObjectTest(){
        Student student = new Student("jiwon",26,"seoul", Arrays.asList(1,2,3));
        RBucketReactive<Student> bucket = this.client.getBucket("student:1", new TypedJsonJacksonCodec(Student.class));
        Mono<Void> set = bucket.set(student);
        Mono<Void> get = bucket.get()
                .doOnNext(System.out::println)
                .then();
        StepVerifier.create(set.concatWith(get))
                .verifyComplete();
    }
}
