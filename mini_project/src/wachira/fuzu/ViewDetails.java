package wachira.fuzu;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewDetails extends JFrame implements ActionListener {
    private FlowLayout layout;
    private JButton btnClose;
    private JTextField txtStdUsername;
    private JTable tblDetails;
    private JLabel  lblSearchStudent,headerLabel,lbltablecontents;
    private Container container;
    private JPanel headerPanel, btnPanel, tableheaderPanel, tablePanel;
    private ImageIcon logoImage;
    private JScrollPane pane;
    private DefaultTableModel model= new DefaultTableModel();

    public ViewDetails(){
        super("Fuzu Primary");
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


        tableheaderPanel=new JPanel();
        tableheaderPanel.setPreferredSize(new Dimension(350,40));
        tableheaderPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        lbltablecontents= new JLabel("Table details");
        lbltablecontents.setFont(new Font("Arial",Font.ITALIC,12));
        tableheaderPanel.add(lbltablecontents);
        container.add(tableheaderPanel);

        btnPanel=new JPanel();
        btnPanel.setPreferredSize(new Dimension(350,40));
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        btnClose=new JButton("Exit");
        btnClose.addActionListener(this::actionPerformed);
        btnPanel.add(btnClose);
        container.add(btnPanel);

        tablePanel=new JPanel();
        tablePanel.setPreferredSize(new Dimension(300,60));
        tablePanel.setLayout(new BorderLayout());
        tblDetails=new JTable(model);
        pane= new JScrollPane(tblDetails);
        pane.setVisible(true);
        tablePanel.add(pane);

        model.addColumn("childUsername");
        model.addColumn("mathematics");
        model.addColumn("english");
        model.addColumn("Kiswahili");
        PreparedStatement st;
        ResultSet rs;
        String query="SELECT `childUsername`,`mathematics`,`english`,`kiswahili` FROM `tbl_gradedetails`";
        try{
            st = Main.getConnection().prepareStatement(query);
            rs = st.executeQuery();
            while(rs.next()){

                model.addRow(new Object[]{rs.getString(1), rs.getInt(2),rs.getInt(3),rs.getInt(4)});
            }
        }catch (SQLException ex){
            Logger.getLogger(ViewDetails.class.getName()).log(Level.SEVERE,null,ex);
        }

        container.add(tablePanel);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnClose) {
            if (Homepage.getPortalSelected() == "teacher") {
                this.dispose();
                Teacher_dashboard TeachDash_Form = new Teacher_dashboard();
                TeachDash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TeachDash_Form.setVisible(true);
                TeachDash_Form.setSize(500,500);
                TeachDash_Form.setLocationRelativeTo(null);
            } else {
                this.dispose();
                Parent_dashboard Parentdash_Form = new Parent_dashboard();
                Parentdash_Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Parentdash_Form.setVisible(true);
                Parentdash_Form.setSize(500,500);
                Parentdash_Form.setLocationRelativeTo(null);
            }
        }
    }
}

