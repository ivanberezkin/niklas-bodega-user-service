## Branchstrategi

Vår grupp använde en Git Flow-inspirerad strategi. Vi delade upp uppgiften i mindre issues och skapade sedan en feature branch för varje issue. Det gör det lättare att se vad varje person jobbar med, och genom att jobba i separata feature branches minimerar vi risken för mergekonflikter.

Flödet ser ut så här:

1. När en feature är klar skapas en pull request (PR) mot `pre-prod`, som måste granskas och godkännas av en person.
2. När PR:en är godkänd mergas den in i `pre-prod`.
3. När PR:en har mergats körs vår CI/CD-pipeline, som bygger en Docker-image och publicerar den på Docker Hub.
4. Nya Docker Image redeployeas automatiskt på Staging environment genom att använda :latest.
5. När flera features är klara och mergade i `pre-prod` skapar vi en PR från `pre-prod` mot `main` för att släppa koden till produktion.
6. PR:er mot `main` kontrolleras av CodeQL och måste godkännas av två personer.
7. För att aktivera nya imagen på Production Environment i Railway behöver man köra Deploy Production actionen i github och ange vilken image man vill aktivera (via sha nummer).


## Rollback
Varje ny deployment skapar en image med två taggar: en unik tagg (`image` + sha) och `latest`. På så sätt har vi alltid kvar äldre versioner av våra releaser.

Om vi behöver göra en rollback går vi in i Railway, väljer produktionsmiljön och byter till en äldre version av Docker-imagen.


## MergiKonflikten
Vi triggade en merge konflikt genom att skriva svaren ovan på samma feature-branch och pushade upp efter varandra. Den första personen kunde pusha, men den andra personen var tvungen att pulla först och då triggades en mergekonflikt.
Lösningen var att vi pratade ihop oss och valde det alternativet som passade oss bättre.

## Länk Till Railway
https://railway.com/project/df08f7c5-35c6-4c9e-a29b-9b0cc3ad887c
Det finns två environments (Staging och Production)

lägger till för demo.