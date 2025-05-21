package App;

import javax.swing.*;
import java.awt.*;

public class ProductDetailPanel extends JPanel {
    private JLabel imageLabel, nameLabel, priceLabel, branchLabel, descLabel;

    public ProductDetailPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(imageLabel);
        add(Box.createVerticalStrut(15));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        add(separator);
        add(Box.createVerticalStrut(15)); 

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        add(nameLabel);
        add(Box.createVerticalStrut(15));

\        priceLabel = new JLabel();
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        add(priceLabel);
        add(Box.createVerticalStrut(10));

        branchLabel = new JLabel();
        branchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        branchLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        add(branchLabel);
        add(Box.createVerticalStrut(10));

        descLabel = new JLabel();
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
        add(descLabel);
    }

    public void updateProduct(Product product) {
        ImageIcon icon = new ImageIcon(product.imagePath);
        Image scaledImg = icon.getImage().getScaledInstance(340, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImg));

        nameLabel.setText(product.name);
        priceLabel.setText("$" + product.price);
        branchLabel.setText(product.branch);
        descLabel.setText("<html><div style='width: 300px;'>" + product.description + "</div></html>");
    }
}
