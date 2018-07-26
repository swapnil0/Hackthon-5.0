/* Interface L298N With NodeMCU
 * By TheCircuit
 */

#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <Servo.h>
Servo myservo;
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
float s=90;
int a=90;


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
myservo.attach(0);
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

  float n=Firebase.getFloat("truck_steer");
  s=Firebase.getFloat("truck_steer");  
  Serial.println(n);
    Serial.println(s);
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
    digitalWrite(ENA, HIGH);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, HIGH);
    
    } 
    if(s==0){
      a=+5;
       myservo.write(a);              // right
      }
      if(s==1){
        
       myservo.write(a);              //Straight
      }
      if(s==2){
        a=-10;
       myservo.write(a);              // left
      }
      
}
