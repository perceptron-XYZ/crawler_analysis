# App Visualizer
In this school project, We implemented a small data visualization tool for app info stored in CSV.

It provides a visualized analysis on the developer's level, for example: display the average rating for each developer.
It is complemented by another project ([google_app_crawler_analyzer](https://github.com/xingyz/google_app_crawler_analyzer) )which has info about an individual app. 

In order to run this program, you need 4 libraries: <b>jcommons, jfreechart, kxml and xstream</b>.

The app supports two types of analysis, list and chart. 

![alt tag](https://raw.githubusercontent.com/perceptron-XYZ/crawler_analysis/master/chartview.png)
![alt tag](https://raw.githubusercontent.com/perceptron-XYZ/crawler_analysis/master/tableview.png)

You have to choose a base element and an aggregate element. For example, 'developers' as base and 'total number of apps' as aggregate will give you the number of apps for each developer. You can also order the result in both orders.
