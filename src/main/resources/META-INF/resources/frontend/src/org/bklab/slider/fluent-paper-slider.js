import {html, PolymerElement} from '@polymer/polymer';
import '@polymer/paper-slider/paper-slider.js';

class FluentPaperSlider extends PolymerElement {
    static get template() {
        return html`
      <paper-slider style="width: 100%" 
        on-value-change="onValueChanged" 
        min="[[min]]" 
        max="[[max]]" 
        value="{{value}}" 
        secondary-progress="200" 
        editable>
    </paper-slider>
    `;
    }
}

customElements.define('fluent-paper-slider', FluentPaperSlider);