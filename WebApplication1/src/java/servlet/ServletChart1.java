/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;

/**
 *
 * @author Nastya Winehouse
 */
public class ServletChart1 extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public ServletChart1() {}

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response)
                            throws ServletException, IOException
    {
        OutputStream           out     = null;
        Dataset                dao     = null;
        DefaultCategoryDataset dataset = null;
        JFreeChart             chart   = null;
        response.setContentType("image/png");
        try {
            dao     = new Dataset();
            dataset = dao.getCategoryDataset();
            chart = ChartFactory.createBarChart("Диаграмма значений", 
                                                null, "Уровень",
                                                dataset,
                                                PlotOrientation.VERTICAL,
                                                true, true, false);
            // Удаление оси абсцисс
            CategoryPlot plot = chart.getCategoryPlot();
            Axis         axis = plot.getDomainAxis();
            axis.setTickLabelsVisible(false);  // метки значений оси
            axis.setAxisLineVisible(false);    // осевая линия
            axis.setTickMarksVisible(false);   // метки деления оси 
            
            CategoryMarker cm2 = new CategoryMarker("Object2");
            cm2.setAlpha(0.5f);
            CategoryMarker cm4 = new CategoryMarker("Object4");
            cm2.setAlpha(0.5f);
            chart.getCategoryPlot().addDomainMarker(cm2, Layer.BACKGROUND);
            chart.getCategoryPlot().addDomainMarker(cm4, Layer.BACKGROUND);

            response.setContentType("image/png");
            // Экспорт диаграммы в PNG
            out = response.getOutputStream();
            ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            out.close();
        }
    }
}

   