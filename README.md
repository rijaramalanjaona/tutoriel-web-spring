# tutoriel-web-spring

Hello world from
http://rpouiller.developpez.com/tutoriels/spring/application-web-spring-hibernate/

- créer projet maven

- modifier pom.xml pour ajouter la dépendance vers servlet-api

- créér bonjour.jsp dans /tutoriel-web-spring/src/main/webapp/vues

- ajouter dépendance spring-webmvc dans pom.xml

- modifier /WEB-INF/web.xml : ajouter contextConfigLocation et ContextLoaderListener

- créer /WEB-INF/dispatcher-servlet.xml

- créer messages_fr.properties dans src/main/resources

- ajouter dans bonjour.jsp le taglib de spring, utiliser <spring:message code="titre.bonjour" />

- /WEB-INF/web.xml Declaration de la servlet de Spring et de son mapping

- créer un Controller BonjourController.java avec les annotations

- dispatcher-servlet.xml ajouter component-scan pour activer la configuration par annotations 

- dispatcher-servlet.xml ajouter la déclaration du bean "InternalResourceViewResolver" qui permet d'indiquer où chercher les ressources

- messages_fr.properties ajouter un EL (Expressions Languages) {0}

- bonjour.jsp dans @page ajouter isELIgnored="false", dans le corps de la jsp ${personne}


#################################################################################
                                  HSQLDB
#################################################################################								  

- download HSQLDB : http://sourceforge.net/projects/hsqldb/?source=typ_redirect
- extract zip dans C:
- lancement : C:\hsqldb-2.3.3\hsqldb\bin\runManagerSwing.bat
- connect :
	- recent : ne pas toucher
	- setting name : nom de la nouvelle bdd (maBase)
	- Type : HSQL database engine in memory
	- driver : ne pas toucher
	- url : jdbc:hsqldb:file:C:\hsqldb-2.3.3\hsqldb\data\maBase
	- user : SA
	- password : 
	
- selectionner la bdd maBase
- menu Command
	- CREATE TABLE
	- INSERT
	- SELECT
	
	
#################################################################################
                 Parametrage ressource JDBC dans tomcat
#################################################################################	

- server.xml : ajout de GlobalNamingResources (création de la ressource DataSource "jdbc/dsMaBase")

- context.xml : ajout de ResourceLink pour associer la ressource jdbc/dsMaBase indiquée dans le fichier "server.xml" avec le nom "jdbc/dsMonApplication" que l'on utilisera dans l'application


#################################################################################
        Modification du projet afin d'inclure l'affichage des données
#################################################################################

- /WEB-INF/web.xml : ajout de resource-ref Declaration de l'utilisation de la ressource JDBC

- pom.xml : ajout des dépendances maven :
	- spring-orm
	- hibernate-entitymanager
	- jstl
	
- création du fichier src/main/resources/META-INF/persistence.xml pour indiquer que la persistance est réalisée grâce à Hibernate

- ajout dans dispatcher-servlet.xml  : 
	- <tx:annotation-driven transaction-manager="transactionManager"
	- bean JndiObjectFactoryBean pour déclarer l'utilisation de la ressource JDBC
	- bean LocalContainerEntityManagerFactoryBean utilise la ressource JDBC et le fichier "persistence.xml" pour aboutir à la création du "EntityManager" qui est utilisé dans la DAO
	- bean JpaTransactionManager pemret d'instancier le gestionnaire de transaction et lui associer la fabrique de "EntityManager"
	
- création de la classe entité Course dans com.rija.dev.bean

- création de l'interface IListeCoursesDAO dans com.rija.dev.dao

- création de l'interface IListeCoursesService dans com.rija.dev.services

- création de la classe ListeCoursesDAO dans com.rija.dev.dao

- création de la classe ListeCoursesService dans com.rija.dev.services

- création du controller AfficherListeCoursesController dans com.rija.dev.controller

- ajout de libellés dans messages_fr.properties

- création de la vue vues/listeCourses.jsp

- fermer le runManagerSwing de HSQLDB avant de lancer l'application http://localhost:8080/tutoriel-web-spring/afficherListeCourses
