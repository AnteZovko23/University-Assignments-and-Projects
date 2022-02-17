var password_input = document.getElementById("password")
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var len = document.getElementById("length");


password_input.onfocus = function() {
    
    document.getElementById("message").style.display="block";

}

password_input.onblur = function() {
    
    document.getElementById("message").style.display="none";

}

password_input.onkeyup = function() {

    var lowercase = /[a-z]/g;

    if(password_input.value.match(lowercase)) {

        letter.classList.remove("invalid")
        letter.classList.add("valid")

    } else {

        letter.classList.remove("valid")
        letter.classList.add("invalid")

    }

    var uppercase = /[A-Z]/g;

    if(password_input.value.match(uppercase)) {

        capital.classList.remove("invalid")
        capital.classList.add("valid")

    } else {

        capital.classList.remove("valid")
        capital.classList.add("invalid")

    }

    var numbers = /[0-9]/g;

    if(password_input.value.match(numbers)) {

        number.classList.remove("invalid")
        number.classList.add("valid")

    } else {

        number.classList.remove("valid")
        number.classList.add("invalid")

    }


    if(password_input.value.length >= 8) {

        len.classList.remove("invalid")
        len.classList.add("valid")

    } else {

        len.classList.remove("valid")
        len.classList.add("invalid")

    }

}