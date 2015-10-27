# Partie 1 - Deux feux synchronisés
Deux feux synchronisés mais non temporisés

## Questions 1 et 2
[Feux non synchronisés et non temporisés](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part1/Q2-FeuxNonSynchro.xml)

![Rien](ressources/part1/Q2.png)

## Question 3
[Feux synchronisés et non temporisés](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part1/Q3-FeuxSynchro.xml)

![Rien](ressources/part1/Q3.png)

## Question 4
[Graphe de marquage](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part1/Q4-GrapheMarquage.txt)

![Rien](ressources/part1/traitement-question4/graphe.png)

## Question 5
Vérifications en LTL

## Question 6
**Vérifications**

- Pas de 2 feux rouges en même temps (sureté)

```
AG[0, inf](M(R1) + M(R2) >= 1)
```

- Un feu passe au moins une fois au vert (non bloqué)

```
AG[0, inf](M(V2) = 1)
```

- Les feux ne se bloquent pas entre eux

```
AG[0, inf](M(R1) + M(P7)) # P7 = place intermédiaire
```

# Partie 2 - Deux feux temporisés
## Question 7
[Feux temporisés sans synchronisation](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part2/Q7-FeuxTemporises.xml)

![Rien](ressources/part2/Q7-1.png)
![Rien](ressources/part2/Q7-2.png)

**Validations**

- A n'importe quel moment il n'y a pas deux feux rouges en même temps

```
A[](feu1.R1 or feu2.R2 or feu1.I1 or feu2.I2)
```
- Pas de deadlock

```
E<> deadlock
```


## Question 8
[Un controleur temporisé est synchronisé avec les 2 feux pour changer leurs états](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part2/Q8-ControleurTemporiseEtSynchro.xml)

![Rien](ressources/part2/Q8-1.png)
![Rien](ressources/part2/Q8-2.png)
![Rien](ressources/part2/Q8-3.png)

**Validations**

- A n'importe quel moment il n'y a pas deux feux rouges en même temps

```
A[](feu1.R1 or feu2.R2)
```

- ?????????

```
E<>(feu1.R1 and feu2.O2)
```

- Pas de deadlock

```
E<> deadlock
```

# Partie 3 - Carrefour en T
## Question 9
[Sans contraintes de temps](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part3/Q9-SansContraintesTemps.xml)

- 2 feux de la grande route considérés comme un seul
- Processus qui régule l'arrivée des voiture dans la petite rue

![Rien](ressources/part3/Q9-1.png)
![Rien](ressources/part3/Q9-2.png)
![Rien](ressources/part3/Q9-3.png)

## Question 10
[Avec contraintes de temps](https://github.com/masters-info-nantes/hong-cheng-lv/blob/master/ressources/part3/Q10-AvecContraintesTemps.xml)

- Petite rue verte 30 secondes
- Dans un cycle, grande route verte au moins 30 secondes
- Delai de 1 seconde entre chaque changement de couleur
- Reste orange pendant 5 secondes

![Rien](ressources/part3/Q10-1.png)
![Rien](ressources/part3/Q10-2.png)
![Rien](ressources/part3/Q10-3.png)

## Question 11

**Validations question 9**

- A n'importe quel moment il n'y a pas deux feux rouges en même temps

```
A[](Major.R1 or Minor.R2 or Minor.R2p)
```

- Pas de deadlock

```
E<> deadlock
```

**Validations question 10**

- A n'importe quel moment il n'y a pas deux feux rouges en même temps

```
A[](Major.R1 or Minor.R2 or Minor.R2p)
```

- Pas de deadlock

```
E<> deadlock
```