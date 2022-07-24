

$(function() {

    document.getElementById("next").addEventListener("click", () => {
      next();  
    })
    document.getElementById("prev").addEventListener("click", () => {
        prev();
    })
})
function test(){
    window.alert("working function");
}

function next(){
    window.location.replace("prearr.html");
}
function prev(){
    window.location.replace("prereq.html");
}
function cancel(){
    localStorage.setItem("startprog", "false");
    window.location.replace("home.html");
  }
//
var country = localStorage.getItem("country");
  var xmlhttp = new XMLHttpRequest();
  var url = 'https://api.covid19api.com/country/'+country;


  xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          var myArr = JSON.parse(this.responseText);
          document.getElementById("confirmed").innerHTML = myArr[myArr.length - 1].Confirmed;
          document.getElementById("activecases").innerHTML = myArr[myArr.length - 1].Active;
          document.getElementById("recovered").innerHTML = myArr[myArr.length - 1].Recovered;
          document.getElementById("death").innerHTML = myArr[myArr.length - 1].Deaths;
          document.getElementById("dateUpdated").innerHTML = myArr[myArr.length - 1].Date;
      }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();