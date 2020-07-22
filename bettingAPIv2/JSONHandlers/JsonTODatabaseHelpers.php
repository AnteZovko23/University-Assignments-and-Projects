<?php


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
        
    }

    return $arr;
       
}

function getJSONFilesArray($jsonFiles){

    foreach (new DirectoryIterator('./JSONFiles/') as $file) {
        if ($file->getExtension() === 'json') {
            array_push($jsonFiles, json_decode(file_get_contents($file->getPathname()), true));
            
        }
    }

    return $jsonFiles;
}


function OpenCon()
 {
 $dbhost = "localhost";
 $dbuser = "root";
 $dbpass = "ChoosePassword";
 $db = "BettingDatabaseV2";
 $conn = new mysqli($dbhost, $dbuser, $dbpass,$db) or die("Connect failed: %s\n". $conn -> error);
 
 return $conn;
 }

function insertQuery($conn, $mainArr, $tableName){

    foreach($mainArr as $key => $value){
        $sql = "INSERT INTO ".$tableName." VALUES ('".implode("','", $value)."');";
        $conn -> query($sql);

    }
}


function getTableColumns($conn, $tableName){
    $columns = array();
    $test = $conn -> query("Describe ".$tableName.";");
    while($row = $test->fetch_assoc()) {
        array_push($columns, $row['Field']);
    }

    return $columns;
}


function getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth){
$mainArr = array();
$temp = array();
$test = array();


foreach ($jsonFiles as $key => $value) {
    array_push($test, findValue($value, "id", [], 0, $temp));
    $temp = [];
    foreach ($keywords as $key => $keyword) {

        
        array_push($test, findValue($value, $keyword, $specifier, $userDepth, $temp));
        $temp = [];        
    
    }
    
    array_push($mainArr, $test);
    $test = [];
}

return $mainArr;

}


function getDataOrdered($mainArr){

    $mainArr2 = array();
                        // PROBLEM?
    //  for($i = 0; $i < count($mainArr[0][0]); $i++){
    // $counter = 0;
    // while(TRUE){
    
    
       
        foreach($mainArr as $key => $val){
            $temp = array();
               for($i = 0; $i < count($val[1]); $i++){
                array_push($temp, $val[0][0]);
                foreach(array_slice($val, 1) as $key => $value){
                    // if(array_key_exists($counter, $value)){
                        array_push($temp, $value[$i]);
                    // }
                    // else{
                    //     // break 3;
                    // }
                    
                }
                array_push($mainArr2, $temp);
                $temp = [];
                   
         
            // }  
                
               }
        // $counter++;   
        }
        
    // }
    
   
    return $mainArr2;


}

function mergeArrays(&$mainArr, ...$givenArrays){

    $counter = 0;
    foreach($mainArr as $key => $val){
        foreach($givenArrays as $givenKey => $givenValue){
            array_push($mainArr[$key], $givenValue[$counter][1]);
        }
        $counter++;
    }

    

}

function array_merge_callback($array1, $array2, $predicate) {
    $result = array();

    foreach ($array1 as $item1) {
        foreach ($array2 as $item2) {
            if ($predicate($item1, $item2)) {
                $result[] = array_merge($item1, $item2);
            }
        }
    }

    return $result;
}


function CloseCon($conn)
 {
 $conn -> close();
 }



?>