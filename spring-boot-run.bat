@echo off
setlocal

::Set the path to your .env file.
set "ENV_FILE=.env"
echo Checking for %ENV_FILE%...
:: Check if ENV_FILE exists or not
if not exist "%ENV_FILE%" (
    echo .env not found!
    exit /b 1
)

:: Loop through each line in the .env file
for /f "usebackq tokens=1,* delims==" %%A in ("%ENV_FILE%") do (
    if not "%%A"=="" if not "%%A" =="#" (
        call set "%%A=%%B"
    )
)

if "#SERVER_PORT%"=="" (
    echo SERVER_PORT is not set in the .env file!
    exit /b 1
)

:: Run your Spring Boot application
echo Starting Spring Boot application on port %SERVER_PORT%...

mvn spring-boot:run

if errorlevel 1 (
    echo Failed to start Spring Boot Application..
    exit /b 1
)

:: Pause to view the results
pause

endlocal