# SimpleHttpServer 简单的HttpServer

Chinese

该HttpServer能够通过固定端口访问本地的html文件。通过在客户端输入localhost：（端口号） 建立连接。
当服务器接收到请求信息后，分析当前的请求信息是get方法还是post方法，根据相应的请求方法获取请求资源以及请求参数。
回应方法里，根据不同的code，比如200，404，500给客户端以相应的回复。

German

Der HttpServer kann über einen festen Port auf lokale HTML-Dateien zugreifen. Stellen Sie eine Verbindung her, indem Sie "localhost: Portnummer" auf dem Client eingeben.
Nach dem Empfang der Request-Informationen analysiert der Server, ob die aktuellen Request-Information eine Get-Methode oder eine Post-Methode ist, und erfasst die Anforderungsressource und den Anforderungsparameter gemäß der entsprechenden Anforderungsmethode.
In der Response-Methode antwortet der Server den Client entsprechende Informationen, laut der verschiedenen Codes, wie z. B. 200 ist Ok, 404 ist Not Found, 500 ist Internal Server Error.


English

