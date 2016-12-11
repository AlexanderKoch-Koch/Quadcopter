var http = require('http');

var server = http.createServer(function(request, response){
	console.log("got a request");
	response.write("hi i want to write a long text in order to test the speed of node js on a raspberry pi connected to a windows 10 pc with google chrome");
	response.end();
})

server.listen(3000);