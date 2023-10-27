# Suppositions

Les suppositions métier effectuées :
- Dans un monde idéal, le lexique aurait été élaboré avec le métier, via un Event Storming par exemple
- Pour le calcul du montant total :
  - On suppose que le prix est la somme des montants pour la consommation de chaque type d'énergie, arrondie chacune au centime inférieur, afin de fournir au client un montant n'ayant pas de fractions de centimes.
  - On considère que si un pro a un CA exactement égal à 1 000 000 €, il se classe dans la catégorie de prix CA supérieur à 1 000 000 €.

Les cas de tests dans les classes `PrivateIndividualTest.java` ainsi que `LegalEntityTest.java` permettent de vérifier
que le programme calcule les montants corrects pour les particuliers et les clients pro.
