if [%1] NEQ [] goto conhost
java -jar Estresador.jar estresador.Master
goto fin

:conhost
java -jar Estresador.jar estresador.Master %1
goto fin

:fin