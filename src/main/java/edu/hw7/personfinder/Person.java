package edu.hw7.personfinder;

import org.jetbrains.annotations.NotNull;

public record Person(
    @NotNull Integer id,
    @NotNull String name,
    @NotNull String address,
    @NotNull String phoneNumber) {
}
