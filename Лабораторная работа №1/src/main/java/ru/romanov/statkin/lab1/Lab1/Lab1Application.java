package ru.romanov.statkin.lab1.Lab1;


import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Опишите функцию, принимающую на вход
 * бесконечную последовательность ассоциативных
 * массивов и выбрасывающую ключи с последовательно повторяющимися значениями
 */
public class Lab1Application {
    public static void main(String[] args) {
        List<ImmutablePair<Integer, String>> list = new ArrayList<>(Stream.of(
                new ImmutablePair<Integer, String>(1, "a"),
                new ImmutablePair<Integer, String>(2, "a"),
                new ImmutablePair<Integer, String>(3, "a"),
                new ImmutablePair<Integer, String>(4, "b"),
                new ImmutablePair<Integer, String>(5, "b"),
                new ImmutablePair<Integer, String>(6, "a"),
                new ImmutablePair<Integer, String>(7, "b"),
                new ImmutablePair<Integer, String>(8, "a")).toList());
        System.out.print(list);
        List<Integer> integers = new ArrayList<>();
        System.out.print(getKeysOfDuplicateValues(list, 0, integers));
    }

    public static List<Integer> getKeysOfDuplicateValues(List<ImmutablePair<Integer, String>> list,
                                                         int startIndex,
                                                         List<Integer> integers) {
        if (list.isEmpty()) {
            return integers;
        }
        if ((list.size() - startIndex) == 1) {
            return integers;
        }

        ImmutablePair<Integer, String> _first = list.get(startIndex);
        ImmutablePair<Integer, String> _second = list.get(startIndex + 1);
        if (_first.right.equals(_second.right)) {
            return (getKeysOfDuplicateValues(list,
                    startIndex + 1, Stream.of(integers, Arrays.asList(_first.left))
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList())));
        } else {
            return (getKeysOfDuplicateValues(list,
                    startIndex + 1, integers));
        }
    }
}