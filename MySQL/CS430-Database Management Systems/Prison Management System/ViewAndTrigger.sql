USE Prison_System;


CREATE VIEW PrisonerInfoA AS 
SELECT Inmate.PID as PID, Parole, Security_Level, Sentence,
Sentence_Length, Bail, Lives_in.B_Name as Block FROM Inmate JOIN Lives_in ON Lives_in.B_Name = 'A' AND Inmate.PID = Lives_in.PID; 

SELECT * FROM PrisonerInfoA;



DELIMITER $$

CREATE TRIGGER InmateSecurityLevel
BEFORE INSERT on Lives_in
FOR EACH ROW
BEGIN
    DECLARE msg VARCHAR(255);
    DECLARE cellBlockSecurity int;
    DECLARE inmateSecurity int;
    SELECT Security_Level INTO inmateSecurity 
    FROM Inmate WHERE PID = NEW.PID;
    
    SELECT Security_Level INTO cellBlockSecurity
    FROM Cellblock WHERE B_Name = NEW.B_Name;
    
    
    if (cellBlockSecurity != inmateSecurity) THEN
    set msg = 'Invalid Inmate Placement';
    SIGNAL SQLSTATE '45000' set MESSAGE_TEXT = msg;
    ENd IF;

END
$$

DELIMITER ;