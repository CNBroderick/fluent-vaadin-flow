package org.bklab.flow.echarts;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

@Tag("echarts")
@NpmPackage(value = "echarts", version = "4.8.0")
@NpmPackage(value = "@types/echarts", version = "^4.6.4")
@NpmPackage(value = "@polymer/paper-slider", version = "^3.0.1")
@JsModule("./src/org/bklab/echarts/echarts.ts")
@JsModule("echarts/echarts.all.js")
@JsModule("@types/echarts/index.d.ts")
public class ECharts extends PolymerTemplate<EChartModel> {

    public ECharts() {
        getElement().getStyle().set("height", "300px");
    }
}
