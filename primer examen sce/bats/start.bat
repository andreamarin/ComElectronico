set PATH=C:\Program Files\Java\jdk1.8.0_171\bin;C:\Program Files\Java\jdk1.8.0_181\bin;%PATH%
set classpath=.;C:\Program Files\Java\jdk1.8.0_181\bin;%classpath%
set direccion=%cd%
start rmiregistry -J-Djava.rmi.server.codebase=file:%direccion%\estresador.jar
FOR /L %%k IN (1,1,3) DO start "Proceso "%%k cjk.bat