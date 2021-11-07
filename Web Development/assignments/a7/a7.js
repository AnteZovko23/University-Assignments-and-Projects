var table = document.getElementById("table")

document.getElementById("delete_btn").addEventListener("dblclick", 

    function deleteAll() {

        table.innerHTML =  `
            <tr>

                <th>FIRST NAME</th>
                <th>LAST NAME</th>
                <th>GENDER</th>
                <th>YEAR</th>

            </tr>
            `

})

document.getElementById("submit_btn").addEventListener("click", 
    function handleInput() {
  
        var elements = document.querySelectorAll(".input")
        var radio_value = document.querySelectorAll('input[type="radio"]:checked');
        
        console.log(radio_value)


        if(elements[0].value.trim() !== "" && elements[1].value.trim() !== ""){
        table.innerHTML += `
        <tr>
            <td>${elements[0].value}</td>
            <td>${elements[1].value}</td>
            <td>${radio_value[0].value}</td>
            <td>${elements[2].value}</td>
        </tr>
            `
        }
})



