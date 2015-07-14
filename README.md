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


#################################################################################
                      Création de données en base
#################################################################################

- pom.xml : ajout de dépendances pour valider le formulaire :
	- validation-api
	- hibernate-validator
	
- /WEB-INF/dispatcher-servlet.xml : ajout de ligne < mvc:annotation-driven > pour activer les annotations de validation de formulaire

- ajout de libellés dans messages_fr.properties

- création du formulaire "CreationForm.java" dans com.rija.dev.controller qui utilise les annotations "NotEmpty" et "Pattern" pour indiquer les contraintes de validation

- IListeCoursesDAO : ajout de la méthode void creerCourse(final Course pCourse);

- IListeCoursesService : ajout de la méthode void creerCourse(final String pLibelle, final Integer pQuantite);

- ListeCoursesDAO : implémentation de void creerCourse(final Course pCourse);

- ListeCoursesService : implémentation de void creerCourse(final String pLibelle, final Integer pQuantite);

- Création du controller CreerListeCoursesController
	- méthode afficher qui retourne "creation" : 
		- place la liste des courses dans l'attribut "listeCourses"
		- initialise le formulaire "creation" s'il n'est pas déjà présent dans l'attribut "creation"
		
	- méthode creer qui retourne afficher(pModel) :
		- appelle la méthode de création en base de données s'il n'y a pas d'erreurs dans la validation
		- appelle simplement la méthode "afficher" pour l'affichage de la page. 
		
- création de la /vues/creation.jsp :
	- formulaire
	- messages d'erreur
	
- test : http://localhost:8080/tutoriel-web-spring/afficherCreationListeCourses
 

#################################################################################
                       Suppression de données en base
#################################################################################

- ajout de libellés dans messages_fr.properties

- IListeCoursesDAO : ajout de la méthode void supprimerCourse(final Course pCourse);

- IListeCoursesService : ajout de la méthode void supprimerCourse(final Integer pIdCourse);

- ListeCoursesDAO : implémentation de void supprimerCourse(final Course pCourse);

- ListeCoursesService : implémentation de void supprimerCourse(final Integer pIdCourse);

- Création du controller SupprimerListeCoursesController
	- méthode afficher qui retourne "suppression" : 
		- place la liste des courses dans l'attribut "listeCourses"
		
	- méthode supprimer qui retourne afficher(pModel) :
		- utilise le paramètre "idCourse" de la requête pour appeler la méthode "supprimerCourse" du service
		- appelle simplement la méthode "afficher" pour l'affichage de la page.
		
- création de la /vues/suppression.jsp

- test : http://localhost:8080/tutoriel-web-spring/afficherSuppressionListeCourses


#################################################################################
                     Modification de données en base
#################################################################################

- Modification en masse de la quantité de liste de courses

- ajout de libellés dans messages_fr.properties

- création de ValidationMessages_fr.properties

- IListeCoursesDAO : ajout de la méthode void modifierCourse(final Course pCourse);

- IListeCoursesService : ajout de la méthode void modifierCourses(final List<Course> pListeCourses);

- ListeCoursesDAO : implémentation de void modifierCourse(final Course pCourse);
	- modifie une entité en bdd
	- lève une exception uniquement si la requête modifie un nombre d'occurrences différent de 1

- ListeCoursesService : implémentation de void modifierCourses(final List<Course> pListeCourses);
	- parcourt les entités de la liste pour les passer en paramètre l'une après l'autre à la DAO
	
- création du formulaire ModificationForm.java dans com.rija.dev.controller 
	- contenant une liste qui comporte l'annotation "@Valid". 
	- cela provoquera la validation de chaque élément de la liste lorsque le formulaire sera validé en entrée de la méthode "modifier" du contrôleur

- création de la classe ModificationCourse dans com.rija.dev.controller
	- cette classe correspond à un élément de la liste contenue dans le formulaire "ModificationForm" ~= bean Course (quantite String avec les annotations)
	- les annotations "NotEmpty" et "Pattern" comportent des valeurs "message" qui correspondent aux messages internationalisés contenus dans le fichier "ValidationMessages_fr.properties" 
	
- Création du controller ModifierListeCoursesController
	- méthode afficher qui retourne "modification" : 
		- place la liste des courses dans le formulaire
		
	- méthode modifier qui retourne afficher(pModel) :
		- récupère la liste des courses du formulaire pour appeler la méthode de modification du service
		- appelle simplement la méthode "afficher" pour l'affichage de la page.
		
- création de la /vues/modification.jsp
	-  la variable "status" permet de nommer les champs du formulaire et de filtrer les messages d'erreur selon l'occurrence de la liste des courses. 
	
- test : http://localhost:8080/tutoriel-web-spring/afficherModificationListeCourses
