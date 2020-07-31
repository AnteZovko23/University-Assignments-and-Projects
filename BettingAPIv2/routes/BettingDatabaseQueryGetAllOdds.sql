
SELECT eventID,
sportID, 
feedEventID,
sportID,
sportName,
startTime,
eventStatus,
active,
deleted,
marketID, 
marketActive, 
marketsSourceID,
marketsSpecialType,
outcomeID,
sourceOutcomeID,
specialOutcomeType,
specialOutcomeValue,
oddID,
specialStatus,
specialActive,
specialDeleted,
specialValue,
oddStatus,
odds FROM

(SELECT * FROM 


-- ********* outcomes with odds ************************
-- EventID, marketID, odds, sourceOutcomeID, specialValue from outcomesWithOdds
(SELECT oddsEventID as eventID, 
oddsMarketID as marketID, 
oddsOutcomeID as outcomeID,
oddsOdd as odds, 
outcomesSourceOutcomeID as sourceOutcomeID, 
outcomesSpecialOutcomeValue as specialOutcomeValue, 
outcomesspecialOutcomeType as specialOutcomeType, 
outcomesSpecialValue as specialValue,
outcomesMarketActive as marketActive,
specialStatus as specialStatus,
specialActive as specialActive,
specialDeleted as specialDeleted,
oddStatus as oddStatus,
oddsOddID as oddID FROM 

        -- ********* Odds ************************
        -- EventID, MarketID, ODD, OutcomeID From Odds
        (SELECT Odds.eventID as oddsEventID, 
        Odds.MarketID as oddsMarketID, 
        Odds.odd as oddsOdd, 
        Odds.outcomeID as oddsOutcomeID,
        Odds.oddID as oddsOddID ,
        Odds.status as oddStatus FROM Odds) as odds
        -- ********* Odds ************************

        INNER JOIN 

        -- ********* MarketOutcomes ************************
        -- OutcomeID, EventID, sourceOutcomeID, specialValue From MarketOutcomes
        (SELECT MarketOutcomes.id as outcomesID, 
        MarketOutcomes.eventID as outcomesEventID, 
        MarketOutcomes.sourceOutcomeID as outcomesSourceOutcomeID,
        MarketOutcomes.specialOutcomeValue as outcomesSpecialOutcomeValue,
        MarketOutcomes.specialOutcomeType as outcomesSpecialOutcomeType,
        MarketOutcomes.specialValue as outcomesSpecialValue ,
        MarketOutcomes.oddID as outcomesOddID,
        MarketOutcomes.active as outcomesMarketActive,
        MarketOutcomes.status as specialStatus,
        MarketOutcomes.marketActive as specialActive,
        MarketOutcomes.deleted as specialDeleted FROM MarketOutcomes) as outcomes 
        -- ********* MarketOutcomes ************************


ON odds.oddsOddID = outcomes.outcomesOddID AND odds.oddsOutcomeID = outcomes.outcomesID AND odds.oddsEventID = outcomes.outcomesEventID) as outcomesWithOdds
-- ********* outcomes with odds ************************

INNER JOIN 

-- ********* Markets ************************
-- EventID, marketID, sourceID, specialType
(SELECT Markets.eventID as marketsEventID, 
Markets.id as marketsID, 
Markets.sourceId as marketsSourceID, 
Markets.specialType as marketsSpecialType FROM Markets) as markets
-- ********* Markets ************************


ON outcomesWithOdds.eventID = markets.marketsEventID  AND outcomesWithOdds.marketID = markets.marketsID) as oddsNoEventInfo

INNER JOIN

(SELECT id,
feedEventId as feedEventID,
sportId as sportID,
sportName,
startAt as startTime,
Events.status as eventStatus,
active,
deleted 

FROM Events) as events

ON oddsNoEventInfo.eventID = events.id;