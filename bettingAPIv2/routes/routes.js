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

    db.query(sql, function(err, data, fields) {
        if(err) throw err;
        var resultArray = Object.values(JSON.parse(JSON.stringify(data)))

      
          var allEvents = underscore.groupBy(resultArray, function(value){
              return value.eventID + '#' + value.feedEventID;
          });

       

        var odds_change = [];

        for(let key in allEvents){

        odds_change.push(helpers.handleAllOddsData(allEvents[key]))
           
     
         }

         res.json({
            odds_change
    
    })      
})
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

module.exports = router;