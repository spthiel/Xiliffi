const {app, BrowserWindow, dialog, ipcMain, session} = require('electron');

function createWindow() {
    session.defaultSession.webRequest.onHeadersReceived((details, callback) => {
        callback({
            responseHeaders: {
                ...details.responseHeaders,
                'Content-Security-Policy': ['default-src \'self\'']
            }
        })
    });

    let win = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true
        }
    });
    win.webContents.openDevTools();
    win.loadFile("pages/projectselector/index.html");

    ipcMain.on('select-dirs', async (event, arg) => {
        const result = await dialog.showOpenDialog(win, {
            properties: ['openDirectory']
        });
        console.log('directories selected', result.filePaths)
        if(result.filePaths.length > 0) {
            win.loadFile("pages/projectselector/index.html")
        }
    });
}

app.whenReady().then(createWindow);
