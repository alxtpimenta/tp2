# tp2
#CHANGELOG:
//Revision 2 (Alexandre)
Removido ID de jogadores e cartas (nao utizilado)
PROJETO PORTADO PARA O GITHUB
//Revision 3 (Alexandre)
Corrigido bug nas cartas (Pife nao possui valete). Construtor corrigido.
Criada nova classe: Interface. Implementa os metodos para imprimir a interface.
//Revision 4 (Alexandre)
Corrigido bug na inicializacao das cartas (faltava inicializar o ArrayList da mao de cartas) do jogador
//Revision 5 (Alexandre)
Jogador:
String nome alterada para final
Mao de cartas do jogador alterado para private
Implementados metodos para acesso a mao de cartas do jogador
Interface:
Implementados metodos de impressao da Interface
Carta:
Adidionado integer para identificar o naipe da carta
Main:
Programa principal implementado
TO-DO:
Verificar as entradas do usuario para evitar conflitos
Implementar as condicoes de vitoria
Implementar interface de vitoria
Implementar verificacao de turno (cada jogador so pode descartar/comprar uma vez por turno)
//Revision 6 (Alexandre)
Interface:
Implementado metodo para limpar o console
Aprimoramentos na interface
Programa principal:
Implementado a verificacao de turno
Implementada a verificacao de entrada
//Revision 7 (Alexandre)
Interface:
Novos metodos implementados
Comandos de impressao da interface agora sao responsabilidade da classe interface (exceto em mensagens de erro)
Mensagens de erro padronizadas
Interface padronizada (consistente)
TODO: Terminar condicoes de vitoria
Primeira carta do lixo visivel
Quando monte acabar, reembaralhar lixo
Modularizacao
//Revision 8 (Alexandre)
Programa principal:
Verificacao de vitoria implementada
Distribuicao de cartas movida para a classe Procedimentos
Interface:
Mensagens de erros movidas para a classe interface
Mensagens de boas vindas movidas para a classe interface
A interface agora mostra a primeira carta do lixo quando o usuario for comprar uma carta
Procedimentos:
Implementado procedimento para distribuir cartas
Implementado procedimento para verificar as condicoes do turno (Quando monte acabar, reembaralhar lixo)
TO-DO:
Implementar tela de vitoria
Separar o codigo em pacotes (modularizar)
//Revision Final1 (Alexandre)
Interface:
Implementada tela de vitoria
Modularizado em pacotes
//Revision Final 2 (Alexandre)
Geral:
Criada nova classe singleton para conter o ambiente de jogo, evitando o uso de variaveis "espalhadas" pelo programa principal
Classes builders inicializarBaralho, distribuirCartas e outras consolidadas e alteradas para usar a nova classe de ambiente de jogo
Implementada nova classe Define, que define as constantes do jogo (numero maximo de cartas, numero de cartas por naipe, etc)
Funcoes ajustadas para utilizar a nova classe Define
Programa principal:
Variaveis do jogo transferidas para a classe Ambiente
Programa principal otimizado para utilizar a nova classe Define
Bugs:
Corrigido bug na entrada de nomes (se fosse digitado algo alem dos dois nomes eram geradas enradas invalidas)
