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
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    /**
     * Creates a new instance of Modification
     */
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    private int id;
    private CompteBancaire compteBancaire;
    public Modification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void findCompte(){
        compteBancaire = gestionnaireCompte.findCompte(id);
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
    
    public String modifier(){
        if(compteBancaire.getSolde()<=0){
            Util.messageErreur("Solde doit etre superieur a 0 ", "Solde doit etre superieur a 0", "form:montant");
            return null;
        }
        gestionnaireCompte.validerModification(compteBancaire);
        Util.addFlashInfoMessage("Compte avec l'ID " + compteBancaire.getId() + " modifié");
        return "listeComptes?faces-redirect=true";
    }
}
