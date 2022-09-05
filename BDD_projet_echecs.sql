
DROP DATABASE echecs;
CREATE DATABASE echecs;

CREATE TABLE utilisateurs (
    uti_id SERIAL PRIMARY KEY,
    uti_pseudo VARCHAR(20) NOT NULL,
    uti_mdp VARCHAR(150) NOT NULL,
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
    hsp_messages TEXT NOT NULL,
    hsp_liste_coups TEXT,
    hsp_date TIMESTAMP NOT NULL,
    hsp_vainqueur_id INT,
    hsp_param_id INT 
);

CREATE TABLE probleme(
    prob_id SERIAL PRIMARY KEY,
    prob_fen_depart VARCHAR(100) NOT NULL,
    prob_liste_deplacements VARCHAR(100),
    prob_traitaublanc BOOLEAN NOT NULL,
    prob_difficulte INT NOT NULL
);

CREATE TABLE resultatProbleme (
    res_id SERIAL PRIMARY KEY,
    res_utilisateur_id INT NOT NULL,
    res_probleme_id INT NOT NULL,
    res_date TIMESTAMP NOT NULL
);

CREATE TABLE parametres (
    par_id SERIAL PRIMARY KEY,
    par_penaltie_piece_id INT NOT NULL,
    par_chrono INT NOT NULL
);

CREATE TABLE penalitePiece (
    pen_id SERIAL PRIMARY KEY,
    pen_nom VARCHAR(20),
    pen_fen VARCHAR(100)
);

ALTER TABLE historiquePartie 
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
        ON UPDATE CASCADE,
    ADD
        CONSTRAINT fk_paramid
        FOREIGN KEY (hsp_param_id)
        REFERENCES parametres(par_id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE;

ALTER TABLE parametres
    ADD
        CONSTRAINT fk_penalpiece
        FOREIGN KEY (par_penaltie_piece_id)
        REFERENCES penalitePiece(pen_id)
        ON DELETE NO ACTION
        ON UPDATE CASCADE;

ALTER TABLE resultatProbleme
    ADD
        CONSTRAINT fk_problemeid
        FOREIGN KEY (res_probleme_id)
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

INSERT INTO penalitePiece (pen_nom, pen_fen)
VALUES
    ('TourBlanc', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/1NBQKBN1'),
    ('CavBlanc', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/T1BQKB1T'),
    ('FouBlanc', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/TN1QK1NT'),
    ('DameBlanc', 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNB1KBNR'),
    ('TourNoir', '1nbqkbn1/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR'),
    ('CavNoir', 'r1bqkb1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR'),    
    ('FouNoir', 'rn1qk1nr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR'),
    ('DameNoir', 'rnb1kbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR');


--INSERT INTO probleme (prob_fen_depart, prob_liste_deplacement, prob_traitaublanc, prob_difficulte)
--VALUES
--    ('q3k1nr/1pp1nQpp/3p4/1P2p3/4P3/B1PP1b2/B5PP/5K2','','false','...')



-- repo utilisateur DAO
-- repo partie mais plus tard
-- repo pb
-- repo penalite piece 
-- repo resPb