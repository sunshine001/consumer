# Consumer
Consumer is a springboot2.X library, it handle broadcast with annotation.

## Deploy
```
mvn clean deploy -P sonatype-oss-release -DskipTests
mvn clean deploy -P sonatype-oss-snapshots -DskipTests
``` 

## Settings
```
<servers>
  <server>
    <id>sonatype-oss-snapshots</id>
    <username>username</username>
    <password>password</password>
  </server>
  <server>
    <id>sonatype-oss-staging</id>
    <username>username</username>
    <password>password</password>
  </server>
</servers>
```

## Repositorie
```
<repositories>
  <repository>
    <id>sonatype</id>
    <url>https://s01.oss.sonatype.org/content/groups/public/</url>
  </repository>
</repositories>
```
