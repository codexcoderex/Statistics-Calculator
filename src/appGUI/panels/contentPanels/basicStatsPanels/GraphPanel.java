package appGUI.panels.contentPanels.basicStatsPanels;

import appGUI.panels.PanelDesign;

import java.awt.*;
import java.util.Arrays;

public class GraphPanel extends PanelDesign {
    private double[] rawData = new double[0];
    private static final int MAX_WIDTH = 1270;
    private static final int MAX_HEIGHT = 410;
    private static final int MIN_BAR_WIDTH = 20;
    private static final int BAR_SPACING = 4;

    public GraphPanel() {
        setOpaque(false);
        setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (rawData == null || rawData.length == 0) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int leftPad = 40, rightPad = 40, topPad = 40, bottomPad = 40;

        // Histogram bins
        double binSize = 5.0;
        double min = Math.floor(Arrays.stream(rawData).min().orElse(0) / binSize) * binSize;
        double max = Math.ceil(Arrays.stream(rawData).max().orElse(0) / binSize) * binSize;
        if (max == min) max = min + binSize;

        int binCount = Math.max(1, (int) ((max - min) / binSize));
        int[] counts = new int[binCount];

        for (double value : rawData) {
            int bin = (int) ((value - min) / binSize);
            bin = Math.max(0, Math.min(bin, binCount - 1));
            counts[bin]++;
        }

        // Dynamically calculate bar width to fit within MAX_WIDTH
        int availableWidth = MAX_WIDTH - leftPad - rightPad;
        int targetBarWidth = Math.max(MIN_BAR_WIDTH, availableWidth / binCount);
        if ((targetBarWidth + BAR_SPACING) * binCount > availableWidth) {
            targetBarWidth = (availableWidth / binCount) - BAR_SPACING;
        }

        int barWidth = Math.max(1, targetBarWidth);
        int totalWidth = Math.min(MAX_WIDTH, leftPad + rightPad + binCount * (barWidth + BAR_SPACING));
        int height = getHeight() > 0 ? getHeight() : MAX_HEIGHT;
        int graphHeight = height - topPad - bottomPad;

        // Resize the panel itself
        setPreferredSize(new Dimension(totalWidth, MAX_HEIGHT));
        revalidate();

        // Draw axes
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(leftPad, height - bottomPad, totalWidth - rightPad, height - bottomPad); // X axis
        g2.drawLine(leftPad, topPad, leftPad, height - bottomPad); // Y axis

        int maxCount = Arrays.stream(counts).max().orElse(1);
        FontMetrics fm = g2.getFontMetrics();

        for (int i = 0; i < binCount; i++) {
            int count = counts[i];
            int barHeight = (int) ((count / (double) maxCount) * (graphHeight - 10));
            int x = leftPad + i * (barWidth + BAR_SPACING);
            int y = height - bottomPad - barHeight;

            // Draw bar
            g2.setColor(new Color(0x757575));
            g2.fillRect(x, y, barWidth, barHeight);

            // Draw count
            if (count > 0) {
                String countStr = String.valueOf(count);
                int countWidth = fm.stringWidth(countStr);
                g2.setColor(Color.BLACK);
                g2.drawString(countStr, x + (barWidth - countWidth) / 2, y - 5);
            }

            // Draw label
            int binStart = (int) (min + i * binSize);
            int binEnd = (int) (binStart + binSize);
            String label = String.format("%d–%d", binStart, binEnd);
            int labelWidth = fm.stringWidth(label);

            g2.setColor(Color.BLACK);
            g2.drawString(label, x + (barWidth - labelWidth) / 2, height - bottomPad + 18);
        }

        g2.dispose();
    }

    public void clear() {
        rawData = new double[0];
        setPreferredSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
        revalidate();
        repaint();
    }

    public void setData(double[] data) {
        this.rawData = (data == null) ? new double[0] : data;
        repaint();
    }
}
