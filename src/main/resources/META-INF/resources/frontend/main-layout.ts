/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-19 15:40:31
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：D:/broderick/develop/vaadin/16/fluent-vaadin-flow/frontend/main-layout.ts
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

import {css, customElement, html, LitElement} from 'lit-element';

@customElement('main-layout')
export class MainLayoutElement extends LitElement {

    static get styles() {
        return css`
      :host {
        display: block;
        height: 100%;
      }
    `;
    }

    render() {
        return html`
      <a href="client-view">Client View</a>
      <a href="server-view">Server View</a>
      <slot></slot>
     `;
    }
}