## RxJava Okta OIDC client

This is an example of an Android app that communicates with a web app via REST API. Both apps are using an Okta SDK to implement OAuth 2.0 and OpenID Connect (OIDC) authorization. The networking part of the Android app is implemented with Retrofit and RxJava.

## Instaltion
This app is using the [Okta OIDC Android SDK](https://github.com/okta/okta-oidc-android) to authorize users. To use it, you need to set up a free [organization Okta account](https://developer.okta.com/signup/) and create a native Android app.

Once created, you'll need to update your `build.gradle` with your `AuthRedirectScheme`:

```gradle
manifestPlaceholders = [
    "appAuthRedirectScheme": "{yourAuthRedirectScheme}"
]
```
Then, update `src/main/java/dev/dbikic/oktaloginexample/OktaManager.kt` to have your client ID, issuer, and other Okta settings:
```kotlin
val config = OIDCConfig.Builder()
    .clientId("{yourClientID}")
    .discoveryUri("https://{yourOktaDomain}/oauth2/default")
    .redirectUri("{yourReversedOktaDomain}:/callback")
    .endSessionRedirectUri("{yourReversedOktaDomain}:/")
```

## Setup the web app
TBD, I'll link the web app setup section of the blog post once it's published