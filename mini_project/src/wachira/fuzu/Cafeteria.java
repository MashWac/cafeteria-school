package wachira.fuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cafeteria extends JFrame  {
    private FlowLayout layout;
    private JTextField userName;
    private JPasswordField field_passWord;
    private JButton btnAddtoCart,btnCancel,btnConfirm;
    private JLabel lblWelcomeCafe,headerLabel, lblSnacks, lblMeal, lblDrinks, lblQuantitySnacks, lblQuantityMeals, lblQuantityDrinks;
    private JComboBox comboBoxDrinks,comboBoxSnacks,comboBoxMeals, comboBoxQuantitySnacks,comboBoxQuantityDrinks, comboBoxQuantityMeals;
    private Container container;
    private JPanel headerPanel, welcomeCafePanel,SnacksPanel, mealPanel, drinksPanel,btnOrdersPanel;
    private ImageIcon logoImage;
    private String[] snackNames={"Samosa", "Mandazi","Sausage", "ChapoSmokie"};
    private String[] mealNames={"Biriyani Rice", "Ugali and Chicken","Chips and Fish", "Pilau Mayai"};
    private String[] drinkNames={"Water", "Soda","Juice"};
    private Integer[] quantityChoicesSnacks={0,1,2,3,4,5};
    private Integer[] quantityChoicesMeals={0,1,2,3,4,5};
    private Integer[] quantityChoicesDrinks={0,1,2,3,4,5};
    private static String str_childUsername;
    int cardBalance=0;
    int orderTotal=0;
    int accountBal=0;




    public Cafeteria() {
        super("FUZU PRIMARY");
        container = getContentPane();
        layout = new FlowLayout(FlowLayout.CENTER);
        setLayout(layout);


        logoImage = new ImageIcon(getClass().getResource("goodlogo.png"));
        headerLabel = new JLabel();
        headerLabel.setBounds(10, 11, 200, 200);
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(500, 200));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(headerLabel);
        container.add(headerPanel);
        Image img = logoImage.getImage();
        Image newImage = img.getScaledInstance(headerLabel.getWidth(), headerLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalLogo = new ImageIcon(newImage);
        headerLabel.setIcon(finalLogo);


        welcomeCafePanel = new JPanel();
        welcomeCafePanel.setPreferredSize(new Dimension(500, 40));
        welcomeCafePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblWelcomeCafe = new JLabel("Welcome to the cafeteria. Please select your order!");
        lblWelcomeCafe.setFont(new Font("Arial",Font.BOLD,18));
        welcomeCafePanel.add(lblWelcomeCafe);
        container.add(welcomeCafePanel);

        SnacksPanel = new JPanel();
        SnacksPanel.setPreferredSize(new Dimension(400, 35));
        SnacksPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblSnacks= new JLabel("Snacks");
        lblSnacks.setFont(new Font("Arial", Font.ITALIC, 11));
        SnacksPanel.add(lblSnacks);
        comboBoxSnacks= new JComboBox(snackNames);
        comboBoxSnacks.setVisible(true);
        SnacksPanel.add(comboBoxSnacks);
        lblQuantitySnacks=new JLabel("Quantity");
        lblQuantitySnacks.setFont(new Font("Arial", Font.ITALIC, 11));
        SnacksPanel.add(lblQuantitySnacks);
        comboBoxQuantitySnacks= new JComboBox(quantityChoicesSnacks);
        comboBoxQuantitySnacks.setVisible(true);
        SnacksPanel.add(comboBoxQuantitySnacks);
        container.add(SnacksPanel);


        mealPanel = new JPanel();
        mealPanel.setPreferredSize(new Dimension(400, 35));
        mealPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblMeal= new JLabel("Full Meals");
        lblMeal.setFont(new Font("Arial", Font.ITALIC, 11));
        mealPanel.add(lblMeal);
        comboBoxMeals=new JComboBox(mealNames);
        comboBoxMeals.setVisible(true);
        mealPanel.add(comboBoxMeals);
        lblQuantityMeals=new JLabel("Quantity");
        lblQuantityMeals.setFont(new Font("Arial", Font.ITALIC, 11));
        mealPanel.add(lblQuantityMeals);
        comboBoxQuantityMeals=new JComboBox(quantityChoicesMeals);
        comboBoxQuantityMeals.setVisible(true);
        mealPanel.add(comboBoxQuantityMeals);
        container.add(mealPanel);

        drinksPanel = new JPanel();
        drinksPanel.setPreferredSize(new Dimension(400, 35));
        drinksPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lblDrinks= new JLabel("Drinks");
        lblDrinks.setFont(new Font("Arial", Font.ITALIC, 11));
        drinksPanel.add(lblDrinks);
        comboBoxDrinks=new JComboBox(drinkNames);
        comboBoxDrinks.setVisible(true);
        drinksPanel.add(comboBoxDrinks);
        lblQuantityDrinks=new JLabel("Quantity");
        lblQuantityDrinks.setFont(new Font("Arial", Font.ITALIC, 11));
        drinksPanel.add(lblQuantityDrinks);
        comboBoxQuantityDrinks=new JComboBox(quantityChoicesDrinks);
        comboBoxQuantityDrinks.setVisible(true);
        drinksPanel.add(comboBoxQuantityDrinks);
        container.add(drinksPanel);

        btnOrdersPanel=new JPanel();
        btnOrdersPanel.setPreferredSize(new Dimension(300,50));
        btnOrdersPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnAddtoCart= new JButton("Proceed to checkout");
        btnAddtoCart.addActionListener(this::btnAddToCartactionPerformed);
        btnCancel =new JButton("Cancel");
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        btnOrdersPanel.add(btnAddtoCart);
        btnOrdersPanel.add(btnCancel);
        container.add(btnOrdersPanel);

    }
    private void btnCancelActionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnCancel) {
            this.dispose();
            Homepage HForm = new Homepage();
            HForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            HForm.setVisible(true);
            HForm.setSize(300,400);
            HForm.setLocationRelativeTo(null);
        }
    }
    private void setCardBalance() {
        PreparedStatement st;
        ResultSet rs;
        str_childUsername = Login.getStr_childUsername();
        String query3 = "UPDATE `tbl_carddetails` SET `amountHeld`=? WHERE `childUsername`=?";

        try {
            st = Main.getConnection().prepareStatement(query3);
            st = Main.getConnection().prepareStatement(query3);
            st.setInt(1, accountBal);
            st.setString(2, str_childUsername);

            if (st.executeUpdate() != 0) {

                this.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadGrades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int getCardBalance(){
        PreparedStatement st;
        ResultSet rs;
        str_childUsername=Login.getStr_childUsername();
        String query1= "SELECT `cardNo`,`amountHeld` FROM `tbl_carddetails` WHERE `childUsername`=?";
        try{
            st = Main.getConnection().prepareStatement(query1);
            st.setString(1, str_childUsername);
            rs= st.executeQuery();
            if(rs.next()) {
                cardBalance = rs.getInt(2);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cardBalance;
    }
    private void btnAddToCartactionPerformed(ActionEvent evt){
        if(evt.getSource()==btnAddtoCart){
            String selectedSnack=comboBoxSnacks.getSelectedItem().toString();
            String selectedQuantitySnack=comboBoxQuantitySnacks.getSelectedItem().toString();
            Integer intQuantitySnack=Integer.parseInt(selectedQuantitySnack);


            String selectedMeal=comboBoxMeals.getSelectedItem().toString();
            String selectedQuantityMeal=comboBoxQuantityMeals.getSelectedItem().toString();
            Integer intQuantityMeal=Integer.parseInt(selectedQuantityMeal);


            String selectedDrink=comboBoxDrinks.getSelectedItem().toString();
            String selectedQuantityDrink=comboBoxQuantityDrinks.getSelectedItem().toString();
            Integer intQuantityDrinks=Integer.parseInt(selectedQuantityDrink);

            cardBalance=getCardBalance();
            int priceOfSnack=0;
            int priceOfMeal=0;
            int priceOfDrink=0;
            PreparedStatement st;
            ResultSet rs;
            String query2="SELECT `itemName`,`itemPrice` FROM `tbl_cafeteria` WHERE `itemName`=?";

            if(intQuantityDrinks!=0) {

                try {
                    st = Main.getConnection().prepareStatement(query2);
                    st.setString(1, selectedDrink);
                    rs = st.executeQuery();
                    if (rs.next()) {
                        priceOfDrink = rs.getInt(2);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(intQuantitySnack!=0) {
                try{
                    st = Main.getConnection().prepareStatement(query2);
                    st.setString(1, selectedSnack);
                    rs= st.executeQuery();
                    if(rs.next()) {
                        priceOfSnack = rs.getInt(2);
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if(intQuantityMeal!=0) {
                try{
                    st = Main.getConnection().prepareStatement(query2);
                    st.setString(1, selectedMeal);
                    rs= st.executeQuery();
                    if(rs.next()) {
                        priceOfMeal = rs.getInt(2);
                    }
                }catch (SQLException ex) {
                    Logger.getLogger(Teacher_registration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            int arrayStoreData[][]={{priceOfSnack,intQuantitySnack},{priceOfMeal,intQuantityMeal},{priceOfDrink,intQuantityDrinks}};
            int totalForSnacks=(arrayStoreData[0][0])*(arrayStoreData[0][1]);
            int totalForMeals=(arrayStoreData[1][0])*(arrayStoreData[1][1]);
            int totalForDrinks=(arrayStoreData[2][0])*(arrayStoreData[2][1]);
            int orderTotal=(totalForDrinks+totalForMeals+totalForSnacks);

            if (cardBalance < orderTotal) {
                JOptionPane.showMessageDialog(null, "Insufficient Funds");
            }else{
                accountBal=cardBalance-orderTotal;
                setCardBalance();
                JOptionPane.showMessageDialog(null, "Order Successful. Card balance updated\n Total: "+orderTotal);

            }




        }

    }
}
