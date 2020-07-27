CREATE TABLE `Odds2` (
  `eventID` varchar(180) NOT NULL,
  `marketID` varchar(180) NOT NULL,
  `outcomeID` varchar(255) NOT NULL,
  `oddID` varchar(180) NOT NULL,
  `id` varchar(180) NOT NULL,
  `status` varchar(255) NOT NULL,
  `odd` varchar(255) NOT NULL,
  PRIMARY KEY (`eventID`,`marketID`,`oddID`,`id`),
  KEY `fk_Odds_2_idx` (`outcomeID`),
  KEY `fk_Odds_2_idx1` (`oddID`),
  KEY `fk_Odds_2_idx2` (`outcomeID`,`oddID`,`eventID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
