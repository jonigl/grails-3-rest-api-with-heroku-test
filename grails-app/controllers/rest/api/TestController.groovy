package rest.api


import grails.core.GrailsApplication
import grails.util.Environment
import grails.plugins.*

class TestController {
    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}