
function displayDetail (parent, i) {
    let recipe = "recipe-"+i;
    let button = "button-"+i;

    $(document.getElementById(recipe)).fadeIn("slow");
    $(document.getElementById(button)).fadeOut(0);
}

function undisplayDetail (parent, i) {
    let recipe = "recipe-"+i;
    let button = "button-"+i;

    $(document.getElementById(recipe)).fadeOut(0);
    $(document.getElementById(button)).fadeIn("slow");
}

function sendMessage (name, recipeId) {
    let text_area = "text-"+recipeId;
    let text = document.getElementById(text_area).value;

    //window.location.href = "http://localhost:80/comment/ajouter?name=" + name + "&text=" + text + "&recipeId=" + recipeId;
    window.location.href = "http://jusdebagarre.xyz/comment/ajouter?name=" + name + "&text=" + text + "&recipeId=" + recipeId;

}
