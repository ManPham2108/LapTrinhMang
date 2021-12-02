package Form.Body;

import Model.AccountModel;
import Model.GroupModel;
import Server.Client;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CreateGroup extends javax.swing.JFrame {

    private Map<String, String> map_usergroup;// save key: iduser, value: idgroup
    private DefaultTableModel dm;
    ArrayList<AccountModel> aruser = new ArrayList<>();
    ArrayList<String> listuserid = new ArrayList<>();
    public CreateGroup() {
        initComponents();
        this.setLocationRelativeTo(null);
        init();
    }

    public void init() {
        tblListUser.setDefaultRenderer(Object.class, new ButtonRenderer());
        dm = new DefaultTableModel(
                new Object[][]{},
                new Object[]{"HỌ VÀ TÊN", "XÓA THÀNH VIÊN"}) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblListUser.setModel(dm);
    }
    public void listUser(ArrayList<AccountModel> listuser) {
        aruser = listuser;
        cbListUser.addItem("");
        for (AccountModel am : listuser) {
            cbListUser.addItem(am.getFullName());
        }
        listuserid.add(Client.getInstance().User.getId());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        cbListUser = new javax.swing.JComboBox<>();
        btnCreateGroup = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNameGroup = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdCloseGroup = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 0, 0));

        cbListUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListUserActionPerformed(evt);
            }
        });

        btnCreateGroup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCreateGroup.setText("TẠO NHÓM");
        btnCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateGroupActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("TÊN NHÓM");

        txtNameGroup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("TẠO NHÓM");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("THÊM THÀNH VIÊN");

        cmdCloseGroup.setText("X");
        cmdCloseGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseGroupActionPerformed(evt);
            }
        });

        tblListUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListUser.setRowHeight(30);
        tblListUser.setRowMargin(3);
        tblListUser.getTableHeader().setReorderingAllowed(false);
        tblListUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListUser);
        if (tblListUser.getColumnModel().getColumnCount() > 0) {
            tblListUser.getColumnModel().getColumn(1).setResizable(false);
            tblListUser.getColumnModel().getColumn(1).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(467, Short.MAX_VALUE)
                .addComponent(cmdCloseGroup)
                .addGap(9, 9, 9))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbListUser, 0, 200, Short.MAX_VALUE)
                                    .addComponent(txtNameGroup)))
                            .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(btnCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(cmdCloseGroup)
                .addGap(1, 1, 1)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNameGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbListUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListUserActionPerformed
        //txtlistuser.append((String) cbListUser.getSelectedItem()+", ");
        
        if (!cbListUser.getSelectedItem().equals("")){
            int index = cbListUser.getSelectedIndex();
            String userid = aruser.get(index-1).getId();
            if(listuserid.contains(userid)==false){
                listuserid.add(userid);
            } 
            JButton btn = new JButton("XÓA");
            dm.addRow(new Object[]{
                cbListUser.getSelectedItem(),
                btn
            });
        }
    }//GEN-LAST:event_cbListUserActionPerformed

    private void cmdCloseGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseGroupActionPerformed
        dispose();
    }//GEN-LAST:event_cmdCloseGroupActionPerformed

    private void tblListUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListUserMouseClicked

        int column = tblListUser.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblListUser.getRowHeight();
        if (row < tblListUser.getRowCount() && row >= 0 && column < tblListUser.getColumnCount() && column >= 0) {
            Object value = tblListUser.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton btn = (JButton) value;
                dm.removeRow(row);
                listuserid.remove(row);
            }
        }


    }//GEN-LAST:event_tblListUserMouseClicked

    private void btnCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateGroupActionPerformed
        // TODO add your handling code here:
        Gson gson = new Gson();
        GroupModel group = new GroupModel(null, txtNameGroup.getText(), listuserid);
        String creategroup = gson.toJson(group);
        try {
            Client.getInstance().send("creategroup#~"+creategroup);
        } catch (IOException ex) {
            Logger.getLogger(CreateGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnCreateGroupActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGroup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateGroup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateGroup;
    private javax.swing.JComboBox<String> cbListUser;
    private javax.swing.JButton cmdCloseGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable tblListUser;
    private javax.swing.JTextField txtNameGroup;
    // End of variables declaration//GEN-END:variables
}

class ButtonRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            ((JButton) value).setBackground(Color.WHITE);
            ((JButton) value).setForeground(Color.red);
            return btn;
        }
        return super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
    }
}
