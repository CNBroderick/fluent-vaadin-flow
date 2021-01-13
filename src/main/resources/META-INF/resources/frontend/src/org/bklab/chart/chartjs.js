import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

class Chartjs extends PolymerElement {
    constructor() {
        super();
    }

    static get template() {
        return html`
            <div id="chart-container" class="chart-container" style="position: relative; height:100%; width:100%;">
                <canvas id="chart" onclick="handleClick"></canvas>
            </div>
        `;
    }

    static get is() {
        return 'chart-js'
    }

    static get properties() {
        return {
            chartJs: {
                type: String,
                observer: '_chartJsChanged'
            },
            chartData: {
                type: String,
                observer: '_chartDataChanged'
            },
            chartOptions: {
                type: String,
                observer: '_chartOptionsChanged'
            }
        }
    }

    _chartJsChanged(newValue) {
        if (this.chart !== undefined && newValue != null) {
            this.chart.chart(this.parseJson(newValue));
            this.chart.update();
        }
    }

    _chartDataChanged(newValue) {
        if (this.chart !== undefined && newValue != null) {
            this.chart.data = this.parseJson(newValue);
            this.chart.update();
        }
    }

    _chartOptionsChanged(newValue) {
        if (this.chart !== undefined && newValue != null) {
            this.chart.options(this.parseJson(newValue));
            this.chart.update();
        }
    }

    handleClick(event) {
        var activePoints = this.chart.getElementAtEvent(event);
        var vaadinServer = this.$server;

        if (activePoints[0]) {
            var activePoint = activePoints[0];
            var data = activePoint._chart.data;
            var datasetIndex = activePoint._datasetIndex;
            var label = data.labels[activePoint._index];
            var datasetLabel = data.datasets[datasetIndex].label;
            var value = data.datasets[datasetIndex].data[activePoint._index];

            vaadinServer.handleClick(label, datasetLabel, value);
        }
    };

    ready() {
        super.ready();
        this.adjustSize();
    }

    connectedCallback() {
        super.connectedCallback();
        const ctx = this.shadowRoot.querySelector('#chart').getContext('2d');
        this.chart = new Chart(ctx, this.parseJson(this.chartJs));
    }

    adjustSize() {
        const c = this.shadowRoot.querySelector('#chart');
        const p = this.shadowRoot.querySelector('#chart-container');

        const h = window.getComputedStyle(p).height;
        const w = window.getComputedStyle(p).width;
        p.height = h + "px";
        p.width = w + "px";
        p.style.height = h + "px";
        p.style.width = w + "px";

        c.setAttribute("height", h);
        c.setAttribute("width", w);
    }

    parseJson(json) {
        return JSON.parse(json, function (key, value) {
            if (typeof value != 'string') return value;
            return (value.substring(0, 8) === 'function') ? eval('(' + value + ')') : value;
        });
    }
}

customElements.define(Chartjs.is, Chartjs);


