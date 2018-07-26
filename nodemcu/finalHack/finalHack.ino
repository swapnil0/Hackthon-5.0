/* Interface L298N With NodeMCU
 * By TheCircuit
 */

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

// Set these to run example.
#define FIREBASE_HOST "test500-74a43.firebaseio.com"
#define FIREBASE_AUTH "3YZAbPgLyJRLqpeFJMiKQsJFMCG1lB6qN2FnOgQT"
#define WIFI_SSID "swapnil"
#define WIFI_PASSWORD "ONLYME@121#"

int ENA = 13; //4;
int IN1 = 12; //0;
int IN2 = 14; //2;
int ENB = 4; //4;
int IN3 = 0; //0;
int IN4 = 2; //2;



void setup() {
  Serial.begin(9600);
   
  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
// set all the motor control pins to outputs
pinMode(ENA, OUTPUT);
pinMode(IN1, OUTPUT);
pinMode(IN2, OUTPUT);
pinMode(ENB, OUTPUT);
pinMode(IN3, OUTPUT);
pinMode(IN4, OUTPUT);
pinMode(15,OUTPUT );
}

// this function will run the motors in both directions at a fixed speed

void testOne() {
// turn on motor
digitalWrite(ENA, HIGH);  // set speed to 200 out of possible range 0~255
digitalWrite(IN1, HIGH);
digitalWrite(IN2, LOW);

delay(5000); // now change motor directions

digitalWrite(IN1, LOW);
digitalWrite(IN2, HIGH);

delay(5000); // now turn off motors

digitalWrite(IN1, LOW);
digitalWrite(IN2, LOW);
}

// this function will run the motors across the range of possible speeds
// note that maximum speed is determined by the motor itself and the operating voltage
// the PWM values sent by analogWrite() are fractions of the maximum speed possible by your hardware




void loop() {
 // testOne();
 
  Serial.print("number: ");
  float n=Firebase.getFloat("hand");
  Serial.println(n);
  if(n==0){
    digitalWrite(ENA, HIGH);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);
    
    } 
    else{
      digitalWrite(IN1, LOW);
      digitalWrite(IN2, LOW);
      }  

     if(n==2){
       digitalWrite(ENA, 100);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, HIGH);
      }
  float b=Firebase.getFloat("movement");
  Serial.println("Move");
  Serial.println(b);
 if(b==0){
    digitalWrite(ENB, HIGH);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    
    } 
    else{
      digitalWrite(IN3, LOW);
      digitalWrite(IN4, LOW);
      }  

     if(b==2){
    digitalWrite(ENB, 100);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN3, LOW);
    digitalWrite(IN4, HIGH);
      }

     float c=Firebase.getFloat("message");
      Serial.println(c);
     if(c==90){
              digitalWrite(15, HIGH);
      }
       if(c==100){
              digitalWrite(15, LOW);
      }
} 
