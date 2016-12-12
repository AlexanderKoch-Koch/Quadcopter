var http = require('http');
var fs = require('fs');
    // NEVER use a Sync function except at start-up!
var index = fs.readFileSync("/home/pi/server/clientFiles" + '/index.html');
	
//TCP Socket/////////////////////////////////////////////////////////////////////////
var net = require('net');

var HOST = '127.0.0.1'; //localhost
var PORT = 8550;  //port java app listens on

net.createServer(function(sock) {
    
    // We have a connection - a socket object is assigned to the connection automatically
    console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);
    
    // Add a 'data' event handler to this instance of socket
    sock.on('data', function(data) {
        //received something from the java app
        console.log('DATA ' + sock.remoteAddress + ': ' + data);
        // Write the data back to the socket, the client will receive it as data from the server
        sock.write('You said "' + data + '"');
        io.emit('DATA ' + sock.remoteAddress + ': ' + data);
    });
    
    // Add a 'close' event handler to this instance of socket
    sock.on('close', function(data) {
        console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
		io.emit('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
    });
    
}).listen(PORT, HOST);

console.log('Server listening on ' + HOST +':'+ PORT);

//////////////////////////////////////////////////////////////////////////////////////////






// Send index.html to all requests
var httpServer = http.createServer(function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.end(index);
});



// Socket.io server listens to our app
var io = require('socket.io').listen(httpServer);

// Send current time to all connected clients
function sendTime() {
    io.emit('time', { time: new Date().toJSON() });
}
// Send current time every 10 secs
setInterval(sendTime, 10000);



// Emit welcome message on connection
io.on('connection', function(socket) {
    // Use socket to communicate with this particular client only, sending it it's own id
    socket.emit('welcome', { message: 'Welcome!', id: socket.id });

    socket.on('i am client', console.log);
	socket.on("message", function(msgText){
		console.log(msgText + " received");
	})
});

httpServer.listen(3000);