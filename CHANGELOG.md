# Changelog

All notable changes to the Android Gaming SDK.
## [2.0.3] - 2025-12-12

### 🐛 Bug Fixes

#### Duplicate OpenGameLog API Call
- **Fixed**: Duplicate calling of OpenGameLog API when GetProfile success
  - **Impact**: Prevents redundant API calls and improves performance

#### Error Message Localization
- **Fixed**: Error messages now follow app language settings
  - **Impact**: Improved user experience with proper localized error messages

### 📦 Build Updates
- **Version**: Updated build version configuration
- **Release**: Version 2.0.3 release build

---

## [2.0.2] - 2024-11-24

### 🚀 New Features

#### AppsFlyer Integration
- **AppsFlyer Tracking Module**: Added comprehensive AppsFlyer SDK integration for advanced attribution and analytics

#### Enhanced SDK Initialization
- **SDKOptions Configuration**: Introduced flexible SDK initialization options with modular enable/disable controls
  - **Module Control**: Enable/disable specific tracking modules using `.enable()` methods
    - `SDKOptions.builder().enableAppsFlyer(true/false)` - Control AppsFlyer integration
    - `SDKOptions.builder().enableITS(true/false)` - Control ITS tracking module
    - `SDKOptions.builder().enableFirebaseAnalytics(true/false)` - Control Firebase Analytics

### 🔧 Enhanced
- **Initialization Flow**: Improved SDK initialization with configurable options
  - Better error handling and validation
  - Asynchronous initialization support
  - Backward compatibility with existing initialization methods

### 📋 Configuration Updates
- **AppsFlyer Dependencies**: New AppsFlyer SDK dependencies added to build configuration
  ```gradle
  dependencies {
      // AppsFlyer SDK for attribution and analytics
      implementation('com.appsflyer:af-android-sdk:6.17.0')
      
      // AppsFlyer Install Referrer (recommended for better attribution)
      implementation 'com.android.installreferrer:installreferrer:2.2'
  }
  ```

- **SDK Options**: New initialization parameters with granular module control
  - **Module Selection**: Individual tracking module enable/disable options
    ```java
    SDKOptions options = new SDKOptions();
        options.enableAppsflyer(true);
        options.enableFirebase(true);
        options.enableIts(true);
    GameSDK.sdkInitialize(context,listener,options);
    ```
  - **Flexible Configuration**: Mix and match modules based on app requirements
  - **Performance Optimization**: Only load enabled modules to reduce app overhead
  - **Runtime Control**: Dynamic module enabling/disabling support

### 🛠 Technical Improvements
- **Analytics Integration**: Unified analytics interface supporting multiple providers
- **Performance Optimization**: Enhanced tracking performance with minimal impact on app performance

---

## [2.0.1] - 2024-10-09

### �️ Removed
- **Airbridge Integration**: Removed Airbridge SDK dependency and related components
  - Cleaned up Airbridge initialization code
  - Removed Airbridge tracking methods and callbacks
  - Updated dependencies to exclude Airbridge libraries

### 🔄 Updated  
- **SQLCipher Library**: Updated to latest version for better performance and security
  - **Google Policy Compliance**: Updated to comply with new Google Play Store requirements for 16KB page size support
  - **16KB Page Size Compatibility**: Updated to resolve Android 15+ 16KB page size alignment issues
  - **Memory Alignment Fix**: Addresses native library compatibility with new Android memory management
  - **Reference**: [Android Developer Guide - Page Sizes](https://developer.android.com/guide/practices/page-sizes)
  - Enhanced database encryption capabilities
  - Improved compatibility with newer Android versions
  - Better memory management and performance optimizations

- **Build Environment**: Updated build system components to support 16KB page size compatibility
  - **Android Gradle Plugin**: 8.1.2 → 8.5.1 (includes 16KB page size support)
  - **Gradle Wrapper**: 8.4 → 8.7
  - **NDK Configuration**: Enhanced to support proper ELF alignment and LLD linker compatibility
  - **CMake Toolchain**: Updated with required linker flags (`-Wl,-z,max-page-size=16384`) for 16KB boundary alignment
  - **Build Tools**: Modernized toolchain to comply with Google Play Store 16KB page size requirements

### 🔧 Enhanced
- **16KB Page Size Compatibility**: Added support for Android 15+ 16KB page size requirements
  - Updated CMake configuration with proper linker flags (`-Wl,-z,max-page-size=16384`)
  - Enhanced NDK configuration for 16KB alignment compliance
  - Added gradle properties for 16KB page size compatibility
  - Future-proof native library building for upcoming Android devices

### 📋 Configuration Updates
- **Gradle Properties**: 
  ```properties
  android.bundle.enable16KPageSizeCompatible=true
  android.enableUncompressedNativeLibsInBundle=false
  ```
- **CMake Configuration**: Enhanced linker flags for proper ELF alignment
- **NDK Settings**: Updated for LLD linker support and 16KB compatibility

### 🛠 Technical Improvements
- **Native Library Alignment**: Optimized `libnative-lib.so` for 16KB boundary alignment
- **Build Performance**: Improved build configuration for better compatibility
- **Memory Optimization**: Enhanced resource management with updated SQLCipher

---

## [2.0.0] - 2025-07-23

### 🚀 New Features

#### User Scope Validation System
- **Government Compliance**: Enhanced user authentication system to comply with national government regulations
- **User Verification**: Improved user identity verification process according to government decree requirements
- **Integration**: Enhanced OAuth flow with real-time scope verification

#### Anonymous SDK Key Management
- **Rate Limiting System**: Implemented SDK anonymous key for API rate limiting
- **Core Integration**: Automatic anonymous key generation and management
- **Privacy Enhancement**: Enhanced user privacy through anonymous identification

#### Enhanced Error Management System
- **Improved Error Messages**: Enhanced error messaging system for better clarity and understanding
- **User-Friendly**: More intuitive and understandable error messages for end users
- **Developer-Friendly**: Detailed error information and better debugging support for developers

#### Advanced Security Implementation
- **ProGuard Enhancement**: Enterprise-grade security measures
  - Code obfuscation with custom dictionary
  - Anti-debugging and anti-reflection protection
  - String obfuscation and package flattening
- **New Files**: 
  - `security-rules.pro`: Advanced security ProGuard rules
  - Validation scripts for build verification

### 🔧 System Upgrades

#### Build System Modernization
- **Gradle**: 7.5 → 8.4
- **Android Gradle Plugin**: 7.4.2 → 8.1.4
- **Java Version**: 8 → 17
- **Target SDK**: 34 → 35
- **Compile SDK**: 34 → 35
- **Android Billing Client**: 6.0.1 → 8.0.0
- **Firebase**: Updated to latest BOM and component versions

#### ITS SDK Update
- **Version**: 1.1.0 → 1.1.1
- **Build Variants**: Added debug, release, and UAT variants
- **Features**: Automatic version tracking, enhanced analytics integration

### 🐛 Bug Fixes

#### Dialog Management
- **Email Change Dialog**: Fixed dismiss behavior after successful activation
- **Error Message Display**: Fixed incorrect error messages in dialogs with listeners

#### Authentication
- **Session Handling**: Improved session persistence and recovery
- **OAuth Integration**: Enhanced callback handling and state management

### ⚠️ Breaking Changes

#### Login Callback API Change
- **`onLoginSuccess` Method**: Signature changed from multiple parameters to single JSONObject
  - **Old**: `onLoginSuccess(String UserId, String UserName, String accesstoken)`
  - **New**: `onLoginSuccess(JSONObject loginData)`
  - **JSONObject Format**:
    ```json
    {
      "user_id": "value_of_userid",
      "user_name": "value_of_username", 
      "auth_checksum": "value_of_checksum",
      "auth_timestamp": "value",
      "auth_token": "value_of_accesstoken"
    }
    ```

#### Android Billing Client 8.0.0
- `enablePendingPurchases()` now requires `PendingPurchasesParams`
- `ProductDetailsResponseListener` signature changed
- `QueryProductDetailsResult` replaces direct `List<ProductDetails>` parameter

#### Build Requirements
- **Minimum Java**: Now requires Java 17
- **Gradle**: Requires Gradle 8.4+
- **AGP**: Requires Android Gradle Plugin 8.1.4+
- **Namespace**: All modules must define namespace in build.gradle

#### Security Impact
- Code obfuscation may affect reflection-based integrations
- ProGuard rules may require adjustment for custom implementations

### 📋 Migration Notes

#### Required Updates
1. **Java 17**: Update development environment
2. **Build Tools**: Update Gradle and AGP versions
3. **Login Callback Migration**: Update `onLoginSuccess` implementation
   ```java
   // Old implementation
   @Override
   public void onLoginSuccess(String UserId, String UserName, String accesstoken) {
       // Your code here
   }
   
   // New implementation  
   @Override
   public void onLoginSuccess(JSONObject loginData) {
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
   ```
4. **Billing Client**: Update billing integration for 8.0.0 API changes
5. **ProGuard**: Review custom rules for compatibility

#### Optional Features
- Scope validation system (backward compatible)
- Anonymous key management (automatic)

---

## [1.2.1] - 2025-07-20

### 🔧 Configuration Enhancement
- **Domain ID Configuration**: Added configurable domain ID in local properties
- **Environment Flexibility**: Enhanced switching between server domains

---

## [1.2.0] - 2025-07-19

### 🐛 Bug Fixes & Improvements
- **Authentication**: Fixed login flow and session management issues
- **Performance**: Optimized memory usage and API request efficiency
- **UI**: Enhanced dialog responsiveness and error handling
- **Logging**: Improved debug logging and error message clarity
