## What transport protocol do we use?

Nous allons utiliser le protocole TCP/IP afin de facilité l'implémentation des sockets de Java

## How does the client find the server (addresses and ports)?

Lors du démarrage du serveur, celui-ci indiquera le port et l'adresse sur lesquels il écoute.  Le client pourra alors initialiser une socket à l'aide de ceux-ci.

## Who speaks first?

Le client va demander au serveur de se connecter, celui-ci pourra accepter ou refuser.

## What is the sequence of messages exchanged by the client and the server? (flow)

1. Le serveur démarre, indique l'adresse et le port sur lequel il écoute
2. Le client lance une tentative de connexion
3. Le serveur accepte ou refuse
4. Si le serveur refuse, le client reçoit un message d'erreur
5. Si le serveur accepte le client reçoit un message indiquant qu'il est prêt à recevoir un calcul
6. Le client envoie un calcul
7. Le serveur le réceptionne et le traite
8. Le serveur envoie le résultat du traitement au client
9. Le client reçoit la réponse
10. Le client a la possibilité de se déconnecter

##  What happens when a message is received from the other party? (semantics)

### Client

RES :  Vérifie si c'est une erreur

QUIT :  impossible

OPERATION : impossible

###  Serveur

RES :  impossible

QUIT :  Le serveur déconnecte le client

OPERATION : Traite l'opération

##  What is the syntax of the messages? How we generate and parse them? (syntax)

Avec X et Y étant des nombres réels 

### ADD

ADD X Y --> x+y

### SUB

SUB X Y --> x-y

### DIV

DIV X Y --> x/y

### MUL

MUL X Y --> x*y

### RESULT

Affiche le résultat

##  Who closes the connection and when?

Le client ferme la connection quand il le souhaite, une fois l'information réceptionnée, le serveur ferme la connection.