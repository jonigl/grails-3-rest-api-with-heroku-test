Grails 3.2.4

http://docs.grails.org/latest/guide/single.html#webApiAndAngularProfiles

$ grails create-app myapp --profile=rest-api

# grails-3-rest-api-with-heroku

https://github.com/grails/grails-core/issues/10377

ref. spring-projects/spring-boot#7360
manually downgrading to tomcat 8.5.5 (for now) fixed the problem for me.

// build.gradle
ext {
    set "tomcat.version", "8.5.5"
}
