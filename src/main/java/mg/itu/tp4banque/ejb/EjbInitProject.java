/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tp4banque.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.tp4banque.entities.CompteBancaire;

/**
 *
 * @author tomik
 */
@Singleton
@Startup
public class EjbInitProject {

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    @PostConstruct
    public void InsertDefaultData() {
        //John Lennon, 150000 ; Paul McCartney, 950000 ; Ringo Starr, 20000 ; Georges Harrisson, 100000
        if (gestionnaireCompte.count() == 0) {
            CompteBancaire[] compteBancaire = {new CompteBancaire("John Lennon", 15000),
                new CompteBancaire("Paul McCartney", 950000),
                new CompteBancaire("Ringo Starr", 20000),
                new CompteBancaire("Georges Harrisson", 100000)};
            for (CompteBancaire compte : compteBancaire) {
                gestionnaireCompte.persist(compte);
            }
        }

    }
}
