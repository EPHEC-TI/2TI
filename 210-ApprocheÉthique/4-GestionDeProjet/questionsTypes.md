# Gestion de projet
[EPHEC](http://www.ephec.be/cours-du-jours/nos-formations/informatique-3) - 2016/17 - T2105 Gestion de projet
_Christophe Gossiaux_

## Q&A - Gestion de Projet

### 1. Quelles sont les 4 valeurs d'Agiles ?

> **Les 4 valeurs fondamentales sont** :
>  * **L'équipe** : La communication et le fait d'avoir une équipe soudée est plus important que les outils utilisés.
> * **L'application** : L'application se doit d'être fonctionnelle, la documentation n'est pas cruciale mais appréciable, car elle représente une charge de travail en plus.
> * **La collaboration** : Le client se doit d'être présent durant toutes les phases du projet et non uniquement lors de la négociation du contrat.
> * **Acceptation du changement** : Le projet se doit d'être flexible et de pouvoir évoluer en fonction des demandes.

### 2. Quels sont les 4 caractéristiques des méthodologies légères ?
> * **Organisation**
> * **Adaptations** aux changements
> * Le fonctionnement est **centré sur les gens**
> * **Pas de documentation exagérée**

### 3. Qu'est-ce que le pair programming ? Quel est son avantage ?
> Il s'agit d'une méthode où **2 programmeurs travaillent sur la même machine**. Le **driver** (celui qui écrit le code) est assistée par **l'observater**. Il a pour rôle de donner son avis et remarquer les imperfections sur le code dans le même temps de rédaction. Il peut donc donner des alternatives de développement. Les rôles s'échangent fréquemment durant la séance.

### 4. Pourquoi dit-on "Si ça fait mal, faites-le plus souvent" ?
>Dans certains projets, il y a des tâches qui sont ennuyantes ou qui font simplement peur, pour surmonter ce types de problèmes, il faut travailler dessus de manière journalière pour que ce soit plus facile à exécuter et ne poseront plus problème plus tard.

### 5. Qu'est-ce qu'un Product Owner ?
* Quel est son rôle et quelles tâches doit-il accomplir ?
* De quelle méthodologie s'agit-il ?
>Le **Product Owner** est *la personne de référence* qui connait le produit et il représente le client. Il a donc pour tâche de le conseiller en autre.

> 1. **Ses tâches principales sont de** :
> * Définir les fonctionnalités
> * Répondre aux questions du client
> * Planifie les étapes de réalisations du produit (fonctionnalités)
> * Valide le résultat
>2. **Il s'agit de la méthodologie Scrum**.

### 6. A quoi correspond "Red - Green - Refactor" ?
Expliquez comment ça marche et l'intérêt

>Il s'agit d'une méthodologie propre à l'eXtreme Programming. Le principe est d'écrire les tests avant même d'écrire le code.
> * **Red** : Ecriture des tests-unitaires
> * **Green** : Ecriture du code
> * **Refractor** : Modification du code si nécessaire

### 7. Dans le cas présent :
Vous êtes le chef d’une équipe qui développe une application depuis le mois de juin. L’ensemble des fonctionnalités ont été promises au client pour fin décembre mais en octobre vous avez déjà 2 mois de retard sur le planning. Votre supérieur hiérarchique vous convoque pour vous demander des comptes sur votre mauvaise gestion. Il propose de remédier à la situation en demandant à l’équipe de travailler tous les samedis jusqu’à la fin du projet, les heures supplémentaires seront payées à 150%.

* **Expliquez-lui le problème de ce genre de solution en faisant référence à la théorie vue en cours.**
* **Comme vous savez qu’il est trop tard pour agrandir l’équipe, quelle alternative pouvez-vous lui proposer ?**

> * Le fait de rajouter des heures de travail dans un domaine stressant et où les heures de travail ne comptent déjà pas peut encore réduire la productivité d'une équipe, voir même provoquer un burn-out chez certaines personnes. De plus, les estimations de temps pour ce genre de projet seront faussées.
> * Il faut revoir les délais et essayer d'avoir les fonctionnalités principales fonctionnelles

### 8. Dans le cas présent :
On vous a donné la responsabilité du développement d’une application mobile se connectant à un web service qui sera, lui, développé par une autre société. Pour ce faire vous avez reçu une documentation complète de l’API du web service qui sera développé. Pour ne pas perdre de temps, le planning prévoit 3 mois de développement où l’application mobile et le web service seront réalisés en parallèle. Les 2 équipes travailleront dans un environnement dédié et indépendant l’une de l’autre de façon à ne pas entraver l’autre. Ensuite viendra la phase de test où votre application mobile sera connectée au web service de la société externe pour effectuer une série de tests.
* Que peut-on critiquer dans cette approche et quels sont les problèmes auxquels on peut s’attendre ?
* Proposez une solution en vous basant sur la théorie vue au cours et en expliquant les avantages.

> * Il y a un problème de communication car chacun fait son application dans son coin sans feedback de l'un vers l'autre. Le plus gros problème est que lorsque l'on mettra les 2 codes ensembles qu'il y ait des gros problèmes de fonctionnalités.
> * Il faudrait réaliser des "small releases" et organiser des réunions avec les deux équipes à chaque étape de projet pour voir si toutes les parties sont fonctionnelles, les unes avec les autres. Les équipes peuvent donc s'entre-aider. Il s'agit d'une méthodologie eXtreme Programming.

### 9. Qu'est-ce que le planning poker ?
* Comment se déroule-t-il ? A quoi sert-il ?

>Il s'agit d'une manière ludique d'estimer le temps et la complexité d'une tâche à développer, chacun peut donner son avis, le tout s'accomplit à l'aide d'un jeu de carte basé sur Fibonacci pour avoir des valeurs plus éloignées.
> * Déroulement :
>   * L'équipe se regroupe
>   * Le responsable explique le but du projet
>   * Des questions sont posées, des conditions mises en place
>   * La complexité de tâche est évaluée par chaque participant et ils choisissent une carte représentant la difficulté.
>   * La carte est retournée, si trop grosse différence de valeur, nouvelle discussion
>   * La discussion est terminée lorsque les votes sont unanimes.
> * Permet d'avoir l'avis de plusieurs personnes plus ou moins impliquées dans le projet et permet d'identifier d'éventuelles tâches trop compliquées.
