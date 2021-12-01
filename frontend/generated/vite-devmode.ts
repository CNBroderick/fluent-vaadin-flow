/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:48
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: D:/broderick/develop/bklab/fluent-vaadin-flow/fluent-vaadin-flow-22/frontend/generated/vite-devmode.ts
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

// @ts-ignore
if (import.meta.hot) {
    // @ts-ignore
    const hot = import.meta.hot;
    let pendingNavigationTo: string | undefined = undefined;

    window.addEventListener('vaadin-router-go', (routerEvent: any) => {
        pendingNavigationTo = routerEvent.detail.pathname + routerEvent.detail.search;
    });
    hot.on('vite:beforeFullReload', (payload: any) => {
        if (pendingNavigationTo) {
            // Force reload with the new URL
            location.href = pendingNavigationTo;
            // Prevent Vite from reloading
            payload.path = '/_fake/path.html';
        }
    });
}
