

$(function() {
    var dest = localStorage.getItem("dest");
    var flnb = localStorage.getItem("flnb");
    var depdate = localStorage.getItem("depdate");
    var startProg = localStorage.getItem("startprog");
    document.getElementById("destination").innerHTML = dest;
    document.getElementById("depdate").innerHTML = depdate;
    if(flnb!=null){
      document.getElementById("flightnumber").innerHTML = flnb;
    } else {
      document.getElementById("flightnumber").innerHTML = "None";
    }

   
    document.getElementById("next").addEventListener("click", () => {
      next();  
    })
    document.getElementById("prev").addEventListener("click", () => {
        prev();
    })

    menuStat();
    flightStat();
    destConf();
})
function test(){
    window.alert("working function");
}

function next(){
    window.location.replace("arrive.html");
}
function prev(){
    window.location.replace("prearr.html");
}
function cancel(){
  localStorage.setItem("startprog", "false");
  window.location.replace("home.html");
}

//menu stats
function menuStat(){
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
}

//destination confirmed

function destConf(){
  var country2 = localStorage.getItem("dest");
  var xmlhttp = new XMLHttpRequest();
  var url2 = 'https://api.covid19api.com/country/'+country2;

  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        console.log(url2);
        document.getElementById("destconf").innerHTML = myArr[myArr.length - 1].Confirmed;
        
    }
  };
  xmlhttp.open("GET", url2, true);
  xmlhttp.send();
}

function flightStat(){
  //flight status
  var xmlhttp = new XMLHttpRequest();
  var iata = localStorage.getItem("flnb");
  var url3 = 'http://api.aviationstack.com/v1/flights?access_key=698c2f6e71dbeb406392acdd9fdbfc73&flight_iata=' + iata;


  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        var data = myArr["data"];
        document.getElementById("flstat").innerHTML = data[0].flight_status;
    }
  };
  xmlhttp.open("GET", url3, true);
  xmlhttp.send();
}

