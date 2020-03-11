package com.github.zk;

import com.github.zk.csv.DealCSV;
import com.github.zk.handler.FastCsvHandler;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void dealCsv() throws IOException {
        IDeal iDeal = new DealCSV();
        Path path = Paths.get("D:\\work\\Updates_NC1.csv");
        iDeal.createEntity(path, new String[][]{{"0","time"},{"1","proName"},{"2","cityName"},{"3","hosptail"},{"4","good"},{"5","dead"}}, new FastCsvHandler());
    }
}
