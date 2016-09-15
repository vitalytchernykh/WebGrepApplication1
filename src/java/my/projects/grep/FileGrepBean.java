/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author chernykh_vv
 */
@ManagedBean
@ViewScoped

public class FileGrepBean {

    private String searchString;
    private String fileNames;
    private List<LogEntryBean> logArrayList;
    private List<LogEntryBean> applyFilterToLogEntriesList;
    private String[] checkBoxFilterStringArray;
    private final CheckBoxFilterItemBean[] checkBoxFilterItems;
    private boolean showRestrictDialog;
    private boolean checkWrap;

    public FileGrepBean() {
        this.showRestrictDialog = false;
        this.logArrayList = new ArrayList<>();

        checkBoxFilterItems = new CheckBoxFilterItemBean[6];
        checkBoxFilterItems[0] = new CheckBoxFilterItemBean(
                "Получение", "Получено сообщение");
        checkBoxFilterItems[1] = new CheckBoxFilterItemBean(
                "Сохранение", "Получатель сохранил сообщение");
        checkBoxFilterItems[2] = new CheckBoxFilterItemBean(
                "Удаление", "Источник успешно удалил переданное сообщение");
        checkBoxFilterItems[3] = new CheckBoxFilterItemBean(
                "Вызов хп", "параметры вызова");
        checkBoxFilterItems[4] = new CheckBoxFilterItemBean(
                "Сохранение в очередь ошибок", "Очередь ошибок сохранила сообщение");
        checkBoxFilterItems[5] = new CheckBoxFilterItemBean(
                "Ошибка", " ERROR Spoon");

        checkBoxFilterStringArray = new String[]{
            "Получено сообщение",
            "Получатель сохранил сообщение",
            "Источник успешно удалил переданное сообщение",
            "параметры вызова",
            "Очередь ошибок сохранила сообщение",
            " ERROR Spoon"};
    }

    public String getCheckBoxFilterInOneString() {
        return Arrays.toString(getCheckBoxFilterStringArray());
    }

    public void grepAction() throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        logArrayList = new ArrayList<>();
        LogMinerBean grepContent = new LogMinerBean();
        int countResultLines = 0;
        setShowRestrictDialog(false);

        for (Part item : request.getParts()) {
            if (item.getSubmittedFileName() != null) {
                try {
                    logArrayList.addAll(grepContent.logMinerBlocksExtractor(item.getInputStream(),
                            searchString, countResultLines));
                    if (logArrayList.size() >= 100) {
                        setShowRestrictDialog(true);
                        return;
                    }
                    grepContent.setExtractedBlocksList();
                } catch (OutOfMemoryError e) {
                    System.out.println("get outOfMemory inside grepAction\n");
                    System.err.println("____fgb Error: " + e);
                    request = null;
                    grepContent.setExtractedBlocksList();
                    logArrayList = null;
                    logArrayList = new ArrayList<>();
                    logArrayList.add(new LogEntryBean("get outOfMemory inside grepAction ERROR Spoon", ""));
                    return;
                }
            }
        }

        //nullin
        request = null;

        Collections.sort(getLogArrayList(), new LogEntryTimeComparator());
    }

    /**
     * @return the applyFilterToLogEntriesList
     */
    public List<LogEntryBean> getApplyFilterToLogEntriesList() {
        applyFilterToLogEntriesList = new ArrayList<>();
        for (LogEntryBean logItem : getLogArrayList()) {
            for (String filterItem : checkBoxFilterStringArray) {
                if (logItem.getLogEntryHead().contains(filterItem)) {
                    applyFilterToLogEntriesList.add(logItem);
                    break;
                }
            }
        }
        return applyFilterToLogEntriesList;
    }

    /**
     * @return the searchString
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * @param searchString the searchString to set
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * @return the checkBoxFilterItems
     */
    public CheckBoxFilterItemBean[] getCheckBoxFilterItems() {
        return checkBoxFilterItems;
    }

    /**
     * @param checkBoxFilterStringArray the checkBoxFilterStringArray to set
     */
    public void setCheckBoxFilterStringArray(String[] checkBoxFilterStringArray) {
        this.checkBoxFilterStringArray = checkBoxFilterStringArray;
    }

    /**
     * @return the checkBoxFilterStringArray
     */
    public String[] getCheckBoxFilterStringArray() {
        return checkBoxFilterStringArray;
    }

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

    /**
     * @return the fileNames
     */
    public String getFileNames() {
        return fileNames;
    }

    /**
     * @param fileNames the fileNames to set
     */
    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    /**
     * @return the showRestrictDialog
     */
    public boolean isShowRestrictDialog() {
        return showRestrictDialog;
    }

    /**
     * @param showRestrictDialog the showRestrictDialog to set
     */
    public void setShowRestrictDialog(boolean showRestrictDialog) {
        this.showRestrictDialog = showRestrictDialog;
    }

    /**
     * @return the checkWrap
     */
    public boolean isCheckWrap() {
        return checkWrap;
    }

    /**
     * @param checkWrap the checkWrap to set
     */
    public void setCheckWrap(boolean checkWrap) {
        this.checkWrap = checkWrap;
    }

}
