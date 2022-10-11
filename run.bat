@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK 17 home dir (change path if necessary)
set JAVA_HOME=C:\Program Files\Java\jdk-17.0.2

cd .\frontend
start cmd /c npm start
cd ..


cd .\backend
start cmd /c mvnw spring-boot:run

exit