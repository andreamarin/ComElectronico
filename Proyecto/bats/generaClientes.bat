if [%1] NEQ [] goto connumclientes
estresador 5 -jar Estresador.jar Client
goto fin

:connumclientes
if [%2] NEQ [] goto connumsolicitudes
estresador %1 -jar Estresador.jar estresador.Cliente
goto fin

:connumsolicitudes
if [%3] NEQ [] goto conrmihost
estresador %1 -jar Estresador.jar estresador.Cliente %2
goto fin

:conrmihost
estresador %1 -jar Estresador.jar estresador.Cliente %2 %3

:fin