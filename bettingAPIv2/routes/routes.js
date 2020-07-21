const express = require('express'),
    router = express.Router();


router.get('/odds', function(req, res){
    let sql = "SELECT * FROM Market;"

    db.query(sql, function(err, data, fields) {
        if(err) throw err;


        res.json({
            "abc":123
    
    })
})
})
module.exports = router;