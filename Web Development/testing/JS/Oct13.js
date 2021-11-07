

/*** Concat arrays ****/
var myArr_1 = [1, 2, 3]
var myArr_2 = [4, 5, 6]

var myArr_3 = [...myArr_1, ...myArr_2]

// console.log(myArr_3)

/************************ */

/* SUM INTEGERS */

var sum = 0

for(let n of myArr_3) {

    sum += n


}

// console.log(sum)

/******************* */

function myReduceFunction(acc, cv) {

    return(acc+cv)

}

var sum_2 = myArr_3.reduce(myReduceFunction)

// console.log(sum_2)


/******* Call function and define it */

var sum_3 = myArr_3.reduce( (acc, cv) => {return(acc + cv)}, 0)

// console.log(sum_3)


/************************* */

// My Solution
new_arr = []
// Use reduce to duplicate the contents in an array
// myArr_3.reduce((acc, cv) => {new_arr.push(cv)
// new_arr.push(cv)}, 0)


// Other Solution
new_arr = myArr_3.reduce((acc, cv) => {return [...acc, cv, cv]}, [])
// console.log(new_arr)
// console.log(copy_myArr_3)
/************************* */


var resultElement = document.querySelector("#result")
resultElement.textContent = "I have been grabbed!"

var paragraphElements = document.querySelectorAll("p")

var pTest = Array.from(paragraphElements)

pTest.reduce((acc,cv) => {cv.textContent = "abc"}, 0)

// Only inputs you want
// var formElements = document.querySelectorAll(".myForm input")