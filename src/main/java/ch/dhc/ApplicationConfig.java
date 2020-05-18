package ch.dhc;

public class ApplicationConfig {

    String name;
    String iconPath;

    ApplicationConfig() {

    }

    ApplicationConfig(String name, String iconPath) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
