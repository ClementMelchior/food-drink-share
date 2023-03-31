
function displayDetail (parent, i) {
    let recipe = "wine-"+i;
    let button = "button-"+i;

    $(document.getElementById(recipe)).fadeIn("slow");
    $(document.getElementById(button)).fadeOut(0);
}

function undisplayDetail (parent, i) {
    let recipe = "wine-"+i;
    let button = "button-"+i;

    $(document.getElementById(recipe)).fadeOut(0);
    $(document.getElementById(button)).fadeIn("slow");
}

function sendMessage (name, wineId) {
    let text_area = "text-"+wineId;
    let text = document.getElementById(text_area).value;

    //window.location.href = "http://localhost:80/vin/comment/ajouter?name=" + name + "&text=" + text + "&wineId=" + wineId;
    window.location.href = "http://jusdebagarre.xyz/comment/ajouter?name=" + name + "&text=" + text + "&wineId=" + wineId;

}
