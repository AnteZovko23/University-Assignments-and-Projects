/****************** */
// Helper Functions
// Keeps routes.js clean

module.exports = {
    getIDSourceIDPairs: function(data) {
        var sMarketId = []
        for(var i = 0; i < data.length; i++){
            sMarketId.push(JSON.parse(Object.values(data[i])))
        }
        sMarketId = [].concat.apply([], sMarketId)

        idSourceId = {}
        for(var i = 0; i < sMarketId.length; i++){
            idSourceId[sMarketId[i].marketId] = sMarketId[i].sourceMarketId
        }
        return idSourceId;
    },

    getIDOddsPairs: function(idData, oddsData) {
        // Pull data from query 
        const id = idData.map((row) => {
        return {
            id:row.marketId,
                
            }
        })

              
        const odds = oddsData.map((row) => {
            return {
                odds:row.eventMarketOutcomes_odd,
                
            }
        })

        /*************************************** */


       // Create two arrays with values only
       var oddsVals = []
       var idVals = []
       for(var i = 0; i < odds.length; i++){
           oddsVals.push(Object.values(odds[i]))
           idVals.push(Object.values(id[i]))
       }

       idVals2 = idVals
       oddsVals = [].concat.apply([], oddsVals)
       idVals = [].concat.apply([], idVals)


       // Create object with matching marketID and odds
        var i;
        var currentKey;
        var currentVal;
        var idOdds = {}
        for (i = 0; i < oddsVals.length; i++) {
            currentKey = idVals[i];
            currentVal = oddsVals[i];
            if(!(currentKey in idOdds)){
                idOdds[currentKey] = new Array()
            }
           
            idOdds[currentKey].push(currentVal)


    }

    return idOdds;
    
},

    oddsKeyChange : function renameKeys(obj, newKeys) {
        const keyValues = Object.keys(obj).map(key => {
          const newKey = newKeys[key] || key;
          return { [newKey]: obj[key] };
        });
        return Object.assign({}, ...keyValues);
      },



    getMarket :   function(idOdds){
    
        // Create array of outcomes for each market
        oddObj = {}
        outcome = []
        market = []
        outcomeObj = {}
        console.log(idOdds)
        for(var i in idOdds) {
            
            for(var j = 0; j < idOdds[i].length; j++){
                // oddObj["id"] = j+1
                oddObj["odds"] = idOdds[i][j]
                outcome.push(oddObj)
                oddObj = {}
            }
            outcomeObj["outcome"] = outcome
            outcomeObj["id"] = i
            market.push(outcomeObj)
            outcome = []
            outcomeObj = {}

        }


        return market
    },

    getSourceOutcomeOdds: function(data) {
        var sourceOutcomeOdds = []
        for(var i = 0; i < data.length; i++){
            sourceOutcomeOdds.push(JSON.parse(Object.values(data[i])))
        }
        sourceOutcomeOdds = [].concat.apply([], sourceOutcomeOdds)
        // console.log(sourceOutcomeOdds)
    }
}
