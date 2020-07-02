/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.mix.DataSeriesItemWithRadius
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.mix;

import com.vaadin.flow.component.charts.model.DataSeriesItem;

public class DataSeriesItemWithRadius extends DataSeriesItem {

    private String radius;
    private String innerRadius;

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
        makeCustomized();
    }

    public String getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(String innerRadius) {
        this.innerRadius = innerRadius;
        makeCustomized();
    }
}
