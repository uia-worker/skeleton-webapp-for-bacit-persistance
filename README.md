# skeleton-webapp-for-bacit-persistance
Testing av persistens med mariadb, maven 2 og payara (IS-201/202 høsten 2020)
```console
docker run --rm --name is201-mariadb -p 127.0.0.1:3306:3306/tcp -v <my/own/datadir>:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=Ex20200702-IS201 -d mariadb:latest
docker run --rm --name java_maven2_build -it -v "$PWD":/usr/src/app  -v "$HOME"/.m2:/root/.m2 janisdocker/javabuilder clean install
docker run --rm --name my-payara -p 8080:8080 -p 4848:4848 -v $PWD/target:/opt/payara/deployments payara/server-full
```
<my/own/datadir> skal erstattes med en mappe du ønsker å lagre data som blir generert i kontainer (f.eks. $HOME/mysql_data), hvor $HOME er en miljøvariabel i Linux og macOS miljøer, så gjelder også WSL på Windows 10 HE)
