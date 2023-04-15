/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4banque.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import mg.itu.tp4banque.ejb.GestionnaireCompte;
import mg.itu.tp4banque.entities.CompteBancaire;
import mg.itu.tp4banque.error.Util;

/**
 *
 * @author tomik
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    /**
     * Creates a new instance of AjoutCompte
     */
    private String nom;
    private int solde;

    @EJB
    GestionnaireCompte gestionnaireCompte;

    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String AjouterCompte() {
        boolean erreur = false;
        if (solde <= 0) {
            Util.messageErreur("Montant doit etre superieur a 0", "Montant doit etre superieur a 0", "form:solde");
            erreur = true;
        }
        if (erreur) {
            return null;
        }
        gestionnaireCompte.persist(new CompteBancaire(nom, solde));
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";
    }

}
