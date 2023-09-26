/**************
 * Public API
 **************/

// Notifications
exports.getToken = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getAPNSToken = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.onMessageReceived = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.onTokenRefresh = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.onApnsTokenReceived = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.subscribe = function (topic, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.unsubscribe = function (topic, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.unregister = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.isAutoInitEnabled = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setAutoInitEnabled = function (enabled, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

// Notifications - iOS-only
exports.onOpenSettings = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setBadgeNumber = function (number, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getBadgeNumber = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.grantPermission = function (success, error, requestWithProvidesAppNotificationSettings) {
    if (typeof success === 'function') {
        success();
    }
};

exports.grantCriticalPermission = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.hasPermission = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.hasCriticalPermission = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

// Notifications - Android-only
exports.setDefaultChannel = function (options, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.createChannel = function (options, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.deleteChannel = function (channelID, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.listChannels = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

// Analytics
exports.setAnalyticsCollectionEnabled = function (enabled, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.isAnalyticsCollectionEnabled = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.logEvent = function (name, params, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setScreenName = function (name, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setUserId = function (id, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setUserProperty = function (name, value, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.fetch = function (cacheExpirationSeconds, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.activateFetched = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.fetchAndActivate = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.resetRemoteConfig = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getValue = function (key, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getInfo = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setConfigSettings = function (fetchTimeout, minimumFetchInterval, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setDefaults = function (defaults, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getAll = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.startTrace = function (name, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.incrementCounter = function (name, counterNamed, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.stopTrace = function (name, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setPerformanceCollectionEnabled = function (enabled, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.isPerformanceCollectionEnabled = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.clearAllNotifications = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};


// Crashlytics
exports.setCrashlyticsCollectionEnabled = function (enabled, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.isCrashlyticsCollectionEnabled = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.logMessage = function (message, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.sendCrash = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.logError = function (message, stackTrace, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setCrashlyticsUserId = function (userId, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setCrashlyticsCustomKey = function (key, value, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.didCrashOnPreviousExecution = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};


// Authentication
exports.verifyPhoneNumber = function (success, error, phoneNumber, opts) {
    if (typeof success === 'function') {
        success();
    }
};

exports.enrollSecondAuthFactor = function (success, error, number, opts) {
    if (typeof success === 'function') {
        success();
    }
};

exports.verifySecondAuthFactor = function (success, error, params, opts) {
    if (typeof success === 'function') {
        success();
    }
};

exports.listEnrolledSecondAuthFactors = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.unenrollSecondAuthFactor = function (success, error, selectedIndex) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setLanguageCode = function (lang, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.createUserWithEmailAndPassword = function (email, password, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.signInUserWithEmailAndPassword = function (email, password, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.authenticateUserWithEmailAndPassword = function (email, password, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.signInUserWithCustomToken = function (customToken, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.signInUserAnonymously = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.authenticateUserWithGoogle = function (clientId, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.authenticateUserWithApple = function (success, error, locale) {
    if (typeof success === 'function') {
        success();
    }
};

exports.authenticateUserWithMicrosoft = function (success, error, locale) {
    if (typeof success === 'function') {
        success();
    }
};

exports.authenticateUserWithFacebook = function (accessToken, success, error,) {
    if (typeof success === 'function') {
        success();
    }
};

exports.signInWithCredential = function (credential, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.linkUserWithCredential = function (credential, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.reauthenticateWithCredential = function (credential, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.isUserSignedIn = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.signOutUser = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};


exports.getCurrentUser = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.reloadCurrentUser = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.updateUserProfile = function (profile, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.updateUserEmail = function (email, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.sendUserEmailVerification = function (actionCodeSettings, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.verifyBeforeUpdateEmail = function (email, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.updateUserPassword = function (password, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.sendUserPasswordResetEmail = function (email, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.deleteUser = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.registerAuthStateChangeListener = function(success, error){
    if (typeof success === 'function') {
        success();
    }
};

exports.useAuthEmulator = function (host, port, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getClaims = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

// Firestore
exports.addDocumentToFirestoreCollection = function (document, collection, timestamp, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.setDocumentInFirestoreCollection = function (documentId, document, collection, timestamp, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.updateDocumentInFirestoreCollection = function (documentId, document, collection, timestamp, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.deleteDocumentFromFirestoreCollection = function (documentId, collection, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.documentExistsInFirestoreCollection = function (documentId, collection, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.fetchDocumentInFirestoreCollection = function (documentId, collection, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.fetchFirestoreCollection = function (collection, filters, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.listenToDocumentInFirestoreCollection = function (success, error, documentId, collection, includeMetadata) {
    if (typeof success === 'function') {
        success();
    }
};

exports.listenToFirestoreCollection = function (success, error, collection, filters, includeMetadata) {
    if (typeof success === 'function') {
        success();
    }
};

exports.removeFirestoreListener = function (success, error, listenerId) {
    if (typeof success === 'function') {
        success();
    }
};

exports.functionsHttpsCallable = function (name, args, success, error) {
    if (typeof success === 'function') {
        success();
    }
};

// Installations
exports.getId = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getInstallationId = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.getInstallationToken = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.deleteInstallationId = function (success, error) {
    if (typeof success === 'function') {
        success();
    }
};

exports.registerInstallationIdChangeListener = function(success, error){
    if (typeof success === 'function') {
        success();
    }
};

// iOS App Lifecycle
exports.registerApplicationDidBecomeActiveListener = function(success, error){
    if (typeof success === 'function') {
        success();
    }
};

exports.registerApplicationDidEnterBackgroundListener = function(success, error){
    if (typeof success === 'function') {
        success();
    }
};

exports.loadNotificationSettings = function (success, error) {
    if (typeof success === 'function') {
      success();
    }
  };
  
  exports.loadOverlaySettings = function (success, error) {
    if (typeof success === 'function') {
      success();
    }
  };

exports.hasOverlayPermission = function (success, error) {
    if (typeof success === 'function') {
        success();
      }
};
