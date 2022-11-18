package wachira.fuzu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Homepage extends JFrame implements ActionListener{
    private FlowLayout layout;
    private JRadioButton btnParentPortal, btnTeacherPortal, btnChildLogin;
    private JButton btnNext;
    private JLabel lblMotto,headerLabel;
    private Container container;
    private JPanel headerPanel, mottoPanel,portalPanel, btnNextPanel;
    private ImageIcon logoImage;
    private static String portalSelected;


    public Homepage(){
        super("Welcome to Fuzu Primary");
        container=getContentPane();
        layout= new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage= new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel= new JLabel();
        headerLabel.setBounds(10,11,100,100);
        headerPanel=new JPanel();
        headerLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setPreferredSize(new Dimension(100,100));
        headerPanel.add(headerLabel);
        container.add(headerPanel);
        Image img=logoImage.getImage();
        Image newImage=img.getScaledInstance(headerLabel.getWidth(),headerLabel.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon finalLogo= new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);

        mottoPanel=new JPanel();
        mottoPanel.setPreferredSize(new Dimension(300,50));
        mottoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblMotto= new JLabel("Something Smart!");
        lblMotto.setFont(new Font("Arial",Font.BOLD,18));
        mottoPanel.add(lblMotto);
        container.add(mottoPanel);

        portalPanel=new JPanel();
        portalPanel.setPreferredSize(new Dimension(300,50));
        portalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnParentPortal=new JRadioButton("Parent");
        btnParentPortal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnParentPortal.isSelected()){
                    btnChildLogin.setSelected(false);
                    btnTeacherPortal.setSelected(false);
                    portalSelected="parent";
                }
            }
        });
        btnTeacherPortal=new JRadioButton("Teacher");
        btnTeacherPortal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnTeacherPortal.isSelected()){
                    btnChildLogin.setSelected(false);
                    btnParentPortal.setSelected(false);
                    portalSelected="teacher";
                }
            }
        });
        btnChildLogin=new JRadioButton("Child");
        btnChildLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnChildLogin.isSelected()){
                    btnTeacherPortal.setSelected(false);
                    btnParentPortal.setSelected(false);
                    portalSelected="child";
                }
            }
        });

        portalPanel.add(btnParentPortal);
        portalPanel.add(btnTeacherPortal);
        portalPanel.add(btnChildLogin);
        container.add(portalPanel);


        btnNextPanel=new JPanel();
        btnNextPanel.setPreferredSize(new Dimension(60,50));
        btnNextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnNext = new JButton("Next");
        btnNext.addActionListener(this);
        btnNextPanel.add(btnNext);
        container.add(btnNextPanel);

    }
    public static String getPortalSelected(){
        return portalSelected;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        this.dispose();
        Login L_Form = new Login();
        L_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        L_Form.setVisible(true);
        L_Form.setSize(500,500);
        L_Form.setLocationRelativeTo(null);
    }

}