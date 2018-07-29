# Run

```bash
linux:~/ratpack-hello # ./gradlew run
linux:~/ratpack-hello # ./gradlew run -t
```


---

# Test

## url, path, param

```bash
linux:~ # curl http://127.0.0.1
linux:~ # curl http://127.0.0.1/hello
linux:~ # curl http://127.0.0.1/hello/test
linux:~ # curl http://127.0.0.1/hello/test?age=10
```


## form data

```bash
linux:~ # curl --request POST --form age=10 http://127.0.0.1:5050/hello/test   
```


## json payload

```bash
linux:~ # curl --request POST --data '{"abc": 1}' http://127.0.0.1:5050/api  
```


---

# Parse

```bash
linux:~ # curl http://localhost:5050/user 
linux:~ # curl http://localhost:5050/user/0
linux:~ # curl -X POST -d '{"name":"test}' http://localhost:5050/user 
```

