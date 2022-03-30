package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenSpareKeysInMap() {
        Map<String, String> argsContainer = new HashMap<>();
        Generator gen = new TopicGenerator();
        argsContainer.put("city", "London");
        argsContainer.put("animal", "cow");
        argsContainer.put("country", "Great Britain");
        String template = "${city} is capital of ${country}";
        gen.produce(template, argsContainer);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenSpareKeysInTemplate() {
        Map<String, String> argsContainer = new HashMap<>();
        Generator gen = new TopicGenerator();
        argsContainer.put("city", "London");
        argsContainer.put("country", "Great Britain");
        String template = "${city} is capital of ${country}. It is ${weather} today.";
        gen.produce(template, argsContainer);
    }

    @Ignore
    @Test
    public void produceLine() {
        Map<String, String> argsContainer = new HashMap<>();
        Generator gen = new TopicGenerator();
        argsContainer.put("animal", "Cow");
        String template = "${animal} is animal";
        assertThat("Cow is animal", is(gen.produce(template, argsContainer)));
    }
}