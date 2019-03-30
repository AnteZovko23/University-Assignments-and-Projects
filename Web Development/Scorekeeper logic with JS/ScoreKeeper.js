var playerOne = document.querySelector("#p1");
var playerTwo = document.querySelector("#p2");
var reset = document.querySelector("#reset");
var numInput = document.querySelector("input");
var p1Display = document.querySelector("#p1Display");
var p2Display = document.querySelector("#p2Display");
var condition = document.querySelector("#condition");


var p1Score = 0;
var p2Score = 0;

var gameOver = false;
var winningScore = 5;

playerOne.addEventListener("click", function(){
if(!gameOver)
{
   p1Score++;
   if(p1Score === winningScore){
       gameOver = true;
       p1Display.classList.add("winner");
   }
   p1Display.textContent = p1Score;

}
   

});

playerTwo.addEventListener("click", function(){

if(!gameOver)
{
    p2Score++;
    if(p2Score === winningScore)
    {
        gameOver = true;
        p2Display.classList.add("winner");

    }
    p2Display.textContent = p2Score;
}
    
    
});

reset.addEventListener("click", function(){
    reset1();
});

function reset1() {
    p1Score = 0;
    p2Score = 0;

    p1Display.textContent = 0;
    p2Display.textContent = 0;
    p1Display.classList.remove("winner");
    p2Display.classList.remove("winner");

    gameOver = false;
}

    
numInput.addEventListener("change", function(){
    condition.textContent = numInput.value;

    winningScore = Number(numInput.value);
    reset1();
})