<?php


    if($_GET["first_number"] &&  $_GET["second_number"]) {
	
	if(isset($_GET["sum"])) {
	
	
	        $sum = floatval($_GET["first_number"]) + floatval($_GET["second_number"]);
		echo "<h1>The sum is: ".$sum."</h1>";

	}
	if(isset($_GET["avg"])) {

		$avg = (floatval($_GET["first_number"] + floatval($_GET["second_number"]))/2);
		echo "<h1>The average is: ".$avg."</h1>";
	}

	if(!(isset($_GET["sum"]) || isset($_GET["avg"]))) {

		echo "<h1>Nothing was selected</h1>";

	}
    }









?>
