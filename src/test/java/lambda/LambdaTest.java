package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * Created by Pierreluo on 2017/9/8.
 */
public class LambdaTest {
    private static void demo0() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("do something.");
            }
        };
        Runnable r = ()-> System.out.println("do something else.");
        ToIntBiFunction<Integer, Integer> t = (x, y) -> x + y;
        int a = t.applyAsInt(5, 9);
        System.out.println(a);
    }

    // 断言型接口、判断型接口
    public static boolean doPredicate(int age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

    // 消费型接口
    public static void donation(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }

    // 供给型接口
    public static List<Integer> supply(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for(int x=0; x < num; x++) {
            list.add(supplier.get());
        }
        return list;
    }

    // 函数型接口
    public static Integer convert(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    // 断言型接口加强
    public static List<String> filter(List<String> list, Predicate<String> predicate) {
        List<String> f = new ArrayList<>();
        for (String s : list) {
            if(predicate.test(s)){
                f.add(s);
            }
        }
        return f;
    }

    private static void demo1() {
        // 断言
        System.out.println(doPredicate(20, (x) -> x >= 18));

        // 消费
        donation(30, x -> System.out.println("I got money "+x));

        // 供给
        int x = 2;
        List<Integer> list = supply(20, () -> x);
        System.out.println(Arrays.toString(list.toArray()));

        // 函数
        System.out.println(convert("30", s -> Integer.parseInt(s) - 2));

        // 断言加强
        List<String> filters = Arrays.asList("a", "asd", "qwe", "sdfsdf", "dfhkjdfkgj");
        List<String> results = filter(filters, str -> str.length() > 3);
        System.out.println(Arrays.toString(results.toArray()));

    }

    static void demo2() {
        Stream<Long> numbers = Stream.generate(new EvenNumber());
        numbers.limit(5).forEach(l -> System.out.println(l));
    }

    public static void main(String[] args) {
        demo2();
    }
}

class EvenNumber implements Supplier<Long> {

    long num = 0;
    @Override
    public Long get() {
        num += 2;
        return num;
    }
}
