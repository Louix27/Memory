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

package fr.univartois.butinfo.ihm.memory;

import java.io.IOException;

import fr.univartois.butinfo.ihm.memory.controller.MemoryController;
import fr.univartois.butinfo.ihm.memory.model.Memory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe MemoryApplication est la classe principale du jeu du Memory fonctionnant
 * avec JavaFX.
 *
 * @author Louis Palie
 *
 * @version 0.1.0
 */
public class MemoryApplication extends Application {

    /**
     * Cette méthode permet d'initialiser l'affichage de la fenêtre de l'application.
     *
     * @param stage La fenêtre (initialement vide) de l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Il faut d'abord récupérer la description de la vue (au format FXML).
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/memory.fxml"));
        Parent viewContent = fxmlLoader.load();

        // Ensuite, on la place dans une Scene...
        Scene scene = new Scene(viewContent);
        // que l'on place elle-même dans la fenêtre.
        stage.setScene(scene);

        // On peut ensuite donner un titre à la fenêtre.
        stage.setTitle("MemoryFX");

        // On peut maintenant initialiser la partie.
        MemoryController controller = fxmlLoader.getController();
        Memory memory = new Memory(4, 4);
        controller.setModel(memory);
        memory.setController(controller);
        memory.startGame();

        // Enfin, on affiche la fenêtre.
        stage.show();
    }

    /**
     * Cette méthode exécute l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

}
