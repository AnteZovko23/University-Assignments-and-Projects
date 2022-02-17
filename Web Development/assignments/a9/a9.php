<?php

ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);

	
	class Sphere {

		private $radius;

		function __construct($r) {

			$this->radius = floatval($r);

		}


		function get_volume() {

			return (4.0/3.0) * pi() * (pow($this->radius, 3));


		}

		function get_surface_area() {


			return 4 * pi() * (pow($this->radius, 2));

		}



		function get_radius() {

			return $this->radius;

		}

		function set_radius($r) {

			$this->radius = floatval($r);

		}

	}



	

	if($_GET["radius"]) {

		
		$sphere = new Sphere($_GET["radius"]);

		echo "The surface area is: ".$sphere->get_surface_area()."<br>";
		echo "The volume is: ".$sphere->get_volume()."";	


	}




?>
