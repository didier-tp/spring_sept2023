cd /d %~dp0
call set_env.bat

set LOMBOK_VERSION=1.18.28
set LOMBOK_CLASSPATH=%MVN_REPOSITORY%\org\projectlombok\%LOMBOK_VERSION%\lombok-%LOMBOK_VERSION%.jar
set LOMBOK_CLASSPATH=C:\Users\Administrateur\.m2\repository\org\projectlombok\lombok\1.18.28\lombok-1.18.28.jar

java -jar  %LOMBOK_CLASSPATH%

pause