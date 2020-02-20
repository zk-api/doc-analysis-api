package com.github.zk.filter;

import java.util.function.Predicate;

/**
 * @author zk
 * @date 2019/5/29 13:48
 */
public abstract class FilterRule2 implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return filter(s);
    }

    public abstract boolean filter(String s);
}
