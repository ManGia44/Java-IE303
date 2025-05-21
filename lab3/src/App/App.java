package App;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class App extends JFrame {
        public App() {
                setSize(1000, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                List<Product> products = Arrays.asList(
                                new Product("4DFWD PULSE SHOES", "Adidas", 160, "resource/img1.png",
                                                "This product is excluded from all gromotional discounts and offers"),
                                new Product("FORUM MID SHOES", "Adidas", 100, "resource/img2.png",
                                                "This product is excluded from all gromotional discounts and offers"),
                                new Product("SUPERNOVA SHOES", "Adidas", 150, "resource/img3.png", "NMD City Stack 2"),
                                new Product("Adidas", "Adidas", 160, "resource/img4.png", "NMD City Stack 2"),
                                new Product("Adidas", "Adidas", 120, "resource/img5.png", "NMD City Stack 2"),
                                new Product("4DFWD PULSE SHOES", "Adidas", 160, "resource/img6.png",
                                                "This product is excluded from all gromotional discounts and offers"),
                                new Product("4DFWD PULSE SHOES", "Adidas", 160, "resource/img1.png",
                                                "This product is excluded from all gromotional discounts and offers"),
                                new Product("FORUM MID SHOES", "Adidas", 100, "resource/img2.png",
                                                "This product is excluded from all gromotional discounts and offers"));
                ProductDetailPanel detailPanel = new ProductDetailPanel();
                detailPanel.updateProduct(products.get(0));

                JPanel rightPanel = new ProductListPanel(products, detailPanel);

                add(detailPanel, BorderLayout.WEST);
                add(new JScrollPane(rightPanel), BorderLayout.CENTER);
        }

        public static void main(String[] args) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                        new App().setVisible(true);
                });
        }
}
