## Branchstrategi

Vår grupp använde en Git Flow-inspirerad strategi. Vi delade upp uppgiften i mindre issues och skapade sedan en feature branch för varje issue. Det gör det lättare att se vad varje person jobbar med, och genom att jobba i separata feature branches minimerar vi risken för mergekonflikter.

Flödet ser ut så här:

1. När en feature är klar skapas en pull request (PR) mot `pre-prod`, som måste granskas och godkännas av en person.
2. När PR:en är godkänd mergas den in i `pre-prod`, som automatiskt deployas till vår staging-miljö i Railway.
3. När flera features är klara och mergade i `pre-prod` skapar vi en PR från `pre-prod` mot `main` för att släppa koden till produktion.
4. PR:er mot `main` kontrolleras av CodeQL och måste godkännas av två personer.
5. När PR:en har mergats körs vår CI/CD-pipeline, som bygger en Docker-image och publicerar den på Docker Hub.
6. För att aktivera den nya imagen i produktion går man manuellt in i Railway och väljer `latest`.

## Rollback

Varje ny deployment skapar en image med två taggar: en unik tagg (`image` + git-nummer) och `latest`. På så sätt har vi alltid kvar äldre versioner av våra releaser.

Om vi behöver göra en rollback går vi in i Railway, väljer produktionsmiljön och byter till en äldre version av Docker-imagen.