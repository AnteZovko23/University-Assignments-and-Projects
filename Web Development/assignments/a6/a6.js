

var text = document.querySelectorAll(".text")


function submit_form() {
    
    var textbox_input = document.getElementById("textbox").value
    text[0].textContent = "My name is: " + textbox_input

    var radio_input = document.querySelectorAll(".radio_input")
    for(var i = 0; i < radio_input.length; i++) {

        if(radio_input[i].checked){
            
            text[1].textContent = "My Gender is: " + radio_input[i].value
            break
        }
        
    }


    var checkbox_input = document.getElementById("education_dropdown")
    text[2].textContent = "My Education Level is: " + checkbox_input.value

}