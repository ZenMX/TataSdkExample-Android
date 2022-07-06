# How to use mx-tata-sdk-android

latest version `0.1.1.0-SNAPSHOT`

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

implementation 'com.m.x.tata.sdk:player:0.0.2.0-SNAPSHOT'

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