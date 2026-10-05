Branching-strategi och CI/CD-flöde
Vi har valt GitHub Flow som vår branching-strategi. Eftersom vi tidigare har arbetat med Trunk-based development kändes det naturligt att testa ett nytt tillvägagångssätt.

Arbetsflöde och miljöer
Vi använder en pre-prod-branch för att samla och testa kod i vår staging-miljö på Railway innan den når produktion:

Feature branches: All ny funktionalitet utvecklas i separata feature-branches.

Staging (Railway): När en ändring är klar skapas en Pull Request (PR) mot pre-prod. Vid godkänd och genomförd merge byggs en ny Docker-image som automatiskt deployas till vår staging-miljö.

Produktion (Railway): När ändringarna har verifierats i staging mergas pre-prod in i main, vilket utlöser en deployment till vår produktionsmiljö på Railway.

Hantering av rollbacks
Om vi behöver göra en rollback har vi tillgång til alla tidigare byggda versioner via Docker Hub. Vi återställer enkelt systemet genom att peka om Railway till en tidigare önskad image-tagg.

(Detta är vårt försök till en merge-konflikt – bäst README vinner!)