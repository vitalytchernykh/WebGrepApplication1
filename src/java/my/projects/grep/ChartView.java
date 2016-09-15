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
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class ChartView {

    private PieChartModel livePieModel;
    private long free;
    private long total;
    private long max;

    public ChartView () {
        this.livePieModel = new PieChartModel();
        this.livePieModel.setTitle("JVM heap monitor");
        this.livePieModel.setLegendPosition("ne");
        this.livePieModel.setFill(false);
        this.livePieModel.setShowDataLabels(true);
    }
    
    public PieChartModel getLivePieModel() {

        try {
            free = getFreeMemory() / (1024 * 1024);
            total = getTotalMemory() / (1024 * 1024);
            max = getMaxMemory() / (1024 * 1024);
        } catch (Exception e) {
            free = 0;
            total = 0;
            max = 0;
        }
        
        livePieModel.getData().put("Free: " + Long.toString(free) + " Mb", free);
        livePieModel.getData().put("Used: " + Long.toString(total - free) + " Mb", (total - free));
        livePieModel.getData().put("Max: " + Long.toString(max) + " Mb", (max - total));

        return livePieModel;
    }

    private static long getTotalMemory() {
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service service = new my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service();
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl port = service.getHeapBeatsWebServiceImplPort();
        return port.getTotalMemory();
    }

    private static long getFreeMemory() {
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service service = new my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service();
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl port = service.getHeapBeatsWebServiceImplPort();
        return port.getFreeMemory();
    }

    private static long getMaxMemory() {
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service service = new my.projects.ws.heapbeats.HeapBeatsWebServiceImpl_Service();
        my.projects.ws.heapbeats.HeapBeatsWebServiceImpl port = service.getHeapBeatsWebServiceImplPort();
        return port.getMaxMemory();
    }

}
