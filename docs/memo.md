# Memo

## Vérification de la solution

Les cas de tests dans les classes `PrivateIndividualTest.java` ainsi que `LegalEntityTest.java` permettent de vérifier
que le programme calcule les montants corrects pour les particuliers et les clients pro.

## Suppositions

Les suppositions métier effectuées :
- Dans un monde idéal, le lexique aurait été élaboré avec le métier, via un Event Storming par exemple
- Pour le calcul du montant total :
  - On suppose que le prix est la somme des montants pour la consommation de chaque type d'énergie, arrondie chacune au centime inférieur, afin de fournir au client un montant n'ayant pas de fractions de centimes.
  - On considère que si un pro a un CA exactement égal à 1 000 000 €, il se classe dans la catégorie de prix CA supérieur à 1 000 000 €.

## Pour aller plus loin

J'ai légèrement dépassé le temps imparti, il me semble.

Pour aller plus loin :
- l'antépénultième commit aurait dû être splitté entre la refacto déplaçant la logique relative au calcul de la facture dans l'interface et l'implémentation du calcul dans l'objet Legal entity
- Mise en place de linter (PMD)
- Mise en place de test d'architecture (ArchUnit par exemple) pour s'assurer du bon découpage entre la couche domain et infra (qui n'existe pas pour l'instant)
- Mise en place d'un exemple de port/adapter pour aller récupérer des données dans un fichier plat ou une base de données et afficher les résultats dans la console
