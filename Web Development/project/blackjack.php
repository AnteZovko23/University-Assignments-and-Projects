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
            <div class="card">
                
                <img class="card-img-top" id="card1" src="..." alt="">
        
            </div>
            </div>

        <div class="col">
            <div class="card">
                <img class="card-img-top" id="card2" src="..." alt="">
        
             </div>
        </div>

        <div class="col">
            <div class="card">
                
                <img class="card-img-top" id="card3" src="..." alt="">
        
            </div>
            </div>
            
        <div class="col">
            <div class="card">
                <img class="card-img-top" id="card4" src="..." alt="">
        
             </div>
        </div>

        <div class="col">
            <div class="card">
                
                <img class="card-img-top" id="card5" src="..." alt="">
        
            </div>
            </div>
    
            

</div>



<!-- 2 rows of cards -->
<div class="row blackjack-row">

<span class="label">Your Hand</span>
        <div class="col">
            <div class="card">
                
                <img class="card-img-top" id="card6" src="..." alt="">
        
            </div>
        </div>
        
        <div class="col">
            <div class="card">
                <img class="card-img-top" id="card7" src="..." alt="">
                
        
            </div>
            

        </div>

        <div class="col">
            <div class="card">
                
                <img class="card-img-top" id="card8" src="..." alt="">
        
            </div>
            </div>
            
        <div class="col">
            <div class="card">
                <img class="card-img-top" id="card9" src="..." alt="">
        
            </div>
        </div>

        <div class="col">
            <div class="card">
                
                <img class="card-img-top" id="card10" src="..." alt="">
        
            </div>
            </div>
    
            
</div>

<div class="row">
<span class="label" id="dealer_total" style="text-align:left">Dealer Total: </span>
            <span class="label" id="player_total" style="text-align:left">Player Total: </span>
</div>
</div>

<div class="container">

<div class="row blackjack-row">
    <!-- play again button -->
    <div class="col">
        <button type="button" id="new_game_btn" class="btn btn-primary btn-lg">Play</button>
    </div>
    <div class="col">
        <button disabled id="hit_btn" type="button" class="btn btn-primary btn-lg">Hit</button>
    </div>
    <div class="col">

        <button disabled id="stand_btn" type="button" class="btn btn-primary btn-lg">Stand</button>

    </div>

    <div class="col">
        <button disabled id="double_btn" type="button" class="btn btn-primary btn-lg">Double</button>
    </div>

    <!-- bet amount -->
    <div class="col">

            <div class="form-group">
        <label for="usr" style="color:white">Bet Amount:</label>
        <input type="number" class="form-control" placeholder="20" value="20" onkeypress="return isNumberKey(event)" id="usr">
        </div>

    </div>

</div>

</div>


<script src="js/blackjack.js"></script>


<!-- Require footer -->
<?php require("components/footer.inc.php"); ?>

