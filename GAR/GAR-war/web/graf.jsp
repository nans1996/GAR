<%-- 
    Document   : newjsf
    Created on : 20.03.2018, 21:14:47
    Author     : Vasilisa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>График</title>
        </head>
        <script src="http://d3js.org/d3.v3.min.js"> </script>
        <style>
        .axis path, .axis line {
            fill: none;
            stroke: #333;
        }
        .axis .grid-line {
            stroke: #000;
            stroke-opacity: 0.2;
        }
        .axis text {
            font: 10px Verdana;
        }
        </style>
        <body>
            <h:commandButton id="hdnBtn" actionListener="#{graf.usual}" style="display: none;" />
            <script type="text/javascript">
                
var height = 500, 
    width = 500, 
    margin= 30,
    rawData = [],
    data = [];
    list = jQuery('#form:hdnBtn').click();
                for (i = 0; i < rawData.length; i++)
                rawData[i]={x: i*10, y: list[i]*10};
                // создание объекта svg
                var svg = d3.select("body").append("svg")
                        .attr("class", "axis")
                        .attr("width", width)
                        .attr("height", height);

                // длина оси X= ширина контейнера svg - отступ слева и справа
                var xAxisLength = width - 2 * margin;

                // длина оси Y = высота контейнера svg - отступ сверху и снизу
                var yAxisLength = height - 2 * margin;

                // функция интерполяции значений на ось Х  
                var scaleX = d3.scale.linear()
                        .domain([0, 100])
                        .range([0, xAxisLength]);

                // функция интерполяции значений на ось Y
                var scaleY = d3.scale.linear()
                        .domain([100, 0])
                        .range([0, yAxisLength]);
                // масштабирование реальных данных в данные для нашей координатной системы
                for (i = 0; i < rawData.length; i++)
                    data.push({x: scaleX(rawData[i].x) + margin, y: scaleY(rawData[i].y) + margin});

                // создаем ось X   
                var xAxis = d3.svg.axis()
                        .scale(scaleX)
                        .orient("bottom");
                // создаем ось Y             
                var yAxis = d3.svg.axis()
                        .scale(scaleY)
                        .orient("left");

                // отрисовка оси Х             
                svg.append("g")
                        .attr("class", "x-axis")
                        .attr("transform", // сдвиг оси вниз и вправо
                                "translate(" + margin + "," + (height - margin) + ")")
                        .call(xAxis);

                // отрисовка оси Y 
                svg.append("g")
                        .attr("class", "y-axis")
                        .attr("transform", // сдвиг оси вниз и вправо на margin
                                "translate(" + margin + "," + margin + ")")
                        .call(yAxis);

                // создаем набор вертикальных линий для сетки   
                d3.selectAll("g.x-axis g.tick")
                        .append("line")
                        .classed("grid-line", true)
                        .attr("x1", 0)
                        .attr("y1", 0)
                        .attr("x2", 0)
                        .attr("y2", -(yAxisLength));

                // рисуем горизонтальные линии координатной сетки
                d3.selectAll("g.y-axis g.tick")
                        .append("line")
                        .classed("grid-line", true)
                        .attr("x1", 0)
                        .attr("y1", 0)
                        .attr("x2", xAxisLength)
                        .attr("y2", 0);

                // функция, создающая по массиву точек линии
                var line = d3.svg.line()
                        .x(function (d) {
                            return d.x;
                        })
                        .y(function (d) {
                            return d.y;
                        });
                // добавляем путь
                svg.append("g").append("path")
                        .attr("d", line(data))
                        .style("stroke", "steelblue")
                        .style("stroke-width", 2);
            </script>
        </body>
    </html>
</f:view>
