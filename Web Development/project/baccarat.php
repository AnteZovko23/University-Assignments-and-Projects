<!-- Header -->
<?php require("components/head.inc.php"); ?>
<!-- End Header -->

<!-- Require menu -->
<?php require("components/menu.inc.php"); ?>

  
<div class="container" id="blackjack-container">
    
    <!-- 2 rows of cards -->
    <div class="row blackjack-row">
        <span class="label">Dealer's Hand</span>
        <div class="col">
            <div class="card" style="width:40%">
                
                <img class="card-img-top" id="card1" src="..." alt="">
        
            </div>
            </div>

        <div class="col">
            <div class="card" style="width:40%">
                <img class="card-img-top" id="card2" src="..." alt="">
        
             </div>
        </div>

        <div class="col">
            <div class="card" style="width:40%">
                
                <img class="card-img-top" id="card3" src="..." alt="">
        
            </div>
            </div>
    
            

</div>



<!-- 2 rows of cards -->
<div class="row blackjack-row">

<span class="label">Player's Hand</span>
        <div class="col">
            <div class="card" style="width:40%">
                
                <img class="card-img-top" id="card4" src="..." alt="">
        
            </div>
        </div>
        
        <div class="col">
            <div class="card" style="width:40%">
                <img class="card-img-top" id="card5" src="..." alt="">
                
        
            </div>
            

        </div>

        <div class="col">
            <div class="card" style="width:40%">
                
                <img class="card-img-top" id="card6" src="..." alt="">
        
            </div>
            </div>
            
            
</div>

<div class="row">
        <span class="label" id="dealer_total" style="text-align:left">Banker Total: </span>
        <span class="label" id="player_total" style="text-align:left">Player Total: </span>
</div>
</div>

<div class="container">

<div class="row blackjack-row">
      <!-- play again button -->
      <div class="col">
        <button type="button" id="deal_btn" class="btn btn-primary btn-lg">Deal</button>
    </div>

    <!-- bet amount -->
    <div class="col">

            <div class="form-group">
        <label for="player_bet" style="color:white">Player Bet:</label>
        <input type="number" class="form-control" placeholder="20" value="5" onkeypress="return isNumberKey(event)" id="player_bet">
        </div>

    </div>
      <!-- bet amount -->
      <div class="col">

<div class="form-group">
<label for="dealer_bet" style="color:white">Banker Bet:</label>
<input type="number" class="form-control" placeholder="20" value="0" onkeypress="return isNumberKey(event)" id="dealer_bet">
</div>

</div>

  <!-- bet amount -->
  <div class="col">

<div class="form-group">
<label for="tie_bet" style="color:white">Tie Bet:</label>
<input type="number" class="form-control" placeholder="20" value="0" onkeypress="return isNumberKey(event)" id="tie_bet">
</div>

</div>
</div>

</div>


<script src="js/baccarat.js"></script>


<!-- Require footer -->
<?php require("components/footer.inc.php"); ?>


