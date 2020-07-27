const express = require('express'),
    router = express.Router();

var fs = require("fs");
var helpers = require("./helperFunctions.js")
var underscore = require("underscore");

var query = fs.readFileSync('./routes/BettingDatabaseQuery.sql').toString();


router.get('/odds', function(req, res){
    let sql = query;

    db.query(sql, function(err, data, fields) {
        if(err) throw err;
        var resultArray = Object.values(JSON.parse(JSON.stringify(data)))

      
          var allEvents = underscore.groupBy(resultArray, function(value){
              return value.eventID + '#' + value.feedEventID;
          });

        //   console.log(allEvents);

        var odds_change = {};

        for(let key in allEvents){

            var specificEvent = underscore.groupBy(allEvents[key], function(value){
                return value.eventID + '#' + value.feedEventID;
            });

            var specificEventData = underscore.map(specificEvent, function(group){
                return {
                    eventID: group[0].eventID,
                    feedEventID: group[0].feedEventID,
                    sportID: group[0].sportID,
                    sportName: group[0].sportName,
                    sportID: group[0].sportID,
                    startTime: group[0].startTime,
                    status: group[0].eventStatus,
                    active: group[0].active,
                    deleted: group[0].deleted,
                }
            });

            

        
        var specificEventMarkets = underscore.groupBy(allEvents[key], function(value){
            return value.eventID + '#' + value.feedEventID + "#" + value.marketsSourceID + "#" + value.oddID;
        });
    
        var specificEventMarketsData = underscore.map(specificEventMarkets, function(group){
            return {
                marketSourceID: group[0].marketsSourceID,
                marketOutcomeActive: underscore.pluck(group, 'marketActive'),
                specialType: group[0].marketsSpecialType,
                sourceOutcomeID: underscore.pluck(group, 'sourceOutcomeID'),
                specialOutcomeType: group[0].specialOutcomeType,
                specialOutcomeValue: group[0].specialOutcomeValue,
                specialStatus: group[0].specialStatus,
                specialActive: group[0].specialActive,
                specialDeleted: group[0].specialDeleted,
                specialValue: group[0].specialValue,
                oddStatus: underscore.pluck(group, 'oddStatus'),
                odds: underscore.pluck(group, 'odds')
            }
        });

        odds_change = (helpers.getMarket(specificEventData, specificEventMarketsData))
        res.json({
            odds_change
    
    })
     
         }

       


          



       

        //   console.log(data2);

       
})
})
module.exports = router;