package stream;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pierreluo on 2017/9/8.
 */
@Getter
@Setter
public class Property {
    String  name;
    // 距离，单位:米
    Integer distance;
    // 销量，月售
    Integer sales;
    // 价格，这里简单起见就写一个级别代表价格段
    Integer priceLevel;
    public Property(String name, int distance, int sales, int priceLevel) {
        this.name = name;
        this.distance = distance;
        this.sales = sales;
        this.priceLevel = priceLevel;
    }
}