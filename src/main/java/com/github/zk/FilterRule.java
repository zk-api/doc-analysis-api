package com.github.zk;

import java.util.function.Predicate;

/**
 * @author zk
 * @date 2019/5/27 17:31
 */
public interface FilterRule extends Predicate<String> {

    /**
     * 过滤条件
     *
     * @param line 每行数据
     * @return
     */
    @Override
    default boolean test(String line) {
        return filter(line);
    }

    boolean filter(String line);
}
