package App;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductListPanel extends JPanel {
    private ProductCard selectedCard = null;

    public ProductListPanel(List<Product> products, ProductDetailPanel detailPanel) {
        setLayout(new GridLayout(2, 4, 10, 10));

        for (Product product : products) {
            ProductCard card = new ProductCard(product);
            card.setCursor(new Cursor(Cursor.HAND_CURSOR));

            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    detailPanel.updateProduct(product);

                    if (selectedCard != null) {
                        selectedCard.setSelected(false);
                    }

                    card.setSelected(true);
                    selectedCard = card;
                }
            });

            add(card);
        }
    }
}
