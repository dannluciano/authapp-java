var newUserButton = document.querySelector("#new-user");
newUserButton.addEventListener('click', function() {
    var newUserForm = document.querySelector("#user-form");
    var display = newUserForm.style.display;
    if (display === "block") {
        newUserForm.style.display = "none";
    } else {
        newUserForm.style.display = "block";
    }
}, false);


