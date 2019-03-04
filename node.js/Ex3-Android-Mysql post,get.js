var express = require('express')
var app = express()
var bodyParser = require('body-parser')


var mysql = require('mysql')

var connection = mysql.createConnection({
  host : "localhost",
  port : 3306,
  user : "root",
  password : "qq192837qq",
  database : "pj_pc"
})
console.log("ing");
app.get("/test", function (req, res) {
    connection.query('select pc_status from pc ', function (err, rows) {
        if (err) {
          console.log("err :"+err)
        }

        console.log('Open DataBase');
        res.json(rows);
    });
});

app.post('/post', (req, res) => {
    console.log('who get in here post /users');
    connection.query('select pc_status from pc ', function (err, rows) {
        if (err) {
          console.log("err :"+err)
        }

        console.log('Open DataBase');
        res.json(rows);
    });
});

app.listen(8888, () => {
    console.log('Example app listening on port 3000!');
});
/*

app.get('/', function() {
  connection.query("select * from pc"),
  function (error,result,fields) {
    if (error) {
      res.send('err : '+error)
    }
    else {
      console.log("test suc")
      res.send("testing sucsess")
    }
  }
})


app.post('/user', function (req,res) {

  var userID = req.body.id
  var userPW = req.body.pw

  if(userID && userPW) {
    connect.query("insert into user (userID, userPW) values ('"+ userID +"', '"+userPW+"')"),
    function (error,result,fields) {
      if (error) {
        res.send('err : '+error)
      }
      else {
        console.log(userID + ',' + userPW)
        res.send('success create user name: '+userID +' pw : ' + userPW)
      }
    }
  }
})


connection.connect();

var sql = 'SELECT * FROM pc';
connection.query(sql, function (err, rows, fields) {
  if (err) console.log(err);
  var R=rows;
  console.log('rows', rows); //row는 배열이다.
  console.log('fields', fields); //fields는 컬럼을 의미한다.
});

app.get('/',function (req, res) {
  res.send(rows)
})


connection.end();//접속이 끊긴다.
app.listen(8888,function() {
  console.log("server starting...")
})
*/
