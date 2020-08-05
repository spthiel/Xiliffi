const {ipcRenderer} = require("electron");

let languages = {"de": false};
let files = [];
let currentPath;
let currentFile;

ipcRenderer.on('files',(event, args) => {



});

async function openFile(file) {
    if(currentPath) {
        await saveFile();
    }
    ipcRenderer.invoke('openFile',file).then(xml => {
        currentPath = file;
        displayFile(xml);
    });
}

function displayFile(json) {

}

async function saveFile() {
    return ipcRenderer.invoke('save',currentPath, currentFile);
}