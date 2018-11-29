package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import main.java.lab1.dataFrame.Column;
import main.java.lab1.dataFrame.DataFrame;

public class ChartController {
    @FXML BorderPane borderPane;

    private DataFrame dataFrame;
    private Column firstColumn;
    private Column secondColumn;

    public void setDataFrame(DataFrame dataFrame) {
        this.dataFrame = dataFrame;
    }

    public void setFirstColumn(Column firstColumn) {
        this.firstColumn = firstColumn;
    }

    public void setSecondColumn(Column secondColumn) {
        this.secondColumn = secondColumn;
    }


    public void createChart (){
        System.out.println(firstColumn.getName()+"\n"+secondColumn.getName());
        final CategoryAxis xAxis = new CategoryAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final LineChart<String, String> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Char of " + firstColumn.getName() + " and " + secondColumn.getName());
        XYChart.Series series = new XYChart.Series();
        series.setName("DataFrame");

        for (int i = 0; i < dataFrame.size(); i++) {
            series.getData().add(new XYChart.Data<>(firstColumn.elementAtIndex(i).toString(), secondColumn.elementAtIndex(i).toString()));
        }

        lineChart.getData().add(series);
        borderPane.setCenter(lineChart);
    }
    public DataFrame getDataFrame (){
        return dataFrame;
    }
}
