/*
 * Class: org.bklab.flow.component.TelephoneNumberLabel
 * Modify date: 2020/3/20 上午10:14
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.html;

import com.vaadin.flow.component.Html;

public class TelephoneNumberLabel extends Html {

    public TelephoneNumberLabel(String telephoneNumber) {
        super(String.format("<a href='tel:%s'>%s</a>", telephoneNumber, telephoneNumber));
    }
}
