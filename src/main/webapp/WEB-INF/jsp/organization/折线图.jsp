<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/5/12 0012
  Time: 下午 6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    $(function () {
        $('#container').highcharts({
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: 'Average Monthly Temperature and Rainfall in Tokyo'
            },
            subtitle: {
                text: 'Source: WorldClimate.com'
            },
            xAxis: [{
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}°C',
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: 'Temperature',
                    style: {
                        color: '#89A54E'
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: 'Rainfall',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    format: '{value} mm',
                    style: {
                        color: '#4572A7'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 120,
                verticalAlign: 'top',
                y: 100,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [{
                name: 'Rainfall',
                color: '#4572A7',
                type: 'column',
                yAxis: 1,
                data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,65.5],
                tooltip: {
                    valueSuffix: ' mm'
                }

            }, {
                name: 'Temperature',
                color: '#89A54E',
                type: 'spline',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6,12],
                tooltip: {
                    valueSuffix: '°C'
                }
            }]
        });
    });
</script>
</body>
</html>
