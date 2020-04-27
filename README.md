# AC-NH-TURNIP-PRICES-WITH-FRIENDS
_AKA turnipprophet.io with 'friend support'_

##Get Started

### Initialize Submodules
```console
git submodule --init --recursive
```

### Build and Run using Docker
Requires... [Docker](https://docs.docker.com/get-docker/)
```console
docker build -t turnips-with-friends:latest . && docker run -it -p 8080:8080 turnips-with-friends:latest
```

This will work for "local mode", but attempting to login with Google will give you the following error:

![Error 401: invalid_client](docs/google_401.png)

This is because in order to authenticate with Google, your application needs Google API credentials setup.

#### Setup Google OAuth2
Start [here](https://developers.google.com/identity/protocols/oauth2/openid-connect) with steps "Obtain OAuth 2.0 credentials" and "Set a redirect URI". Your redirect URI will most likely be `http://localhost:8080/login/oauth2/code/google`

Modify the [`application.properties`](ac-nh-turnip-prices-with-friends/src/main/resources/application.properties) with your application id and secret:
```console
spring.security.oauth2.client.registration.google.client-id=changeme
spring.security.oauth2.client.registration.google.client-secret=changeme
```

Now when you build, your Google credentials will be bundled inside the JAR that Docker creates. Which is probably a bad idea...

#### Setup Google OAuth2 - a better way

Rather than modifying the default [`application.properties`](ac-nh-turnip-prices-with-friends/src/main/resources/application.properties) file, you can provide your own with a Docker [volume](https://docs.docker.com/storage/volumes/):
```console
docker run -it -p 8080:8080 -v /your/path/to/application.properties:/usr/src/app/application.properties turnips-with-friends:latest
```


### Development
Use your favorite Java IDE with Maven integration

TODO