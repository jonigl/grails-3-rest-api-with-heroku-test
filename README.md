# grails-3-rest-api-with-heroku

## Grails 3.2.4

$ grails create-app myapp --profile=rest-api

http://docs.grails.org/latest/guide/single.html#webApiAndAngularProfiles

## build.gradle (fix issue - workaround)

https://github.com/grails/grails-core/issues/10377 (ref. spring-projects/spring-boot#7360)

Downgrading to tomcat 8.5.5
```
ext {
    set "tomcat.version", "8.5.5"
}
```

## Heroku

### build.gradle - Tasks

Add the following tasks:

```
task stage() {
    dependsOn clean, war
}
tasks.stage.doLast() {
    delete fileTree(dir: "build/distributions")
    delete fileTree(dir: "build/assetCompile")
    delete fileTree(dir: "build/distributions")
    delete fileTree(dir: "build/libs", exclude: "*.war")
}
war.mustRunAfter clean

task copyToLib(type: Copy) {
    into "$buildDir/server"
    from(configurations.compile) {
        include "webapp-runner*"
    }
}

stage.dependsOn(copyToLib)
```

### build.gradle - webapp-runner

Add this to dependencies {}
`compile 'com.github.jsimone:webapp-runner:8.5.5.2'`

### Procfile

Add a file named: Procfile and then copy/paste the following line:

`web: java -Dgrails.env=prod -jar build/server/webapp-runner-*.jar --expand-war --port $PORT build/libs/*.war`
