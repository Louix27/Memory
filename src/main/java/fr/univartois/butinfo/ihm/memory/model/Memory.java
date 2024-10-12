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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * La classe Memory permet de gérer une partie du jeu.
 *
 * @author Louis Palie
 *
 * @version 0.1.0
 */
public class Memory {

    /**
     * Le nombre de lignes dans la grille du jeu.
     */
    private int nRows;

    /**
     * Le nombre de colonnes dans la grille du jeu.
     */
    private int nColumns;

    /**
     * Le nombre de paires à retrouver.
     */
    private int nbPairs;

    /**
     * La grille du jeu, contenant les cartes à retourner.
     */
    private Card[][] cards;

    /**
     * La première carte retournée par l'utilisateur.
     */
    private Card firstSelected;

    /**
     * La seconde carte retournée par l'utilisateur.
     */
    private Card secondSelected;

    /**
     * La propriété comptant le nombre de paires révélées.
     */
    private final IntegerProperty nbRevealedPairs;

    /**
     * Le contrôleur gérant l'affichage de la partie du Memory.
     */
    private IMemoryController controller;


    /**
     * Construit une nouvelle instance de Memory.
     *
     * @param nRows Le nombre de lignes dans la grille du jeu.
     * @param nColumns Le nombre de colonnes dans la grille du jeu.
     */
    public Memory(int nRows, int nColumns) {
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.nbPairs = (nRows * nColumns) >> 1;
        this.nbRevealedPairs = new SimpleIntegerProperty(0);
    }

    /**
     * Modifie le contrôleur gérant l'affichage de la partie.
     *
     * @param controller Le contrôleur gérant l'affichage de la partie.
     */
    public void setController(IMemoryController controller) {
        this.controller = controller;
    }

    /**
     * Démarre la partie du Memory.
     */
    public void startGame() {
        prepareCards();
        controller.initCount(nbRevealedPairs);
        controller.initGrid(cards, nRows, nColumns);
    }

    /**
     * Crée les cartes du jeu et les place aléatoirement sur la grille.
     */
    private void prepareCards() {
        // On commence par créer les paires de cartes.
        List<String> values = List.of("bird-1", "bird-2", "cat-1", "cat-2", "dog-1", "dog-2", "pig", "rabbit");
        List<Card> cardList = new ArrayList<>(nbPairs);
        for (int i = 0; i < nbPairs; i++) {
            cardList.add(new Card(values.get(i % values.size())));
            cardList.add(new Card(values.get(i % values.size())));
        }

        // Ensuite, on mélange les cartes.
        Collections.shuffle(cardList);

        // Enfin, on les place sur une grille en deux dimensions.
        cards = new Card[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                cards[i][j] = cardList.get(i * nColumns + j);
            }
        }
    }

    /**
     * Choisie une carte à une position donnée pour la retourner.
     *
     * @param row La ligne de la carte choisie.
     * @param column La colonne de la carte choisie.
     */
    public void chooseCard(int row, int column) {
        // On récupère la carte, et on s'assure qu'elle est valide.
        Card card = cards[row][column];
        if (card.isRevealed()) {
            // La carte est déjà retournée.
            return;
        }

        if (firstSelected == null) {
            // C'est la première carte choisie.
            firstSelected = card;
            firstSelected.setRevealed(true);
            return;
        }

        // C'est la deuxième carte sélectionnée.
        secondSelected = card;
        secondSelected.setRevealed(true);
        incNbRevealedPairs();

        // Après 1s, on cache ou on fixe la paire retounée.
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> checkSelectedPair()));
        timeline.play();
    }

    /**
     * Vérifie si la paire sélectionnée comporte deux cartes identiques, et met à jour le
     * jeu en conséquence.
     */
    private void checkSelectedPair() {
    if (firstSelected.equals(secondSelected)) {
        pinChosenCards();
        nbPairs--;
        if (nbPairs == 0) {
            // Fin de la partie.
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fin de la partie");
                alert.setHeaderText(null);
                alert.setContentText("Bravo! Vous avez trouve toutes les paires!");
                alert.showAndWait();
            });
        }

    } else {
        hideChosenCards();
    }
}

    /**
     * Incrémente le nombre de paires ayant été révélées.
     */
    private void incNbRevealedPairs() {
        int currentPairs = nbRevealedPairs.get();
        nbRevealedPairs.set(currentPairs + 1);
    }

    /**
     * Fixe les cartes choisies en position retournée.
     */
    private void pinChosenCards() {
        firstSelected.setPinned(true);
        secondSelected.setPinned(true);

        firstSelected = null;
        secondSelected = null;
    }

    /**
     * Cache les cartes choisies.
     */
    private void hideChosenCards() {
        firstSelected.setRevealed(false);
        secondSelected.setRevealed(false);

        firstSelected = null;
        secondSelected = null;
    }


}
