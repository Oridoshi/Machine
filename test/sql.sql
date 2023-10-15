CREATE FUNCTION Historique() RETURNS TRIGGER AS
$$
BEGIN
	INSERT into Historique values (OLD.np, OLD.lib, OLD.coul, OLD.qs, NEW.qs)
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Ajout Historique
BEFORE UPDATE ON PRODUIT
FOR EACH STATEMENT --( ligne modifier ) ou ROW ( chaque ligne même non modifier )
EXECUTE PROCEDURE Historique();


CREATE OR REPLACE FUNCTION verifAchatPossible() RETURNS TRIGGER AS
$$
DECLARE
    qsActu INTEGER;
BEGIN
    SELECT qs INTO qsActu FROM PRODUIT WHERE np = OLD.np;

    IF qsActu < NEW.qa THEN
        RAISE EXCEPTION 'L''achat n''est pas possible : quantité insuffisante en stock.';/*A AJOUTER*/
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER nomTrigger
BEFORE INSERT OR UPDATE ON Achat
FOR EACH ROW
EXECUTE FUNCTION verifAchatPossible();
