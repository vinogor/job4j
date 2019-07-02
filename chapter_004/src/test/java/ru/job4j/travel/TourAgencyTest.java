package ru.job4j.travel;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.Student;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TourAgencyTest {

    private TourAgency tourAgency;
    private List<Profile> profiles;

    @Before
    public void setUp() {
        tourAgency = new TourAgency();
        profiles = List.of(
                new Profile(new Address("Moscow", "Kutuzovskiy", 32, 1)),
                new Profile(new Address("Saint-Petersburg", "Leninskiy", 13, 134))
        );
    }

    @Test
    public void collectTest1() {
        List<Address> result = tourAgency.collect(profiles);
        assertThat(result, is(List.of(
                new Address("Moscow", "Kutuzovskiy", 32, 1),
                new Address("Saint-Petersburg", "Leninskiy", 13, 134)
        )));
    }
}