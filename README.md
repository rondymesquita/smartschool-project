# Smartschool Project

## Requisitos
- Gradle 1.8+
- Tomcat 7+
- Banco de dados Mysql or Postgres

#### Configurando e executando
* Instale o Gradle em [https://www.gradle.org](https://www.gradle.org)

* Configure suas credenciais do banco de dados em **smartschool-core/src/main/resources/application.properties**
* Vá para **smartschool-core** via terminal e execute
```
$ gradle install
```
* Vá para **smartschool-ws** via terminal e execute
```
$ gradle eclipse
```

- Agora, execute **smartschool-ws** no Tomcat
	- Para gerar o pacote **war**, vá para **smartschool-ws** e execute
```
$ gradle package
```
. Gradle irá gerar o pacote **war** em **smartschool-ws/build**