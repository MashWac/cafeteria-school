package wachira.fuzu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parent_dashboard extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JRadioButton  btnviewClass, btnAddChild;
    private JButton btnNext,btnLogOut;
    private JLabel lblWelcome,headerLabel;
    private Container container;
    private JPanel headerPanel, welcomePanel,portalPanel, btnNextPanel;
    private ImageIcon logoImage;
    private String portalSelected;


    public Parent_dashboard(){
        super("Welcome to Fuzu Primary");
        container=getContentPane();
        layout= new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage= new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel= new JLabel();
        headerLabel.setBounds(10,11,200,200);
        headerPanel=new JPanel();
        headerPanel.setPreferredSize(new Dimension(300,200));
        headerPanel.add(headerLabel);
        container.add(headerPanel);
        Image img=logoImage.getImage();
        Image newImage=img.getScaledInstance(headerLabel.getWidth(),headerLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalLogo= new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);

        welcomePanel=new JPanel();
        welcomePanel.setPreferredSize(new Dimension(300,50));
        welcomePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lblWelcome= new JLabel("What would you like to do?");
        lblWelcome.setFont(new Font("Arial",Font.ITALIC,12));
        welcomePanel.add(lblWelcome);
        container.add(welcomePanel);

        portalPanel=new JPanel();
        portalPanel.setPreferredSize(new Dimension(300,50));
        portalPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        btnviewClass=new JRadioButton("view Performance");
        btnviewClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnviewClass.isSelected()){
                    btnAddChild.setSelected(false);
                    portalSelected="view child";
                }
            }
        });
        btnAddChild=new JRadioButton("Add child");
        btnAddChild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnAddChild.isSelected()){
                    btnviewClass.setSelected(false);
                    portalSelected="Add child";
                }
            }
        });
        portalPanel.add(btnviewClass);
        portalPanel.add(btnAddChild);
        container.add(portalPanel);


        btnNextPanel=new JPanel();
        btnNextPanel.setPreferredSize(new Dimension(300,50));
        btnNextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnNext = new JButton("Next");
        btnNext.addActionListener(this::btnNextActionPerformed);
        btnLogOut =new JButton("Logout");
        btnLogOut.addActionListener(this);
        btnNextPanel.add(btnNext);
        btnNextPanel.add(btnLogOut);
        container.add(btnNextPanel);
    }
    private void btnNextActionPerformed(ActionEvent evt) {
        if (portalSelected=="Add child") {
            this.dispose();
            AddChildDetails child_Form = new AddChildDetails();
            child_Form.setVisible(true);
            child_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            child_Form.setSize(600,800);
            child_Form.setLocationRelativeTo(null);
        }else{
            this.dispose();
            ViewDetails view_Form = new ViewDetails();
            view_Form.setVisible(true);
            view_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view_Form.setSize(400,500);
            view_Form.setLocationRelativeTo(null);
        }
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnLogOut) {
            this.dispose();
            Login L_Form = new Login();
            L_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            L_Form.setVisible(true);
            L_Form.setSize(500,500);
            L_Form.setLocationRelativeTo(null);
        }
    }
}
