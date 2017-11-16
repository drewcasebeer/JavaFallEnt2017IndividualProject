$(document).ready(function() {
    var path = window.location.pathname;
    path = path.substring(1);

    if(path == "login") {
        $('#login').addClass('active');
    } else if(path == "movielist") {
        $('#movielist').addClass('active');
    } else {
        $('#home').addClass('active');
    }
});