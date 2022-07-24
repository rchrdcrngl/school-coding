
    function signup(){
        sessionStorage.setItem("login", "true");
        var country = document.getElementById("country").value;
        localStorage.setItem("country", country);
        window.location.replace("home.html");
    }