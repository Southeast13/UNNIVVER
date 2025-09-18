@echo off
where gradle >nul 2>nul
if %ERRORLEVEL%==0 (
  gradle %*
) else (
  echo Gradle not found. Please install Gradle or add a proper gradle wrapper.
  exit /b 1
)
