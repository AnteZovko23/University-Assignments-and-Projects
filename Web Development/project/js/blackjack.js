
// Declare Variables

var images = []
const dealer_cards = ["#card1", "#card2", "#card3", "#card4", "#card5"]
const player_cards = ["#card6", "#card7", "#card8", "#card9", "#card10"]

var current_hand_dealer = []
var current_hand_player = []

var can_be_blackjack = true

var player_total = 0
var dealer_total = 0

var is_double_down = false

var card_value_map = {}


// Get image names
$.get({
    url: "image_names.php",
    async: false,
    data: "JSON",
    success: function(response) {
        
        images = JSON.parse(response)
        
        
        
    }
});

const back_of_card = images.pop()

// Find value of card for each card
images.forEach(card_path => {
    
    var card = card_path.split("/")[2]
    var card_value = card.split("_")[0]

    if(card_value === "A") {
        card_value = 11
    } else if(card_value === "J" || card_value === "Q" || card_value === "K") {
        card_value = 10
    } else {
        
        card_value = parseInt(card_value)
    }


    card_value_map[card_path] = card_value


});

// copy of images
var current_deck = [...images]
var current_dealer_cards = [...dealer_cards]
var current_player_cards = [...player_cards]
var card_value_map_copy = {...card_value_map}

// Start game
function start_game() {
    for(var i = 0; i < 4; i++) {
        
        if(i % 2 === 0) {
        
        
        $(current_dealer_cards[0]).toggleClass("card-animate")
        
        if(i == 2) {
            
            $(current_dealer_cards[0]).attr("src", "" + back_of_card);
            
        } else {
            
            
            var random_image = pickImage();
            current_hand_dealer.push(random_image)
            dealer_total += card_value_map_copy[random_image]

            $(current_dealer_cards[0]).attr("src", "" + random_image);
            
        }
        // sleep for 1 second
        setTimeout(function() {
            $(current_dealer_cards[0]).removeClass("card-animate")
            $(current_dealer_cards[1]).removeClass("card-animate")
            
            
        }, 400);

        current_dealer_cards.splice(0, 1)
         
        } else {

            
            $(current_player_cards[0]).toggleClass("card-animate")
        

            var random_image = pickImage();
          
            current_hand_player.push(random_image)

            // If first two aces
            if(i === 3 && card_value_map_copy[random_image] === 11 && card_value_map_copy[current_hand_player[0]] === 11)
                player_total += 1
            else
            player_total += card_value_map_copy[random_image]
            $(current_player_cards[0]).attr("src", "" + random_image);
            
            // sleep for 1 second
            setTimeout(function() {
                $(current_player_cards[0]).removeClass("card-animate")
                $(current_player_cards[1]).removeClass("card-animate")
                
            }, 400);
            
            current_player_cards.splice(0, 1)
        }
    }

    current_dealer_cards.unshift("#card2")
    // display current totals
    $("#dealer_total").text("Dealer Total: " + dealer_total)
    $("#player_total").text("Player Total: " + player_total)

}

// Calculate current total of cards
function get_total(cards, current_total, player_or_dealer) { 
    
    
  
    var card_value = card_value_map_copy[cards[cards.length-1]]

    if(card_value == 11 && (current_total + card_value) > 21) {

        card_value = 1

    } else if ((current_total + card_value) > 21) {

        var lower_values = 0
        cards.forEach(card => {
            
            if(card_value_map_copy[card] === 11) {

                lower_values += 1

            } else {
                lower_values += card_value_map_copy[card]
            }
        });

        if(player_or_dealer === "p") {
    
            player_total = lower_values
        
        } else if(player_or_dealer === "d") {

        
            dealer_total = lower_values
        
        }
        
        return

    }

    if(player_or_dealer === "p") {
    
        player_total += card_value
    
    } else if(player_or_dealer === "d") {
    
        dealer_total += card_value
    
    }
    
  

}

// Add another card
function add_card(current_cards, current_hand) {

    
    $(current_cards[0]).toggleClass("card-animate")
    
    var random_image = pickImage();
    current_hand.push(random_image)
    $(current_cards[0]).attr("src", "" + random_image);

    
    // sleep for 1 second
    setTimeout(function() {
        $(current_cards[0]).removeClass("card-animate")
        
    }, 400);

    current_cards.splice(0, 1)
    
}


// Reveal hidden card from dealer
function reveal_card_dealer() {

    $(current_dealer_cards[0]).removeAttr("src");
    
    
    $(current_dealer_cards[0]).toggleClass("card-animate")
        
    var random_image = pickImage();
    $(current_dealer_cards[0]).attr("src", "" + random_image);
    current_hand_dealer.push(random_image)

    
    // sleep for 1 second
    setTimeout(function() {
        $(current_dealer_cards[0]).toggleClass("card-animate")
        
    }, 400);
    
    current_dealer_cards.splice(0, 1)

}

// check balance
function check_balance() {

    if(localStorage.getItem("balance") <= 0 || (localStorage.getItem("balance") - $("#usr").val() < 0)) {

        alert("Insufficient Funds!")
        return false

    } else {
        return true
    }


}

// Change current balance
function change_balance(win) {

     
        var bet_amount = eval($("#usr").val())



       // Get value from session storage
       var session_balance = localStorage.getItem("balance")
   
       // Set value in session storage
       if(win && is_double_down){
            localStorage.setItem("balance", eval(session_balance) + eval(bet_amount * 4))
    } else if(win) {
            localStorage.setItem("balance", eval(session_balance) + eval(bet_amount * 2))

       } else {

             localStorage.setItem("balance", eval(session_balance) - eval(bet_amount))

       }
   
       document.getElementById('balance').innerHTML = 
       'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);

}



// NEW GAME
$("#new_game_btn").click(function() {
    
    if(($("#usr").val()).trim()==="") {             
    
        alert("Enter a bet amount!")
    
    } else {



    if(check_balance()) {

        change_balance(false)

        resetGame()
        shuffle_cards()
        dealer_total = 0
        player_total = 0
        current_hand = []
    
     
    
        $("#new_game_btn").attr("disabled", "disabled")
        $("#usr").attr("disabled", "disabled")

        $("#hit_btn").removeAttr("disabled")
        $("#stand_btn").removeAttr("disabled")
        $("#double_btn").removeAttr("disabled")
        
        
        start_game()
    
        is_game_over(false)

    }

   
   
}

})

// When hit button is clicked
$("#hit_btn").click(function() {

    add_card(current_player_cards, current_hand_player)
    $("#double_btn").attr("disabled", "disabled")
    updatePlayerTotal()
    if(player_total == 21)
        is_game_over(true)
    else
        is_game_over(false)



})

// Stand
$("#stand_btn").click(function() {

    // disable hit and stand buttons
    $("#hit_btn").attr("disabled", "disabled")
    $("#stand_btn").attr("disabled", "disabled")
    $("#double_btn").attr("disabled", "disabled")

    card_value_map_copy = {...card_value_map}
    // reveal dealer cards
    reveal_card_dealer()

    // update dealer total
    updateDealerTotal()


    while (dealer_total <= 16){
        add_card(current_dealer_cards, current_hand_dealer)
        updateDealerTotal()
        console.log(dealer_total)
    }
    is_game_over(true)
})

// Double Down
$("#double_btn").click(function() {

    if(localStorage.getItem("balance") <= 0 || (eval(localStorage.getItem("balance")) - eval($("#usr").val() * 2) < 0)) {
        
        alert("Insufficient Funds!")
        
    } else {

        is_double_down = true
        // disable hit and stand buttons
        $("#hit_btn").attr("disabled", "disabled")
        $("#stand_btn").attr("disabled", "disabled")
        $("#double_btn").attr("disabled", "disabled")
        change_balance(false)
        
        // add one more card to player
        add_card(current_player_cards, current_hand_player)
        updatePlayerTotal()
     
        // disable double down button
        $("#double_btn").attr("disabled", "disabled")
        
   // disable hit and stand buttons
   $("#hit_btn").attr("disabled", "disabled")
   $("#stand_btn").attr("disabled", "disabled")

   card_value_map_copy = {...card_value_map}
   // reveal dealer cards
   reveal_card_dealer()

   // update dealer total
   updateDealerTotal()


   while (dealer_total <= 16){
       add_card(current_dealer_cards, current_hand_dealer)
       updateDealerTotal()
   }


    // check if player has won or lost
    is_game_over(true)
    }

})

// reshuffle cards
function shuffle_cards() {

    current_deck = [...images]
    current_dealer_cards = [...dealer_cards]
    current_player_cards = [...player_cards]
    

}


// Update total of the player
function updatePlayerTotal() {

    get_total(current_hand_player, player_total, "p")


}

// Update total of the dealer
function updateDealerTotal() {

    get_total(current_hand_dealer, dealer_total, "d")



}

// reset buttons
function reset_buttons() {


    $("#new_game_btn").removeAttr("disabled")
    $("#usr").removeAttr("disabled")
    $("#hit_btn").attr("disabled", "disabled")
    $("#stand_btn").attr("disabled", "disabled")
    $("#double_btn").attr("disabled", "disabled")

    

}

// Check game status
function is_game_over(done) {



    // display current totals
    $("#dealer_total").text("Dealer Total: " + dealer_total)
    $("#player_total").text("Player Total: " + player_total)
    
    if(dealer_total > 21) {

        
        $("#dealer_total").html("Dealer busts!")
        $("#dealer_total").css("color", "lightgreen")
        change_balance(true)
        reset_buttons()
        

    } else if(player_total > 21) {


        $("#player_total").html("You bust!")
        $("#player_total").css("color", "red")
        reset_buttons()
        


    } else if(player_total == 21 && can_be_blackjack) {

        reveal_card_dealer()
        updateDealerTotal()

        if(dealer_total != 21) {
            
            $("#player_total").html("BLACKJACK!")
            $("#player_total").css("color", "black")
            var bet_amount = $("#usr").val()
            localStorage.setItem("balance", eval(localStorage.getItem("balance")) + eval(bet_amount * 2) + eval(bet_amount * 0.5))
            document.getElementById('balance').innerHTML = 
            'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);
            reset_buttons()

            
        } else {

            $("#player_total").html("Tie!")
            $("#player_total").css("color", "blue")
            var bet_amount = $("#usr").val()
            localStorage.setItem("balance", eval(localStorage.getItem("balance")) + eval(bet_amount))
            document.getElementById('balance').innerHTML = 
            'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);
            reset_buttons()

     
            
        }


    } else if(dealer_total == 21) {

        $("#dealer_total").html("Dealer wins!")
        $("#dealer_total").css("color", "red")
        reset_buttons()
        

    } 


    else if(current_hand_player.length === 5 || done ) {

        if(current_hand_player.length === 5){
          // disable hit and stand buttons
            $("#hit_btn").attr("disabled", "disabled")
            $("#stand_btn").attr("disabled", "disabled")
            $("#double_btn").attr("disabled", "disabled")

            card_value_map_copy = {...card_value_map}
            // reveal dealer cards
            reveal_card_dealer()

            // update dealer total
            updateDealerTotal()


            while (dealer_total <= 16){
                add_card(current_dealer_cards, current_hand_dealer)
                updateDealerTotal()
                console.log(dealer_total)
            }
        }
        if(player_total > dealer_total) {
    
            $("#player_total").html("You win!")
            $("#player_total").css("color", "lightgreen")
            change_balance(true)
            reset_buttons()

            
    
        } else if(player_total < dealer_total) {
    
            $("#dealer_total").html("Dealer wins!")
            $("#dealer_total").css("color", "red")
            reset_buttons()
            
    
        } else {
    
            $("#player_total").html("Tie!")
            $("#player_total").css("color", "blue")
            var bet_amount = eval($("#usr").val())
            localStorage.setItem("balance", eval(localStorage.getItem("balance")) + eval(bet_amount))
            document.getElementById('balance').innerHTML = 
            'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);
            reset_buttons()
     
            
    
        }
    } 

    can_be_blackjack = false

}
   





// pick a random image
function pickImage() {

    var random = Math.floor(Math.random() * current_deck.length)
    var image = current_deck[random]
    // remove image from deck
    current_deck.splice(random, 1)


    return image

}

// Reset game
function resetGame() {

    current_hand_dealer = []
    current_hand_player = []

    can_be_blackjack = true

    is_double_down = false

    for(var i = 0; i < 5; i++) {
        $(dealer_cards[i]).removeAttr("src");
        $(player_cards[i]).removeAttr("src");
    }

    $("#player_total").css("color", "white")
    $("#dealer_total").css("color", "white")

    player_total = 0
    dealer_total = 0
    card_value_map_copy = {...card_value_map}


}

// Check if entered value is a number
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        
        if(charCode === 46) 
            return true

        alert("Enter a proper value")
        return false;

    }
    return true;
}