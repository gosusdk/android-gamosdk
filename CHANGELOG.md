# Changelog

All notable changes to the Android Gaming SDK.

## [2.0.0] - 2025-07-23

### 🚀 New Features

#### WebView TopUp System
- **New API**: `GameSDK.showTopUp(GameItemWebTopupObject, IGameTopupListener)`
- **Classes Added**: 
  - `GameItemWebTopupObject`: TopUp transaction data object
  - `IGameTopupListener`: TopUp event callback interface
  - `DialogTopupWebview`: WebView dialog for top-up interface
- **Features**: Built-in validation, error handling, and comprehensive callback system

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
- WebView TopUp system (backward compatible)
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
