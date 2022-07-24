
  var startprog = localStorage.getItem("startprog");
  if (startprog =="true"){
    window.location.replace("wait.html");
  }


  function submit(){
    var date = document.getElementById("month").value+"-"+document.getElementById("day").value+"-"+document.getElementById("year").value;
    saveData(document.getElementById("destination").value, document.getElementById("flightnumber").value,date);
    getCoor(document.getElementById("destination").value);
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



function saveData(dest, flnb, depdate){
  localStorage.setItem("dest", dest);
  localStorage.setItem("flnb", flnb);
  localStorage.setItem("depdate", depdate);
  localStorage.setItem("startprog", "true");
}



function getAddress(long,lat){
  var url = "http://api.positionstack.com/v1/reverse?access_key=7f7c4fea3b40b5e4be83c129f4b6bcfd&query="+long+","+lat;
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        var a =  myArr["data"];
        var b = a[0];
        var country = b.country;
        console.log(country);
        document.getElementById("destination").value = country;
    }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();
}

function getCoor(address){
  var url = "http://api.positionstack.com/v1/forward?access_key=7f7c4fea3b40b5e4be83c129f4b6bcfd&query="+address;
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        var a =  myArr["data"];
        var b = a[0];
        var long = b.longitude;
        var lat = b.latitude;
        localStorage.setItem("long", lat);
        localStorage.setItem("lat", long);
        console.log("getCoor:" + localStorage.getItem("long")+","+localStorage.getItem("lat"));
    }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();
  window.location.replace("prereq.html");
}