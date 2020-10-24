package ray.mintcat.wizard;

import io.izzel.taboolib.loader.Plugin;

@Plugin.Version(5.34)
public class Wizard extends Plugin {
    public static String getTitle(){
        return "§7§l[§f§lWizard§7§l] ";
    }
    public static String getVersion(){
        return plugin.getDescription().getVersion();
    }
}
