package com.github.zk;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author zk
 * @date 2019/5/24 15:29
 */
public interface IDeal {
    /**
     * 按照过滤条件将文档组织成List<Map>
     * @param path 文件路径
     * @param titleNames 列索引和列名称
     *                   new String[][]{{"列1索引","列1名称"},{"列2索引","列2名称"}}
     * @param filterRule 行过滤条件
     * @return
     * @throws IOException
     */
    List<Map> createEntity(Path path, String[][] titleNames, Class<? extends Predicate> filterRule) throws IOException;

    List<Map> createEntity(Path[] paths, String[][] titleNames, Class<? extends Predicate> filterRule) throws IOException;

    /**
     * 将文档组织成List<Map>
     * @param path 文件路径
     * @param titleNames 列索引和列名称
     *                   new String[][]{{"列1索引","列1名称"},{"列2索引","列2名称"}}
     * @return
     * @throws IOException
     */
    List<Map> createEntity(Path path, String[][] titleNames) throws IOException;

    List<Map> createEntity(Path[] paths, String[][] titleNames) throws IOException;
}
