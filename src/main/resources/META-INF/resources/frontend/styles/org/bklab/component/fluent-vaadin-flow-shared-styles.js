/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 15:58:37
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: D:/broderick/develop/bklab/fluent-vaadin-flow/fluent-vaadin-flow-22/src/main/resources/META-INF/resources/frontend/styles/org/bklab/component/fluent-vaadin-flow-shared-styles.js
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';
import '@vaadin/vaadin-charts/theme/vaadin-chart-base-theme';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
  <style>
    html {
      --lumo-font-size: 1rem;
      --lumo-font-size-xxxl: 1.75rem;
      --lumo-font-size-xxl: 1.375rem;
      --lumo-font-size-xl: 1.125rem;
      --lumo-font-size-l: 1rem;
      --lumo-font-size-m: 0.875rem;
      --lumo-font-size-s: 0.8125rem;
      --lumo-font-size-xs: 0.75rem;
      --lumo-font-size-xxs: 0.6875rem;
      --lumo-line-height-m: 1.4;
      --lumo-line-height-s: 1.2;
      --lumo-line-height-xs: 1.1;
      --lumo-size-xl: 3rem;
      --lumo-size-l: 2.5rem;
      --lumo-size-m: 2rem;
      --lumo-size-s: 1.75rem;
      --lumo-size-xs: 1.5rem;
      --lumo-space-xl: 1.875rem;
      --lumo-space-l: 1.25rem;
      --lumo-space-m: 0.625rem;
      --lumo-space-s: 0.3125rem;
      --lumo-space-xs: 0.1875rem;
      
      --vaadin-charts-color-0, #7cb5ec;
      --vaadin-charts-color-1, #90ed7d;
      --vaadin-charts-color-2, #f7a35c;
      --vaadin-charts-color-3, #8085e9;
      --vaadin-charts-color-4, #f15c80;
      --vaadin-charts-color-5, #e4d354;
      --vaadin-charts-color-6, #2b908f;
      --vaadin-charts-color-7, #f45b5b;
      --vaadin-charts-color-8, #91e8e1;
      --vaadin-charts-color-9, #434348;
    }
  </style>
</custom-style>

<dom-module id="text-field-style" theme-for="vaadin-text-field vaadin-date-picker vaadin-time-picker vaadin-combo-box vaadin-text-area vaadin-password-field">
  <template>
    <style>
      [part="input-field"] {
        border: 1px solid #d9d9d9;
        background-color: var(--lumo-base-color);
      }
      
      :host([theme~="pagination"]) [part="input-field"] {
        border: none;
      } 
      
      :host([readonly]) [part="input-field"] {
        border: none;
      } 

      :host([invalid]) [part="input-field"] {
        box-shadow: inset 0 0 0 1px var(--lumo-error-color);
      }
    </style>
  </template>
</dom-module>

<custom-style>
  <style>
    html {
      overflow:hidden;
    }
  </style>
</custom-style>

<dom-module id="app-layout" theme-for="vaadin-app-layout">
  <template>
    <style>
      :host(:not([dir='rtl']):not([overlay])) [part='drawer'] {
        border-right: none;
        box-shadow: var(--lumo-box-shadow-s);
        background-color: var(--lumo-base-color);
        z-index: 1;
      }
      :host([dir='rtl']:not([overlay])) [part='drawer'] {
        border-left: none;
        box-shadow: var(--lumo-box-shadow-s);
        background-color: var(--lumo-base-color);
        z-index: 1;
      }
      [part='navbar'] {
        box-shadow: var(--lumo-box-shadow-s);
      }
    </style>
  </template>
</dom-module>

<dom-module id="chart" theme-for="vaadin-chart">
  <template>
    <style include="vaadin-chart-default-theme">
      :host {
        --vaadin-charts-color-0: var(--lumo-primary-color);
        --vaadin-charts-color-1: var(--lumo-error-color);
        --vaadin-charts-color-2: var(--lumo-success-color);
        --vaadin-charts-color-3: var(--lumo-contrast);
      }
      .highcharts-container {
        font-family: var(--lumo-font-family);
      }
      .highcharts-background {
        fill: var(--lumo-base-color);
      }
      .highcharts-title {
        fill: var(--lumo-header-text-color);
        font-size: var(--lumo-font-size-xl); 
        line-height: var(--lumo-line-height-xs);
        color: var(--lumo-contrast);
        font-weight: 500;
        font-family: "Microsoft Yahei", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        letter-spacing: 0.1em;
      }
      
      .highcharts-legend-item text {
        fill: var(--lumo-body-text-color);
      }
      .highcharts-axis-title,
      .highcharts-axis-labels {
        fill: var(--lumo-secondary-text-color);
      }
      .highcharts-axis-line,
      .highcharts-grid-line,
      .highcharts-tick {
        stroke: var(--lumo-contrast-10pct);
      }
      .highcharts-column-series rect.highcharts-point {
        stroke: var(--lumo-base-color);
      }
    </style>
  </template>
</dom-module>


<dom-module id="fluent-flow-vaadin-css">
    <template>
        <style>
        :root {
            --app-bar-height: var(--lumo-size-xl);

            --navi-drawer-width: calc(var(--lumo-size-m) * 7);
            --navi-drawer-rail-width: calc(var(--lumo-size-m) * 1.75);
            --navi-item-indentation: calc(var(--lumo-icon-size-s) + var(--lumo-space-s));

            --details-drawer-width: calc(var(--lumo-size-m) * 11);

            --transition-duration-s: 160ms;
            --transition-duration-m: 240ms;
            --transition-duration-l: 320ms;

            /* Responsive sizing and spacing */
            --lumo-space-r-m: var(--lumo-space-m);
            --lumo-space-r-l: var(--lumo-space-l);
            --lumo-space-r-x: var(--lumo-space-l);
            --lumo-space-wide-r-m: var(--lumo-space-wide-m);
            --lumo-space-wide-r-l: var(--lumo-space-wide-l);
        }

            @media (max-width: 479px) {
            :root {
            --lumo-space-r-x: 0;
        }
        }

            @media (min-width: 480px) and (max-width: 1023px) {
            :root {
            --lumo-space-r-x: var(--lumo-space-m);
        }
        }

            @media (max-width: 1023px) {
            :root {
            --lumo-space-r-m: var(--lumo-space-s);
            --lumo-space-r-l: var(--lumo-space-m);
            --lumo-space-wide-r-m: var(--lumo-space-wide-s);
            --lumo-space-wide-r-l: var(--lumo-space-wide-m);
        }
        }

            html,
            body {
            height: 100%;
            overflow: hidden;
            width: 100%;
        }

            .root {
            background-color: var(--lumo-contrast-5pct);
        }

            .app-header-outer,
            .app-footer-outer {
            z-index: 3;
        }

            vaadin-grid-cell-content {
            text-overflow: ellipsis;
        }

            vaadin-text-field {
            align-self: auto;
        }
        </style>
    </template>
</dom-module>
`;

document.head.appendChild($_documentContainer.content);
