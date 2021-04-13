import {DataSet, DataSetEdges, DataSetNodes, Network, Options} from 'vis-network';
import {css, customElement, html, LitElement} from 'lit-element';


@customElement('vis-network-diagram')
export class NetworkDiagram extends LitElement {

    static styles = css`
    :host {
        display: block;
        padding: var(--lumo-space-m) var(--lumo-space-m); /*<1>*/
    }
    `;

    readonly #networkDiagramCanvas: HTMLElement;
    readonly #network: Network;
    #data: DataSet;
    #_nodes: DataSetNodes;
    #_edges: DataSetEdges;
    #_options: Options;


    render() {
        return html`
            <span id="vis-network-diagram-container" style="height: 100%; width: 100%">
                <canvas id="vis-network-diagram"
                        style="width: 100%; height: 100%"
                        @click="this.handleClick"
                ></canvas>
            </span>
        `
    }

    constructor() {
        super();
        // @ts-ignore
        this.#networkDiagramCanvas = document.getElementById("vis-network-diagram");
        this.#networkDiagramCanvas.innerText = '正在加载。。。';

        this.#_nodes = [];
        this.#_edges = [];
        this.#data = new DataSet(this.#_nodes, this.#_edges);
        this.#_options = {};
        this.#network = new Network(this.#networkDiagramCanvas, this.#data, this.#_options);
    }

    async connectedCallback() {
        super.connectedCallback();

        this.#networkDiagramCanvas.innerText = '';
        this.#network.fit();
    }

    async handleClick() {

    }


    set nodes(value: DataSetNodes) {
        this.#_nodes = value;
    }

    set edges(value: DataSetEdges) {
        this.#_edges = value;
    }

    set options(value: Options) {
        this.#_options = value;
    }
}

customElements.define('vis-network-diagram', NetworkDiagram);