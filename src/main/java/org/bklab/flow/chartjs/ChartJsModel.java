package org.bklab.flow.chartjs;

import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * Represents chart's necessary data to be created.
 * jsonChart aggregates jsonChartData and jsonChartOptions.
 */
public interface ChartJsModel extends TemplateModel {
    void setChartJs(String jsonChart);

    void setChartData(String jsonChartData);

    void setChartOptions(String jsonChartOptions);
}
