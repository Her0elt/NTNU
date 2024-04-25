
stoltype(typenavn)
modell(modell, typenavn*)
produksjonsstol(stolnr, modell*)
standardstol(stolnr*, pris, ant_på_lager)
bestilling(ordrenr*, stolnr*, antall, (stoff_deltypenr, rullnr)*)
ordre(ordrenr, ordredato, antatt_levdato, reell_levdato, rabatt, leveringsstatus, kundenr*)
kunde(kundenr, kundenavn, kundeadresse)
spesialstol(stolnr*, (deltypenr, rullnr)*)
deltype(deltypenr, navn, farge, pris)
ordinær_del(deltypenr*, ant_på_lager, stoffbehov)
stoff(deltypenr*)
stoff(deltypenr*,rullnr*, ant_meter_rest)
sammensetning(deltypenr*, stolnr*, antall, status)
arbeidsstasjon(arbst_nr, arbst_navn, plassering, informasjon)




--1
SELECT t.typenavn, COUNT(*) AS "ant_modeller"
FROM stoltype t LEFT JOIN modell m ON (t.typenavn = m.typenavn)
GROUP BY t.typenavn;

--2

SELECT t.typenavn, AVG(*) AS "snitt_ant_modeller"
FROM ((stoltype t LEFT JOIN modell m ON (t.typenavn = m.typenavn)) LEFT JOIN produksjonsstol p ON
(m.modell = p.modell)) JOIN bestilling b ON (p.stolnr = b.stolnr)
GROUP BY t.typenavn;

--3

SELECT SUM(antall) AS "tot_ant"
FROM bestilling b JOIN ordre o ON (b.ordrenr = o.ordrenr)
WHERE reell_levdato IS NULL;

--4

SELECT SUM(antall) AS "tot_ant_standard"
FROM (bestilling b JOIN ordre o ON (b.ordrenr = o.ordrenr)) JOIN produksjonsstol p ON (b.stolnr =p.stolnr)
WHERE reell_levdato IS NULL
AND p.stolnr IN (SELECT stolnr FROM standardstol);


