var express = require('express');
var mysql = require('mysql');
var app = express();
var connection = mysql.createConnection({
    host: '58.121.52.237',
    user: 'Project',
    password: 'testing00',
    database: 'pj_pc',
    port: 3306
});
app.get("/test", function (req, res) {
    connection.query('select pc_status from pc ', function (err, rows) {
        if (err) throw err;

        console.log('Open DataBase');
        res.json(rows);
    });
    app.post('/post', (req, res) => {
        console.log('who get in here post /users');
        var inputData;

        req.on('data', (data) => {
            inputData = JSON.parse(data);
        });

        req.on('end', () => {
            console.log("SSIBAR;");
        });

        res.write("OK!");
        res.end();
    });

    app.listen(3000, () => {
        console.log('Example app listening on port 3000!');
    });

});
