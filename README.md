# skeleton-webapp-for-bacit-persistance
Testing av persistens med mariadb, maven 2 og payara (IS-201/202 høsten 2020)

Bruk "Fork" først og gjør repositorien din egen (i ditt github konto).
Så kan du klone den lokalt
$ git clone https://github.com/uia-worker/skeleton-webapp-for-bacit-persistance.git

Starte en databasetjeneste (<my/own/datadir> er en lokal mappe, så det må man endre; <mysql_root_passord> er passordet til database administrator som har alle rettighetene mot databasen, inkludert sletting av alt)
```console
$ docker run --rm --name is201-mariadb -p 127.0.0.1:3306:3306/tcp -v <my/own/datadir>:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=<mysql_root_passord> -d mariadb:latest
```
Bruke maven 2 for å kompilere og linke kode og lage en WAR-fil (webapplikasjonsfil); må utføres fra den mappen hvor filstrukturen for Java webapplikasjonen ligger (jeg genererte filstrukturen vha. Maven arktetyper)
```console
$ docker run --rm --name java_maven2_build -it -v "$PWD":/usr/src/app  -v "$HOME"/.m2:/root/.m2 janisdocker/javabuilder clean install
```
Starte Payara server (tidligere Glassfish; OBS! legg merke til at kun WAR-fil med applikasjons-spesifikt navn kopieres over til deployeringsmappen inne i kontaineren)
```console
$ docker run --rm --name my-payara -p 8080:8080 -p 4848:4848 -v $PWD/target/servletannotation.war:/opt/payara/deployments/servletannotation.war payara/server-full
```
**$PWD** er miljøvariabelen i Linux/Unix miljøer som står for "Print Working Directory" og den kan brukes for å representere den mappen som man utfører programmet i

**$HOME** er miljøvariabelen i Linux/Unix miljøer som oversettes til hjemmemappe (vanlig med /home/<dittbrukernavn>

**<my/own/datadir>** skal erstattes med en mappe du ønsker å lagre data som blir generert i kontainer (f.eks. $HOME/mysql_data), hvor $HOME er en miljøvariabel i Linux og macOS miljøer, så det gjelder også WSL 2 på Windows 10 Home Edition)
