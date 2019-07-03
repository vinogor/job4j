package ru.job4j.travel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TourAgency {

    // отфильтровать повторы
    // отсортировать по полю city

    List<Address> collect(List<Profile> profiles) {
        return profiles
                .stream()
                .map(Profile::getAddress)
                .distinct()
                .sorted(Comparator.comparing(Address::getCity))
                .collect(Collectors.toList());
    }
}