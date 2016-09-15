/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.projects.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author chernykh_vv
 */
public class LogMinerBean {

    private List<LogEntryBean> extractedBlocksList;
    private final Pattern timePrefixPattern;
    
    public LogMinerBean() {
        this.timePrefixPattern = Pattern.compile("^(\\d{2}:\\d{2}:\\d{2},\\d{3})(.+)");
    }

    public List<LogEntryBean> logMinerBlocksExtractor(InputStream fileObjectStream,
            String searchString, int countResultLines) throws IOException {

        String logEntryLine;
        String block_head = "";
        String block_data = "";
        int blockNum = 0;
        extractedBlocksList = new ArrayList<>();

        Matcher timePrefixMatcher;

        BufferedReader br = new BufferedReader(new InputStreamReader(fileObjectStream));
        while ((logEntryLine = br.readLine()) != null) {
            timePrefixMatcher = timePrefixPattern.matcher(logEntryLine);
            if (timePrefixMatcher.matches()) {
                if ((block_head + block_data).contains(searchString)
                        //) { //add error block stick here!
                        || block_head.contains(" ERROR ")) {

                    try {
                        extractedBlocksList.add(new LogEntryBean(block_head, block_data));
                        // restrict output to 100 log items
                        countResultLines = countResultLines + 1;
                        if (countResultLines == 100) {
                            return extractedBlocksList;
                        }
                    } catch (OutOfMemoryError e) {
                        System.out.println("get outOfMemory inside excavator");
                        System.err.println("My Error: " + e);
                        block_head = null;
                        block_data = null;
                        blockNum = 0;
                        fileObjectStream.close();
                        br.close();
                        extractedBlocksList = null;
                        extractedBlocksList = new ArrayList<>();
                        extractedBlocksList.add(new LogEntryBean("get outOfMemory inside excavator ERROR Spoon", ""));
                        return extractedBlocksList;
                    }
                }

                // force unique log timestamp values, comparator kostyl' (time + block counter)
                block_head = timePrefixMatcher.group(1) + ".(" + blockNum + ")" + timePrefixMatcher.group(2);

                blockNum = blockNum + 1;
                block_data = "";
            } else {
                block_data = block_data + "\n" + logEntryLine;
            }
        }

        // catch last block
        if (block_data != "" && (block_head + block_data).contains(searchString)) {
            extractedBlocksList.add(new LogEntryBean(block_head, block_data));
        }

        // nullin objects
        block_head = null;
        block_data = null;
        blockNum = 0;
        fileObjectStream.close();
        br.close();

        return extractedBlocksList;
    }

    /**
     */
    public void setExtractedBlocksList() {
        this.extractedBlocksList = null;
    }
}
