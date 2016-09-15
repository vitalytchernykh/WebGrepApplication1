/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author chernykh_vv
 */

@ManagedBean
@ViewScoped

public class PaginatorView {
     
    private List<LogEntryBean> logArrayList;
     
    /**
     * @return the logArrayList
     */
    public List<LogEntryBean> getLogArrayList() {
        return logArrayList;
    }

    /**
     * @param logArrayList the logArrayList to set
     */
    public void setLogArrayList(List<LogEntryBean> logArrayList) {
        this.logArrayList = logArrayList;
    }
 
}
