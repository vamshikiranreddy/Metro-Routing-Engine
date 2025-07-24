package MetroApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MetroAppSwingUI extends JFrame {
    private JComboBox<String> sourceBox, destBox;
    private JTextPane resultPane;
    private JLabel fareLabel, timeLabel;
    private Graph_M graph;

    public MetroAppSwingUI() {
        graph = new Graph_M();
        setTitle("🚇 Namma Metro Route Finder");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title Banner
        JLabel title = new JLabel("🚇 Namma Metro Route Finder", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        JPanel banner = new JPanel();
        banner.setBackground(new Color(0, 71, 171));
        banner.add(title);
        add(banner, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(230, 230, 250));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Select Source & Destination"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputPanel.add(new JLabel("From:"), gbc);
        sourceBox = new JComboBox<>(graph.getAllStations().toArray(new String[0]));
        gbc.gridx = 1; inputPanel.add(sourceBox, gbc);

        gbc.gridy = 1; gbc.gridx = 0;
        inputPanel.add(new JLabel("To:"), gbc);
        destBox = new JComboBox<>(graph.getAllStations().toArray(new String[0]));
        gbc.gridx = 1; inputPanel.add(destBox, gbc);

        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton findBtn = new JButton("🔍 Find Route");
        findBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        findBtn.setBackground(new Color(0, 153, 76));
        findBtn.setForeground(Color.WHITE);
        findBtn.addActionListener(this::findRoute);
        inputPanel.add(findBtn, gbc);

        add(inputPanel, BorderLayout.WEST);

        // Result Pane
        resultPane = new JTextPane();
        resultPane.setEditable(false);
        resultPane.setContentType("text/html");
        resultPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        JScrollPane scrollPane = new JScrollPane(resultPane);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Route Details"));

        // Fare & Time
        JPanel footer = new JPanel(new GridLayout(1, 2, 10, 10));
        fareLabel = styledLabel("Estimated Fare: ₹0.00", new Color(0, 100, 0));
        timeLabel = styledLabel("Est. Travel Time: 0 min", new Color(0, 128, 128));
        footer.add(fareLabel);
        footer.add(timeLabel);

        JPanel right = new JPanel(new BorderLayout(10, 10));
        right.add(scrollPane, BorderLayout.CENTER);
        right.add(footer, BorderLayout.SOUTH);
        add(right, BorderLayout.CENTER);
    }

    private JLabel styledLabel(String text, Color bg) {
        JLabel lbl = new JLabel(text, JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl.setForeground(Color.WHITE);
        lbl.setBackground(bg);
        lbl.setOpaque(true);
        lbl.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return lbl;
    }

    private void findRoute(ActionEvent e) {
        String src = (String) sourceBox.getSelectedItem();
        String dst = (String) destBox.getSelectedItem();
        if (src.equals(dst)) {
            showError("Source and destination cannot be the same.");
            return;
        }

        List<String> path = graph.getShortestPath(src, dst);
        if (path.isEmpty()) {
            showError("No route found.");
            return;
        }

        double fare = graph.calculateFare(path);
        int time = (int) Math.ceil(graph.estimateTravelTime(path));

        StringBuilder html = new StringBuilder("<html><body style='font-family:Segoe UI;'>");
        html.append("<h3>🛤 Route:</h3><ul>");
        for (String st : path) {
            String color = getHexColor(graph.getLineColor(st));
            html.append("<li><span style='color:").append(color).append(";'>")
                .append(st).append(" (").append(graph.getLineColor(st)).append(")</span></li>");
        }
        html.append("</ul></body></html>");

        resultPane.setText(html.toString());
        fareLabel.setText(String.format("Estimated Fare: ₹%.2f", fare));
        timeLabel.setText(String.format("Est. Travel Time: %d min", time));
    }

    private void showError(String msg) {
        resultPane.setText("<html><body style='color:red; font-family:Segoe UI;'>" + msg + "</body></html>");
        fareLabel.setText("Estimated Fare: ₹0.00");
        timeLabel.setText("Est. Travel Time: 0 min");
    }

    private String getHexColor(String line) {
        return switch (line) {
            case "Purple" -> "#800080";
            case "Green" -> "#008000";
            default -> "#000000";
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MetroAppSwingUI().setVisible(true));
    }
}
