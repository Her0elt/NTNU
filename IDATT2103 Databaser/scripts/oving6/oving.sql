---kandidat(kandidat_id, navn(fornavn,etternavn), telefon, epost, kvalifikasjoner*,oppdragsnr*)
-- kvalifikasjoner(kvalifikkasjons_id, beskrivelse, kandidat*, oppdrag*)
-- Bedrift(orgnr, navn, telefon, epost, oppdrag*)
--kvalifikasjon_kandidat(kvalifikasjon_kandidat_id, kvalifikasjon_id*, kandidat_id*)
--jobbhistorikk(jobbhistorikk_id, kandidat_id*)
-- oppdrag(oppdragsnr, bedrifts_navn*, start, slutt, timer, sluttrapport*, jobbhistorikk_id*)
-- sluttraport(sluttraport_id, info, oppdrag*)


CREATE TABLE kandidat (
    kandidat_id INT NOT NULL AUTO_INCREMENT, 
    fornavn VARCHAR(45) NOT NULL,
    etternavn VARCHAR(45) NOT NULL, 
    telefon INT NOT NULL,
    epost VARCHAR(255) NOT NULL,
    CONSTRAINT kandidat_pk PRIMARY KEY(kandidat_id)
    );

CREATE TABLE kvalifikasjon(
    kvalifikasjon_id INT NOT NULL AUTO_INCREMENT,
    beskrivelse VARCHAR(45) NOT NULL,
    CONSTRAINT kvalifikasjons_pK PRIMARY KEY (kvalifikasjons_id)
);

CREATE TABLE kvalifikasjon_kandidat(
    kvalifikasjon_kandidat_id NOT NULL AUTO_INCREMENT,
    kvalifikasjon_id INT NOT NULL,
    kandidat_id INT NOT NULL,
    CONSTRAINT kvalifikasjon_kandidat_pk PRIMARY KEY (kvalifikasjon_kandidat_id)
    CONSTRAINT kvalifikasjons_fk FOREIGN KEY(kvalifikasjon_id) REFERENCES kvalifikasjon(kvalifikasjon_id)
    CONSTRAINT kandidat_pk FOREIGN KEY(kandidat_id) REFERENCES kandidat(kandidat_id)
);

CREATE TABLE bedrift(
    orgnr INT NOT NULL,
    navn VARCHAR(45) NOT NULL,
    telefon INT NOT NULL,
    epost VARCHAR(255) NOT NULL,
    CONSTRAINT bedrift_pk PRIMARY KEY(orgnr)
);

CREATE TABLE jobbhistorikk(
    jobbhistorikk_id INT NOT NULL
    kandidat_id INT NOT NULL,
    CONSTRAINT jobbhistorikk_pk PRIMARY KEY (jobbhistorikk_id)
    CONSTRAINT  oppdrag_fk FOREIGN KEY(oppdragsnr) REFERENCES oppdrag(oppdragsnr)
    CONSTRAINT kandidat_pk FOREIGN KEY(kandidat_id) REFERENCES kandidat(kandidat_id)
);

CREATE TABLE oppdrag(
    oppdragsnr INT NOT NULL,
    bedrifts_navn VARCHAR(45) NOT NULL,
    kvalifikasjoner_id INT NOT NULL,
    start DATE NOT NULL,
    slutt DATE NOT NULL,
    jobbhistorikk_id NULL,
    CONSTRAINT oppdrag_pk PRIMARY KEY(oppdragsnr)
    CONSTRAINT kvalifikasjons_fk FOREIGN KEY(kvalifikasjons_id) REFERENCES kvalifikasjon(kvalifikasjons_id)
    CONSTRAINT bedrifts_navn_pk FOREIGN KEY(bedrifts_navn) REFERENCES bedrift(navn)
    CONSTRAINT jobbhistorikk_pk FOREIGN KEY(jobbhistorikk_id) REFERENCES jobbhistorikk(jobbhistorikk_id)
);

CREATE TABLE sluttraport(
    sluttraport_id INT NOT NULL AUTO_INCREMENT,
    info VARCHAR(1000) NOT NULL,
    oppdragsnr INT NOT NULL,
    CONSTRAINT sluttrapport_pk PRIMARY KEY(sluttraport_id)
    CONSTRAINT oppdrag_fk FOREIGN KEY(oppdragsnr) REFERENCES oppdrag(oppdragsnr)
);

--d
SELECT navn, telefon, epost from Bedrift;

SELECT oppdragsnr, navn, telefon from oppdrag JOIN bedrift ON oppdrag.bedrifts_navn = bedrift.navn;

SELECT navn, beskrivelse, kvalifikasjon_kandidat_id from kandidat 
JOIN kvalifikasjon_kandidat ON kandidat.kandidat_id = kvalifikasjon_kandidat.kandidat_id
JOIN kvalifikasjon ON kvalifikasjon_kandidat.kvalifikasjon_id = kvalifikasjon.kvalifikasjon_id

SELECT navn, beskrivelse, kvalifikasjon_kandidat_id from kandidat 
LEFT JOIN kvalifikasjon_kandidat ON kandidat.kandidat_id = kvalifikasjon_kandidat.kandidat_id
LEFT JOIN kvalifikasjon ON kvalifikasjon_kandidat.kvalifikasjon_id = kvalifikasjon.kvalifikasjon_id;

SELECT kandidat_id CONCAT(fornavn, " ", etternavn) as navn, sluttdato, oppdragsnr, bedrifts_navn FROM kandidat
JOIN jobbhistorikk ON kandidat.kandidat_id = jobbhistorikk.kandidat_id
JOIN oppdrag ON jobbhistorikk.jobbhistorikk_id = oppdrag.jobbhistorikk_id AND kandidat_id = ?;

