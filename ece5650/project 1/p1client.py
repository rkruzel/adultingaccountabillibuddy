
import socket 
HOST = "96.27.14.81"

PORT = 8889

clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
clientSocket.connect((HOST, PORT)) 
while True: 
	inputFromKeyboard = raw_input('Enter command: ') # get command from user
	clientSocket.send(inputFromKeyboard) # send command to server
	if (inputFromKeyboard=='exit'): # If input from user is 'exit', close connection
		break
	
# print 'waiting for response from server...'
	receivedMessage = clientSocket.recv(1024) # Get reply from server
# print 'server response received: '
	print receivedMessage # Print the reply on the screen     
clientSocket.close()
