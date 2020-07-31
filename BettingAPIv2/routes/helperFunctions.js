/****************** */
// Helper Functions
// Keeps routes.js clean

var underscore = require("underscore");
var moment = require("moment");


var eventMarketStatuses = {
    "2":"suspended",
    "3":"deactivated",
    "4":"cancelled"

}

var eventStatuses = {
    "2":"live",
    "3":"suspended",
    "4":"ended",
    "5":"abandoned",
    "6":"deactivated",
    "7":"cancelled",
    "8":"delayed",
 
}

module.exports = {


    allOddsQuery : function(db, sql, res){
        var dataScope = this;

        db.query(sql, function (err, data, fields) {
            if(err) throw err;
            var resultArray = Object.values(JSON.parse(JSON.stringify(data)))
    
          
              var allEvents = underscore.groupBy(resultArray, function(value){
                  return value.eventID + '#' + value.feedEventID;
              });
            var odds_change = []
           
           
            for(let key in allEvents){
    
                odds_change.push(dataScope.handleAllOddsData(allEvents[key]))
         
         
             }

             res.json({
    
                odds_change

            
            })   
            
    })



    },

    handleAllOddsData : function(allEvents) {

        var specificEvent = underscore.groupBy(allEvents, function(value){
            return value.eventID + '#' + value.feedEventID;
        });

        var specificEventData = underscore.map(specificEvent, function(group){
            return {
                eventID: group[0].eventID,
                feedEventID: group[0].feedEventID,
                sportID: group[0].sportID,
                sportName: group[0].sportName,
                startTime: group[0].startTime,
                status: group[0].eventStatus,
                active: group[0].active,
                deleted: group[0].deleted,
            }
        });

        

    
    var specificEventMarkets = underscore.groupBy(allEvents, function(value){
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

    return this.getAllOdds(specificEventData, specificEventMarketsData)
        
    },

    getAllOdds : function(eventData, eventMarketsData){
        // Create array of outcomes for each market
        oddObj = {}
        outcome = []
        market = []
        outcomeObj = {}
        marketObj = {}
        oddsObj = {}

        if((eventData[0].status === "0" || eventData[0].status === "1") && eventData[0].active === "1" && eventData[0].deleted === "0"){


        eventMarketsData.forEach(element => {
            
        

            
            if((element["specialStatus"] === "0" || element["specialStatus"] === "1") && element["specialActive"] === "1" && element["specialDeleted"] === "0"){

            

            for(var i = 0; i < element["sourceOutcomeID"].length; i++){
                
                oddObj["id"] = element["sourceOutcomeID"][i].split('/')[1];
                oddObj["odds"] = (parseFloat(element["odds"][i])).toString();
                if(element["oddStatus"][i] !== "1" || element["marketOutcomeActive"][i] !== "1"){
                    oddObj["active"] = "false"
                }
                
                outcome.push(oddObj);
                oddObj = {};
            }
            outcomeObj["outcome"] = outcome
            outcomeObj["id"] = element["marketSourceID"];
            if(element["specialType"] !== ''){
                outcomeObj["specifiers"] = element["specialType"]+"="+element["specialValue"]

            } else if(element["specialOutcomeType"] !== ''){
                outcomeObj["specifiers"] = element["specialOutcomeType"]+"="+element["specialOutcomeValue"]
            }
            

        } else{

            outcomeObj["id"] = element["marketSourceID"];
            outcomeObj["status"] = eventMarketStatuses[element["specialStatus"]]
            if(eventMarketStatuses[element["specialStatus"]] === undefined && element["specialActive"] === "0"){
                outcomeObj["active"] = "false" 
            }

        }

            
            market.push(outcomeObj)
            outcome = []
            outcomeObj = {}
        })

        marketObj["market"] = market;
        
        oddObj["odds"] = marketObj;
        oddObj["event_id"] = eventData[0].feedEventID
        oddObj["timestamp"] = eventData[0].startTime;


    }
    else {

        oddObj["event_id"] = eventData[0].feedEventID
        oddObj["timestamp"] = eventData[0].startTime;
        oddObj["status"] = eventStatuses[eventData[0].status]
            if(eventStatuses[eventData[0].status] === undefined && eventData[0].active === "0"){
                oddObj["active"] = "false" 
            }

    }
        
        return oddObj
    },


    handleAllSportsData : function(data){
        var sports = [];
    
        var allTeams = underscore.groupBy(data, function(value){
            return value.eventID + '#' + value.feedEventId;
        });


        var specificTeamData = underscore.map(allTeams, function(group){
            return {
                eventID: group[0].eventID,
                feedEventId: group[0].feedEventId,
                status: group[0].status,
                active: group[0].active,
                deleted: group[0].deleted,
                sportId: group[0].sportId,
                sportName: group[0].sportName,
                categoryId: group[0].categoryId,
                categoryName: group[0].categoryName,
                eventID: group[0].eventID,
                tournamentId: group[0].tournamentId,
                tournamentName: group[0].tournamentName,
                startAt: group[0].startAt,
                teamNames: underscore.pluck(group, 'teamName'),
                teamID: underscore.pluck(group, 'teamId'),
                type: underscore.pluck(group, 'type'),
                
                
            }
        })

        specificTeamData.forEach(element => {
            sports.push(this.getAllSports(element));
        });
        

        return sports;
    },


    getAllSports : function(element){
        competitorsObj = {}
        competitors = []
        market = []
        match = {}
        sports = {}

        if((element.status === "0" || element.status === "1") && element.active === "1" && element.deleted === "0"){
        
        sports["sport"] = {
            "sportName": element.sportName,
            "sportId": element.sportId
        }

        sports["category"] = {
            "categoryName": element.categoryName,
            "categoryId": element.categoryId
        }

        sports["tournament"] = {
            "tournamentName": element.tournamentName,
            "tournamentId": element.tournamentId
        }

                for(var i = 0; i < element["teamNames"].length; i++){
                
                competitorsObj["name"] = element["teamNames"][i];
                competitorsObj["id"] = element["teamID"][i];
                competitorsObj["type"] = element["type"][i];
                
                competitors.push(competitorsObj);
                competitorsObj = {};
                }
                match["competitors"] = competitors


        sports["match"] = match

        sports["event_Id"] = element.feedEventId;
        
        sports["timestamp"] = element.startAt;

    }
    else {

        sports["event_Id"] = element.feedEventId;
        sports["timestamp"] = element.startAt;
        sports["status"] = eventStatuses[element.status]
            if(eventStatuses[element.status] === undefined && element.active === "0"){
                sports["active"] = "false" 
            }

    }
        

        return sports





    },


    getTimeRange : function(){
        var afterDateAllowed = Date.parse(moment().add(25, "days").calendar())
        var beforeDateAllowed = Date.parse(moment().subtract(7, "days").calendar())

        return [beforeDateAllowed, afterDateAllowed]
    }


}