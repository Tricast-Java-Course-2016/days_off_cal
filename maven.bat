@ECHO ON
REM Set the maven enviromental variables for the session
@SET M2_HOME=%CD:~0,2%\apps\apache-maven-3.3.9
@SET M2=%M2_HOME%\bin
@SET MAVEN_OPTS=-Xmx2048m
@SET M2_REPO=%CD:~0,2%\repository\.m2\
@SET PATH=%M2%;%PATH%
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse