const {ipcRenderer} = require("electron");

function openFileBrowser() {
    let from = document.forms[0].hl.value;
    from = from && from.toLowerCase() || "en";
    let to = document.forms[0].sl.value;
    to = to && to.split(",").map(value => value.trim().toLowerCase()) || ["de"];
    ipcRenderer.send('select-dirs',{
        "from": from,
        "to": to
    });
}

let inputValue = [];
let lastLangLength = 0;

function formatArray(event) {
   console.log(event);
   event.preventDefault();
   if(event.code.match(/Key[a-zA-Z]/g)) {
       if(lastLangLength === 2) {
           inputValue.push(", ");
           lastLangLength = 0;
       }
       inputValue.push(event.key);
       lastLangLength++;
   } else if(event.code === "Backspace") {
       inputValue.pop();
       lastLangLength--;
       if(lastLangLength === 0) {
           inputValue.pop();
           if(inputValue.length > 0) {
               lastLangLength = 2;
           } else {
               lastLangLength = 0;
           }
       }
   }
   event.target.value = inputValue.join("");
}