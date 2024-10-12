/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.memory.model;

import javafx.beans.property.IntegerProperty;

/**
 * L'interface IMemoryController définit le contrat qui doit être respecté par n'importe
 * quel contrôleur du jeu du Memory.
 *
 * @author Louis Palie
 *
 * @version 0.1.0
 */
public interface IMemoryController {

    /**
     * Configure le modèle du jeu utilisé pour gérer la partie.
     *
     * @param memory Le modèle utilisé pour gérer la partie.
     */
    void setModel(Memory memory);

    /**
     * Initialise le lien entre le nombre de paires retournées et son affichage dans la vue.
     *
     * @param count La propriété comptant le nombre de paires ayant été retournées.
     */
    void initCount(IntegerProperty count);

    /**
     * Initialise le lien entre la grille des cartes du jeu et leur affichage dans la vue.
     *
     * @param cards La grille des cartes du jeu du Memory.
     * @param nRows Le nombre de lignes de cartes à afficher.
     * @param nColumns Le nombre de colonnes de cartes à afficher.
     */
    void initGrid(Card[][] cards, int nRows, int nColumns);

}
