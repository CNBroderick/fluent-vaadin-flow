package org.bklab.flow.chartjs;

import com.vaadin.flow.component.ComponentEvent;

/**
 * Represents dataset click event.
 * Holds information which dataset values was clicked.
 */
public class ClickEvent extends ComponentEvent<ChartJs> {
    private String label;
    private String value;
    private String datasetLabel;

    public ClickEvent(ChartJs source, boolean fromClient, String label, String datasetLabel, String value) {
        super(source, fromClient);

        this.label = label;
        this.value = value;
        this.datasetLabel = datasetLabel;
    }

    /**
     * Corresponds to value on x axis of vertical chart.
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * Corresponds to value on y axis of vertical chart.
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Label the dataset, it;s value corresponds to values in the legend.
     *
     * @return
     */
    public String getDatasetLabel() {
        return datasetLabel;
    }
}
