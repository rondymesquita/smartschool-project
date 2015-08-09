# Smartschool Project

## Requisitos Base
- Gradle 2.5+
- Tomcat 7+
- Banco de dados Mysql or Postgres 9

###Configurando e executando
* Instale o Gradle em [https://www.gradle.org](https://www.gradle.org)

### Core
* Configure suas credenciais do banco de dados em **smartschool-core/src/main/resources/application.properties**
* Vá para **smartschool-core** via terminal e execute
```
$ gradle install
```
Isso irá instalar o core como dependência na sua máquina.

###Web
#### Requisitos
 - NodeJS
 - Bower

 - Vá para **smartschool-java-web** via terminal e execute:
```
$ bower install
$ gradle eclipse
```

###WS

 - Vá para **smartschool-ws** via terminal e execute:
```
$ gradle eclipse
```

Para gerar o pacote **war**, vá para **smartschool-ws** e execute:

```
$ gradle package
```

###Observações
####Core
- Rodar testes unitários e gerar relatório: 
```
$ gradle test jacocoTestReport
```

-  Inserir massa de dados para testes manuais do Web e WS:
```
$ gradle runDataTests
```
- Inserir dados de deploy no banco de dados de produção: (Usuário admin inicial) (login: admin@admin, senha: admin)
```
$ gradle runDeployTests
```




