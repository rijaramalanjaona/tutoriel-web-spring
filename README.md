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
