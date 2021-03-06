package homework122JavaCorePopulationCensus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        long count = persons.stream()
                .filter(s -> s.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> uppercaseList = persons.stream()
                .filter(s -> s.getSex() == Sex.MAN)
                .filter(s -> s.getAge() > 18)
                .filter(s -> s.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + uppercaseList);

        List<String> uppercaseList1 = persons.stream()
                .filter(s -> s.getEducation() == Education.HIGHER)
                .filter(s -> s.getAge() > 18)
                .filter(s -> (s.getAge() < 65 && s.getSex() == Sex.MAN)||(s.getAge() < 60 && s.getSex() == Sex.WOMAN))
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Список по фамилии потенциально работоспособных с высшим образованием: " + uppercaseList1);
    }
}

