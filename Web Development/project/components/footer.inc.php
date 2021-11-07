  
  <footer class="footer">
    <div class="container-fluid text-center">
        <span class="label" id="balance">
            Current Balance: 
        </span>
      </div>


  </footer>
  
    <!-- JavaScript -->
    <script>
      document.getElementById('balance').innerHTML = 
          // localStorage.getItem('player');
          'Current Balance: $' + localStorage.getItem('balance');

    </script>
  <script src="js/bootstrap.min.js"></script>
  </body>



</html>
