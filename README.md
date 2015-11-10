# Gsafe tests d'acceptance

Les tests d'acceptance sont lancés via jbehave.
Allez dans gsafe-validation-tests et lancez la commande:

```
mvn clean integration-test
```
# Gsafe tests de charge

Si le mock vous est nécessaire pour lancer les tests de charge, allez dans gsafe-load-mock et lancez
```
mvn clean package
```
puis 
```
java -jar target/mock-1.0-SNAPSHOT.jar server config.yml
```

Vous pouvez ensuite lancer les tests de charge en vous rendant dans gsafe-load-tests et en lançant
```
sbt test -Dload.env=integration
```
Les données des environnements sont paramétrables dans gsafe-load-tests/resources/application.conf
Un fichier de test est disponible dans gsafe-load-test/src/main/resources (à déplacer dans /var/gsafe/gatling par défaut)
