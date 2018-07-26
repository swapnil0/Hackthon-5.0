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

}
void loop() {
 // testOne();
 
  Serial.print("number: ");
  float forward=Firebase.getFloat("drive");
  Serial.println(forward);
  if(forward==0){
    digitalWrite(ENA, 10);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN1, HIGH);
    digitalWrite(IN2, LOW);
    digitalWrite(ENB, 10);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN3, HIGH);
    digitalWrite(IN4, LOW);
    } 
  
     if(forward==2){
     digitalWrite(ENA, 10);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, HIGH);
    digitalWrite(ENB, 100);  // set speed to 200 out of possible range 0~255
    digitalWrite(IN3, LOW);
    digitalWrite(IN4, HIGH);
      }

      if(forward==3){
        digitalWrite(ENA, 10);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN1, HIGH);//Left Right
        digitalWrite(IN2, LOW);
        digitalWrite(ENB, 10);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, HIGH);
        } 

        if(forward==4){
        digitalWrite(ENA, 10);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN1, LOW);//Left Right
        digitalWrite(IN2, HIGH);
        digitalWrite(ENB, 10);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN3, HIGH);
        digitalWrite(IN4, LOW);
    
        }

        if(forward==1){
           digitalWrite(ENA,LOW);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN1, LOW);//Left Right
        digitalWrite(IN2, LOW);
        digitalWrite(ENB, LOW);  // set speed to 200 out of possible range 0~255
        digitalWrite(IN3, LOW);
        digitalWrite(IN4, LOW);
          }
      
}
