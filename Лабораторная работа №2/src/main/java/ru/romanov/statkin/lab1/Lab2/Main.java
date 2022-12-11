package ru.romanov.statkin.lab1.Lab2;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Напишите функцию, которая принимает на вход канал состоящий из последовательности чисел,
 * первое из которых является количеством последующих элементов, которые нужно поместить в массив,
 * а за ней следуют элементы этого массива, и возвращающая отдельные массивы.
 * Например 3, 4, 0, 2, 1, 2, 2, 4, 5 будет превращено в [4, 0, 2], [2], [4, 5]
 */
public class Main {

    public static void main(String[] args) {
        System.out.print(getArrays(List.of(3, 4, 0, 2, 1, 2, 2, 4, 5)));
    }

    public static ConcurrentLinkedQueue<List<Integer>> getArrays(List<Integer> integerList) {
        return processQueue(new ConcurrentLinkedQueue<>(integerList), new ConcurrentLinkedQueue<>());
    }

    private static ConcurrentLinkedQueue<List<Integer>> processQueue(ConcurrentLinkedQueue<Integer> integers, ConcurrentLinkedQueue<List<Integer>> outerQueue) {
        if (integers.isEmpty()) {
            return outerQueue;
        }
        int countOfDigits = integers.poll();
        outerQueue.offer(integers.parallelStream().limit(countOfDigits).collect(Collectors.toList()));
        integers.parallelStream().limit(countOfDigits).forEachOrdered(value -> integers.poll());
        return processQueue(integers, outerQueue);
    }

}