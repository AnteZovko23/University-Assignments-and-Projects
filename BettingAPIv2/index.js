/**
 * Creates endpoints that serve event information
 * 
 * Endpoints: /odds -> gets all odds from all events
 *            /odds/sport -> gets all sportIDs and names
 *            /odds/sportID:{sportID} -> gets all odds from a specific sport
 *            /odds/since:{since} -> gets all odds from a given date
 *            /odds/sport:{sport}/since:{since} -> gets all odds from a given sport and date         
 * 
 */




// Overhead
const express = require('express'),
app = express(),
mysql = require('mysql'),
cors = require('cors'),
bodyParser = require('body-parser');
//*********** */


// Opens connection to database
db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'ChoosePassword',
    database: 'BettingDatabaseV2',
    multipleStatements: true
})

var server = {
    port: 4030
}

// Gets endpoints
const usersRouter = require('./routes/routes');

// Starts server
app.use(cors())
app.use(bodyParser.json());
app.use(usersRouter);
app.listen(server.port, () => console.log('Server Started'));