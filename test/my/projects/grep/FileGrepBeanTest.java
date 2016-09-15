/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chernykh_vv
 */
public class FileGrepBeanTest {

    public FileGrepBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getApplyFilterToLogEntriesList method, of class FileGrepBean.
     */
    @Test
    public void testGetApplyFilterToLogEntriesList() {

        System.out.println("getApplyFilterToLogEntriesList");

        FileGrepBean instance = new FileGrepBean();

        // define log items
        LogEntryBean leb0 = new LogEntryBean("00:00:00,000 any string 0", "entry 0 data");
        LogEntryBean leb1 = new LogEntryBean("00:00:01,000 Получено сообщение",
                "Получено сообщение 1 data");
        LogEntryBean leb2 = new LogEntryBean("00:00:02,000 any string 2", "entry 2 data");
        LogEntryBean leb3 = new LogEntryBean("00:00:03,000 Получатель сохранил сообщение",
                "Получатель сохранил сообщение 3 data");
        LogEntryBean leb4 = new LogEntryBean("00:00:04,000 any string 4", "entry 4 data");
        LogEntryBean leb5 = new LogEntryBean("00:00:05,000 Источник успешно удалил переданное сообщение",
                "Источник успешно удалил переданное сообщение 5 data");
        LogEntryBean leb6 = new LogEntryBean("00:00:06,000 any string 6", "entry 6 data");
        LogEntryBean leb7 = new LogEntryBean("00:00:07,000 параметры вызова",
                "параметры вызова 7 data");
        LogEntryBean leb8 = new LogEntryBean("00:00:08,000 any string 8", "entry 8 data");
        LogEntryBean leb9 = new LogEntryBean("00:00:09,000 Очередь ошибок сохранила сообщение",
                "Очередь ошибок сохранила сообщение 9 data");
        LogEntryBean leb10 = new LogEntryBean("00:00:10,000 any string 10", "entry 10 data");

//        System.out.println(instance.getCheckBoxFilterItems()[0].getItemValue());
        // emulate greped log
        List<LogEntryBean> testList = new ArrayList<>();
        
        testList.addAll(Arrays.asList(leb0, leb1, leb2, leb3, leb4, leb5
            , leb6, leb7, leb8, leb9, leb10));

        instance.setLogArrayList(testList);

//        System.out.println(errorInstance.logArrayList);
        // motor!
        List<LogEntryBean> filteredLogEntries = instance.getApplyFilterToLogEntriesList();

//        System.out.println(filteredLogEntries.get(0).getLogEntryHead());
        String result = "";

        for (LogEntryBean logItem : filteredLogEntries) {
            result = result + logItem.getLogEntryHead() + "\n" + logItem.getLogEntryData() + "\n";
        }

//        System.out.println("!!!res!!!" + result + "!!!");
        String expResult = "00:00:01,000 Получено сообщение" + "\n"
                + "Получено сообщение 1 data" + "\n"
                + "00:00:03,000 Получатель сохранил сообщение" + "\n"
                + "Получатель сохранил сообщение 3 data" + "\n"
                + "00:00:05,000 Источник успешно удалил переданное сообщение" + "\n"
                + "Источник успешно удалил переданное сообщение 5 data" + "\n"
                + "00:00:07,000 параметры вызова" + "\n"
                + "параметры вызова 7 data" + "\n"
                + "00:00:09,000 Очередь ошибок сохранила сообщение" + "\n"
                + "Очередь ошибок сохранила сообщение 9 data" + "\n";

//        System.out.println("!!!expRes!!!" + expResult + "!!!");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getApplyFilterToLogEntriesList method, of class FileGrepBean.
     */
    @Test
    public void testGetErrorBlocksFromLogEntriesList() {

        System.out.println("getErrorBlocksFromLogEntriesList");

        FileGrepBean errorInstance = new FileGrepBean();

        // define log items
        LogEntryBean leb0 = new LogEntryBean("00:00:00,000 any string 0", "entry 0 data");
        LogEntryBean leb1 = new LogEntryBean("00:00:01,000 ERROR Spoon",
                "error 1 data");
        LogEntryBean leb2 = new LogEntryBean("00:00:02,000 any string 2", "entry 2 data");

        // emulate greped log
        List<LogEntryBean> testList = new ArrayList<>();

        testList.add(leb0);
        testList.add(leb1);
        testList.add(leb2);

        errorInstance.setLogArrayList(testList);

        // motor!
        List<LogEntryBean> errorLogEntries = errorInstance.getApplyFilterToLogEntriesList();
//        System.out.println(errorLogEntries.get(0).getLogEntryHead());

        String result = "";

        for (LogEntryBean logItem : errorLogEntries) {
            result = result + logItem.getLogEntryHead() + "\n" + logItem.getLogEntryData() + "\n";
        }
//        System.out.println("!!!res!!!" + result + "!!!");

        String expResult = "00:00:01,000 ERROR Spoon" + "\n"
                + "error 1 data" + "\n";

//        System.out.println("!!!expRes!!!" + expResult + "!!!");
        assertEquals(expResult, result);
    }

}
