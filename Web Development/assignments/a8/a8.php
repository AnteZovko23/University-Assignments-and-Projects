<!DOCTYPE html>

<!--

    Name: Ante Zovko
    Date: October 29
    Course Number: 499 001
    Quarter: Fall

    Assignment 8

-->

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="description" content="Assignment 8">

        <title>Assignment 8</title>
        <link rel="stylesheet" href="a8.css">

    </head>



    <body>

        <form action="processing.php" method="GET">
        <div id="form-div">
            
            <input required class="input" id="first_number" name="first_number" type="text" placeholder="First Number">
            
            <br>
            <br>
            
            <input required class="input" id="second_number" name="second_number" type="text" placeholder="Second Number">
            
            <br>
            <br>

            <label for="sum">Sum</label>
            <input type="checkbox" id="checkbox1" name="sum" value="sum">
            <label for="avg">Average</label>

            <input type="checkbox" id="checkbox2" name="avg" value="avg">
            
            <br>
            <br>

            <div id="button">
                <button class="button" type="submit" id="submit_btn" value="Submit" >Submit</button>
            
        </div>





    </form>
    </body>


</html>
