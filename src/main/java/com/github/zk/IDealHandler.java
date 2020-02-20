package com.github.zk;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 解析处理器
 *
 * @author zk
 * @date 2020/2/20 22:43
 */
public interface IDealHandler {
    /**
     * 文件处理
     * @param titleNames
     * @param path
     * @param pattern
     * @return
     * @throws IOException
     */
    List<Map> dealFile(String[][] titleNames, Path path, String pattern) throws IOException;

    /**
     * 文件处理，支持过滤行
     * @param titleNames
     * @param filterRule
     * @param path
     * @param pattern
     * @return
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    List<Map> dealFilterFile(String[][] titleNames, Class<? extends Predicate> filterRule, Path path, String pattern) throws IOException, InstantiationException, IllegalAccessException;
}
