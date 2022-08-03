
DROP DATABASE echecs;
CREATE DATABASE echecs;

CREATE TABLE utilisateurs (
    uti_id SERIAL PRIMARY KEY,
    uti_pseudo VARCHAR(20) NOT NULL,
    uti_mdp VARCHAR(20) NOT NULL,
    uti_nom VARCHAR(20) NOT NULL,
    uti_prenom VARCHAR(20) NOT NULL,
    uti_elo INT DEFAULT 800,
    uti_email VARCHAR(50) NOT NULL,
    uti_coach BOOLEAN DEFAULT FALSE,
    uti_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE  historiquePartie (
    hsp_id SERIAL PRIMARY KEY,
    hsp_id_J1 INT NOT NULL,
    hsp_id_J2 INT NOT NULL,
    hsp_message_id INT NOT NULL,
    hsp_deplacements TEXT,
    hsp_date TIMESTAMP NOT NULL,
    hsp_vainqueur_id int
);

CREATE TABLE probleme(
    prob_id SERIAL PRIMARY KEY,
    prob_fen_depart VARCHAR(100) NOT NULL,
    prob_liste_deplacement VARCHAR(100),
    prob_traitaublanc BOOLEAN NOT NULL,
    prob_difficulte INT NOT NULL
);

CREATE TABLE historiqueMessage (
	hsm_id SERIAL PRIMARY KEY,
  	hsm_liste_message TEXT
);

CREATE TABLE resultatProbleme (
    res_id SERIAL PRIMARY KEY,
    res_utilisateur_id INT NOT NULL,
    res_partie_id INT NOT NULL
);

CREATE TABLE penalitePiece (
    pen_id SERIAL PRIMARY KEY,
    pen_nom VARCHAR(20),
    pen_fen VARCHAR(100)
);

ALTER TABLE historiquePartie 
    ADD
        CONSTRAINT FK_Partie_Message
        FOREIGN KEY (hsp_message_id)
        REFERENCES historiqueMessage(hsm_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    ADD
        CONSTRAINT fk_j1id
        FOREIGN KEY (hsp_id_J1)
        REFERENCES utilisateurs(uti_id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    ADD
        CONSTRAINT fk_j2id
        FOREIGN KEY (hsp_id_J2)
        REFERENCES utilisateurs(uti_id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE;

ALTER TABLE resultatProbleme
    ADD
        CONSTRAINT fk_partieid
        FOREIGN KEY (res_partie_id)
        REFERENCES probleme(prob_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    ADD
        CONSTRAINT fk_utilisateurid
        FOREIGN KEY (res_utilisateur_id)
        REFERENCES utilisateurs(uti_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;

INSERT INTO utilisateurs (uti_pseudo, uti_mdp, uti_nom, uti_prenom, uti_email, uti_coach, uti_admin)
VALUES
    ('Admin_Nico', 'adminNico', 'perret', 'nicolas', 'adminNico@gmail.com','true','true'),
    ('AdminGortex', 'admin' , 'desmoulin', 'Felix', 'desmoulin.felix@gmail.com' , 'true', 'true'),
    ('Fradmin', 'admin', 'philippe', 'francois', 'francoisphilippe67@gmail.com', 'true', 'true' ),
    ('Admin_FM', 'echecs', 'MASSON', 'François-Marie', 'francoismarie.masson@gmail.com', 'true', 'true');
