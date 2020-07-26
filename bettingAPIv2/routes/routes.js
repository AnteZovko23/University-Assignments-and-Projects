const express = require('express'),
    router = express.Router();


router.get('/odds', function(req, res){
    let sql = "SELECT eventID,sportID,feedEventID,sportName,startTime,eventStatus,active,deleted,marketID,marketActive,marketsSourceID,marketsSpecialType,outcomeID,sourceOutcomeID,specialOutcomeType,specialOutcomeValue,oddID,specialStatus,specialActive,specialDeleted,specialValue,oddStatus,odds FROM(SELECT*FROM(SELECT oddsEventID as eventID,oddsMarketID as marketID,oddsOutcomeID as outcomeID,oddsOdd as odds,outcomesSourceOutcomeID as sourceOutcomeID,outcomesSpecialOutcomeValue as specialOutcomeValue,outcomesspecialOutcomeType as specialOutcomeType,outcomesSpecialValue as specialValue,outcomesMarketActive as marketActive,specialStatus as specialStatus,specialActive as specialActive,specialDeleted as specialDeleted,oddStatus as oddStatus,oddsOddID as oddID FROM(SELECT Odds.eventID as oddsEventID,Odds.MarketID as oddsMarketID,Odds.odd as oddsOdd,Odds.outcomeID as oddsOutcomeID,Odds.oddID as oddsOddID,Odds.status as oddStatus FROM Odds)as odds INNER JOIN(SELECT MarketOutcomes.id as outcomesID,MarketOutcomes.eventID as outcomesEventID,MarketOutcomes.sourceOutcomeID as outcomesSourceOutcomeID,MarketOutcomes.specialOutcomeValue as outcomesSpecialOutcomeValue,MarketOutcomes.specialOutcomeType as outcomesSpecialOutcomeType,MarketOutcomes.specialValue as outcomesSpecialValue,MarketOutcomes.oddID as outcomesOddID,MarketOutcomes.active as outcomesMarketActive,MarketOutcomes.status as specialStatus,MarketOutcomes.marketActive as specialActive,MarketOutcomes.deleted as specialDeleted FROM MarketOutcomes)as outcomes ON odds.oddsOddID=outcomes.outcomesOddID AND odds.oddsOutcomeID=outcomes.outcomesID AND odds.oddsEventID=outcomes.outcomesEventID)as outcomesWithOdds INNER JOIN(SELECT Markets.eventID as marketsEventID,Markets.id as marketsID,Markets.sourceId as marketsSourceID,Markets.specialType as marketsSpecialType FROM Markets)as markets ON outcomesWithOdds.eventID=markets.marketsEventID AND outcomesWithOdds.marketID=markets.marketsID)as oddsNoEventInfo INNER JOIN(SELECT id,feedEventId as feedEventID,sportId as sportID,sportName,startAt as startTime,Events.status as eventStatus,Events.active,deleted FROM Events)as events ON oddsNoEventInfo.eventID=events.id;"

    db.query(sql, function(err, data, fields) {
        if(err) throw err;
        console.log(data);

        res.json({
            "abc":123
    
    })
})
})
module.exports = router;