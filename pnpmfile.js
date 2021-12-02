/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-12-02 17:19:55
 * _____________________________
 * Project name: fluent-vaadin-flow-22
 * Class name: D:/broderick/develop/bklab/fluent-vaadin-flow/fluent-vaadin-flow-22/pnpmfile.js
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

/**
 * NOTICE: this is an auto-generated file
 *
 * This file has been generated for `pnpm install` task.
 * It is used to pin client side dependencies.
 * This file will be overwritten on every run.
 */

const fs = require('fs');

const versionsFile = require('path').resolve(__dirname, 'target/frontend/versions.json');

if (!fs.existsSync(versionsFile)) {
  return;
}
const versions = JSON.parse(fs.readFileSync(versionsFile, 'utf-8'));

module.exports = {
  hooks: {
    readPackage
  }
};

function readPackage(pkg) {
    const {dependencies} = pkg;

    if (dependencies) {
        for (let k in versions) {
            if (dependencies[k] && dependencies[k] !== versions[k]) {
                pkg.dependencies[k] = versions[k];
            }
        }
    }

    // Forcing chokidar version for now until new babel version is available
    // check out https://github.com/babel/babel/issues/11488
    if (pkg.dependencies.chokidar) {
        pkg.dependencies.chokidar = '^3.4.0';
    }

    return pkg;
}
