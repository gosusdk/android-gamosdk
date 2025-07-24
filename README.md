GameSDK for Android v2.0.0
========================

**Latest Gaming SDK with Enhanced Features**

* Authentication & User Verification
* WebView TopUp System  
* Billing & Payment
* Event Tracking & Analytics
* Government Compliance Support
* Enhanced Security & Privacy

## What's New in v2.0.0

### 🚀 **New Features**
- **WebView TopUp System**: Built-in top-up functionality with seamless payment experience
- **Government Compliance**: Enhanced user verification to comply with national regulations
- **Anonymous Key Management**: Automatic rate limiting and privacy protection
- **Improved Error Messages**: User-friendly and developer-friendly error handling
- **Advanced Security**: Enterprise-grade ProGuard security implementation

### 🔧 **System Upgrades**
- **Modern Build System**: Updated to Gradle 8.4, AGP 8.1.4, Java 17
- **Latest Android**: Target SDK 35, Compile SDK 35
- **Updated Dependencies**: Android Billing Client 8.0.0, Firebase latest versions
- **ITS SDK 1.1.1**: Enhanced analytics and tracking capabilities

### ⚠️ **Breaking Changes**
- **Login Callback**: `onLoginSuccess` now uses JSONObject instead of separate parameters
- **Java 17 Required**: Minimum development requirement updated
- **Build Tools**: Requires Gradle 8.4+ and AGP 8.1.4+

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
    classpath 'com.android.tools.build:gradle:8.1.4'  // Updated for v2.0.0
    classpath "com.google.protobuf:protobuf-gradle-plugin:0.9.4"
    classpath 'com.google.gms:google-services:4.4.2'
    classpath 'com.google.firebase:firebase-crashlytics-gradle:2.9.0'
}
```	
#### 2. In your module (app-level) Gradle file `<project>/<app-module>/build.gradle`, add more plugins dependency to your `build.gradle` file:

```gradle
// google service plugin (use firebase tracking)
apply plugin: 'com.google.gms.google-services'
apply plugin: 'com.google.firebase.crashlytics'  // Added for v2.0.0

android {
    compileSdk 35      // Updated for v2.0.0
    
    defaultConfig {
        targetSdk 35   // Updated for v2.0.0
        minSdkVersion 26
        // ...
    }
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17  // Updated for v2.0.0
        targetCompatibility JavaVersion.VERSION_17  // Updated for v2.0.0
    }
    namespace 'your.package.name'  // Required for v2.0.0
}

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
    implementation 'com.android.billingclient:billing:8.0.0'  // Updated for v2.0.0
    implementation 'com.google.guava:guava:31.1-android'

    //for sigin GG SDK
    implementation 'com.google.android.gms:play-services-auth:20.6.0'
    //for firebase
    implementation platform('com.google.firebase:firebase-bom:31.1.0')
    implementation 'com.google.firebase:firebase-analytics:21.3.0'
    implementation 'com.google.firebase:firebase-messaging:23.2.1'
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
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
This file information will be sent separately by email by the product operator.
```json
{
  "server_address": "sample_value",
  "server_port": 0,
  "negotiation_type": "sample_value",
  "client_id": "sample_value",
  "its_app_write_key": "sample_value",
  "its_app_signing_key": "sample_value",
  "airb_app_name": "sample_value",
  "airb_app_token": "sample_value"
}
```
#### 4. Edit Your Resources and Manifest
**- Open the /app/res/values/strings.xml file.**

```xml
<string name="facebook_app_id">1234</string>
<string name="fb_login_protocol_scheme">fb1234</string>
<string name="facebook_client_token">56789</string>
```
##### 4.1 Add file config rule backup

**-Add new  /app/src/main/res/xml/backup_rules_11.xml**
```xml
<full-backup-content>
<exclude domain="sharedpref" path="its_prefs.xml"/>
<exclude domain="sharedpref" path="rl_prefs.xml"/>
</full-backup-content>
```

**-Add new  /app/src/main/res/xml/backup_rules_12.xml**
```xml
<data-extraction-rules>
<cloud-backup>
<exclude domain="sharedpref" path="its_prefs.xml"/>
<exclude domain="sharedpref" path="rl_prefs.xml"/>
</cloud-backup>
</data-extraction-rules>
```

**-Open the /app/manifest/AndroidManifest.xml file.**
```xml
Merge XML manifest
<application
        tools:replace = "android:fullBackupContent"
        android:allowBackup = "true"
        android:fullBackupContent="@xml/backup_rules_11"
        android:dataExtractionRules="@xml/backup_rules_12"
/>

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
            public void onLoginSuccess(JSONObject loginData) {  // Updated for v2.0.0
                //invoked when login successfully
                try {
                    String userId = loginData.getString("user_id");
                    String userName = loginData.getString("user_name");
                    String accessToken = loginData.getString("auth_token");
                    String checksum = loginData.getString("auth_checksum");
                    String timestamp = loginData.getString("auth_timestamp");
                    // Your code here
                } catch (JSONException e) {
                    // Handle JSON parsing error
                }
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

1. GosuSDK Basic Functions
---
```java
//login
GameSDK.showLogin();

//Logout
GameSDK.logout();

//WebView TopUp (New in v2.0.0)
GameItemWebTopupObject topupObject = new GameItemWebTopupObject(
    "product_id", 
    "product_name", 
    "amount", 
    "currency",
    "user_id"
);
GameSDK.showTopUp(topupObject, new IGameTopupListener() {
    @Override
    public void onTopupSuccess(String message) {
        // Handle successful top-up
    }
    
    @Override
    public void onTopupError(String error) {
        // Handle top-up error
    }
});

//User Scope Validation (New in v2.0.0)
GameSDK.validateScopeOfUser();
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

The SDK supports tracking in-app events with enhanced analytics capabilities (ITS SDK 1.1.1). To use it, you need to implement the `GTrackingManager` module. For detailed information, refer to the code example below.

```java
// Basic tracking events
GTrackingManger.getInstance().completeRegistration("User_id");
GTrackingManger.getInstance().completeTutorial();

// Gaming-specific events  
GTrackingManger.getInstance().createNewCharacter("server_info", "char_id", "char_name");
GTrackingManger.getInstance().enterGame("user_id", "char_id", "char_name", "server_info");
GTrackingManger.getInstance().levelUp("char_id", "server_info", level);

// Custom events
JSONObject customData = new JSONObject();
customData.put("key", "value");
GTrackingManger.getInstance().trackCustomEvent("event_name", customData);
```

For detailed information on tracking events, please refer to the [Tracking Guide](./TRACKING_GUIDE.md).

## Migration from v1.x to v2.0.0

### Required Updates
1. **Update Java to 17**: Update your development environment
2. **Update Build Tools**: Update Gradle to 8.4+ and AGP to 8.1.4+
3. **Update Login Callback**: Change `onLoginSuccess` method signature to use JSONObject
4. **Update Billing Client**: Update billing integration for 8.0.0 API changes
5. **Add Namespace**: Add namespace declaration in your app's build.gradle

### Optional Features
- **WebView TopUp**: Integrate new top-up functionality if needed
- **Scope Validation**: Use government compliance features if required
- **Anonymous Key Management**: Automatically handled by the SDK

## System Requirements

- **Minimum SDK**: Android API 26+
- **Target SDK**: Android API 35
- **Java Version**: Java 17+ (required for development)
- **Gradle**: 8.4+
- **Android Gradle Plugin**: 8.1.4+

## Support & Documentation

- **Changelog**: [CHANGELOG.md](./CHANGELOG.md)
- **Tracking Guide**: [TRACKING_GUIDE.md](./TRACKING_GUIDE.md)
- **Download**: [Latest Release](https://github.com/gosusdk/android-gamosdk/releases)

