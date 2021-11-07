var counter = 0;

var counterElement = document.getElementById("counter")

// document.querySelector("#counter").innerText = counter;

// document.getElementById("incr-btn").addEventListener("click", function() {

//     counter++;
//     document.querySelector("#counter").innerText = counter;


// })

var results = document.getElementById("previous-entries")


function increment() {

    counter++
    counterElement.innerText = counter

}

function reset() {

    counter = 0;
    counterElement.innerText = counter;

}  

function save() {

    var temp = counterElement.innerText;

    results.textContent += temp + " - ";


}