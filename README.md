# Client Registration Microservice


 ## Getting Started

 To successfully build the project, you need to have installed:
 * Java 1.8
 * Gradle (download and install gradle or use gradlew tool from project root folder)

 ## Build and running

 To build project execute from project root:
 * Using gradle:
 ```
 gradle build
 ```
 * Using gradlew:
 ```
 gradlew build
 ```
 Following command will start spring boot application on port 8080:
 ```
 java -jar build/libs/clients-0.0.1-SNAPSHOT.jar
 ```

 ## Custom run options

 ###Ports customization

 To run tomcat on specific port (i.e. 8090) execute:
 ```
 java -jar build/libs/clients-0.0.1-SNAPSHOT.jar --server.port=8090
 ```

 ## Docker run

 To run docker container execute
 ```
 docker build -t client-registeration .
 docker run --name client-container -p 3333:8080 -d client-registeration
 ```

 To remove docker container execute
 ```
 docker stop client-container
 docker rm client-container
 ```

 ## Test
 ```
 >curl http://localhost:3333/clients/
 <[{"clientId":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e","name":"YURIY","address":"KIEV"}]
 ```

 ```
 >curl http://localhost:3333/clients/3a3e99f0-5ad9-47fa-961d-d75fab32ef0e
 <[{"clientId":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e","name":"YURIY","address":"KIEV"}]
 ```

 ```
curl -H "Content-Type: application/json" -X POST http://localhost:3333/clients/ -d "{\"name\":\"Bill\", \"address\":\"NY\"}"
{"clientId":"c24b967d-0f73-46fb-a47d-2176a453c087","name":"Bill","address":"NY"}
 ```
 ```
curl http://localhost:3333/clients/
[{"clientId":"3a3e99f0-5ad9-47fa-961d-d75fab32ef0e","name":"YURIY","address":"KIEV"},
{"clientId":"c24b967d-0f73-46fb-a47d-2176a453c087","name":"Bill","address":"NY"}]
 ```

```
docker run --name client-psql -p 5432:5432 -d  -e POSTGRES_PASSWORD=postgres client-psql
```