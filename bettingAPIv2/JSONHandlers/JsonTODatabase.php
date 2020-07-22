<?php

include "JsonTODatabaseHelpers.php";

$conn = OpenCon();


$jsonFiles = array();
$jsonFiles = getJSONFilesArray($jsonFiles);
// array_pop($jsonFiles);

/************************** */
// // Get Event Table
// $keywords = ["id","feedId","feedEventId", "type", "status", "sportId",  "sportName",  "categoryId", 
// "categoryName","tournamentId",  "tournamentName",  "name",  "startAt", "active", "deleted"];
// $specifier = [];
// $userDepth = 0;


// $mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
// $mainArr = getDataOrdered($mainArr);


// insertQuery($conn, $mainArr, "Events");



// // /********************************************** */

// // // Get Market Table

// $keywords = ["marketId", "eventId"];
// $userDepth = 2;
// $specifier = ["eventMarkets"];
// $idEventID = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
// $idEventID = getDataOrdered($idEventID);


// $keywords = ["id", "name"];
// $userDepth = 2;
// $specifier = ["markets"];
// $idName = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);
// // print_r($idName);


// $keywords = ["sourceMarketId"];
// $userDepth = 4;
// $specifier = ["markets", "marketSourceMaps"];
// $mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

// $keywords = ["betradarSpecialType"];
// $userDepth = 6;
// $specifier = ["markets", "marketSourceMaps", "marketSourceMapSpecials"];
// $specialType = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

// mergeArrays($idName, $mainArr, $specialType);

// $idName = getDataOrdered($idName);
// $allIDs = array_merge_callback($idName, $idEventID, function ($item1, $item2) {
//     return $item1[0] == $item2[0];
// });



// foreach($allIDs as $key => $val){
//    array_splice($allIDs[$key], 4, 1);
// }

// $abc = array_unique($allIDs, SORT_REGULAR);


// insertQuery($conn, $abc, "Markets");

/********************************************** */

// Get EventMarket Table


// $keywords = ["id","marketId", "name", "active"];
// $userDepth = 4;
// $specifier = ["markets", "marketOutcomes"];
// $mainArr = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);


// $keywords = ["sourceMarketOutcomeId"];
// $userDepth = 6;
// $specifier = ["markets", "marketOutcomes", "marketOutcomeSourceMaps"];
// $sourceID = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

// $keywords = ["specialValue", "betradarSpecialType"];
// $userDepth = 8;
// $specifier = ["markets", "marketOutcomes", "marketOutcomeSourceMaps", "marketOutcomeSourceMapSpecials"];
// $specialVal = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);

// mergeArrays($mainArr, $sourceID, $specialVal);
// $mainArr = getDataOrdered($mainArr);
// // print_r($mainArr);


// $keywords = ["id", "eventId", "marketId", "favourite", "status", "active", "deleted"];
// $userDepth = 2;
// $specifier = ["eventMarkets"];
// $mainArr2 = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);


// $keywords = ["specialValue"];
// $userDepth = 4;
// $specifier = ["eventMarkets", "eventMarketSpecials"];
// $specialVal = getArrayForInsert($jsonFiles, $keywords, $specifier, $userDepth);


// mergeArrays($mainArr2, $specialVal);
// $mainArr2 = getDataOrdered($mainArr2);


// $test = array_merge_callback($mainArr, $mainArr2, function ($item1, $item2) {
//         return $item1[1] == $item2[2];
//     });

// foreach($test as $key => $val){
//    array_splice($test[$key], 8, 1);
// }

// insertQuery($conn, array_unique($test, SORT_REGULAR), "MarketOutcomes");

?>