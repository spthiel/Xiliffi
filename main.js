const {app, BrowserWindow, dialog, ipcMain, session} = require('electron');

function createWindow() {

    let win = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            nodeIntegration: true
        }
    });
    win.webContents.openDevTools();
    win.setAlwaysOnTop(true);
    win.resizable = false;
    win.menuBarVisible = false;

    ipcMain.on('select-dirs', async (event, arg) => {
        console.log(arg);
        const result = await dialog.showOpenDialog(win, {
            properties: ['openDirectory']
        });
        console.log('directories selected', result.filePaths)
        if(result.filePaths.length > 0) {
            await win.loadFile("pages/editor/index.html");
            win.setAlwaysOnTop(false);
            win.resizable = true;
            win.menuBarVisible = true;
            win.maximize();
        }
    });

    win.loadFile("pages/projectselector/index.html");

}

app.whenReady().then(createWindow);
