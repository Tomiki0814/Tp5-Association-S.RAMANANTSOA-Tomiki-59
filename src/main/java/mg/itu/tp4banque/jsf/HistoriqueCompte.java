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

/**
 *
 * @author tomik
 */
@Named(value = "historiqueCompte")
@ViewScoped
public class HistoriqueCompte implements Serializable {

    /**
     * Creates a new instance of HistoriqueCompte
     */
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    private int id;
    private CompteBancaire compteBancaire;

    public HistoriqueCompte() {
    }

    public void findCompte() {
            compteBancaire = gestionnaireCompte.findCompte(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
    
    
}
