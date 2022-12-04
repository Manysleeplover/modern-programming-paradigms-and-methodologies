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
    /**
     * Инициализируем списки, вызываем целевой метод
     */
    public static void main(String[] args) {
        //Стрим, генерирующий список пар<Число, Строка> - где ключ последовательность от 1 до бесконечности, а значение либо "а" либо "б"
        //Ограничен 10 элементами
        List<ImmutablePair<Integer, String>> list = Stream.iterate(0, i -> i + 1).sequential().limit(10)
                .map(i -> new ImmutablePair<>(i + 1, Math.random() >= 0.5 ? "a" : "b")).toList();

        List<Integer> integers = new ArrayList<>();

        System.out.println("Список повторяющихся ключей до выполнения метода" + integers);
        System.out.println("Исходный второй ассоциативный массив: " + list);
        System.out.println("Результат выполнения метода" + getKeysOfDuplicateValues(list, 0, integers));
    }

    /**
     * Метод, возвращающий ключ первого вхождения последовательно повторяющегося элемента.
     * То есть если мы имеем (1,a),(2,a),(3,a)(4,b), то он выведет (1,2)
     * @param list - бесконечный ассоциативный массив
     * @param startIndex - индекс, на котором закончилась предыдущая итерация
     * @param integers - список с ключами повторяющихся значений
     */
    public static List<Integer> getKeysOfDuplicateValues(List<ImmutablePair<Integer, String>> list,
                                                         int startIndex,
                                                         List<Integer> integers) {
        //Если список пустой, то ничего не делает
        if (list.isEmpty()) {
            return integers;
        }
        //Если остался один элемент, то тоже ничего не делаем
        if ((list.size() - startIndex) == 1) {
            return integers;
        }

        //Получаем иммутабельные пары для сравнения
        ImmutablePair<Integer, String> _first = list.get(startIndex);
        ImmutablePair<Integer, String> _second = list.get(startIndex + 1);

        //Собсна сравниваем
        //Если пары равны, передаем в следующую итерацию список с ключом
        if (_first.right.equals(_second.right)) {
            return (getKeysOfDuplicateValues(list,
                    startIndex + 1, Stream.of(integers, Arrays.asList(_first.left))
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList())));
        // На нет и суда нет.
        } else {
            return (getKeysOfDuplicateValues(list,
                    startIndex + 1, integers));
        }
    }
}