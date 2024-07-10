import java.awt.*;
import javax.swing.*;

public class MainMenuGUI extends JFrame {
    private JButton create;
    private JButton view;
    private JButton manage;
    private JButton book;
    
    private JButton exit;

    public MainMenuGUI() {
        super("Main Menu");
        
        setSize(450, 450);
        setLayout(new BorderLayout());

        initialize();

        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initialize() {
        // NORTH PANEL
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.decode("#E43D12"));

        JLabel titleL1 = new JLabel("GRAND BUDAPEST");
        titleL1.setForeground(Color.decode("#EBE9E1"));
        titleL1.setFont(new Font("Century Gothic", Font.BOLD, 45));
        titleL1.setBorder(BorderFactory.createEmptyBorder(25, 20,0, 20));
        titleL1.setHorizontalAlignment(JLabel.CENTER);

        JLabel titleL2 = new JLabel("BOOKING");
        titleL2.setForeground(Color.decode("#EBE9E1"));
        titleL2.setFont(new Font("Century Gothic", Font.BOLD, 45));
        titleL2.setBorder(BorderFactory.createEmptyBorder(0, 20, 25, 20));
        titleL2.setHorizontalAlignment(JLabel.CENTER);
        
        northPanel.add(titleL1, BorderLayout.NORTH);
        northPanel.add(titleL2, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(Color.decode("#EBE9E1"));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        create = new JButton("CREATE");
        create.setFont(new Font("Century Gothic", Font.BOLD, 20));
        create.setOpaque(true);
        create.setBorderPainted(false);
        create.setMargin(new Insets(30, 10, 30, 10));
        create.setPreferredSize(new Dimension(175, 40));
        create.setBackground(Color.decode("#D6536D"));
        create.setForeground(Color.decode("#EBE9E1"));

        view = new JButton("VIEW");
        view.setFont(new Font("Century Gothic", Font.BOLD, 20));
        view.setOpaque(true);
        view.setBorderPainted(false);
        view.setMargin(new Insets(10, 20, 10, 20));
        view.setPreferredSize(new Dimension(175, 40));
        view.setBackground(Color.decode("#D6536D"));
        view.setForeground(Color.decode("#EBE9E1"));

        manage = new JButton("MANAGE");
        manage.setFont(new Font("Century Gothic", Font.BOLD, 20));
        manage.setOpaque(true);
        manage.setBorderPainted(false);
        manage.setMargin(new Insets(10, 20, 10, 20));
        manage.setPreferredSize(new Dimension(175, 40));
        manage.setBackground(Color.decode("#D6536D"));
        manage.setForeground(Color.decode("#EBE9E1"));

        book = new JButton("BOOK");
        book.setFont(new Font("Century Gothic", Font.BOLD, 20));
        book.setOpaque(true);
        book.setBorderPainted(false);
        book.setMargin(new Insets(10, 20, 10, 20));
        book.setPreferredSize(new Dimension(175, 40));
        book.setBackground(Color.decode("#D6536D"));
        book.setForeground(Color.decode("#EBE9E1"));

        exit = new JButton("EXIT");
        exit.setFont(new Font("Century Gothic", Font.BOLD, 20));
        exit.setOpaque(true);
        exit.setBorderPainted(false);
        exit.setMargin(new Insets(10, 20, 10, 20));
        exit.setPreferredSize(new Dimension(175, 40));
        exit.setBackground(Color.decode("#EFB11D"));
        exit.setForeground(Color.decode("#3A3A3A"));

        centerPanel.add(create);
        centerPanel.add(view);
        centerPanel.add(manage);
        centerPanel.add(book);
        centerPanel.add(exit);
        this.add(centerPanel, BorderLayout.CENTER);
    }
    
}