/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chernykh_vv
 */
public class LogEntryTimeComparatorTest {

//    private static Set<LogEntryBean> testEntryList;
    private static List<LogEntryBean> testEntryList;

    public LogEntryTimeComparatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
//        testEntryList = new TreeSet<>(new LogEntryTimeComparator());
        testEntryList = new ArrayList<>();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of compare method, of class LogEntryTimeComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        LogEntryBean logEntry1 = new LogEntryBean("00:00:01", "");
        LogEntryBean logEntry2 = new LogEntryBean("00:00:01", "");
        LogEntryBean logEntry3 = new LogEntryBean("00:00:02", "");

        testEntryList.add(logEntry3);
        testEntryList.add(logEntry2);
        testEntryList.add(logEntry1);

        Collections.sort(testEntryList, new LogEntryTimeComparator());

        String result = "";

        Iterator<LogEntryBean> iterator = testEntryList.iterator();
        while (iterator.hasNext()) {
            result = result + iterator.next().getLogEntryHead();
        }

        String expResult = "00:00:01"+"00:00:01"+"00:00:02";
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
