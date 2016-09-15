/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chernykh_vv
 */
public class CheckBoxFilterItemBeanTest {
    
    public CheckBoxFilterItemBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testRealFilterItemsList() {
    
        System.out.println("RealFilterItemsList");

        FileGrepBean instance = new FileGrepBean();

        String result = "[";
        String expResult = instance.getCheckBoxFilterInOneString();
        CheckBoxFilterItemBean[] filterObj = instance.getCheckBoxFilterItems();

        for (int i =0; i < filterObj.length-1; i++) {
            result = result + filterObj[i].getItemValue() + ", ";
        }
        result = result + filterObj[filterObj.length-1].getItemValue() + "]";
        
//        System.out.println("out: " + expResult);
//        System.out.println("res: " + result);

        assertEquals(expResult, result);
    }
    
}
