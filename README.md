GameSDK for Android
========================

* Authenticate
* Billing
* Tracking

INSTALLATION
------------

**Download the official version: [click here](https://github.com/gosusdk/android_ginsdk/releases)**

#### 1. In your root-level (project-level) Gradle file `<project>/build.gradle`, add more plugins dependency to your `build.gradle` file:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url "https://sdk-download.airbridge.io/maven" }
    }
}
dependencies {
    // ...
    // google service (use firebase tracking)
    classpath 'com.google.gms:google-services:4.3.15'
}
```	
#### 2. In your module (app-level) Gradle file `<project>/<app-module>/build.gradle`, add more plugins dependency to your `build.gradle` file:

```gradle
// google service plugin (use firebase tracking)
apply plugin: 'com.google.gms.google-services'

dependencies {
    // ...
    // GameSDK
    implementation files('libs/gamesdk-v1.1.7.aar')
    //for in app billing
    implementation 'com.android.billingclient:billing:6.0.1'
    //for appsflyer
    implementation 'com.appsflyer:af-android-sdk:6.3.2'
    implementation 'com.android.installreferrer:installreferrer:2.2'
    //for showLogin facebook sdk
    implementation 'com.facebook.android:facebook-android-sdk:latest.release'
    //for sigin GG SDK
    implementation 'com.google.android.gms:play-services-auth:20.6.0'
    //for firebase
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:31.1.0')
    implementation 'com.google.guava:guava:31.1-android'
    implementation 'com.google.firebase:firebase-messaging:23.2.1'
    implementation 'com.google.firebase:firebase-analytics'
    // GRPC Deps
    implementation 'io.grpc:grpc-okhttp:1.57.1'
    implementation 'io.grpc:grpc-protobuf-lite:1.57.1'
    implementation 'io.grpc:grpc-stub:1.57.1'
    compileOnly 'org.apache.tomcat:annotations-api:6.0.53'
    //airbridge
    implementation "io.airbridge:sdk-android:2.22.0"
}
```	
**-Move config file (google-services.json) into the module (app-level) root directory of your app.**
```
app/
  google-services.json
```

**- Add gosu-service.json file to folder main/assets**
```json
{
  "client_id": "",
  "airb_app_name": "sdkgosutest",
  "airb_app_token": "d878da2af447440385fe9a4fe37b06a0"
}
```
#### 4. Edit Your Resources and Manifest
**- Open the /app/res/values/strings.xml file.**
```xml
<string name="facebook_app_id">1234</string>
<string name="fb_login_protocol_scheme">fb1234</string>
<string name="facebook_client_token">56789</string>
```
**-Open the /app/manifest/AndroidManifest.xml file.**
```xml
<!-- ============ PERMISSION ============== -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- use for Push GSM -->
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
<!-- use for iab -->
<uses-permission android:name="com.android.vending.BILLING" />
<uses-permission android:name="com.google.android.gms.permission.AD_ID" />

<!-- ============ Facebook META config ============== -->
<meta-data
    android:name="com.facebook.sdk.ApplicationId"
    android:value="@string/facebook_app_id"/>
<meta-data
    android:name="com.facebook.sdk.ClientToken"
    android:value="@string/facebook_client_token" />
<provider android:authorities="com.facebook.app.FacebookContentProvider116350609033094"
    android:name="com.facebook.FacebookContentProvider"
    android:exported="true"/>

<!-- ======= AF Tracking ======= -->
<receiver
    android:name="com.appsflyer.MultipleInstallBroadcastReceiver"
    android:exported="true" >
    <intent-filter>
        <action android:name="com.android.vending.INSTALL_REFERRER" />
    </intent-filter>
</receiver>

<receiver
    android:name="com.appsflyer.SingleInstallBroadcastReceiver"
    android:exported="true" >
    <intent-filter>
        <action android:name="com.android.vending.INSTALL_REFERRER" />
    </intent-filter>
</receiver>
```
USAGE GOSU LOGIN SDK
--------------------
1. Initialize configuration for GosuSDK
---
```java
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        GameSDK.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onCreate(Bundle savedInstanceState)()
    {
        // ...
        //Initialize SDK
        GameSDK.sdkInitialize(this,  new IGameInitListener() {
            @Override
            public void onSuccess() { //invoked when login successfully
                onLogin();
            }
            @Override
            public void onError(GameException exception) {
                exception.printStackTrace();
            }
        });
    }
    private void onLogin() {
        GameSDK.onLogin(new IGameOauthListener() {
            @Override
            public void onLoginSuccess(String UserId, String UserName, String accesstoken) {
                //invoked when login successfully
            }
            @Override
            public void onLogout() {
                 //invoked when logout successfully
                GameSDK.showLogin();
            }

            @Override
            public void onError() {
                //invoked when login failed
            }
        });
}
```
**NOTE**
* Login with Google: You send SHA-1 us [click here](https://developers.google.com/android/guides/client-auth)
* Login with Facebook: You send hash key us [more here](https://developers.facebook.com/docs/facebook-login/android)

2. GosuSDK Basic Functions
---
```java
//login
GameSDK.showLogin();
//Logout
GameSDK.logout();

```
3. Paying on Google Play with GosuSDK
---
```java
public void call_billing()
{
    String productID = "thb.stand.gg.pack8";
    String productName = "Mua gói 100KNB";
    String currencyUnit = "USD";
    String amount = "22000";
    String serverID       = "S1";
    String characterID    = "Character_ID";
    String extraInfo    = "";

    GameItemIAPObject gosuItemIAPObject = new GameItemIAPObject(
        productID, 
        productName, 
        currencyUnit, 
        amount, 
        serverID, 
        characterID, 
        extraInfo
    );
    GameSDK.payment(gosuItemIAPObject, new IGamePaymentListener() {
        @Override
        public void onPaymentSuccess(String message) {
            Log.d("T123", message);
        }

        @Override
        public void onPaymentError(String message) {
            Log.d("T123", message);

        }
    });

    /**
     * productID:ID of the product
     * productName:Name of the product
     * currencyUnit:currency unit
     * amount:	Price of the item
     * serverID: ID of the server
     * characterID: ID of the character
     * extraInfo: Additional information that partners can send, which will be sent to the API to add gold after IAP payment.
     */
}
```

USAGE GOSU TRACKING SDK
--------------------

```java
GTrackingManger.getInstance().trackingStartTrial();
GTrackingManger.getInstance().trackingTutorialCompleted();
GTrackingManger.getInstance().doneNRU(
        "server_id",
        "role_id",
        "Role Name"
);
/* custom event */
GTrackingManger.getInstance().trackingEvent("level_20");
GTrackingManger.getInstance().trackingEvent("level_20", "{\"customer_id\":\"1234\"}");
/* example: 
jsonContent = {"event": "event_name", "params": {"key": "value", "key2": "value2"} }
*/
JSONObject jsonContent = new JSONObject();
jsonRole.put("character", "CharacterName");
jsonRole.put("server", "ServerID");        
GTrackingManger.getInstance().trackingEvent("event_name", jsonContent);
```
