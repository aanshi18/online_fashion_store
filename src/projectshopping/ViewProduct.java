/*
 * Admin can VIEW all the products in the table.
 * Admin can also SEARCH for a product based in its id.
 */
package projectshopping;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;


public final class ViewProduct extends javax.swing.JPanel {

    DefaultTableModel model;
    String username = "root";
    String password = "";
    String driverName = "com.mysql.jdbc.Driver";
    String dbName = "fashion_store_db";
    String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
    ResultSet rs;
    Connection conn;
    Statement stmt;
    private Object jComboxBox1;

    /**
     * Creates new form ViewProduct
     * @throws java.sql.SQLException
     */
    public ViewProduct() throws SQLException {
        initComponents();
        this.setSize(1920, 1080);
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(dbURL, username, password);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception e = " + e);
        }
        ArrayList<Integer> productId = this.getAllProductIds("SELECT product_id from cloth");
        Object data[][] = new Object[productId.size()][7];
        rs=stmt.executeQuery("SELECT * from cloth WHERE 1");
        int i=0;
        while(rs.next()){
            data[i][0] = rs.getString(1);
            data[i][1] = rs.getString(2);
            data[i][2] = rs.getString(3);            
            data[i][3] = rs.getString(4);
            data[i][4]=rs.getString(8);
            data[i][5]=rs.getString(9);
            data[i][6]=rs.getString(13);
            i++;
        }
        model = new DefaultTableModel();
        String[] columnNames = {"productId", "productName","price", "stock","colour","rating","dateOfAdding"};
        model.setDataVector(data, columnNames);
        // set the model to the table
        jTable1.setModel(model);
    }
 ArrayList<Integer> getAllProductIds(String Query) throws SQLException {
        ArrayList<Integer> productIds = new ArrayList<>();
       
        int i = 0;
        String selectQuery  = Query;
        rs=stmt.executeQuery(selectQuery);
        try {
            
            while (rs.next()) {
                productIds.add(rs.getInt(1));
            }
          //  System.out.println("No. of product Ids = " + productIds.size());
        } catch (Exception e) {
            System.out.println("e = " + e);
            e.printStackTrace();
        }
         // TODO add your handling code here:
         
        return productIds;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setLayout(null);

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ProductId", "ProductName", "Price", "Stock", "colour", "Rating", "DateOfAdding"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(30, 190, 890, 500);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/view product panel.PNG"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(30, 40, 900, 90);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search button.jpg"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(1320, 70, 100, 50);
        add(jTextField1);
        jTextField1.setBounds(1040, 70, 260, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectshopping/cart.jpg"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(0, 0, 2950, 920);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            ArrayList<Integer> productId = this.getAllProductIds("SELECT * FROM `cloth` WHERE product_id LIKE '"+jTextField1.getText()+"%' ");
            Object data[][] = new Object[productId.size()][7];
            rs=stmt.executeQuery("SELECT * FROM `cloth` WHERE product_id LIKE '"+jTextField1.getText()+"%' ");
            int i=0;
            while(rs.next()){
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4]=rs.getString(8);
                data[i][5]=rs.getString(9);
                data[i][6]=rs.getString(13);
                i++;
            }
            model = new DefaultTableModel();
            String[] columnNames = {"productId", "productName","price", "stock","colour","rating","dateOfAdding"};
            model.setDataVector(data, columnNames);
            // set the model to the table
            jTable1.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(ViewProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}