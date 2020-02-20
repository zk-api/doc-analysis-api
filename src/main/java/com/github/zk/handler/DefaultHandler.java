package com.github.zk.handler;

import com.github.zk.IDealHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 默认处理器
 *
 * @author zk
 * @date 2020/2/20 22:50
 */
public class DefaultHandler implements IDealHandler {
    @Override
    public List<Map> dealFile(String[][] titleNames, Path path, String pattern) throws IOException {
        List<Map> list = new LinkedList<>();
        Files.lines(path).forEach(line -> {
            HashMap map = new HashMap(titleNames.length);
            /**
             * 要解析的列索引
             */
            int[] indexs = new int[titleNames.length];
            int titleLen = titleNames.length;
            for (int i = 0; i < titleLen; i++) {
                indexs[i] = Integer.valueOf(titleNames[i][0]);
            }

            String[] split = line.split(pattern);
            /**
             * 将要解析的列存入map
             */
            int indexsLen = indexs.length;
            for (int i = 0; i < indexsLen; i++) {
                map.put(titleNames[i][1],split[indexs[i]]);
            }

            list.add(map);
        });
        return list;
    }

    @Override
    public List<Map> dealFilterFile(String[][] titleNames, Class<? extends Predicate> filterRule, Path path, String pattern) throws IOException, InstantiationException, IllegalAccessException {
        List<Map> list = new LinkedList<>();
        Files.lines(path).filter(filterRule.newInstance()).forEach(line -> {
            HashMap map = new HashMap(titleNames.length);
            /**
             * 要解析的列索引
             */
            int[] indexs = new int[titleNames.length];
            int titleLen = titleNames.length;
            for (int i = 0; i < titleLen; i++) {
                indexs[i] = Integer.valueOf(titleNames[i][0]);
            }

            String[] split = ((String) line).split(pattern);
            /**
             * 将要解析的列存入map
             */
            int indexsLen = indexs.length;
            for (int i = 0; i < indexsLen; i++) {
                map.put(titleNames[i][1],split[indexs[i]]);
            }

            list.add(map);
        });
        return list;
    }
}
