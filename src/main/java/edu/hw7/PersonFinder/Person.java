package edu.hw7.PersonFinder;

import org.jetbrains.annotations.NotNull;

public record Person(
    int id,
    @NotNull String name,
    @NotNull String address,
    @NotNull String phoneNumber) {
}
