const express = require('express'),
    router = express.Router();

var fs = require("fs");
var helpers = require("./helperFunctions.js")
var underscore = require("underscore");
var queryObj = {};

queryObj["getAllOdds"] = fs.readFileSync('./routes/BettingDatabaseQueryGetAllOdds.sql').toString();
queryObj["getAllSports"] = fs.readFileSync('./routes/BettingDatabaseQueryGetSports.sql').toString();


router.get('/odds', function(req, res){
    let sql = queryObj["getAllOdds"];

    var odds_change = [];
    
    helpers.allOddsQuery(db, sql, res);

    


   

})



router.get('/sport', function(req, res){

    let sql = queryObj["getAllSports"];
    db.query(sql, function(err, data, fields){
        if(err) throw err;
       
        var resultArray = Object.values(JSON.parse(JSON.stringify(data)))
       
      
        var sports = helpers.handleAllSportsData(resultArray);
            


        res.json({
            sports
        })

    })


})

router.get('/odds/sportID::sportID', function(req, res){


    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.sportID = " + req.params.sportID + ";";
    helpers.allOddsQuery(db, sql, res)
    

})

router.get('/odds/since::since', function(req, res){


    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.startTime >= " + req.params.since + ";";
    helpers.allOddsQuery(db, sql, res)
    

})


router.get('/odds/sportID::sportID/since::since', function(req, res){


    let sql = queryObj["getAllOdds"].slice(0, -1) + " WHERE events.sportID = " + req.params.sportID + " AND" +
    " events.startTime >= " + req.params.since + ";";
    helpers.allOddsQuery(db, sql, res)
    

})



module.exports = router;