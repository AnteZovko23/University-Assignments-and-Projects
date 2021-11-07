
var input_button = document.getElementById('input-btn')
var delete_button = document.getElementById('delete-btn')
var input_array = []
var input_field = document.getElementById('input-field')
var list = document.getElementById('list')


var array_from_local = JSON.parse(localStorage.getItem('Array'))

if(array_from_local) {

    

}



input_button.addEventListener("click", function() {

    input_array.push(input_field.value)

    localStorage.setItem("Array", JSON.stringify(input_array))

    input_field.value = ""

    render(input_array)




})

function render(current_array) {

    list.innerHTML = ""

    current_array.forEach(element => {
        
        list.innerHTML += `

            <li><a target="_blank" href="${element}">${element}</a></li>


        `
    });

}
