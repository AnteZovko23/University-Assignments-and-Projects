<?php

/**
 * Useful functions to keep the main JsonToDatabase clean and readable
 * 
 * 
 */

// Recursively navigates through multidimensional arrays looking for a specific key
function findValue($json, $keyword, $specifier, $userDepth, &$arr){
    static $depth = 0;
    static $path = array();  
    foreach($json as $key => $value){
        if((string)$key === end($specifier) && is_array($value) && count($value) === 0){
       
            
            array_push($arr, null);
               
        }
       
        else if(is_array($value)){
            $depth++; 
            array_push($path, (string) $key);
            findValue($value, $keyword, $specifier, $userDepth, $arr);
            array_pop($path);
            $depth--;
        }
        else if($key === $keyword && $depth === $userDepth && (empty(array_diff($specifier, $path)) || count($specifier)== 0)){
            
            array_push($arr, $value);
        }
        else if($depth === $userDepth && (empty(array_diff($specifier, $path)) || count($specifier)== 0) && !in_array($keyword, array_keys($json))){
            array_push($arr, null);
            break;
        }
      
        
       
        
    }

    return $arr;
       
}


// Opens database Connection
function OpenCon()
 {
 $dbhost = "localhost";
 $dbuser = "root";
 $dbpass = "ChoosePassword";
 $db = "BettingDatabaseV2";
 $conn = new mysqli($dbhost, $dbuser, $dbpass,$db) or die("Connect failed: %s\n". $conn -> error);
 
 return $conn;
 }



 // Dynamically inserts data into tables
function insertQuery($conn, $mainArr, $tableName){
    foreach($mainArr as $key => $value){
        
            if($tableName === "Events"){

            
            $duplicateUpdate = getTableColumns($conn, "Events");
            $duplicateUpdate = array_diff_key($duplicateUpdate, [0 => "a"]);

            foreach($duplicateUpdate as $key => $val){
                $duplicateUpdate[$key] = $val. " = '". $value[$key]."'";
            }

            $sql = "INSERT INTO ".$tableName." VALUES ('".implode("','", $value)."') ON DUPLICATE KEY UPDATE ".implode("," , $duplicateUpdate).";";

            } else {
    
                $sql = "INSERT INTO ".$tableName." VALUES ('".implode("','", $value)."');";
    
                }
        
        $conn -> query($sql);
        echo $conn -> error;

    }
}

// Gets all columns for a given table
function getTableColumns($conn, $tableName){
    $columns = array();
    $tableColumns = $conn -> query("Describe ".$tableName.";");
    while($row = $tableColumns->fetch_assoc()) {
        array_push($columns, $row['Field']);
    }

    return $columns;
}

// Organizes data retrieved from FindValue()
function getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth){
$mainArr = array();
$temp = array();
$temp2 = array();


foreach ($jsonFiles as $key => $value) {
    array_push($temp2, findValue($value, "id", [], 0, $temp));
    $temp = [];
    foreach ($keywords as $key => $keyword) {

        
        array_push($temp2, findValue($value, $keyword, $specifier, $userDepth, $temp));
        $temp = [];        
    
    }
    
    array_push($mainArr, $temp2);
    $temp2 = [];
}

return $mainArr;

}

// Merges all arrays retrieved in GetArrayForInsert()
function getDataOrdered($mainArr){

    $mainArr2 = array();
       
        foreach($mainArr as $key => $val){
            $temp = array();
               for($i = 0; $i < count($val[1]); $i++){
                array_push($temp, $val[0][0]);
                foreach(array_slice($val, 1) as $key => $value){
                
                        array_push($temp, $value[$i]); 
                }
                array_push($mainArr2, $temp);
                $temp = [];
                   
         
              
                
               }
         
        }
    return $mainArr2;


}

// Merges arrays
function mergeArrays(&$mainArr, ...$givenArrays){

    $counter = 0;
    foreach($mainArr as $key => $val){
        foreach($givenArrays as $givenKey => $givenValue){
            array_push($mainArr[$key], $givenValue[$counter][1]);
        }
        $counter++;
    }

    

}

// Merges arrays based on specific values
function array_merge_callback($array1, $array2, $firstIndex, $secondIndex, $predicate, $thirdIndex = "-1") {
    $result = array();

    foreach ($array1 as $item1) {
        foreach ($array2 as $item2) {
            if ($predicate($item1, $item2)) {
                $item2 = array_diff_key($item2, [$firstIndex => "a", $secondIndex => "a", $thirdIndex => "a"]);
                $result[] = array_merge($item1, $item2);
            }
        }
    }

    return $result;
}

// Merges arrays based on specific values and keeps duplicates
function array_merge_callbackKeep(&$array1, $array2, $firstIndex, $secondIndex, $predicate) {

    $length = count(array_diff_key($array2[0], [$firstIndex => "a", $secondIndex => "a"]));
    foreach ($array1 as $key => $item1) {
        

        for($i = 0; $i < $length; $i++){
            
            array_push($array1[$key], null);
        }
        
        foreach ($array2 as $item2) {
            if ($predicate($item1, $item2)) {
                $item2 = array_diff_key($item2, [$firstIndex => "a", $secondIndex => "a"]);
                $array1[$key] = array_merge($item1, $item2);
            }
            $length = count(array_diff_key($item2, [$firstIndex => "a", $secondIndex => "a"]));
        }
    }

}


// Closes Connection
function CloseCon($conn)
 {
 $conn -> close();
 }



?>