const express = require('express'),
    router = express.Router();


router.get('/odds', function(req, res){
    let sql = 'SELECT marketSourceMaps FROM Market;SELECT marketId FROM EventMarkets;' +
    'SELECT eventMarketOutcomes_odd FROM EventMarkets; SELECT marketOutcomeSourceMaps FROM Outcome;'

    db.query(sql, function(err, data, fields) {
        if(err) throw err;

        var helpers = require("./helperFunctions.js")

        // Get id and sourceID pairs object
        var IDSourceIDPairs = helpers.getIDSourceIDPairs(data[0])
        
        // Pair marketID and odds
        var IDOddsPairs = helpers.getIDOddsPairs(data[1], data[2])

        // Pair sourceMarketID and odds
        var sourceIDOddsPairs = helpers.oddsKeyChange(IDOddsPairs, IDSourceIDPairs)

        var sourceIDsourceOutcomeIDPairs = helpers.getSourceOutcomeOdds(data[3]);

     
        // Get market
        market = helpers.getMarket(sourceIDOddsPairs, sourceIDsourceOutcomeIDPairs)
        

        res.json({
            "odds_change": {
                "odds":{
                    "market": market
                
            }
            
        }})
    
    })
})

module.exports = router;