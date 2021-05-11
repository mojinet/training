package fr.modji.papeterie.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Properties properties;

    // instancie l'attribue propertie en mémoire et charger depuis un fichier externe
    // le bloc static permet d'instancier une seul fois au lancement du programme
    // static est un bloc d'initialisation
    static{
        properties = new Properties();
        // on charge un fichier setting
        try {
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getter : a partir d'une key on recupere une value
    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

}
