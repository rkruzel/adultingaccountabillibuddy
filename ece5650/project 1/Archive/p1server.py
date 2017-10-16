import socket
import sys

class Node: # Customer Record Class 
	def __init__(self,customerID, customerFirstName, customerLastName, customerPhone):
		self.id = customerID
		self.first = customerFirstName
		self.last = customerLastName
		self.phone = customerPhone

	def display(self):
	# display record
		return 'Customer record: ' + str(self.id) + ' ; ' + self.first + ' ; ' + self.last + ' ; ' + self.phone

class customerDB: # Customer Database Class
	def __init__(self):
		self.db = []

	def display(self): # display all records 
		allRecords = ''
		for record in self.db:
			allRecords = allRecords + record.display() + '\n'
		if (allRecords == ''):
			return 'Database is empty!'
		else: 
			return allRecords 

	def insert(self, record): # insert a record
		self.db.append(record)
		return 'Operation was completed successfully.'

	def remove (self, customerID): # remove a record
		found = False
		for record in self.db:
			if (record.id  == customerID):
				self.db.remove(record)					
				found = True
				break
		if (found):
			return 'Operation was completed successfully.'
		else:
			return 'ERROR: No match was found!'

	def search(self, last): # search for all records with the specifiedlast name
		found = False
		matchingRecords = ''
		for record in self.db:
			if (record.last == last):
				matchingRecords = matchingRecords + record.display()
		if (matchingRecords == ''):
			return 'ERROR: No match was found!'
		else:
			return matchingRecords

	def show (self, customerID): # show record with specified ID
		found = False
		for record in self.db:
			if (record.id == customerID):
				return record.display()
				found = True
				break
		if (not found):
			return 'ERROR: No match was found!'

nextCustomerID = 0

cDB = customerDB()
# create customer database

HOST = "10.0.0.5"
PORT = 8889

servSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
servSocket.bind((HOST, PORT))

servSocket.listen(5)	#2.7 doesnt assign on no parameter given
serverRunning = True

while serverRunning:
	connection, address = servSocket.accept()	#blocks here til connection is made
	clientActive = True							#inner loop exit condition

	while clientActive:
		mOutSucc = "Operation was completed successfully."
		mOutErr = "ERROR: The operation is not supported!"
		message = connection.recv(1024)
		command = message.split()
		
		
		if (command[0] == "display"):
			mOut = customerDB.display(cDB)
			connection.send(mOut)
			
		elif (command[0] == "show"):
			mOut = customerDB.show(cDB,command[1])
			connection.send(mOut)
			
		elif (command[0] == "insert"):
			nextCustomerID = nextCustomerID +1
			record = Node(nextCustomerID,command[1],command[2],command[3])
			mOut = customerDB.insert(cDB,record)
			connection.send(mOut)
			

		elif (command[0] == "remove"):
			mOut = customerDB.remove(cDB, command[1])
			connection.send(mOut)
					
		
		elif (command[0] == "search"):
			mOut = customerDB.search(cDB, command[1])
			connection.send(mOut)
			
		
		elif (command[0] == "exit"):
			mOut = "Connection Closed"
			connection.send(mOut)
			clientActive = False
			serverRunning = False

		else:
			connection.send(mOutErr)
			

servSocket.close()	#end of program


