const {ipcRenderer} = require("electron");

function openFileBrowser() {
    ipcRenderer.send('select-dirs');
}
