package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class InputPanel extends PanelDesign {

    private JTextArea inputArea;

    private SampleSizePanel sampleSizePanel;
    private TotalSumPanel totalSumPanel;
    private MinMaxPanel minMaxPanel;
    private SampleRangePanel sampleRangePanel;
    private CentralTendencyPanel centralTendencyPanel;
    private VariabilityPanel variabilityPanel;
    private GraphPanel graphPanel;
    private JLabel errorLabel;


    public InputPanel(
        SampleSizePanel sampleSizePanel,
        TotalSumPanel totalSumPanel,
        MinMaxPanel minMaxPanel,
        SampleRangePanel sampleRangePanel,
        CentralTendencyPanel centralTendencyPanel,
        VariabilityPanel variabilityPanel,
        GraphPanel graphPanel
    ) 

    {
        this.sampleSizePanel = sampleSizePanel;
        this.totalSumPanel = totalSumPanel;
        this.minMaxPanel = minMaxPanel;
        this.sampleRangePanel = sampleRangePanel;
        this.centralTendencyPanel = centralTendencyPanel;
        this.variabilityPanel = variabilityPanel;
   

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createVerticalGlue());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 0));
        labelPanel.setOpaque(false);

        JLabel inputLabel = new JLabel("Data Input:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 15));
        labelPanel.add(inputLabel);

        add(labelPanel);

        // Create a JTextArea for input
        inputArea = new JTextArea(1, 20);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputArea.setMaximumSize(new Dimension(600, 140));
        inputArea.setPreferredSize(new Dimension(600, 140));
        inputArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        add(inputArea);

        // Initialize and add the error label
        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 0));
        errorPanel.setOpaque(false); // keep it transparent

        errorLabel = new JLabel(" "); // keep a blank space so layout doesn't collapse
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        errorPanel.add(errorLabel);
        add(errorPanel);

        add(Box.createVerticalGlue());

        setPreferredSize(new Dimension(670, 200));

        // Add DocumentListener to the area
        inputArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void updateStats() {
                try {
                    String text = inputArea.getText();
                    statistics.BasicAlgorithm.ParseResult result = statistics.BasicAlgorithm.parseInputWithInvalids(text);
                    double[] data = result.numbers;

                    if (!result.invalids.isEmpty()) {
                        throw new InvalidInputException("Invalid input: " + String.join(", ", result.invalids));
                    }

                    if (data.length > 0) {
                        double minValue = Arrays.stream(data).min().orElse(data[0]);
                        for (double d : data) {
                            if (Math.abs(d - minValue) > 100) {
                                throw new InvalidInputException("All values must be within 100 of the lowest value (" + minValue + ")");
                            }
                        }
                    }

                    double maxAllowed = Integer.MAX_VALUE;
                    for (double d : data) {
                        if (d < 0 || d > maxAllowed) {
                            throw new InvalidInputException("Invalid input: reached integer limit");
                        }
                    }

                    // Only update panels if all input is valid
                    errorLabel.setText("");
                    sampleSizePanel.setSampleSize(data);
                    totalSumPanel.setTotalSum(data);
                    minMaxPanel.setMinMax(data);
                    sampleRangePanel.setSampleRange(data);
                    centralTendencyPanel.setCentralTendency(data);
                    variabilityPanel.setVariability(data);
                    graphPanel.setData(data);

                } catch (InvalidInputException ex) {
                    errorLabel.setText(ex.getMessage());
                    sampleSizePanel.setSampleSize(new double[0]);
                    totalSumPanel.setTotalSum(new double[0]);
                    minMaxPanel.setMinMax(new double[0]);
                    sampleRangePanel.setSampleRange(new double[0]);
                    centralTendencyPanel.setCentralTendency(new double[0]);
                    variabilityPanel.setVariability(new double[0]);
                    graphPanel.clear();
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
        });
    }


    public String getInput() {
        return inputArea.getText();
    }

    
}
