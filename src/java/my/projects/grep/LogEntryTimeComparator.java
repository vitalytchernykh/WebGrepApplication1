/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.util.Comparator;

/**
 *
 * @author chernykh_vv
 */
public class LogEntryTimeComparator implements Comparator<LogEntryBean> {

    @Override
    public int compare(LogEntryBean logEntry1, LogEntryBean logEntry2) {
        return logEntry1.getLogEntryHead().compareTo(logEntry2.getLogEntryHead());
    }
}
