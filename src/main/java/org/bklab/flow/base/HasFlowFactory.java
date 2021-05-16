/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-16 14:44:20
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.base.HasFlowFactory
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.base;

import com.vaadin.flow.component.Component;
import org.bklab.flow.IFlowFactory;

public interface HasFlowFactory<C extends Component, E extends IFlowFactory<? extends C>> {
    E asFactory();

}
