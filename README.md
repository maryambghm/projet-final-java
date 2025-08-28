# 🎮 Game Arena - Application de gestion de tournois de jeux vidéo

## 📌 Introduction

**Game Arena** est une application web conçue pour faciliter l'organisation et la gestion de tournois de jeux vidéo. Cette plateforme permet aux joueurs de participer à des compétitions et aux administrateurs de gérer les événements, les participants et leurs performances.

---

## 🎯 Objectifs du projet

### Objectif principal
Développer une application robuste et évolutive permettant de gérer efficacement des tournois de jeux vidéo.

### Objectifs secondaires
- Offrir une interface utilisateur intuitive et agréable.
- Garantir la sécurité des données et une gestion fiable des utilisateurs.
- Implémenter une architecture en trois couches pour une meilleure maintenance et évolutivité.

---

## 🧩 Contexte et justification

Avec la montée en puissance des compétitions e-sport et des communautés de gamers, il est crucial de disposer d'outils performants pour organiser et suivre les tournois. Cette application propose une solution centralisée pour gérer les événements, 
les joueurs et les résultats, tout en garantissant une expérience utilisateur de qualité.

---

## 🧠 Fonctionnalités

### 1. Front-end

#### Interface utilisateur :
- Page d'accueil avec tournois à la une, prochains événements, actualités.
- Pages de détail pour chaque tournoi : informations, calendrier des matchs, inscriptions, statut du tournoi.
- Dashboard utilisateur : suivi des participations, résultats, gestion du profil.

#### Gestion des utilisateurs :
- Formulaires d’inscription, connexion, mot de passe oublié.
- Gestion du profil utilisateur (infos, avatar).

#### Communication :
- Notifications pour rappels, résultats, mises à jour.
- Messagerie interne entre joueurs et administrateurs.

---

### 2. Back-end

#### Gestion des tournois :
- Création/modification de tournois par les admins (nom, jeu, format, règles…).
- Inscriptions des joueurs avec vérification des critères.
- Génération automatique des brackets (simple/double élimination).

#### Gestion des matchs :
- Planification des matchs (date/heure).
- Validation des résultats par les administrateurs.
- Mise à jour automatique des brackets et classements.

#### Statistiques et performances :
- Statistiques détaillées (victoires, défaites, ratio).
- Historique des participations et performances par joueur.

---

### 3. DAO

#### Données utilisateurs :
- Stockage sécurisé des infos personnelles, rôles et préférences.
- Chiffrement des mots de passe et protection des emails.

#### Données tournois :
- Base de données complète des tournois, participants et résultats.
- Historique des événements passés et statistiques associées.

#### Logs & monitoring :
- Journalisation des actions critiques.
- Monitoring de la performance et analyse des erreurs.

---

## 🛠️ Technologies utilisées

| Élément                        | Technologie                                   |
|-------------------------------|-----------------------------------------------|
| Langage                       | Java 21                                       |
| Frameworks                    | Spring Core, Spring MVC, Spring Data JPA      |
| Vue (templates)               | Thymeleaf                                     |
| Base de données               | MySQL                           |
| Build & dépendances           | Maven                                         |
| Sécurité                      | Spring Security                               |
| Contrôle de version           | Git                                           |

---

## ✅ Exigences fonctionnelles

### Utilisateurs :
- Inscription, connexion, modification de profil.
- Administration des tournois, gestion des résultats.

### Tournois :
- Création de tournois avec différents formats.
- Inscriptions et génération de matchs.

### Notifications & communication :
- Système de notification intégré.
- Messagerie interne.

---

## 🔐 Exigences non fonctionnelles

- **Sécurité :**
  - Chiffrement des données sensibles.
  - Protection contre les attaques XSS, CSRF, injections SQL.
- **Accessibilité :**
  - Design responsive (PC, tablettes, mobiles).
  - Conformité aux normes d’accessibilité (WCAG).

---

## 📦 Livrables

- **Code source** : dépôt Git.
- **Documentation** :
  - Technique : architecture, API, déploiement.
  - Utilisateur : guide, FAQ, guide d'administration.
- **Tests** :
  - Tests unitaires et d’intégration.
  - Rapport de tests + plan de correction des bugs.

---

## 👥 Équipe projet

- Équipe composée de **3 développeurs Java**.
- Travail collaboratif basé sur Git.
- Réunions de suivi et gestion agile du projet.

---

## 📅 Gestion de projet

- Méthodologie : Agile / Scrum
- Sprints avec jalons journaliers.
- Suivi de tâches via un outil collaboratif (Trello).

---

## 🚀 Lancement

> ⚙️ Lancement prévu dès que l’architecture de base est validée et que les premiers modules sont testés. Le déploiement pourra se faire sur un serveur local ou un service cloud selon les contraintes techniques.

---

