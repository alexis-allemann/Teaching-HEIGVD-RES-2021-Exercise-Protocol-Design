# Protocol design :arrows_counterclockwise:

| Key                          | Value                                                        |
| ---------------------------- | ------------------------------------------------------------ |
| :phone: Protocol             | TCP                                                          |
| :computer: Server IP address | The client knows the address or makes a DNS query to find it |
| :door: Port                  | 9999                                                         |
| :traffic_light: Order        | Client initiate the communication                            |

```sequence
Client->Server: OPERATION (ADD, SUB, MUL, DIV)
Server->Client: RESULT
note right of Server: If an error occured, server doesn't send a RESULT but an ERROR
Server->Client: ERROR
Client->Server: QUIT
```

## Semantics :wavy_dash:
### Client
- OPERATION :  NA (the client is the sender)
- RESULT : Display result
- ERROR : Display error message
- QUIT :  NA (the client is the sender)

### Server

- OPERATION:  check if operation is supported and if numbers are valid  
- RESULT : NA (the server is the sender)
- ERROR : NA (the server is the sender)
- QUIT:  Close connection with client

## Syntax :pencil:
- OPERATION (ADD, SUB, MUL, DIV) : number number
- RESULT : number
- ERROR : string
- QUIT: empty

## End of connection :end:
Only the client can choose to terminate the connection with the server. It sends the "QUIT" message to inform the server that the connection can be closed.