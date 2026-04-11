package org.example.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr",
                                          "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

                               /////////////// Basic Stream Operations///////////////
        // evenNumbers

        List<Integer> evenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .toList();
        System.out.println(evenNumbers);
        //sortedDesc

        List<Integer> sortedDesc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(sortedDesc);

        List<String> result = names.stream()
                .filter(name -> name != null && name.startsWith("A"))
                //uppercase
                .map(name -> name.toUpperCase())
                .toList();
        //distinct:
        List<Integer> uniqueNumbers = numbers.stream()
                        .distinct()
                        .toList();
        System.out.println(uniqueNumbers);
        System.out.println(result);

                           /////////////////// Intermediate Stream Tasks/////////////////

        //Count strings longer than 5:
        long count = names.stream()
                .filter(name -> name != null && name.length()>5)
                .count();
        System.out.println(count);

        //Find the first element that matches a condition
        Optional<Integer> first = numbers.stream()
                .filter(num -> num > 5)
                .findFirst();
        System.out.println(first);
        //find the number divisible by 5
        boolean divisible = numbers.stream()
                .anyMatch(num -> num % 5 ==0);

        System.out.println(divisible);

        //Collect elements into a Set instead of a List.
        Set<Integer> set = numbers.stream()
                .collect(Collectors.toSet());

        System.out.println(set);

        //skip frist 3 numbers:
        List<Integer> skip = numbers.stream()
                .skip(3)
                .toList();
        System.out.println(skip);
                           /////////////// Numeric Streams & Reductions/////////////////
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        //min&max
        int max = numbers.stream()
                .max(Integer::compare)
                .get();

        int min = numbers.stream()
                .min(Integer::compare)
                .get();

        System.out.println(max);
        System.out.println(min);

        System.out.println(sum);
        //avrege
        double avg = numbers.stream()
                .mapToInt(num -> num)
                .average()
                .getAsDouble();

        System.out.println(avg);
        //multiply using reduce
        int multiply = numbers.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println(multiply);
        //Count positive numbers
        long positive = numbers.stream()
                .filter(num -> num > 0)
                .count();

        System.out.println(positive);
                    ///////// ////////// Collectors & Grouping///////////////////

        // Group students by department
        Map<String, List<Student>> grouped = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));

        System.out.println(grouped);
        //Partition even / odd
        Map<Boolean, List<Integer>> partition = numbers.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));

        System.out.println(partition);
        //Join strings
        String joined = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.joining(", "));

        System.out.println(joined);
        //Group employees by age + count
        Map<Integer, Long> countByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));

        System.out.println(countByAge);
        //Average salary per department
        Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        System.out.println(avgSalary);

                     ///////////////////// Advanced Operations/////////////////
        //FlatMap + Optional
        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );
        List<String> flat = nestedWords.stream()
                .flatMap(list -> list.stream())
                .toList();

        System.out.println(flat);
        //Unique characters
        List<String> chars = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .flatMap(name -> Arrays.stream(name.split("")))
                .distinct()
                .toList();

        System.out.println(chars);
        //Optional filtering
        List<Optional<String>> optionals = List.of(
                Optional.of("Ali"),
                Optional.empty(),
                Optional.of("Mona")
        );

        List<String> present = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        System.out.println(present);
        //String → length
        List<Integer> lengths = names.stream()
                .filter(name -> name != null)
                .map(name -> name.length())
                .toList();

        System.out.println(lengths);
        //Uppercase names starting with A
        List<String> upperA = names.stream()
                .filter(name -> name != null && name.startsWith("A"))
                .map(String::toUpperCase)
                .toList();

        System.out.println(upperA);
        //Find duplicates
        Set<Integer> duplicates = numbers.stream()
                .filter(num -> Collections.frequency(numbers, num) > 1)
                .collect(Collectors.toSet());

        System.out.println(duplicates);
        //Second highest number
        int second = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .get();

        System.out.println(second);
        //Sort employees by salary then name
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .toList();

        System.out.println(sorted);
        //Remove null & empty strings
        List<String> clean = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .toList();

        System.out.println(clean);
        //Partition students pass/fail
        Map<Boolean, List<Student>> passFail = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGrade() >= 60));

        System.out.println(passFail);


    }
}
