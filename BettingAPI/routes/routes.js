const express = require('express'),
    router = express.Router();


router.get('/odds', function(req, res){
    let sql = 'SELECT marketSourceMaps FROM Market;SELECT marketId FROM EventMarkets;' +
    'SELECT eventMarketOutcomes_odd FROM EventMarkets; SELECT marketOutcomeSourceMaps FROM Outcome; SELECT id, type FROM Specials;'+
    'SELECT marketId, specialValue FROM OutcomeSpecials;'

    db.query(sql, function(err, data, fields) {
        if(err) throw err;

        var helpers = require("./helperFunctions.js")

        // Get id and sourceID pairs object
        var IDSourceIDPairs = helpers.getIDSourceIDPairs("marketId", "sourceMarketId", data[0])
        

        // Pair marketID and odds
        var IDOddsPairs = helpers.getIDOddsPairs(data[1], data[2])
        
        // Pair sourceMarketID and odds
        var sourceIDOddsPairs = helpers.oddsKeyChange(IDOddsPairs, IDSourceIDPairs)
        // console.log(IDSourceIDPairs)

        // Pair SourceMarketID and SourceOutcomeID
        var sourceIDsourceOutcomeIDPairs = helpers.getSourceOutcomeOdds(data[3]);
        console.log(sourceIDsourceOutcomeIDPairs)

        // Special type Specifier
        var specialType = helpers.getIDSourceIDPairs("id", "type", data[4])


        // Get MarketID and SpecialValue pairs
        var marketIDSpecialValues = helpers.getMarketIDSpecialValuePairs(data[5])
        
        var sourceIDSpecialValues = helpers.oddsKeyChange(marketIDSpecialValues, IDSourceIDPairs)

        

        
    
        // Get market
        market = helpers.getMarket(sourceIDOddsPairs, sourceIDsourceOutcomeIDPairs, specialType, sourceIDSpecialValues)
        

        res.json({
            "odds_change": {
                "odds":{
                    "market": market
                    
                
            },
            "event_id": "sr:match:14993373",
            "timestamp": "1234"
        }})
    
    })
})

module.exports = router;