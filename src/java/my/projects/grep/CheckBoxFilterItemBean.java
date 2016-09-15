/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

/**
 *
 * @author chernykh_vv
 */
public class CheckBoxFilterItemBean {

    private String itemLabel;
    private String itemValue;

    public CheckBoxFilterItemBean(String itemLabel, String itemValue) {
        this.itemLabel = itemLabel;
        this.itemValue = itemValue;
    }

    /**
     * @return the itemLabel
     */
    public String getItemLabel() {
        return itemLabel;
    }

    /**
     * @return the itemValue
     */
    public String getItemValue() {
        return itemValue;
    }


}
