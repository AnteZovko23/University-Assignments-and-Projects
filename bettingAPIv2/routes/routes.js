const express = require('express'),
    router = express.Router();

var fs = require("fs");

var query = fs.readFileSync('./routes/BettingDatabaseQuery.sql').toString();


router.get('/odds', function(req, res){
    let sql = query;

    db.query(sql, function(err, data, fields) {
        if(err) throw err;
        console.log(data);

        res.json({
            "abc":123
    
    })
})
})
module.exports = router;