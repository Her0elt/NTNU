--1
--a 
SELECT bok_id tittel FROM BOK WHERE bok_id = 1
--b
SELECT * FROM BOK, FORLAG Du får ut alt fra begge tabellene med dublikater
--c
SELECT * FROM FORLAG, BOK WHERE BOK.bok_id = FORLAG.forlag_id

SELECT * FROM FORLAG NATURAL JOIN BOK WHERE BOK.bok_id = FORLAG.forlag_id

--d UNION og INTERSECT er operatorer som må være unionkompatible som betyr at de begge tabelene som blir utført operasjoner 
--på må ha like mange colunner og de kolonnene må ha samme data type
--SELECT * FROM FORLAG UNION SELECT * FROM BOK 
--denne setning henter ut alt fra begge tabellene og legger sammen det med samme type verdi. 

--2

--a

SELECT DISTINCT forlag_navn FROM FORLAG  --projeksjon

--b

SELECT FORLAG.forlag_id FROM FORLAG LEFT JOIN BOK ON FORLAG.forlag_id = BOK.forlag_id WHERE BOK.forlag_id IS NULL f

--forening og projeksjon diffresjon

--c

SELECT * FROM FORFATTER WHERE fode_aar = 1948 seleksjon

--d 

SELECT FORLAG.forlag_navn, FORLAG.adresse FROM FORLAG JOIN BOK ON FORLAG.forlag_id = BOK.forlag_id AND BOK.tittel = 'Generation X' 
--forening og Projeksjon, seleksjon, 

--e

SELECT BOK.tittel FROM BOK 
JOIN BOK_FORFATTER ON (BOK.bok_id = BOK_FORFATTER.bok_id) 
JOIN FORFATTER ON FORFATTER.forfatter_id = BOK_FORFATTER.forfatter_id AND FORFATTER.etternavn = ‘Hamsun’ 
--projeksjon og forening, seleksjon 

--f

SELECT FORLAG.forlag_navn, FORLAG.adresse, FORLAG.telefon, BOK.tittel, BOK.utgitt_aar FROM FORLAG 
LEFT JOIN BOK ON FORLAG.forlag_id = BOK.forlag_id 
--projeksjon og forening yttreforing 
