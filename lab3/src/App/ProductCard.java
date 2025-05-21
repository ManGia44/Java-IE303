package App;

import javax.swing.*;
import java.awt.*;

public class ProductCard extends JPanel {
    private boolean isSelected = false;

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        repaint();
    }

    public ProductCard(Product product) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 130));

        ImageIcon icon = new ImageIcon(product.imagePath);
        Image scaledImg = icon.getImage().getScaledInstance(280, 240, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaledImg));
        img.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel name = new JLabel(product.name);
        JLabel description = new JLabel(product.description);
        JLabel branch = new JLabel(product.branch);
        JLabel price = new JLabel("$" + product.price);

        name.setFont(new Font("Arial", Font.BOLD, 20));
        description.setFont(new Font("Arial", Font.PLAIN, 13));
        branch.setFont(new Font("Arial", Font.PLAIN, 13));
        price.setFont(new Font("Arial", Font.BOLD, 20));

        name.setAlignmentX(Component.LEFT_ALIGNMENT);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        topPanel.add(name);
        topPanel.add(Box.createVerticalStrut(2));
        topPanel.add(description);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(branch, BorderLayout.WEST);
        bottomPanel.add(price, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(img, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Vẽ viền và bo góc
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 16;
        int strokeWidth = isSelected ? 3 : 1;

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        g2.setColor(isSelected ? new Color(0, 120, 215) : Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.drawRoundRect(strokeWidth / 2, strokeWidth / 2,
                getWidth() - strokeWidth, getHeight() - strokeWidth, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}
