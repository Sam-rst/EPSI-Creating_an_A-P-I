# Tony Stark IA

Ce projet a été créé par Samuel RESSIOT dans le cadre d'un cours en Spring Java pour créer une API qui demande à une IA sous license Ollama avec le model llama3.2:1b qui possèdent 1 milliard de données.

Sources :
- Site officiel d'Ollama : <https://ollama.com/>
- Lien du github d'Ollama : <https://github.com/ollama/ollama>

## Requirements

- JDK17
- Gradle
- Base de données MySQL
- IntelliJ / Vscode (README moins adapté)

## Configuration

1. Ajouter ses identifiants de base de données dans les environnements de variables des configs :
    - username
    - password


2. Créer sa base de données sous le nom : **ollama**

3. Lancer l'application : **TonyStarkAIApplication**

4. Tester les différentes routes sur Postman :
    - Poser une question à Tony : *POST /conversation/askQuestion*
        - Params :
            - question: string
            - id_conversation: int (Optional : si vous souhaitez raccorder la question à une conversation)

    - Récupérer la conversation selon son id : *GET /conversation/{id}*
        - Params :
            - id_conversation: int
