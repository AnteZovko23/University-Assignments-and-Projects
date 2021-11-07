
<!--

    Name: Ante Zovko
    Date: November 7th, 2021
    Course Number: 499 001
    Quarter: Fall

    Project 1

-->



<!-- Header -->
<?php require("components/head.inc.php"); ?>
<!-- End Header -->

<!-- Require menu -->
<?php require("components/menu.inc.php"); ?>

    
    <header class="page-header gradient">
    
    <div class="container pt-3">

      <div class="row align-items-center justify-content-center">
        <div class="col text-center">

          <h1 class="neonText">
            Casino Games Inc    
          </h1>
          
          <h2 class="neonText">Life is a Game</h2> 
          
          
          <p class="neonText" style="animation: none">
            <strong>Casino Games Inc</strong> is a gaming company that offers a wide range of online casino games.
          

        </div>
        
      </div>
 
        
      </div>

    </div>
    <!--https://getwaves.io/-->
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#000" fill-opacity="1" d="M0,128L60,122.7C120,117,240,107,360,112C480,117,600,139,720,170.7C840,203,960,245,1080,240C1200,235,1320,181,1380,154.7L1440,128L1440,320L1380,320C1320,320,1200,320,1080,320C960,320,840,320,720,320C600,320,480,320,360,320C240,320,120,320,60,320L0,320Z"></path></svg>
  </header>
  <script>

      if(localStorage.key(0) == null){
        // Set local storage 
        localStorage.setItem('balance', 100000);
      }

    </script>


<!-- Require footer -->
<?php require("components/footer.inc.php"); ?>

