GameSDK for Android
========================

* Authentication
* Billing
* Tracking

INSTALLATION
------------

**Download the official version: [click here](https://github.com/gosusdk/android-gamosdk/releases)**

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
    // google service (use firebase tracking & firebase analytic)
    classpath 'com.android.tools.build:gradle:7.4.2'
    classpath "com.google.protobuf:protobuf-gradle-plugin:0.9.4"
    classpath 'com.google.gms:google-services:4.4.2'
    classpath 'com.google.firebase:firebase-crashlytics-gradle:2.9.0'
}
```	
#### 2. In your module (app-level) Gradle file `<project>/<app-module>/build.gradle`, add more plugins dependency to your `build.gradle` file:

```gradle
// google service plugin (use firebase tracking)
apply plugin: 'com.google.gms.google-services'

dependencies {
    // ...
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    //add library (include: GamoSDK & ItsSDK)
    implementation fileTree(dir: "libs", include: ["*.aar"])

    //for showLogin facebook sdk
    implementation 'com.facebook.android:facebook-android-sdk:latest.release'
    //for in app billing
    implementation 'com.android.billingclient:billing:6.0.1'
    implementation 'com.google.guava:guava:31.1-android'

    //for sigin GG SDK
    implementation 'com.google.android.gms:play-services-auth:20.6.0'
    //for firebase
    implementation platform('com.google.firebase:firebase-bom:31.1.0')
    implementation 'com.google.firebase:firebase-analytics:21.3.0'
    implementation 'com.google.firebase:firebase-messaging:23.2.1'
    implementation("com.google.firebase:firebase-analytics")
    // biometric
    implementation "androidx.biometric:biometric:1.1.0"
    //airbridge
    implementation "io.airbridge:sdk-android:2.22.0"
 
    // GRPC Deps for GamoSDK & ItsSDK
    implementation 'io.grpc:grpc-okhttp:1.57.1'
    implementation 'io.grpc:grpc-protobuf-lite:1.57.1'
    implementation 'io.grpc:grpc-stub:1.57.1'
    compileOnly 'org.apache.tomcat:annotations-api:6.0.53' // necessary for Java 9+
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'com.android.installreferrer:installreferrer:2.2'
    implementation("com.google.android.play:review:2.0.1")
    implementation 'androidx.core:core:1.10.1'
    implementation "net.zetetic:sqlcipher-android:4.5.6@aar"
    implementation "androidx.sqlite:sqlite:2.3.1"
    implementation 'androidx.lifecycle:lifecycle-process:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-common:2.6.1'
    implementation 'androidx.browser:browser:1.8.0'
    implementation 'com.rudderstack.android.sdk:core:1.25.1'
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
  "client_id": "client_id",
  "its_app_write_key": "",
  "its_app_signing_key": "",
  "airb_app_name": "",
  "airb_app_token": ""
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
Merge XML manifest
<application
        tools:replace = "android:fullBackupContent"
        android:allowBackup = "true"
        android:fullBackupContent = "true"/>
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
<activity android:name="com.facebook.FacebookActivity"
    android:configChanges=
        "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
    android:label="@string/app_name" />
<activity
    android:name="com.facebook.CustomTabActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="@string/fb_login_protocol_scheme" />
    </intent-filter>
</activity>

<meta-data
    android:name="com.facebook.sdk.ApplicationId"
    android:value="@string/facebook_app_id"/>
<meta-data
    android:name="com.facebook.sdk.ClientToken"
    android:value="@string/facebook_client_token" />
<provider android:authorities="com.facebook.app.FacebookContentProvider116350609033094"
    android:name="com.facebook.FacebookContentProvider"
    android:exported="true"/>
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

The SDK supports tracking in-app events. To use it, you need to implement the `GrackingManager` module. For detailed information, refer to the code example below.
```java
GTrackingManger.getInstance().completeRegistration("User_id");
GTrackingManger.getInstance().completeTutorial();
```
For detailed information on tracking events, please refer to the [Tracking Guide](./TRACKING_GUIDE.md).

