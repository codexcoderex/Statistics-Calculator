package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import java.awt.*;
import java.util.Arrays;

public class GraphPanel extends PanelDesign {
    private double[] rawData = new double[0];

    public GraphPanel() {
        setPreferredSize(new Dimension(670, 410));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = 650;
        int height = 400;
        int leftPad = 40, rightPad = 0, topPad = 40, bottomPad = 40;
        int graphWidth = width - leftPad - rightPad;
        int graphHeight = height - topPad - bottomPad;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw axes
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(leftPad, height - bottomPad, width - rightPad, height - bottomPad); // X axis
        g2.drawLine(leftPad, topPad, leftPad, height - bottomPad); // Y axis

        if (rawData == null || rawData.length == 0) {
            g2.dispose();
            return;
        }

        double binSize = 5.0;
        double min = Math.floor(Arrays.stream(rawData).min().orElse(0) / binSize) * binSize;
        double max = Math.ceil(Arrays.stream(rawData).max().orElse(0) / binSize) * binSize;
        if (max == min) {
            max = min + binSize;
        }
        int binCount = Math.max(1, (int) ((max - min) / binSize));

        int[] counts = new int[binCount];

        for (double value : rawData) {
            int bin = (int) ((value - min) / binSize);
            if (bin < 0) bin = 0;
            if (bin >= binCount) bin = binCount - 1;
            counts[bin]++;
        }

        int maxCount = Arrays.stream(counts).max().orElse(1);

        // Draw bars
        int barWidth = graphWidth / binCount;
        for (int i = 0; i < binCount; i++) {
            int barHeight = (int) ((counts[i] / (double) maxCount) * (graphHeight - 10));
            int x = leftPad + i * barWidth;
            int y = height - bottomPad - barHeight;

            g2.setColor(new Color(100, 149, 237));
            g2.fillRect(x, y, barWidth - 8, barHeight);

            // Draw bin label
            g2.setColor(Color.BLACK);
            int binStart = (int) (min + i * binSize);
            int binEnd = (int) (binStart + binSize);
            String label = String.format("%d–%d", binStart, binEnd);
            FontMetrics fm = g2.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            g2.drawString(label, x + (barWidth - 8 - labelWidth) / 2, height - bottomPad + 18);

            // Draw count above bar
            String countStr = String.valueOf(counts[i]);
            int countWidth = fm.stringWidth(countStr);
            g2.drawString(countStr, x + (barWidth - 8 - countWidth) / 2, y - 5);
        }

        g2.dispose();
    }

    // Call this to clear the graph
    public void clear() {
        rawData = new double[0];
        repaint();
    }

    // Call this to set new data and draw the bar graph
    public void setData(double[] data) {
        this.rawData = (data == null) ? new double[0] : data;
        repaint();
    }
}
