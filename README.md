# call_center

Cuenta con un productor de llamadas y un consumidor (dispatcher). El dispatcher bloquea hasta que una nueva llamada ingresa en donde le pide al callcenter por alguien disponible para atender la llamada. Una vez que consigue alguien genera un hilo separado para consumir la llamada. 
Una vez terminada la llamada (5 segundos), la persona vuelve a marcarse como disponible en el callcenter para tomar otro llamado.

Se procesan el total de las llamadas ya que si llega 1 o 100 al mismo tiempo se agregan en una cola y se consumen por orden de llegada. 

Si el callcenter esta totalmente ocupado atendiendo llamadas el dispatcher espera y vuelve a preguntar a los 5 milisengundos hasta que consiga alguien que pueda tomar el llamado
