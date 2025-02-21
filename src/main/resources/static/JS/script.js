console.log("Script Loaded...");

let currentTheme = getTheme();

changeTheme();

function changeTheme(){
    document.querySelector('html').classList.add(currentTheme);

    const change_btn=document.querySelector("#theme_change_btn");
    change_btn.addEventListener('click',(event)=>{
        console.log("Chnage theme button called..");
        const oldTheme = currentTheme
    if(currentTheme == "dark"){
        currentTheme = "light";
    }else
    {
        currentTheme="dark";
    }

    setTheme(currentTheme);

    document.querySelector('html').classList.remove(oldTheme);

    document.querySelector('html').classList.add(currentTheme);
    });
    
}

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    let theme = localStorage.getItem("theme");
    if(theme) return theme;
    else return "light";
}


// diappres message after some time
document.addEventListener("DOMContentLoaded", function() {
    // Select the alert message
    var alertMessage = document.querySelector("[role='alert']");
    
    if (alertMessage) {
        // Set a timeout to hide the message after 5 seconds (5000 milliseconds)
        setTimeout(function() {
            alertMessage.style.display = "none";
        }, 5000);
    }
});
