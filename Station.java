package MetroApp;

public class Station {
    private String name;
    private String lineColor;

    public Station(String name, String lineColor) {
        this.name = name;
        this.lineColor = lineColor;
    }

    public String getName() {
        return name;
    }

    public String getLineColor() {
        return lineColor;
    }

    @Override
    public String toString() {
        return name + " (" + lineColor + ")";
    }
}
