

var h1 = document.querySelector("h1");
var h2 = document.querySelector("h2");
var body = document.querySelector("body")

function changeColor(){
  var red = Math.random() * 255
  var green = Math.random() * 255
  var blue = Math.random() * 255
  body.style.backgroundColor = "rgb("+red+","+green+","+blue+")";
}


var myTimer = null

function goCrazy() {
       

   myTimer = setInterval(changeColor, 500);

    h1.setAttribute("style", "animation:red-to-black 4s linear 0s infinite alternate")
    h2.setAttribute("style", "animation:red-to-black 4s linear 0s infinite alternate")

}   

function goNormal() {

  clearInterval(myTimer)
  body.style.backgroundColor = "steelblue";
  h1.setAttribute("style", "transform:translate(0px, 0px)")
  h2.setAttribute("style", "transform:translate(0px, 0px)")
}
