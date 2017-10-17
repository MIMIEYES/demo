package stream;



import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pierreluo on 2017/9/8.
 */
public class StreamTest {

    static void demo0() {
        List<String> filters = Arrays.asList("a", "asd", "qwe", "sdfsdf", "dfhkjdfkgj");
        long filterCount = filters.stream().filter(s -> s.contains("a")).count();
        System.out.println(filterCount);
        filters.stream().forEach(x -> System.out.println(x));
        Object o = filters.stream().collect(Collectors.toList());
        System.out.println(o.getClass().getName());
    }

    static List<Property> properties = null;
    static {
        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
        Property p3 = new Property("永和大王", 580, 3000, 1);
        Property p4 = new Property("肯德基", 6000, 200, 4);
        properties = Arrays.asList(p1, p2, p3, p4);
    }

    static void demo1() {
        Collections.sort(properties, (x, y) -> x.distance.compareTo(y.distance));
        String name = properties.get(0).name;
        System.out.println(name);
    }

    static void demo2() {
        String name = properties.stream()
                .sorted((x, y) -> x.distance.compareTo(y.distance))
                .findFirst().get().name;
        System.out.println(name);
        String name1 = properties.stream()
                .sorted(Comparator.comparingInt(x -> x.distance))
                .findFirst().get().name;
        System.out.println(name1);
    }

    static void demo3() throws IOException {
        String content = Files.readAllLines(Paths.get("D:\\developmentTool\\pac.txt"))
                .stream().limit(5).collect(Collectors.joining("$$$\n"));
        System.out.println(content);
    }

    static void demo4() {
        properties.stream().map(x -> x.getDistance()).forEach(i -> System.out.println(i));
    }

    static List<List<String>> lists = null;
    static {
        lists = new ArrayList<>();
        lists.add(Arrays.asList("apple", "click"));
        lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
        lists.add(Arrays.asList("c#", "biezhi"));
    }
    static void demo5() {

        // 此处 Lamdba表达式 Function<T, R> == Function<Collection, Stream>
        // Collection::stream 等价于 list -> list.stream()
        long a = lists.stream().flatMap(Collection::stream)
                .filter(s -> s.length() > 2).count();
        System.out.println(a);
    }

    static void demo6() {
        Map<Integer, List<Property>> map = properties.stream()
                .collect(Collectors.groupingBy(x -> x.getDistance()));
    }

    static void demo7() {
        Converter<String, Integer> a = x -> Integer.valueOf(x);
        Converter<String, Integer> _a = Integer::valueOf;
    }

    public static void main(String[] args) throws IOException {
        demo5();
    }
}
