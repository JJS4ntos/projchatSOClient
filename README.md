# projchatSOClient
Client do nosso Chat para o trabalho de Sistemas Operacionais
Envia pacotes contendo mensagem e o servidor será responsável por ler e transmitir.

Prévia
---------------------------

![enter image description here](https://lh3.googleusercontent.com/-nBQZ_ryhs0A/WgsoIuoFGCI/AAAAAAAAANc/oOMD1-7pEB89gpZqpZoydqMzn_QwvVz7QCLcBGAs/s500/cliente.png "Client")

Funcionamento básico client
---------------------------

 - O Client estabelece conexão com o Servidor, cria uma Thread que roda em loop infinito para escutar todos os pacotes enviados pelo Servidor.
