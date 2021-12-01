/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-01 09:22:48
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: D:/broderick/develop/bklab/fluent-vaadin-flow/fluent-vaadin-flow-22/vite.config.ts
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

import {UserConfigFn} from 'vite';
import {overrideVaadinConfig} from './vite.generated';

const customConfig: UserConfigFn = (env) => ({
    // Here you can add custom Vite parameters
    // https://vitejs.dev/config/
});

export default overrideVaadinConfig(customConfig);
