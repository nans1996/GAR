/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;


/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "chartView")
public class ChartView implements Serializable{
    
    private LineChartModel areaModel;
 
    @PostConstruct
    public void init() {
        createAreaModel();
    }
 
    public LineChartModel getAreaModel() {
        return areaModel;
    }
 
    private void createAreaModel() {
        areaModel = new LineChartModel();
 
        LineChartSeries target = new LineChartSeries();
        target.setFill(true);
        target.setLabel("Больше спать");
        target.set("1", 1);
        target.set("2", 2);
        target.set("3", 3);
        target.set("4", 3);
        target.set("5", 3);
        target.set("6", 4);
        target.set("7", 4);
        target.set("8", 4);
        target.set("9", 4);
        target.set("10", 5);
        target.set("11", 6);
        target.set("12", 7);
        target.set("13", 8);
        target.set("14", 9);
 
 
        areaModel.addSeries(target);
 
        areaModel.setTitle("Достижение цели");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
 
        Axis xAxis = new CategoryAxis("Дни");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Шкала выполнения");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
}
