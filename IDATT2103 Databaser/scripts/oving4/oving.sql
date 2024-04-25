-- Oppgave 1

--a

SELECT * FROM ordredetalj 
JOIN ordrehode ON ordredetalj.ordrenr = ordrehode.ordrenr
AND ordrehode.levnr = 44;

--b

SELECT navn, levby FROM prisinfo 
JOIN levinfo ON levinfo.levnr = prisinfo.levnr 
AND prisinfo.delnr = 1;

--c


SELECT navn, prisinfo.levnr, (pris) as "pris" from levinfo 
INNER JOIN  prisinfo ON (levinfo.levnr = prisinfo.levnr AND prisinfo.delnr = 201 )
ORDER BY pris
LIMIT 1;

--d

SELECT ordrehode.ordrenr , dato, beskrivelse, kvantum, pris, (kvantum*pris) as 'belop' from ordrehode 
JOIN ordredetalj ON ordrehode.ordrenr = ordredetalj.ordrenr AND ordrehode.ordrenr = 16
JOIN prisinfo ON ordredetalj.delnr = prisinfo.delnr AND ordrehode.levnr = prisinfo.levnr
JOIN delinfo ON ordredetalj.delnr = delinfo.delnr;

--e

SELECT delnr, levnr FROM prisinfo
WHERE pris > (
  SELECT pris FROM prisinfo
  WHERE katalognr = 'X7770'
);


--f

CREATE TABLE fylke_by(
  fylke_by_id INT NOT NULL,
  levby   VARCHAR(20) NOT NULL,
	fylke   VARCHAR(20) NOT NULL,
	CONSTRAINT fylke_pk PRIMARY KEY(fylke_by_id)
  );

CREATE TABLE levinfo_2( 
  levnr INTEGER, 
  navn VARCHAR(20) NOT NULL, 
  adresse VARCHAR(20) NOT NULL, 
  fylke_by_id INT NOT NULL, 
  postnr INTEGER NOT NULL, 
  CONSTRAINT levinfo_pk PRIMARY KEY(levnr), 
  CONSTRAINT fylke_by_fk FOREIGN KEY(fylke_by_id) REFERENCES fylke_by(fylke_by_id));

INSERT INTO fylke_by VALUES(1 , 'OSLO','OSLO');
INSERT INTO  levinfo_2 VALUES(1 , 'Hermann','HHHHHHH',1, '1274');

--ii

CREATE VIEW levinfo_fylke AS
SELECT levnr,navn,adresse,postnr,levby,fylke FROM levinfo_2 
JOIN fylke_by ON levinfo_2.fylke_by_id = fylke_by.fylke_by_id;

--g

SELECT DISTINCT(levby) FROM levinfo WHERE levby NOT IN (SELECT DISTINCT(levby) FROM levinfo
JOIN prisinfo ON (levinfo.levnr = prisinfo.levnr));


--h

CREATE VIEW ordre_18 as
SELECT levinfo.levnr,ordredetalj.ordrenr, (ordredetalj.kvantum*pris) as 'belop' 
FROM ordredetalj 
JOIN prisinfo ON ordredetalj.delnr = prisinfo.delnr
JOIN levinfo ON prisinfo.levnr = levinfo.levnr
AND ordrenr = 18;

SELECT levnr, min(b) as min_belop FROM (
SELECT levnr , SUM(belop) as b
FROM ordre_18
GROUP BY levnr
HAVING COUNT(levnr) = (SELECT COUNT(delnr) FROM ordredetalj WHERE ordrenr = 18)
)min_pris
GROUP BY levnr
LIMIT 1;


SELECT levnr, SUM(pris*kvantum)as SUM FROM prisinfo 
JOIN ordredetalj ON prisinfo.delnr = ordredetalj.delnr AND ordredetalj.ordrenr = 18
GROUP by levnr HAVING COUNT(levnr) = (SELECT COUNT(delnr) FROM ordredetalj WHERE ordrenr = 18)
ORDER BY SUM(pris*kvantum) LIMIT 1;

--oppgave 2

--a

SELECT * FROM forlag
WHERE telefon LIKE '2%'
UNION ALL
SELECT * FROM forlag
WHERE telefon NOT LIKE '2%';

--b

SELECT AVG(IF(dod_aar IS NULL, YEAR(s), dod_aar)-
IF(dod_aar IS NULL AND fode_aar < 1900,NULL, fode_aar)) as avg_age FROM forfatter;

--c

SELECT COUNT(IF(dod_aar IS NULL, YEAR(CURRENT_DATE), dod_aar)-
IF(dod_aar IS NULL AND fode_aar < 1900,NULL, fode_aar))/COUNT(*) * 100 as avg_age FROM forfatter;




