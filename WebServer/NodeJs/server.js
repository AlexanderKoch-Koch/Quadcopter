var http = require('http'),
    url = require('url'),
    path = require('path'),
    fs = require('fs'),
	jsonSocket = require('json-socket');

var webSocketClients = [];
var domainSocketClient;
///////////////////////////////////////////////////////////
//create http server	
var mimeTypes = {
    "html": "text/html",
    "jpeg": "image/jpeg",
    "jpg": "image/jpeg",
    "png": "image/png",
    "js": "text/javascript",
    "css": "text/css"};
	

var server = http.createServer(function(req, res) {
    var uri = url.parse(req.url).pathname;
	if(uri == "/"){
		uri = "/index.html";
	}
	var filePath = "/home/pi/WebServer/htdocs" + uri;
    var filename = path.join(process.cwd(), uri);
    fs.exists(filePath, function(exists) {
        if(!exists) {
            console.log("not exists: " + uri);
            res.writeHead(200, {'Content-Type': 'text/plain'});
            res.write('404 Not Found\n');
            res.end();
			return;
        }
        var mimeType = mimeTypes[path.extname(filePath).split(".")[1]];
        res.writeHead(200, {'Content-Type':mimeType});

        var fileStream = fs.createReadStream(filePath);
        fileStream.pipe(res);

    }); //end path.exists
});
//////////////////////////////////////////////////////////////////////////////////////////
//configure socket.io
var io = require("socket.io") (server);
io.listen(server);

function sendTime() {
    io.emit('time', { time: new Date().toJSON() });
	console.log('time', { time: new Date().toJSON() });
}



io.on('connection', function(socket) {
	webSocketClients.push(socket);
    // Use socket to communicate with this particular client only, sending it it's own id
    socket.emit('welcome', { message: 'Welcome!', id: socket.id });
	//number = 0;
    socket.on('i am client', console.log);
	
	socket.on("message", function(data){
		if(domainSocketClient != null){
			domainSocketClient.write(JSON.stringify(data) + "\n");
			console.log("routing message from socket.,io: " + JSON.stringify(data));
		}
		
	});
});


var number = 0;
setInterval(sendNumber, 1);

function sendNumber(){
	io.emit("number", {number: number});
	number++;
}


//////////////////////////////////////////////////////////////////////////////////////////
//configure internal serevr socket
var net = require('net');

var HOST = '127.0.0.1';
var PORT = 5000;

// Create a server instance, and chain the listen function to it
// The function passed to net.createServer() becomes the event handler for the 'connection' event
// The socket object the callback function receives UNIQUE for each connection
net.createServer(function(socket) {
	socketJson = new jsonSocket(socket);
	socket.write("hello\n");
    domainSocketClient = socket;
    // We have a connection - a socket object is assigned to the connection automatically
    console.log('CONNECTED: ' + socket.remoteAddress +':'+ socket.remotePort);
    
	setInterval(sendSocketMessage, 10000);
	function sendSocketMessage(){
		socket.write(new Date().toJSON() + "\n");
	}

    // Add a 'data' event handler to this instance of socket
    socketJson.on('data', function(data) {
        
        console.log('DATA testString' + socket.remoteAddress + ': ' + data);
		console.log('DATA message: ' + data.message);
		console.log('DATA message: ' + typeof data);
		try{
			var json = JSON.parse(data);
		}catch(ex){
			console.log("couldn't convert received data from unix domain socket into Json Object: " + ex);
		}
		
        io.emit("message", json);
        
    });
	
	socket.on('message', function(message) {
        console.log("test");
        io.emit("message", message);
        
    });
    
    // Add a 'close' event handler to this instance of socket
    socket.on('close', function(data) {
        console.log('CLOSED: ' + socket.remoteAddress +' '+ socket.remotePort);
		socket.destroy();
    });
	
	socket.on('error', function (ex) {
		if(ex == "Error: This socket has been ended by the other party"){
			//do nothing
		}else if(ex == "Error: This socket is closed"){
			//do nothing
		}else{
			console.log("ignoring exception: " + ex);
		}
	});
    
}).listen(PORT, HOST);


server.listen(8080);