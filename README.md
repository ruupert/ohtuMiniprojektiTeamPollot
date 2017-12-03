# ohtuMiniprojektiTeamPollot

Tämä on Helsingin yliopiston syyslukauden 2017 ohjelmistotuotanto-kurssiin liittyvä osatehtävä/harjoitus, jossa tehtävänä on tuottaa ketterin menetelmin (scrum) ohjelmisto neljän viikon aikana. Tarkoitus on tuottaa [lukuvinkkikirjasto](https://github.com/mluukkai/ohjelmistotuotanto2017/wiki/miniprojekti-speksi) neljässä sprintissä. Asiakastapaamisia (=ohjaaja) on kolme(?), joissa tarkoitus on pyrkiä selvittämään mitä oikein halutaan ja sitten pyrkiä toteuttamaan niistä tärkeimmät ominaisuudet seuraavassa sprintissä.
## Sovellus

https://shitstaging.herokuapp.com/

## Backlog

https://drive.google.com/open?id=16cQePEa3Y4ZwrMPcriLoQ8XcfTCgFnOQ

## Muuta informaatiota

[Käytössä oleva spring-cucumber pohja/konfiguraatio](https://github.com/mluukkai/spring-cucumber)

## Käyttöohje

Oletus on, että Javasta löytyy sopiva versio ja se, että projektin hakemiston gradle komennot sopivat käyttämääsi ympäristöön.

Nykyisen kehyslinjan käyttööonotto tapahtuu seuraavasti. 

Kloonaa repositorio itsellesi, siirry kloonattuun hakemistoon ja käynnistä sovellus:

```sh
# git clone https://github.com/Ouzii/ohtuMiniprojektiTeamPollot.git
# cd ohtuMiniprojektiTeamPollot
# ./gradle run 

```

Windowsissa gradle -komennon sijaan suoritetaan gradlew.bat. 

Odota kunnes Tomcat on saanut itsensä alustettua ja käyntiin, niin avaa selain osoitteessa [http://localhost:8080/](http://localhost:8080)



### Master
[![Build Status](https://travis-ci.org/ruupert/ohtuMiniprojektiTeamPollot.svg?branch=master)](https://travis-ci.org/ruupert/ohtuMiniprojektiTeamPollot) [![codecov](https://codecov.io/gh/ruupert/ohtuMiniprojektiTeamPollot/branch/master/graph/badge.svg)](https://codecov.io/gh/ruupert/ohtuMiniprojektiTeamPollot) [![Maintainability](https://api.codeclimate.com/v1/badges/2042f1ea1ea3812bfcdc/maintainability)](https://codeclimate.com/github/ruupert/ohtuMiniprojektiTeamPollot/maintainability)

### Development
[![Build Status](https://travis-ci.org/ruupert/ohtuMiniprojektiTeamPollot.svg?branch=development)](https://travis-ci.org/ruupert/ohtuMiniprojektiTeamPollot) 

### Definition of Done:
- Ominaisuus on implementoitu ja integroitu sovellukseen.
- Ominaisuudelle on tehty JUnit testit ja Cucumber testit.
- Travis-ci on buildannut onnistuneesti ominaisuuden kanssa.
- Ominaisuuden on hyväksynyt tekijän lisäksi vähintään yksi toinen ryhmän jäsen.


