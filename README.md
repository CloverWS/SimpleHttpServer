# SimpleHttpServer 简单的HttpServer

Chinese

该HttpServer能够通过固定端口访问本地的HTML文件。通过在客户端输入localhost：（端口号） 建立连接。
当服务器接收到请求信息后，分析当前的请求信息是GET方法还是POST方法，根据相应的请求方法获取请求资源以及请求参数。
回应方法里，根据不同的code，比如200，404，500给客户端以相应的回复。

German

Der HttpServer kann über einen festen Port auf lokale HTML-Dateien zugreifen. Stellen Sie eine Verbindung her, indem Sie "localhost: Portnummer" auf dem Client eingeben.
Nach dem Empfang der Request-Informationen analysiert der Server, ob die aktuellen Request-Information eine Get-Methode oder eine Post-Methode ist, und laut der entsprechenden Request-Methode erfasst der Server die Request-Ressource und den Request-Parameter.
In der Response-Methode antwortet der Server den Client entsprechende Informationen, laut der verschiedenen Codes, wie z. B. 200 ist Ok, 404 ist Not Found, 500 ist Internal Server Error.


English

The HttpServer can access local HTML files through a fixed port. Establish a connection by entering localhost: (port number) on the client.
After receiving the request information, the server analyzes whether the current request information is a GET method or a POST method, and according to the corresponding request method acquires the server the request resource and the request parameter.
In the response method, the server respond the client according to different codes, such as 200 is Ok, 404 is Not Found, 500 is Internal Server Error.
