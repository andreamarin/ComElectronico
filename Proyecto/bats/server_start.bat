if [%1] NEQ [] goto conhost
java -jar Estresador.jar estresador.ServidorDisparos
goto fin

:conhost
java -jar Estresador.jar estresador.ServidorDisparos %1
goto fin

:fin