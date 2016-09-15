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
public class LogEntryBean {

    private String logEntryHead;
    private String logEntryData;

    public LogEntryBean(String logEntryHead, String logEntryData) {
//        Pattern logEntryPattern = Pattern.compile("^(\\d{2}:\\d{2}:\\d{2},\\d{3})(.+)(\\dx\\d{2})\\s+-\\s+(.+)");
//        Matcher logEntryMatcher;
//        logEntryMatcher = logEntryPattern.matcher(logEntryLine);
//        System.out.println(logEntryMatcher.group(1)+"|"+logEntryMatcher.group(2)+"|"+logEntryMatcher.group(3)+"|"+logEntryMatcher.group(4));
        this.logEntryHead = logEntryHead;
        this.logEntryData = logEntryData;
    }

    /**
     * @return the logEntryHead
     */
    public String getLogEntryHead() {
        return logEntryHead;
    }

    /**
     * @param logEntryHead the logEntryHead to set
     */
    public void setLogEntryHead(String logEntryHead) {
        this.logEntryHead = logEntryHead;
    }

    /**
     * @return the logEntryData
     */
    public String getLogEntryData() {
        return logEntryData;
    }

    /**
     * @param logEntryData the logEntryData to set
     */
    public void setLogEntryData(String logEntryData) {
        this.logEntryData = logEntryData;
    }

}
