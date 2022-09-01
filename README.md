# How to integrate mx-tata-sdk-android

latest version `0.6.4.14-SNAPSHOT`

## add maven repository

`'http://13.232.78.227:8099/nexus/content/repositories/public/'`

for gradle 7.x

```gradle

dependencyResolutionManagement {    
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {        
    google()
        mavenCentral()
        jcenter()
        maven {            
          allowInsecureProtocol true            
          url 'http://13.232.78.227:8099/nexus/content/repositories/public/'        
        }    
      }
  }


```

for other gradle version

```gradle

allprojects {
    repositories {  
    google()
    jcenter()
    maven { url 'http://13.232.78.227:8099/nexus/content/repositories/public/'
    }    
}

```

## add sdk dependency

```gradle 

implementation 'com.m.x.tata.sdk:player:0.6.4.14-SNAPSHOT'

```

## init sdk

call following at the beginning of you application's onCreate() function

```kotlin

MxSDK.Builder(this).debug(BuildConfig.DEBUG).build()

```

example

```kotlin

override fun onCreate() {
    super.onCreate()

    MxSDK.Builder(this).debug(BuildConfig.DEBUG).build()
}

```

handle error

```kotlin

override fun onCreate() {
    super.onCreate()

    MxSDK.Builder(this).debug(BuildConfig.DEBUG).errorListener {
        Toast.makeText(this@App, "error $it", Toast.LENGTH_SHORT).show()
        return@errorListener true
    }.build()
}

```


if you want to test for candidate. (UAT)

```kotlin

override fun onCreate() {
    super.onCreate()

    MxSDK.Builder(this).debug(false).candidate(true).build()
}

```

## start playback

```kotlin

MxSDK.startPlay()
    .withContent("video id", "tv show")
    .withToken("user token")
    .withUniqueId("user id")
    .withDSN("user DSN")
    .play(this)


```

for only test playback

```kotlin

MxSDK.testPlayback(this@MainActivity)

```

## MxSDK Description

class MxSDK is interface that tata app interacts with MX content. 

public method is following.

```java

public final class MxSDK {
    /**
     * request a PlaybackBuilder for playback
     */
    public static @NonNull PlaybackBuilder startPlay();

    /**
     * start a test playback immediately, only used for test.       
     */
    public static void testPlayback(Activity activity);

    public static class Builder {
        /**
         * provide an extra ExecutorService, it will be used for network request,
         * get video detail e.g.
         * @param networkExecutor, extra ExecutorService, if not set, MxSDK will create a new one.
         * @return this
         */
        public @NonNull Builder networkExecutor(@NonNull ExecutorService networkExecutor);

        /**
         * @param debug, if is true, MxSDK will access DEV API server, log is debug level.
         * when debug is false, MxSDK will access production API server.
         * default is false.
         * @return this
         */
        public @NonNull Builder debug(boolean debug);
            
        /**
         * log is verbose level. every log will be printed.
         * @return this
         */
        public @NonNull Builder verbose();

        /**
         * log is silent level, nothing will be printed.
         * @return this
         */
        public @NonNull Builder silent();

        /**
         * @param candidate, true, sdk will access candidate env. otherwise, release env.
         * if debug is true, this flag will be ignored.                  
         * @return this
         */
        public @NonNull Builder candidate(boolean candidate);

        /**
         * @param listener, if error occur, onMxSDKError() will be called with error code.
         * @return this
         */
        public @NonNull Builder errorListener(OnMxSDKErrorListener listener);

        /**
         * call this to finish initialize MxSDK.
         */
        public void build();
    }

    public static class PlaybackBuilder {
        /**
         * @param contentId, id of video
         * @param contentType, type of video, 'tvshow_episode' e.g.
         * @return this
         */
        public @NonNull PlaybackBuilder withContent(@NonNull String contentId, @NonNull String contentType);

        /**
         * @param token, token of user, subscribe user should give this parameter.
         * @return this
         */
        public @NonNull PlaybackBuilder withToken(@Nullable String token);

        /**
         * @param userId, user id, tata app provide
         * @return this
         */
        public @NonNull PlaybackBuilder withUserId(@Nullable String userId);

        /**
         * @param dsn, DSN, tata app provide
         * @return this
         */
        public @NonNull PlaybackBuilder withDSN(@Nullable String dsn);

        /**
         * call this to start playback immediately after all parameters are set.      
         */
        public void play(@NonNull Activity context);
    }

    public @interface ErrorCode {
        int ErrorTokenExpired = 100;
        int ErrorTokenAuthIssue = 101;
        int ErrorPartnerIdIsNull = 102;
        int ErrorContentNotAvailable = 300;
        int ErrorNetworkIssue = 301;
        int ErrorInternalServerIssue = 302;
    }
}



```

## Release Note

### 0.6.4.14-SNAPSHOT

FIX:

* Player control lock on player screen gets auto unlocked for next episode


### 0.6.3.12-SNAPSHOT

FIX:

* continue watching issue, player should start with position where it left

### 0.6.2.10-SNAPSHOT

FIX:

* hide next button for movie & short video

* subtitle list doesn't show even if video really has subtitle

### 0.6.1.7-SNAPSHOT

FIX:

* add x-timestamp on API request

* fix issue of disable subtitle

### 0.6.0.5-SNAPSHOT

FIX:

* add more log

### 0.6.0.0-SNAPSHOT

FIX:

* optimize, remove unnecessary parameter for tracking.

### 0.5.0.0-SNAPSHOT

FEATURE:

* introduce OnMxSDKErrorListener for error handle 

### 0.4.0.0-SNAPSHOT

FEATURE:

* support candidate env

* update tracking accordingly

* normalize error code

FIX:

* fix some initialization issue

### 0.3.0.0-SNAPSHOT

FEATURE:

* more tracking

* handle error

### 0.2.0.0-SNAPSHOT

FEATURE:

* prepare for tracking

FIX:

* update according API  

### 0.1.4.0-SNAPSHOT

FEATURE:

* support video ad

### 0.1.3.0-SNAPSHOT

FIX:

* API encryption issue.

### 0.1.2.0-SNAPSHOT

FIX:

* retry icon overlaps with play icon when playback failed.

* optimize, fields in MxSDK is private now (previous is public). 

### 0.1.1.0-SNAPSHOT

FIX:

* compatibility optimize.

### 0.1.0.0-SNAPSHOT

FEATURE:

* pause\start, 

* rewind forward, 
  
* player control lock,
  
* speed, subtitle,
  
* quality,
  
* audio language,
  
* age rating,
  
* skip intro, skip credit,
  
* auto play next,
  
* Next episode CTA with timer,
  
* back button.


### 0.0.2.0-SNAPSHOT

FEATURE:

* support testPlayback, only can play one  movie, no other feature.

