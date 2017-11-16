#include <WiFi.h>
#include <SPI.h>
#include <WiFiClient.h>
/* ######### robot wifi connection logic ##############
##### by Robert Kruzel for Capstone Project F2017 #####*/


// arrays
char ssid[] = "";
char pass[] = "";

int status = WL_IDLE_STATUS;

IPAddress server(10.0.0.5);
int port = 4444;

int robotID;

WiFiClient client;


void setup() 
{
  Serial.begin(9600);
  Serial.print("Connecting...");
  
  // connect to WiFi
  status = WiFi.begin(ssid, pass);
  
  while (status != WL_CONNECTED);
  {
    // 5 sec timeout waiting for connection
    // loop til wifi connection established
    delay(5000);
  } 
  Serial.println("Established Connection to WiFi Network");

  // open socket connection
  Serial.println("Creating socket");

  while (!client.connect(server, port)
  {
      if (!client.connect(server, port)
      {
      Serial.println("Socket Connection Failed");
      }
      else
      {
      Serial.println("Socket Connection Succeeded.");
      }
  }
}

void loop() 
{ 
  // reconnection logic
  if (!WiFi.connect(ssic, pass)
  {
    Serial.println("Attempting WiFi Reconnect.");
  }
  
  if (!client.connect(server, port)
  {
    Serial.println("Attempting Socket Reconnect.");
  }
  
  // operational main loop code goes here




    
  
  // look for data from server on input buffer
  if (client.available() > 0)
    {
      // get value from input buffer, flushes buffer
      char readByte = client.read();

      server.write(readByte);
      parseMessage(server.read());
    }
  } 
}

void parseMessage(String string)
{
  // handle message decoding
  String message = string;

  /* arduino doesnt have string split, we need to 
   write TCP protocol such that size is constant
  and fields are always in the same position in string 
  this is implimented with String data = string.substring(start, end)
  */


  
}

