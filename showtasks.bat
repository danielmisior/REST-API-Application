call runcrud.bat
if "%ERRORLEVEL%" == "0" goto findtasks
echo.
echo RUNCRUD has errors - breaking work
goto fail

:findtasks
start chrome http://localhost:8080/crud/v1/tasks/
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot start chrome and find tasks - breaking work
goto fail

goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished