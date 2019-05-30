package com.github.zk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author zk
 * @date 2019/5/24 16:38
 */
public class DealTXT implements IDeal {
    @Override
    public List<Map> createEntity(Path path, String[][] titleNames) throws IOException {
        return dealFile(titleNames, path);
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames) throws IOException {
        List<Map> list = new LinkedList<>();
        for (Path path : paths) {
            List<Map> mapList = dealFile(titleNames,  path);
            list.addAll(mapList);
        }
        return list;
    }

    private List<Map> dealFile(String[][] titleNames, Path path) throws IOException {
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

            String[] split = line.split("\\s+");
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
    public List<Map> createEntity(Path path , String[][] titleNames , Class<? extends Predicate> filterRule) throws IOException {
        List<Map> list = null;
        try {
            list = dealFilterFile(titleNames, filterRule, path);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames, Class<? extends Predicate> filterRule) throws IOException {
        List<Map> list = new LinkedList<>();
        try {
            for (Path path : paths) {
                if (path != null) {
                    List<Map> mapList = dealFilterFile(titleNames, filterRule, path);
                    list.addAll(mapList);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Map> dealFilterFile(String[][] titleNames, Class<? extends Predicate> filterRule, Path path) throws IOException, InstantiationException, IllegalAccessException {
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

            String[] split = ((String) line).split("\\s+");
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