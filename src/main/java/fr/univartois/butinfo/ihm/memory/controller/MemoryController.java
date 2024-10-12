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

package fr.univartois.butinfo.ihm.memory.controller;

import java.net.URL;

import fr.univartois.butinfo.ihm.memory.model.Card;
import fr.univartois.butinfo.ihm.memory.model.IMemoryController;
import fr.univartois.butinfo.ihm.memory.model.Memory;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * La classe MemoryController propose un contrôleur permettant de gérer une partie du
 * Memory présentée à l'utilisateur sous la forme d'une interface graphique JavaFX.
 *
 * @author Louis Palie
 *
 * @version 0.1.0
 */
public class MemoryController implements IMemoryController {

    /**
     * Le label affichant le nombre de paires ayant été retournées
     */
    @FXML
    private Label labelCountPairs;

    /**
     * La grille sur laquelle afficher les cartes du jeu.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Les boutons permettant d'afficher et de choisir une carte à retourner.
     */
    private Button[][] buttons;

    /**
     * Le modèle du jeu, qui gère la partie affichée.
     */
    private Memory memory;

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.univartois.butinfo.ihm.memory.model.IMemoryController#setModel(fr.univartois.
     * butinfo.ihm.memory.model.Memory)
     */
    @Override
    public void setModel(Memory memory) {
        this.memory = memory;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.ihm.memory.model.IMemoryController#initCount(javafx.beans.
     * property.IntegerProperty)
     */
    @Override
    public void initCount(IntegerProperty count) {
        labelCountPairs.textProperty().bind(count.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.ihm.memory.model.IMemoryController#initGrid(fr.univartois.
     * butinfo.ihm.memory.model.Card[][], int, int)
     */
    @Override
    public void initGrid(Card[][] cards, int nRows, int nColumns) {
        buttons = new Button[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                buttons[i][j] = initButton(cards[i][j], i, j);
            }
        }
    }
    /**
     * Crée le bouton à la position donnée.
     *
     * @param card La carte associée au bouton à créer.
     * @param row L'indice de la ligne où se trouve le bouton.
     * @param column L'indice de la colonne où se trouve le bouton.
     *
     * @return Le bouton qui a été créé.
     */
    private Button initButton(Card card, int row, int column) {
        Button button = new Button();
        button.setPrefSize(100, 100);
        gridPane.add(button, column, row);
        updateButton(button, card, false);
        button.setOnAction(e -> memory.chooseCard(row, column));
        button.disableProperty().bind(card.getPinnedProperty());
        card.getRevealedProperty().addListener((obs, oldVal, newVal) -> updateButton(button, card, newVal));
        return button;
    }
    
    /**
     * Met à jour l'affichage du bouton donné.
     *
     * @param button Le bouton à mettre à jour.
     * @param card La carte correspondant au bouton.
     * @param revealed Si la carte a été révélée.
     */
    private void updateButton(Button button, Card card, boolean revealed) {
        // On commence par regarder quelle image afficher.
        URL urlImage;
        if (revealed) {
            urlImage = getClass().getResource("../view/images/" + card + ".gif");
        } else {
            urlImage= getClass().getResource("../view/images/unknown.gif");
        }
        
        // On crée ensuite la vue associée à cette image.
        Image image = new Image(urlImage.toExternalForm(), 100, 100, true, false);
        ImageView view = new ImageView(image);
        view.setFitHeight(100);
        view.setFitWidth(100);
        button.setGraphic(view);
    }

}
