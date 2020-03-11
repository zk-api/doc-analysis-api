package com.github.zk.handler;

import com.github.zk.IDealHandler;
import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * FastCsv处理器
 *
 * @author zk
 * @date 2020/3/11 12:00
 */
public class FastCsvHandler implements IDealHandler {
    @Override
    public List<Map> dealFile(String[][] titleNames, Path path, String pattern) throws IOException {
        List<Map> list = new ArrayList<>();
        CsvReader csvReader = new CsvReader();
        CsvContainer csv = csvReader.read(path, StandardCharsets.UTF_8);
        for (CsvRow row : csv.getRows()) {
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 0; i < titleNames.length; i++) {
                rowMap.put(titleNames[i][1], row.getField(Integer.valueOf(titleNames[i][0])));
            }
            list.add(rowMap);
        }
        return list;
    }

    @Override
    public List<Map> dealFilterFile(String[][] titleNames, Class<? extends Predicate> filterRule, Path path, String pattern) throws IOException, InstantiationException, IllegalAccessException {
        return null;
    }
}
