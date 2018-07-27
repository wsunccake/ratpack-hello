import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import ratpack.form.Form

import static ratpack.groovy.Groovy.ratpack

JsonSlurper jsonSlurper = new JsonSlurper()


ratpack {
    handlers {
        get {
            render "Hello Ratpack!"
        }

        prefix('hello') {
            path(':name?') {
                String name = (allPathTokens.get('name')) ? allPathTokens.name : 'Ratpack'
                String age = (request.queryParams.get('age')) ? request.queryParams.age : 'unknown'
                byMethod {
                    get {
                        response.send"Hello ${name}, your age is ${age}"
                    }

                    post {
                        parse(Form).then { formData ->
                            age = formData.age ? formData.age : '0'
                            response.send "Hello ${name}, your age is ${age}"
                        }
                    }
                }
            }
        }

        path('api') {
            byMethod {
                post {
                    request.body.map {body ->
                        jsonSlurper.parseText(body.text) as Map
                    }.then { data ->
                        data << ['id': '1']
                        response.send(JsonOutput.toJson(data))
                    }
                }
            }
        }
    }
}
