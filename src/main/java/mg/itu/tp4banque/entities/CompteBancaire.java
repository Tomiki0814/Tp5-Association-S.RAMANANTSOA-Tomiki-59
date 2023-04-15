/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tp4banque.entities;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author tomik
 */
@Entity
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private int solde;

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

    public int getId() {
        return id;
    }

    public CompteBancaire() {
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public void deposer(int montant) {
        solde += montant;
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
    }


}
