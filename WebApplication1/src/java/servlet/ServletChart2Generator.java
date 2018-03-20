package servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
// import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleInsets;

public class ServletChart2Generator extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ServletChart2Generator(){}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {
		OutputStream out = response.getOutputStream();
		try {
			String type = request.getParameter("type");
			JFreeChart chart = null;
			if (type.equals("bar"))
				chart = createBarChart();
			else if (type.equals("line"))
				chart = createLineChart();

			if (chart != null) {
				response.setContentType("image/png");
				ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		} finally {
			out.close();
		}
	}
	private JFreeChart createBarChart()
	{
		JFreeChart             chart   = null;
		Dataset                dao     = new Dataset();
		DefaultCategoryDataset dataset = dao.getCategoryDataset();
		chart = ChartFactory.createBarChart("Диаграмма значений", 
                null, "Уровень",
                dataset,
                PlotOrientation.HORIZONTAL,
                true, true, false);
	    CategoryPlot plot = chart.getCategoryPlot();
	    // Определение фона 
		plot.setBackgroundPaint(Color.lightGray);
		plot.setBackgroundAlpha(0.2f);

		// Цвет осей на графике
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);
		
		// Размещение цифровой оси в нижней части диаграммы  
		chart.getCategoryPlot().setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
	    // Выделение объектов
		CategoryMarker cm2 = new CategoryMarker("Object2");
		cm2.setAlpha(0.5f);
		CategoryMarker cm4 = new CategoryMarker("Object4");
		cm4.setAlpha(0.5f);
		chart.getCategoryPlot().addDomainMarker(cm2, Layer.BACKGROUND);
		chart.getCategoryPlot().addDomainMarker(cm4, Layer.BACKGROUND);
		
		return chart;
	}
	private JFreeChart createLineChart()
	{
		Dataset    dao     = new Dataset();
		XYDataset  dataset = dao.getXYDataset();

		final JFreeChart chart = ChartFactory.createXYLineChart(
		            "Line Chart",             // chart title
		            "X",                      // x axis label
		            "Y",                      // y axis label
		            dataset,                  // data
		            PlotOrientation.VERTICAL,
		            true,                     // include legend
		            true,                     // tooltips
		            false                     // urls
		        );
		XYPlot plot = chart.getXYPlot();

		// Фон графика
		plot.setBackgroundPaint(Color.lightGray);
		plot.setBackgroundAlpha(0.2f);
		
		// Цвет сетки на диаграмме
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint (Color.gray);

        // Удаляем из диаграммы осевые линии
		ValueAxis axis = plot.getRangeAxis();  // RangeAxis
		axis.setAxisLineVisible(false);
		axis = plot.getDomainAxis();           // DomainAxis
		axis.setAxisLineVisible(false);
		// Определение отступа меток делений  
		plot.setAxisOffset(new RectangleInsets (1.0, 1.0, 1.0, 1.0));
		
		// Исключаем представление линий 1-го графика
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible (0, false);
//      renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

//      change the auto tick unit selection to integer units only
//      final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//      rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		return chart; 
	}	
}
