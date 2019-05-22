package ru.job4j.converter;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConverterTest {

    private Converter converter;

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void when60RubleToDollarThen1() {
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    @Test
    public void when70RubleToEuroThen1() {
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    @Test
    public void when1DollarToRubleThen60() {
        int result = converter.dollarToRuble(1);
        assertThat(result, is(60));
    }

    @Test
    public void when1EuroToRubleThen70() {
        int result = converter.euroToRuble(1);
        assertThat(result, is(70));
    }
}