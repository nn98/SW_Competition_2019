var express = require('express');
var app = express();
app.get('/',function (req, res) {
  res.send("Hello World")
})

app.get('/pass', function(req,res) {
  var data = req.query.data
  res.send(data)

})

app.listen(8888, function () {
  console.log("server starting with 8888")
})
