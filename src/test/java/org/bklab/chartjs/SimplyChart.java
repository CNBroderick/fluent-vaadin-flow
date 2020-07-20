package org.bklab.chartjs;

import org.bklab.flow.chartjs.ChartJs;

public class SimplyChart extends ChartJs {

    public SimplyChart() {
        super("{\n" +
                "    \"type\": \"bar\",\n" +
                "    \"data\": {\n" +
                "        \"labels\": [\"Red\", \"Blue\", \"Yellow\", \"Green\", \"Purple\", \"Orange\"],\n" +
                "        \"datasets\": [{\n" +
                "            \"label\": \"# of Votes\",\n" +
                "            \"data\": [12, 19, 3, 5, 2, 3],\n" +
                "            \"backgroundColor\": [\n" +
                "                \"rgba(255, 99, 132, 0.2)\",\n" +
                "                \"rgba(54, 162, 235, 0.2)\",\n" +
                "                \"rgba(255, 206, 86, 0.2)\",\n" +
                "                \"rgba(75, 192, 192, 0.2)\",\n" +
                "                \"rgba(153, 102, 255, 0.2)\",\n" +
                "                \"rgba(255, 159, 64, 0.2)\"\n" +
                "            ],\n" +
                "            \"borderColor\": [\n" +
                "                \"rgba(255, 99, 132, 1)\",\n" +
                "                \"rgba(54, 162, 235, 1)\",\n" +
                "                \"rgba(255, 206, 86, 1)\",\n" +
                "                \"rgba(75, 192, 192, 1)\",\n" +
                "                \"rgba(153, 102, 255, 1)\",\n" +
                "                \"rgba(255, 159, 64, 1)\"\n" +
                "            ],\n" +
                "            \"borderWidth\": 1\n" +
                "        }]\n" +
                "    },\n" +
                "    \"options\": {\n" +
                "        \"scales\": {\n" +
                "            \"yAxes\": [{\n" +
                "                \"ticks\": {\n" +
                "                    \"beginAtZero\": true\n" +
                "                }\n" +
                "            }]\n" +
                "        }\n" +
                "    }\n" +
                "}");
        getElement().setProperty("height", "300px");
    }
}
