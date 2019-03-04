var express = require('express');
var app = express();
var bodyParser = require('body-parser')

app.use(bodyParser.urlencoded({ extended: true}))

app.post('/user',function(req,res) {

  var userID = req.body.id
  var userPW = req.body.pw

  res.send('id : '+userID+'pw : '+userPW)

})

app.listen(8888,function() {
  console.log("server starting 8888")
})
