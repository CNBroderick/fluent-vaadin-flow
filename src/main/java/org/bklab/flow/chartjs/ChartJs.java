package org.bklab.flow.chartjs;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.shared.Registration;

@Tag("chart-js")
@NpmPackage(value = "chart.js", version = "2.9.3")
@NpmPackage(value = "moment", version = "2.24.0")
@NpmPackage(value = "@polymer/paper-slider", version = "^3.0.1")
@JsModule("./src/chart/chartjs.js")
@JsModule("chart.js/dist/Chart.min.js")
@JsModule("moment/moment.js")
/**
 * This class is a java wrapper for chart.js web component.
 */
public class ChartJs extends PolymerTemplate<ChartJsModel> {

    /**
     * @param chartJson - Valid JSON representation of Chart.js chart.
     */
    public ChartJs(String chartJson) {
        super();
        getElement().getStyle().set(ElementConstants.STYLE_WIDTH, "100%");
        getElement().getStyle().set(ElementConstants.STYLE_HEIGHT, "100%");
        getElement().getStyle().set("alignSelf", "center");
        getElement().getStyle().set("alignItems", "center");
        getModel().setChartJs(chartJson);
    }

    /**
     * Enables to add a listener on frontend click event.
     * This is invoked only when a dataset is clicked.
     *
     * @param listener
     */
    public Registration addClickListener(ComponentEventListener<ClickEvent> listener) {
        return addListener(ClickEvent.class, listener);
    }

    /**
     * Enables to update whole chart configuration and dataset.
     *
     * @param chart - Valid JSON representation of Chartjs chart.
     */
    public void updateChart(String chart) {
        getModel().setChartJs(chart);
    }

    /**
     * Enables to update datasets.
     *
     * @param data
     */
    public void updateData(String data) {
        getModel().setChartData(data);
    }

    /**
     * Enables to update chart options.
     *
     * @param options
     */
    public void updateOptions(String options) {
        getModel().setChartOptions(options);
    }

    /**
     * This is a RPC callback invoked by client on dataset click event.
     * It raise ClickEvent.
     *
     * @param label        - chart label when having vertical chart than it is x value.
     * @param datasetLabel - dataset label usually is seen in legend.
     * @param value        - value of dataset corresponds to y value in vertical chart.
     */
    @ClientCallable
    private void handleClick(String label, String datasetLabel, String value) {
        fireEvent(new ClickEvent(this, true, label, datasetLabel, value));
    }
}
