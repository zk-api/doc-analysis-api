package com.github.zk;

import com.github.zk.filter.FilterRule;
import com.github.zk.txt.DealTXT;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        Path[] paths = new Path[2];
        paths[0] = Paths.get("H:\\test.txt");
        paths[1] = Paths.get("H:\\test - 副本.txt");

        IDeal deal = new DealTXT();

        long stime = System.currentTimeMillis();
        List<Map> entity = deal.createEntity(paths[0], new String[][]{{"0","序号"},{"1","name"},{"2","age"},{"3","33"}
        ,{"4","44"},{"5","55"},{"6","66"},{"7","77"},{"8","88"},{"9","99"},{"10","100"}},FilterTest.class);
        long etime = System.currentTimeMillis();
        System.out.println(etime - stime);
    }
}

class FilterTest implements FilterRule {

    @Override
    public boolean filter(String line) {
        boolean flag = false;
        if ("1".equals(line.split("\\s+")[0])) {
            flag = true;
        }
        return flag;
    }
}