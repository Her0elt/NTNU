-- oppgave 2
-- 1

SELECT * from borettslag WHERE borettslag.etabl_aar >= 175 AND borettslag.etabl_aar <= 1985
SELECT * from borettslag WHERE borettslag.etabl_aar BETWEEN 175 AND 1985

--2

SELECT (CONCAT(fornavn, ' ', etternavn)) AS navn, (SELECT YEAR(CURDATE())) -((ansiennitet)) as ansiennitet 
FROM andelseier ORDER BY ansiennitet

--3

SELECT * FROM borettslag ORDER BY etabl_aar LIMIT 1
SELECT MIN(*) FROM borettslag ORDER BY etabl_aar

--4 

SELECT DISTINCT(bygn_adr) FROM bygning 
LEFT JOIN leilighet ON leilighet.bygn_id = bygning.bygn_id AND leilighet.ant_rom >= 3

--5

SELECT COUNT(*) as antall FROM borettslag JOIN bygning ON bygning.bolag_navn = borettslag.bolag_navn AND bygning.bolag_navn = 'Tertitten'
SELECT COUNT(*) as antall FROM bygining WHERE bygning.bolag_navn = 'Tertitten'

--6

SELECT borettslag.* ,COUNT(bygning.bolag_navn) FROM bygning WHERE bygning.bolag_navn = borettslag.bolag_navn as antall_bygninger 
FROM borettslag ORDER BY borettslag.bolag_navn

--7

SELECT COUNT(*) as antall FROM leilighet 
JOIN bygning on bygning.bygn_id = leilighet.bygn_id AND bygning.bolag_navn = 'Tertitten'

--8

SELECT MAX(ant_etasjer) FROM bygning WHERE bolag_navn = 'Tertitten'

--9

SELECT (CONCAT(fornavn, ' ', etternavn)) AS navn, telefon FROM andelseier WHERE NOT EXISTS 
(SELECT leilighet.bygn_id FROM leilighet WHERE leilighet.and_eier_nr = andelseier.and_eier_nr)

--10

SELECT borettslag.* , COUNT(andelseier.bolag_navn) FROM andelseier 
WHERE andelseier.bolag_navn = borettslag.bolag_navn)as antall_eiere 
FROM borettslag 
ORDER BY antall_eiere

--11

SELECT * , (SELECT leilighet.leil_nr FROM leilighet
WHERE leilighet.and_eier_nr = andelseier.and_eier_nr) as leilighet_nr 
FROM andelseier

--12

SELECT DISTINCT(borettslag.bolag_navn) FROM borettslag 
JOIN bygning ON bygning.bolag_navn = borettslag.bolag_navn 
JOIN leilighet ON leilighet.bygn_id = bygning.bygn_id AND leilighet.ant_rom = 4

--13

SELECT poststed.poststed, poststed.postnr, COUNT(andelseier.and_eier_nr) as antall FROM andelseier 
JOIN leilighet ON leilighet.and_eier_nr = andelseier.and_eier_nr  
JOIN bygning ON leilighet.bygn_id = bygning.bygn_id 
JOIN poststed ON bygning.postnr = poststed.postnr 
GROUP BY poststed.postnr
