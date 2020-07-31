
/**
 * Defines andpoints and sends query to database
 * 
 */


// Overhead
const express = require('express'),
    router = express.Router();
var fs = require("fs");
var helpers = require("./helperFunctions.js")
const {idParamValidation, timeParamValidation, check, errorResponse} = require('./URLValidator.js');
var queryObj = {};
queryObj["getAllOdds"] = fs.readFileSync('./routes/BettingDatabaseQueryGetAllOdds.sql').toString();
queryObj["getAllSports"] = fs.readFileSync('./routes/BettingDatabaseQueryGetSports.sql').toString();
/**************** */


/********* ENDPOINTS **********************/
router.get('/odds', function(req, res){

    var [beforeDateAllowed, afterDateAllowed] = helpers.getTimeRange();
    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.startTime >= " + beforeDateAllowed + " AND" +
    " events.startTime <= " + afterDateAllowed + ";";
    helpers.allOddsQuery(db, sql, res) 

})



router.get('/sport', function(req, res){

    var [beforeDateAllowed, afterDateAllowed] = helpers.getTimeRange();
    let sql = queryObj["getAllSports"].slice(0, -1) + " WHERE events.startAt >= " + beforeDateAllowed + " AND" +
    " events.startAt <= " + afterDateAllowed + ";";
    helpers.allSportsQuery(db, sql, res)
  

})


router.get('/odds/sportID::sportID', idParamValidation("sportID"), errorResponse, function(req, res){

    var [beforeDateAllowed, afterDateAllowed] = helpers.getTimeRange();

    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.sportID = " + req.params.sportID + " AND " + 
    " events.startTime >= " + beforeDateAllowed + " AND" +
    " events.startTime <= " + afterDateAllowed + ";";
    helpers.allOddsQuery(db, sql, res)
    

})


router.get('/odds/since::since', timeParamValidation("since"), errorResponse, function(req, res){


    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.startTime >= " + req.params.since + ";";
    helpers.allOddsQuery(db, sql, res)
    

})


router.get('/odds/sportID::sportID/since::since', idParamValidation("sportID").concat(timeParamValidation("since")), errorResponse, function(req, res){


    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.sportID = " + req.params.sportID + " AND" +
    " events.startTime >= " + req.params.since + ";";
    helpers.allOddsQuery(db, sql, res)
    

})
/********* ENDPOINTS **********************/


module.exports = router;