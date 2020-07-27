/****************** */
// Helper Functions
// Keeps routes.js clean
var eventMarketStatuses = {
    "2":"suspended",
    "3":"deactivated",
    "4":"cancelled"

}

module.exports = {

    getMarket : function(eventData, eventMarketsData){
        // Create array of outcomes for each market
        console.log(eventMarketsData);
        oddObj = {}
        outcome = []
        market = []
        outcomeObj = {}
        marketObj = {}
        oddsObj = {}
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
            if(outcomeObj["status"] === undefined){
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
        
        


        return oddObj
    }


}