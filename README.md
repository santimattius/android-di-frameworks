# Android Dependency Injection Frameworks

This is basic android project with essential configurations for  app develop in android.

## Verification

Run check project:

```shell
> ./gradlew check
```

Run tests project:

```shell
> ./gradlew test
```

## DeteKt

```shell
> ./gradlew :app:detekt
```

## Coverage

Debug:

```shell
> ./gradlew :app:testDebugUnitTestCoverage
```

Release:

```shell
> ./gradlew :app:testReleaseUnitTestCoverage
```

## Configure Local Secrets

Check this [documentation](https://github.com/google/secrets-gradle-plugin#installation)

Using local properties for define api key:

```properties
apiKey="{your-api-key}"
```

Using var:
```kotlin
val apiKey = BuildConfig.apiKey
```

Using into AndroidManifest:
```xml
<meta-data android:value="${apiKey}" />

```

### Github actions

```yml
name: Create Secrets
on:
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]
  workflow_dispatch:
jobs:
  keys:
    name: Tests
    runs-on: macos-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v2
      - name: Set Up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 17
      - name: Setup API Key
        env:
          APIKEY: ${{ secrets.APIKEY }}
        run: echo apiKey="$APIKEY" > ./local.properties
      - name: Set gradlew permissions
        run: chmod +x gradlew
      - name: Run Tests
        run: ./gradlew :app:check
```

## Dependencies (default branch)

Below you will find the libraries used to build the template and according to my criteria the most
used in android development so far.

- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)**, dependencies provider.
- **[Retrofit](https://square.github.io/retrofit/)**, networking.
- **[Gson](https://github.com/google/gson)**, json parser.
- **[Coil](https://coil-kt.github.io/coil/compose/)**, with image loader.
- **[Kotlin coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)**.
- **[Mockk](https://mockk.io/)**, testing library.
- **[MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)**, networking testing library.
