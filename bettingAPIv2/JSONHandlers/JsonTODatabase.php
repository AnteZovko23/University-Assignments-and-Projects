<?php

include "JsonTODatabaseHelpers.php";

$conn = OpenCon();


$jsonFiles = array();
$jsonFiles = getJSONFilesArray($jsonFiles);
// array_pop($jsonFiles);

/************************** */
// Get Event Table
$keywords = ["feedId","feedEventId", "type", "status", "sportId",  "sportName",  "categoryId", 
"categoryName","tournamentId",  "tournamentName",  "name",  "startAt", "active", "deleted"];
$specifier = [];
$userDepth = 0;


$mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$mainArr = getDataOrdered($mainArr);
// print_r($mainArr);


insertQuery($conn, $mainArr, "Events");



// // /********************************************** */

// // // Get Market Table

$keywords = ["marketId"];
$userDepth = 2;
$specifier = ["eventMarkets"];
$idEventID = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$idEventID = getDataOrdered($idEventID);



$keywords = ["id", "name"];
$userDepth = 2;
$specifier = ["markets"];
$idName = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$idName = getDataOrdered($idName);


$keywords = ["sourceMarketId"];
$userDepth = 4;
$specifier = ["markets", "marketSourceMaps"];
$mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$mainArr = getDataOrdered($mainArr);



$keywords = ["betradarSpecialType"];
$userDepth = 6;
$specifier = ["markets", "marketSourceMaps", "marketSourceMapSpecials"];
$specialType = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$specialType = getDataOrdered($specialType);

mergeArrays($idName, $mainArr, $specialType);
$allIDs = array_merge_callback($idName, $idEventID, function ($item1, $item2) {
    return ($item1[0] == $item2[0]) && ($item1[1] == $item2[1]);
});


foreach($allIDs as $key => $val){
   array_splice($allIDs[$key], 5, 1);
   array_splice($allIDs[$key], 5, 1);
}

$abc = array_unique($allIDs, SORT_REGULAR);


insertQuery($conn, $abc, "Markets");

/********************************************** */

// Get EventMarket Table


$keywords = ["id","marketId", "name", "active"];
$userDepth = 4;
$specifier = ["markets", "marketOutcomes"];
$mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$mainArr = getDataOrdered($mainArr);



$keywords = ["sourceMarketOutcomeId"];
$userDepth = 6;
$specifier = ["markets", "marketOutcomes", "marketOutcomeSourceMaps"];
$sourceID = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$sourceID = getDataOrdered($sourceID);


$keywords = ["specialValue"];
$userDepth = 8;
$specifier = ["markets", "marketOutcomes", "marketOutcomeSourceMaps", "marketOutcomeSourceMapSpecials"];
$specialVal = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$specialVal = getDataOrdered($specialVal);



mergeArrays($mainArr, $sourceID, $specialVal);

// $mainArr = getDataOrdered($mainArr);



$keywords = ["id", "eventId", "marketId", "favourite", "status", "active", "deleted"];
$userDepth = 2;
$specifier = ["eventMarkets"];
$mainArr2 = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$mainArr2 = getDataOrdered($mainArr2);


$keywords = ["specialValue"];
$userDepth = 4;
$specifier = ["eventMarkets", "eventMarketSpecials"];
$specialVal = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$specialVal = getDataOrdered($specialVal);


mergeArrays($mainArr2, $specialVal);


$test = array_merge_callback($mainArr, $mainArr2, function ($item1, $item2) {

    return ($item1[0] == $item2[0]) && ($item1[2] == $item2[3]);
    });



foreach($test as $key => $val){
   array_splice($test[$key], 7, 1);
   array_splice($test[$key], 8, 1);
   array_splice($test[$key], 8, 1);
}

// print_r($test);

insertQuery($conn, array_unique($test, SORT_REGULAR), "MarketOutcomes");

/**************************************************** */

// Odds Table

$keywords = ["id", "eventMarketId", "marketOutcomeId", "status", "odd"];
$userDepth = 4;
$specifier = ["eventMarkets", "eventMarketOutcomes"];
$odds = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$odds = getDataOrdered($odds);
// print_r($odds);

$keywords = ["id","marketId"];
$userDepth = 4;
$specifier = ["markets", "marketOutcomes"];
$idMarketId = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
$idMarketId = getDataOrdered($idMarketId);
// print_r($idMarketId);


$oddsForInsert = array_merge_callback($odds, $idMarketId, function ($item1, $item2) {
        return ($item1[0] == $item2[0]) && ($item1[3] == $item2[1]);
    });

// print_r($oddsForInsert);

foreach($oddsForInsert as $key => $val){
   array_splice($oddsForInsert[$key], 6, 1);
   array_splice($oddsForInsert[$key], 6, 1);
}

// // print_r(array_unique($oddsForInsert2, SORT_REGULAR));

insertQuery($conn, array_unique($oddsForInsert, SORT_REGULAR), "Odds");

?>