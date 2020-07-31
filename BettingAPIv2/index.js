const express = require('express'),
app = express(),
mysql = require('mysql'),
cors = require('cors'),
bodyParser = require('body-parser');


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


const usersRouter = require('./routes/routes');

app.use(cors())
app.use(bodyParser.json());
app.use(usersRouter);
app.listen(server.port, () => console.log('Server Started'));