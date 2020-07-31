SELECT events.id as eventID,
        feedEventId,
        status,
        active,
        deleted,
        sportId,
        sportName,
        categoryId,
        categoryName,
        tournamentId,
        tournamentName,
        startAt,
        co.id as eventTeamID,
        co.name as teamName,
        teamId,
        co.type as type

FROM (SELECT * FROM Events) as events INNER JOIN (SELECT * FROM Competitors) as co ON events.id = co.eventID;