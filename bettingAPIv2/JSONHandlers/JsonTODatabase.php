<?php

include "JsonTODatabaseHelpers.php";

$conn = OpenCon();


$jsonFiles = array();
$jsonFiles = getJSONFilesArray($jsonFiles);

/************************** */
// Get Event Table
$keywords = ["id","feedId","feedEventId", "type", "status", "sportId",  "sportName",  "categoryId", 
"categoryName","tournamentId",  "tournamentName",  "name",  "startAt", "active", "deleted"];
$specifier = [];
$userDepth = 0;


$mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$mainArr = getDataOrdered($mainArr);
insertQuery($conn, $mainArr, "Events");



/********************************************** */

// Get Market Table

$keywords = ["marketId", "eventId"];
$userDepth = 2;
$specifier = ["eventMarkets"];
$idEventID = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$idEventID = getDataOrdered($idEventID);


$keywords = ["id", "name"];
$userDepth = 2;
$specifier = ["markets"];
$idName = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);



$keywords = ["sourceMarketId"];
$userDepth = 4;
$specifier = ["markets", "marketSourceMaps"];
$mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

$keywords = ["betradarSpecialType"];
$userDepth = 6;
$specifier = ["markets", "marketSourceMaps", "marketSourceMapSpecials"];
$specialType = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

$counter = 0;
foreach($idName as $key => $val){
    array_push($idName[$key], $mainArr[$counter][0]);
    array_push($idName[$key], $specialType[$counter][0]);
    $counter++;
}

$idName = getDataOrdered($idName);

$allIDs = array_merge_callback($idName, $idEventID, function ($item1, $item2) {
    return $item1[0] == $item2[0];
});

foreach($allIDs as $key => $val){
    $allIDs[$key] = array_unique($val);
}

$abc = array_unique($allIDs, SORT_REGULAR);


insertQuery($conn, $abc, "EventMarkets");




?>