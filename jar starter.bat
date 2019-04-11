@echo off

echo Current path: %CD%
echo -------------------------------------------------------------------------------
echo Avaible files in current path: 
dir /B
echo -------------------------------------------------------------------------------

set /p file=Desired JAR File: 
set /p args=Args: 

echo Starting the Java app...
echo.

echo ===============================================================================
echo %file%
echo -------------------------------------------------------------------------------
java -jar %file% %args%
echo ===============================================================================

echo.
echo App finished.
echo Return value: %errorlevel%
echo.

pause
exit