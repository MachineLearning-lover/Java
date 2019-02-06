package streams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


@RunWith(JUnit4.class)
public class StreamTests {

    @Test
    public void stream_sources(){
        Supplier<Person> supplier = () -> new Person("a"+new Random().nextInt(5),new Random().nextInt(100));
//        Stream.generate(supplier)
//                .limit(10)
//                .forEach(System.out::println);

        Map<String,Integer> map = Stream.generate(supplier)
                .limit(100)
                .collect(Collectors.toMap(Person::getName, Person::getAge, (c, d)->c+d));
        System.out.println(map);

        Map<String, List<Integer>> map2 = Stream.generate(supplier)
                .limit(100)
                .collect(groupingBy(Person::getName, mapping(Person::getAge, toList())));
        System.out.println(map2);

    }
}
