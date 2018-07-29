import data.User
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import ratpack.form.Form
import service.DefaultUserService
import service.UserService

import static ratpack.groovy.Groovy.ratpack


JsonSlurper jsonSlurper = new JsonSlurper()


ratpack {
    bindings {
        bindInstance(UserService, new DefaultUserService())
    }

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
                        response.send "Hello ${name}, your age is ${age}"
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
                    request.body.map { body ->
                        jsonSlurper.parseText(body.text) as Map
                    }.then { data ->
                        data << ['id': '1']
                        response.send(JsonOutput.toJson(data))
                    }
                }
            }
        }

        prefix('user') {
            path(':id?') {
                byMethod {
                    get { UserService userService ->
                        if (pathTokens.get('id')) {
                            render JsonOutput.toJson(userService.show(pathTokens.id.toInteger()))
                        } else {
                            render JsonOutput.toJson(userService.list())
                        }
                    }

                    post { UserService userService ->
                        parse(User.class).then { user ->
                            user.id = userService.list().size() + 1
                            userService.add(user)
                            render JsonOutput.toJson(user)
                        }

                    }
                }
            }
        }

    }
}
