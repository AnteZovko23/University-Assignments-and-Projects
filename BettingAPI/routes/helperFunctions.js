/****************** */
// Helper Functions
// Keeps routes.js clean

module.exports = {

    // Given two params, creates object with specific keys and values
    getIDSourceIDPairs: function(key, value, data) {
        var sMarketId = []
        if(key === "marketId" && value === "sourceMarketId"){
            for(var i = 0; i < data.length; i++){
            sMarketId.push(JSON.parse(Object.values(data[i])))
        }
        sMarketId = [].concat.apply([], sMarketId)
        }
        else{
            sMarketId = data
        }
        

        idSourceId = {}
        for(var i = 0; i < sMarketId.length; i++){
            idSourceId[sMarketId[i][key]] = sMarketId[i][value]
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



    getMarket :   function(idOdds, outcomeID, specifiers, specialValues){
        console.log(outcomeID);
        // Create array of outcomes for each market
        oddObj = {}
        outcome = []
        market = []
        outcomeObj = {}
        for(var i in idOdds) {
        
        
        
            while(idOdds[i].length != 0){

           
        
            for(var j = 0; j < outcomeID[i].length; j++){
                oddObj["id"] = outcomeID[i][j]
                oddObj["odds"] = idOdds[i].shift()
                outcome.push(oddObj)
                oddObj = {}
            }
            outcomeObj["outcome"] = outcome
            outcomeObj["id"] = i
            if(specifiers[i] !== undefined)
            outcomeObj["specifiers"] = specifiers[i]+"="+specialValues[i].shift()
            market.push(outcomeObj)
            outcome = []
            outcomeObj = {}
         
        }
        }
        
        


        return market
    },

    getSourceOutcomeOdds: function(data) {
        var sourceOutcomeOdds = []
        for(var i = 0; i < data.length; i++){
            sourceOutcomeOdds.push(JSON.parse(Object.values(data[i])))
        }
        sourceOutcomeOdds = [].concat.apply([], sourceOutcomeOdds)

        sourceOutcomeIDPair = {}
        for(var i = 0; i < sourceOutcomeOdds.length; i++){
            var fields = sourceOutcomeOdds[i].sourceMarketOutcomeId.split('/')
            if(!(fields[0] in sourceOutcomeIDPair)){
                sourceOutcomeIDPair[fields[0]] = new Array()
            }
           
            sourceOutcomeIDPair[fields[0]].push(fields[1])
        }
        
        return sourceOutcomeIDPair
    },


    getMarketIDSpecialValuePairs: function(data){

        vals = []
        obj = {}
        for(var i = 0; i < data.length; i++){
            vals.push(Object.values(data[i]))
        }

        for(var i = 0; i < vals.length; i++){
            if(!(vals[i][0] in obj)){
                obj[vals[i][0]] = new Array()
            }

            obj[vals[i][0]].push(vals[i][1])

        }


        return obj;

    }
}
