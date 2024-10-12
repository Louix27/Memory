

package fr.univartois.butinfo.ihm.memory.model;

import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * La classe Card représente une carte du jeu du Memory, pour laquelle il existe une autre
 * carte identique dans le jeu et qui doit être retrouvée.
 *
 * @author Louis Palie
 *
 * @version 0.1.0
 */
public class Card {

    /**
     * La valeur de cette carte, qui permet de la comparer à une autre carte.
     */
    private final String value;
    private final BooleanProperty revealed;
    private final BooleanProperty pinned;

    /**
     * La propriété indiquant si cette carte est actuellement révélée ou non.
     */


    /**
     * La propriété indiquant si cette carte est définitivement retournée ou non.
     */


    /**
     * Construit une nouvelle instance de Card.
     * 
     * @param value La valeur de la carte, qui permet de la comparer à une autre carte.
     */
    public Card(String value) {
        this.value = value;
        this.revealed = new SimpleBooleanProperty(false);
        this.pinned = new SimpleBooleanProperty(false);
    }

    /**
     * Révèle (ou cache) cette carte.
     *
     * @param revealed Le booléen indiquant si la carte est révélée.
     */
    public void setRevealed(boolean revealed) {
        this.revealed.set(revealed);
    }
    
    /**
     * Indique si cette carte est actuellement révélée.
     *
     * @return Si la carte est actuellement révélée.
     */
    public boolean isRevealed() {
        return this.revealed.get();
    }

    /**
     * Donne la propriété indiquant si cette carte est actuellement révélée ou non.
     *
     * @return La propriété indiquant si cette carte est actuellement révélée ou non.
     */
    public BooleanProperty getRevealedProperty() {
        return this.revealed;
    }

    /**
     * Fixe (ou non) cette carte en position retournée.
     *
     * @param pinned Le booléen indiquant si la carte est définitivement retournée.
     */
    public void setPinned(boolean pinned) {
        this.pinned.set(pinned);
    }

    /**
     * Indique si cette carte est actuellement fixée en position retournée.
     *
     * @return Si la carte est actuellement fixée.
     */
    public boolean isPinned() {
        return this.pinned.get();
    }

    /**
     * Donne la propriété indiquant si cette carte est définitivement retournée ou non.
     *
     * @return La propriété indiquant si cette carte est définitivement retournée ou non.
     */
    public BooleanProperty getPinnedProperty() {
        return this.pinned;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            // Les deux objets sont identiques.
            return true;
        }

        if (obj == null) {
            // L'autre objet est null.
            return false;
        }

        if (obj instanceof Card other) {
            // On compare les valeurs des deux cartes.
            return Objects.equals(value, other.value);
        }

        // L'autre objet est d'une classe incompatible.
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return value;
    }

}
