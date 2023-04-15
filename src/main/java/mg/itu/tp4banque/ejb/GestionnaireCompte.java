/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tp4banque.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import mg.itu.tp4banque.entities.CompteBancaire;
import mg.itu.tp4banque.error.Util;

/**
 *
 * @author tomik
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "mysql008",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public List<CompteBancaire> getAllCompteBancaire() {
        Query query = em.createQuery("select e from CompteBancaire e");
        return query.getResultList();
    }

    public Long count() {
        String rqt = "select count(c) from CompteBancaire c";
        Query query = em.createQuery(rqt);
        return (Long) query.getSingleResult();
    }

    public CompteBancaire findCompte(int id) {
        return em.find(CompteBancaire.class, id);
    }

    public String transfererArgent(int idEnvoyeur, int idReceveur, int montant) {
        CompteBancaire envoyeur = findCompte(idEnvoyeur);
        CompteBancaire receveur = findCompte(idReceveur);
        boolean erreur = false;
        if (receveur == null) {
            erreur = true;
            Util.messageErreur("Aucun compte avec l'id " + idReceveur, "Aucun compte avec l'id " + idReceveur, "form:idReceveur");

        }
        if (envoyeur == null) {
            erreur = true;
            erreur = true;
            Util.messageErreur("Aucun compte avec l'id " + idEnvoyeur, "Aucun compte avec l'id " + idEnvoyeur, "form:idEnvoyeur");
        }

        if (montant <= 0) {
            Util.messageErreur("Montant doit etre superieur a 0", "Montant doit etre superieur a 0", "form:montant");
        }

        if (erreur) {
            return null;
        }
        envoyeur.setSolde(envoyeur.getSolde() - montant);
        receveur.setSolde(receveur.getSolde() + montant);
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";

    }

    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire = em.merge(compteBancaire);
        compteBancaire.deposer(montant);
        //(compteBancaire);
    }

    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire = em.merge(compteBancaire);
        compteBancaire.retirer(montant);
        //persist(compteBancaire);
    }

    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
    
    public void validerModification(CompteBancaire compteBancaire){
        compteBancaire = em.merge(compteBancaire);
    }
}
