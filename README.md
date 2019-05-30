# doc-analysis-api
文档解析API
# 适用格式
以空格分隔的文档数据，如
```
1 zk 30
2 zx 35
3 wmh 30
```
## 使用方式
### 1.实例化处理类
IDeal deal = new DealTXT();
### 2.调用createEntity接口
/**
 * path 文件路径
 * titleNames 要解析的列索引和列名称，例：new String[][]{{"0":"id"},{"1","name"}}
 * clazz 过滤行条件类
 */
``` 
deal.createEntity(Path path , String[][] titleNames , Class<? extends FilterRule> clazz);
```
### 3.过滤行条件类实现FilterRule接口
## 代码示例
```
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
```
```
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
```
