
// Declare Variables
var images = []
const dealer_cards = ["#card1", "#card2", "#card3"]
const player_cards = ["#card4", "#card5", "#card6"]

var current_hand_dealer = []
var current_hand_player = []


var player_total = 0
var dealer_total = 0


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
        card_value = 1
    } else if(card_value === "J" || card_value === "Q" || card_value === "K" || card_value === "10") {
        card_value = 0
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

// When deal button is clicked
$("#deal_btn").click(function() {


    // Make sure a bet amount is entered
    if(($("#player_bet").val()).trim()==="" || ($("#dealer_bet").val()).trim()==="" || ($("#tie_bet").val()).trim()==="") {             
    
        alert("Enter a bet amount!")
    
    } else {


    // If user has sufficient funds
    if(check_balance()) {

        var total_amount = eval($("#player_bet").val()) + eval($("#dealer_bet").val()) + eval($("#tie_bet").val())
        localStorage.setItem("balance", eval(localStorage.getItem("balance")) - total_amount)
        document.getElementById('balance').innerHTML = 
        'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);

        resetGame()
        shuffle_cards()
        dealer_total = 0
        player_total = 0
        current_hand = []
    
        
        
        start_game()
    
        determine_winner()

    }

    }   

})

// Start the game
function start_game() {
    for(var i = 0; i < 4; i++) {
        
        if(i % 2 === 0) {
        
        
        $(current_dealer_cards[0]).toggleClass("card-animate")
        
       
            
        
        var random_image = pickImage();
        current_hand_dealer.push(random_image)
        dealer_total += card_value_map_copy[random_image]

        $(current_dealer_cards[0]).attr("src", "" + random_image);
            
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

    player_total = eliminate_double_digit(player_total)
    dealer_total = eliminate_double_digit(dealer_total)

    // display current totals
    $("#dealer_total").text("Dealer Total: " + dealer_total)
    $("#player_total").text("Player Total: " + player_total)

}


// Check all the rules
function determine_winner() {

    
    if(player_total === 8 || player_total === 9) {

        
        $("#player_total").html("Player wins")
        $("#player_total").css("color", "lightgreen")
        change_balance("player_win")

        

    } else {


        if(player_total === 6 || player_total === 7) {
    
            
            evaluate_dealer_cards()
    
    
        } else if(player_total >= 0 && player_total <= 5) {
    
            if(dealer_total == 8 || dealer_total == 9) {
    
                $("#dealer_total").html("Dealer wins")
                $("#dealer_total").css("color", "red")
                change_balance("dealer_win")
    

    
            } else {
                
                add_card(current_player_cards, current_hand_player, 'p')
                evaluate_dealer_cards()
    
            }
            
    
    
        }
    } 
    


}

// Evaluate dealer rules
function evaluate_dealer_cards() {

    if(dealer_total >= 0 && dealer_total <= 2){

        if(!(current_hand_player.map(evaluate_card_values).includes(8) && current_hand_player.map(evaluate_card_values).includes(9))) {
            add_card(current_dealer_cards, current_hand_dealer, 'd')
        }

    } else if(dealer_total === 3) {

        if(card_value_map_copy[current_hand_player[2]] !== 8) {

            add_card(current_dealer_cards, current_hand_dealer, 'd')

        }

    } else if(dealer_total === 4) {

        if(card_value_map_copy[current_hand_player[2]] >= 2 && card_value_map_copy[current_hand_player[2]] <= 7) {
            
            add_card(current_dealer_cards, current_hand_dealer, 'd')
            
        }
    } else if(dealer_total === 5) {

        if(card_value_map_copy[current_hand_player[2]] >= 4 && card_value_map_copy[current_hand_player[2]] <= 7) {
            
            add_card(current_dealer_cards, current_hand_dealer, 'd')
            
        }

    } else if (dealer_total === 6) {

        if(card_value_map_copy[current_hand_player[2]] >= 6 && card_value_map_copy[current_hand_player[2]] <= 7) {
            
            add_card(current_dealer_cards, current_hand_dealer, 'd')
            
        }

    } else if (dealer_total === 7) {



    } else if (dealer_total === 8 || dealer_total === 9) {

        $("#dealer_total").html("Dealer wins")
        $("#dealer_total").css("color", "red")
        change_balance("dealer_win")


    }

    evaluate_totals()

}


   

// Pick winner
function evaluate_totals() {

    if(player_total > dealer_total) {

        $("#player_total").html("Player wins")
        $("#player_total").css("color", "lightgreen")
        change_balance("player_win")

    } else if(player_total < dealer_total) {

        $("#dealer_total").html("Dealer wins")
        $("#dealer_total").css("color", "red")
        change_balance("dealer_win")


    } else {

        $("#player_total").html("Tie")
        $("#player_total").css("color", "blue")
        change_balance("tie")



    }


}

// Eliminate first digit of a double digit number 
function eliminate_double_digit(given_number) {

    return given_number % 10


}


// Add a card
function add_card(current_cards, current_hand, player_or_dealer) {

    
    $(current_cards[0]).toggleClass("card-animate")
    
    var random_image = pickImage();
    current_hand.push(random_image)
    $(current_cards[0]).attr("src", "" + random_image);
    
    if(player_or_dealer === 'p'){
        player_total += card_value_map_copy[random_image]
        player_total = eliminate_double_digit(player_total)
    } else if (player_or_dealer === 'd') {

        dealer_total += card_value_map_copy[random_image]
        dealer_total = eliminate_double_digit(dealer_total)

    }
    
    // sleep for 1 second
    setTimeout(function() {
        $(current_cards[0]).removeClass("card-animate")
        
    }, 400);

    current_cards.splice(0, 1)
    
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


    for(var i = 0; i < 3; i++) {
        $(dealer_cards[i]).removeAttr("src");
        $(player_cards[i]).removeAttr("src");
    }

    $("#player_total").css("color", "white")
    $("#dealer_total").css("color", "white")

    player_total = 0
    dealer_total = 0
    card_value_map_copy = {...card_value_map}


}

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

// reshuffle cards
function shuffle_cards() {

    current_deck = [...images]
    current_dealer_cards = [...dealer_cards]
    current_player_cards = [...player_cards]
    

}

// reset buttons
function reset_buttons() {


    $("#deal_btn").removeAttr("disabled")
    $("#player_bet").removeAttr("disabled")
    $("#dealer_bet").removeAttr("disabled")
    $("#tie_bet").removeAttr("disabled")
  

    

}

// Get card value for given card name
function evaluate_card_values(card_name) {

    return card_value_map_copy[card_name]

}

// check balance
function check_balance() {

    var total_amount = eval($("#player_bet").val()) + eval($("#dealer_bet").val()) + eval($("#tie_bet").val())


    if(localStorage.getItem("balance") <= 0 || (eval(localStorage.getItem("balance")) - total_amount < 0)) {

        alert("Insufficient Funds!")
        return false

    } else {
        return true
    }


}

// Change current balance
function change_balance(outcome) {


    var player_bet = eval($("#player_bet").val())
    var dealer_bet = eval($("#dealer_bet").val())
    console.log(dealer_bet)
    var tie_bet = eval($("#tie_bet").val())

  

    if(outcome === "player_win") {
    
        localStorage.setItem("balance", eval(localStorage.getItem("balance")) + (player_bet * 2))

    
    } else if(outcome === "dealer_win") {

        localStorage.setItem("balance", eval(localStorage.getItem("balance")) + ((dealer_bet) + (dealer_bet * 0.95)))


    } else if(outcome === "tie") {

        localStorage.setItem("balance", eval(localStorage.getItem("balance")) + (tie_bet * 8))


    }

   
    document.getElementById('balance').innerHTML = 
    'Current Balance: $' + Number(localStorage.getItem('balance')).toFixed(2);

}

