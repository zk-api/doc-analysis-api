package com.github.zk.txt;

import com.github.zk.IDeal;
import com.github.zk.IDealHandler;
import com.github.zk.handler.DefaultTXTHandler;

import java.io.IOException;
import java.nio.file.Path;
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
        return createEntity(path, titleNames, new DefaultTXTHandler());
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames) throws IOException {
        return createEntity(paths, titleNames, new DefaultTXTHandler());
    }

    @Override
    public List<Map> createEntity(Path path, String[][] titleNames, IDealHandler handler) throws IOException {
        return handler.dealFile(titleNames, path, "\\s+");
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames, IDealHandler handler) throws IOException {
        List<Map> list = new LinkedList<>();
        for (Path path : paths) {
            List<Map> mapList = handler.dealFile(titleNames, path, "\\s+");
            list.addAll(mapList);
        }
        return list;
    }

    @Override
    public List<Map> createEntity(Path path, String[][] titleNames, Class<? extends Predicate> filterRule) throws IOException {
        return createEntity(path, titleNames, filterRule, new DefaultTXTHandler());
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames, Class<? extends Predicate> filterRule) throws IOException {
        return createEntity(paths, titleNames, filterRule, new DefaultTXTHandler());
    }

    @Override
    public List<Map> createEntity(Path path , String[][] titleNames , Class<? extends Predicate> filterRule, IDealHandler handler) throws IOException {
        List<Map> list = null;
        try {
            list = handler.dealFilterFile(titleNames, filterRule, path, "\\s+");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map> createEntity(Path[] paths, String[][] titleNames, Class<? extends Predicate> filterRule, IDealHandler handler) throws IOException {
        List<Map> list = new LinkedList<>();
        try {
            for (Path path : paths) {
                if (path != null) {
                    List<Map> mapList = handler.dealFilterFile(titleNames, filterRule, path, "\\s+");
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
}