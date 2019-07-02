package ru.job4j.travel;

import java.util.List;
import java.util.stream.Collectors;

public class TourAgency {

    List<Address> collect(List<Profile> profiles) {
        return profiles
                .stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }
}