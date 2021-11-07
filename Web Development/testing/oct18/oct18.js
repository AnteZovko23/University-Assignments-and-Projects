const inputBtn = document.getElementById("input-btn")

const saveCurTabBtn = document.getElementById("save-btn")

const deleteBtn = document.getElementById("delete-btn")

let inputArr = []

const inputField = document.getElementById("input-field")

const listTag = document.getElementById("listEl")

// check if there are items in local storage
let fromLocal = JSON.parse(localStorage.getItem("arr"))
if (fromLocal) {
    inputArr = fromLocal
    render(inputArr)
}

inputBtn.addEventListener("click", function () {
    // console.log("button clicked using addEventListener")

    // Add item to the array
    inputArr.push(inputField.value)

    // Save the array to local storage
    localStorage.setItem("arr", JSON.stringify(inputArr))

    // Clear the input field
    inputField.value = ""

    // Render the item to the ul
    render(inputArr)

})

// write an event listener to grab the current chrome tab url and save it in our bookmark app
saveCurTabBtn.addEventListener("click", function () {
    chrome.tabs.query({active: true, currentWindow: true}, function (tabs) {
        // push the current chrome tab url into our array
        inputArr.push(tabs[0].url)

        // save the updated array into local storage
        localStorage.setItem("arr", JSON.stringify(inputArr))

        render(inputArr)
    })
})

deleteBtn.addEventListener("click", function () {
    inputArr = []
    localStorage.clear()
    deleteAll()
})

function render(currArr) {
    listTag.innerHTML = ""

    for (let i = 0; i < currArr.length; i++) {
        listTag.innerHTML += `
            <li><a target="_blank" href="${currArr[i]}">${currArr[i]}</a></li>
        `
    }
}

function deleteAll() {
    listTag.innerHTML = ""
}