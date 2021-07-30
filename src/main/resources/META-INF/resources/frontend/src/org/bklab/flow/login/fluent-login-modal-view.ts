/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-30 17:48:38
 * _____________________________
 * Project name: fluent-vaadin-flow.main
 * Class name：D:/broderick/develop/bklab/fluent-vaadin-flow/fluent-vaadin-flow-20/src/main/resources/META-INF/resources/frontend/src/org/bklab/flow/login/fluent-login-modal-view.ts
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

import {css, customElement, html, LitElement} from 'lit-element';

@customElement('fluent-login-modal-view')
export class FluentLoginModalView extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      
      .outline{
          
      }
      
      .bklab-login-copyright:link {color: var(--lumo-secondary-text-color)} 
      .bklab-login-copyright:visited {color: var(--lumo-secondary-text-color)}
      .bklab-login-copyright:hover {color: var(--lumo-secondary-text-color)} 
      .bklab-login-copyright:active {color: var(--lumo-secondary-text-color)}
      `;
  }

  render() {
    return html`
      <style include="shared-styles">
        :host {
          display: block;
          height: 100%;
        }

        .bklab-login-copyright:link {
          color: var(--lumo-secondary-text-color)
        }

        .bklab-login-copyright:visited {
          color: var(--lumo-secondary-text-color)
        }

        .bklab-login-copyright:hover {
          color: var(--lumo-secondary-text-color)
        }

        .bklab-login-copyright:active {
          color: var(--lumo-secondary-text-color)
        }
      </style>
      <vaadin-vertical-layout
          style="width: 100%;height: 100%;justify-content: center;padding: var(--lumo-space-xs);background: url(&quot;data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%221920%22%20height%3D%221080%22%20fill%3D%22none%22%3E%3Cg%20opacity%3D%22.2%22%20clip-path%3D%22url%28%23E%29%22%3E%3Cpath%20d%3D%22M1466.4%201795.2c950.37%200%201720.8-627.52%201720.8-1401.6S2416.77-1008%201466.4-1008-254.4-380.482-254.4%20393.6s770.428%201401.6%201720.8%201401.6z%22%20fill%3D%22url%28%23A%29%22%2F%3E%3Cpath%20d%3D%22M394.2%201815.6c746.58%200%201351.8-493.2%201351.8-1101.6S1140.78-387.6%20394.2-387.6-957.6%20105.603-957.6%20714-352.38%201815.6%20394.2%201815.6z%22%20fill%3D%22url%28%23B%29%22%2F%3E%3Cpath%20d%3D%22M1548.6%201885.2c631.92%200%201144.2-417.45%201144.2-932.4S2180.52%2020.4%201548.6%2020.4%20404.4%20437.85%20404.4%20952.8s512.276%20932.4%201144.2%20932.4z%22%20fill%3D%22url%28%23C%29%22%2F%3E%3Cpath%20d%3D%22M265.8%201215.6c690.246%200%201249.8-455.595%201249.8-1017.6S956.046-819.6%20265.8-819.6-984-364.005-984%20198-424.445%201215.6%20265.8%201215.6z%22%20fill%3D%22url%28%23D%29%22%2F%3E%3C%2Fg%3E%3Cdefs%3E%3CradialGradient%20id%3D%22A%22%20cx%3D%220%22%20cy%3D%220%22%20r%3D%221%22%20gradientUnits%3D%22userSpaceOnUse%22%20gradientTransform%3D%22translate%281466.4%20393.6%29%20rotate%2890%29%20scale%281401.6%201720.8%29%22%3E%3Cstop%20stop-color%3D%22%23107c10%22%2F%3E%3Cstop%20offset%3D%221%22%20stop-color%3D%22%23c4c4c4%22%20stop-opacity%3D%220%22%2F%3E%3C%2FradialGradient%3E%3CradialGradient%20id%3D%22B%22%20cx%3D%220%22%20cy%3D%220%22%20r%3D%221%22%20gradientUnits%3D%22userSpaceOnUse%22%20gradientTransform%3D%22translate%28394.2%20714%29%20rotate%2890%29%20scale%281101.6%201351.8%29%22%3E%3Cstop%20stop-color%3D%22%230078d4%22%2F%3E%3Cstop%20offset%3D%221%22%20stop-color%3D%22%23c4c4c4%22%20stop-opacity%3D%220%22%2F%3E%3C%2FradialGradient%3E%3CradialGradient%20id%3D%22C%22%20cx%3D%220%22%20cy%3D%220%22%20r%3D%221%22%20gradientUnits%3D%22userSpaceOnUse%22%20gradientTransform%3D%22translate%281548.6%20952.8%29%20rotate%2890%29%20scale%28932.4%201144.2%29%22%3E%3Cstop%20stop-color%3D%22%23ffb900%22%20stop-opacity%3D%22.75%22%2F%3E%3Cstop%20offset%3D%221%22%20stop-color%3D%22%23c4c4c4%22%20stop-opacity%3D%220%22%2F%3E%3C%2FradialGradient%3E%3CradialGradient%20id%3D%22D%22%20cx%3D%220%22%20cy%3D%220%22%20r%3D%221%22%20gradientUnits%3D%22userSpaceOnUse%22%20gradientTransform%3D%22translate%28265.8%20198%29%20rotate%2890%29%20scale%281017.6%201249.8%29%22%3E%3Cstop%20stop-color%3D%22%23d83b01%22%20stop-opacity%3D%22.75%22%2F%3E%3Cstop%20offset%3D%221%22%20stop-color%3D%22%23c4c4c4%22%20stop-opacity%3D%220%22%2F%3E%3C%2FradialGradient%3E%3CclipPath%20id%3D%22E%22%3E%3Cpath%20fill%3D%22%23fff%22%20d%3D%22M0%200h1920v1080H0z%22%2F%3E%3C%2FclipPath%3E%3C%2Fdefs%3E%3C%2Fsvg%3E&quot;);"
          id="outerLayout">
        <vaadin-vertical-layout theme="spacing-s"
                                style="width: 360px; margin: auto; padding: 2em; justify-content: center; align-items: center; border: 1px solid #eaeced; overflow: hidden; max-width:90vw; background-color:var(--lumo-base-color); border-radius:var(--lumo-border-radius-l);box-shadow: 0 2px 6px rgba(0,0,0,0.15);"
                                id="content"></vaadin-vertical-layout>
        <vaadin-horizontal-layout theme="spacing" id="copyright"
                                  style="align-self: center; justify-content: center; align-items: center; flex-wrap: wrap; align-content: center; margin: var(--lumo-space-m); padding: var(--lumo-space-xs);"
                                  class="bklab-login-copyright">
          <a href="https://bbkki.com" class="bklab-login-copyright"
             style="align-self: center; display: flex; flex-wrap: wrap; align-content: center; justify-content: center; align-items: center; flex-direction: row;"
             target="_blank"><span style="margin: var(--lumo-space-s);" id="span">Broderick Labs</span><span
              style="word-break: break-all; display: flex; text-align: center; margin: var(--lumo-space-s);">布克约森实验室</span></a>
        </vaadin-horizontal-layout>
      </vaadin-vertical-layout>
    `;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
