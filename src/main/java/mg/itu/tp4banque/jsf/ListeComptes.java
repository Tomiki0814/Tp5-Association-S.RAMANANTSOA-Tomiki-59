/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tp4banque.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.tp4banque.ejb.GestionnaireCompte;
import mg.itu.tp4banque.entities.CompteBancaire;
import mg.itu.tp4banque.error.Util;

/**
 *
 * @author tomik
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    /**
     * Creates a new instance of ListeComptes
     */
    private List<CompteBancaire> compteBancaires;
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {

        compteBancaires = gestionnaireCompte.getAllCompteBancaire();
        return compteBancaires;
    }
    
    public List<CompteBancaire> getCompteBancaires(){
        return compteBancaires = gestionnaireCompte.getAllCompteBancaire();
    }
    
    public String supprimer(CompteBancaire compte){
        gestionnaireCompte.supprimerCompte(compte);
        Util.addFlashInfoMessage("Compte de " + compte.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }

}
