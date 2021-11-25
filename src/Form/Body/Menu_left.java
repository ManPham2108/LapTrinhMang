
package Form.Body;

import Form.Body.Event.EventMenuLeft;
import Form.Body.Event.EventMenuLeft;
import Form.Body.Event.PublicEvent;
import Form.UpdateInfo.BodyUpdateInfo;
import Form.UpdateInfo.FormUpdateInfo;
import Model.AccountModel;
import Model.GroupModel;
import Server.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import component.ScrollBar;
import java.awt.Component;
import java.util.ArrayList;
import net.miginfocom.swing.MigLayout;

public class Menu_left extends javax.swing.JPanel {
    private ArrayList<AccountModel> listPeople  = new ArrayList<>();
    private ArrayList<GroupModel> listGroup  = new ArrayList<>();
    private Gson gson = new Gson();
    public Menu_left() {
        initComponents();
        init();
        showPeople();
    }
    private void init(){
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx","0[]0","0[]0"));
        menuMessage.setSelected(true);
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft(){
            @Override
            public void addlistUser(ArrayList<AccountModel> listuser) {
                txtName.setText(Client.getInstance().User.getFullName());
                menuList.removeAll();
                listPeople = listuser;
                for(AccountModel ac : listuser){
                    menuList.add(new Item_People(ac),"wrap");
                    refreshMenuList();
                }
            }
            @Override
            public void updateStatusOnline(String Id) {
                for(AccountModel ac : listPeople){
                    if(Id.equals(ac.getId())){
                        ac.setStatus(true);
                        break;
                    }   
                }
                if(menuMessage.isSelected()){
                    for(Component com : menuList.getComponents()){
                        Item_People item = (Item_People) com;
                        if(item.getUser().getId().equals(Id)){
                            item.updateStatus();
                            break;
                        }                                                
                    }
                } 
            }
            @Override
            public void updateStatusOffline(String Id) {
                for(AccountModel ac : listPeople){
                    if(Id.equals(ac.getId())){
                        ac.setStatus(false);
                        break;
                    }   
                }
                if(menuMessage.isSelected()){
                    for(Component com : menuList.getComponents()){
                        Item_People item = (Item_People) com;
                        if(item.getUser().getId().equals(Id)){
                            item.updateStatus();
                            break;
                        }                                                
                    }
                }
            }
            @Override
            public void listGroup(ArrayList<GroupModel> listgroup) {
                listGroup = listgroup;
            }
        });
    }
    public void showPeople() {
        menuList.removeAll();
        for(AccountModel ac : listPeople){
           menuList.add(new Item_People(ac),"wrap");
        }
        refreshMenuList();
    }

    private void showGroup() {
        menuList.removeAll();
        for (GroupModel gm : listGroup) {
            menuList.add(new Item_Group(gm), "wrap");
        }
        refreshMenuList();
    }

    private void showSystem() {
        menuList.removeAll();
        menuList.add(new Item_System("System"),"wrap");
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new component.MenuButton();
        menuGroup = new component.MenuButton();
        menuBox = new component.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JPanel();
        menuInfor = new javax.swing.JLayeredPane();
        imageAvatar1 = new component.ImageAvatar();
        imageAvatar2 = new component.ImageAvatar();
        jPanel1 = new javax.swing.JPanel();
        txtName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        menuMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/message_selected.png"))); // NOI18N
        menuMessage.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/Image/message_selected.png"))); // NOI18N
        menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/Image/message_selected.png"))); // NOI18N
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });

        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/group_selected.png"))); // NOI18N
        menuGroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/Image/group_selected.png"))); // NOI18N
        menuGroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/Image/group_selected.png"))); // NOI18N
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });

        menuBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/box_selected.png"))); // NOI18N
        menuBox.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/Image/box_selected.png"))); // NOI18N
        menuBox.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/Image/box_selected.png"))); // NOI18N
        menuBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoxActionPerformed(evt);
            }
        });

        menu.setLayer(menuMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        menu.setLayer(menuGroup, javax.swing.JLayeredPane.DEFAULT_LAYER);
        menu.setLayer(menuBox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addComponent(menuMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(menuGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(menuBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        sp.setBackground(new java.awt.Color(242, 242, 242));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 167, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        menuInfor.setBackground(new java.awt.Color(51, 255, 255));
        menuInfor.setOpaque(true);

        imageAvatar1.setBackground(new java.awt.Color(255, 255, 255));
        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-user-male-skin-type-5-48.png"))); // NOI18N
        imageAvatar1.setInheritsPopupMenu(true);
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar1MouseClicked(evt);
            }
        });

        imageAvatar2.setBorderSize(0);
        imageAvatar2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/add-user-group.png"))); // NOI18N
        imageAvatar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar2MouseClicked(evt);
            }
        });

        menuInfor.setLayer(imageAvatar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        menuInfor.setLayer(imageAvatar2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout menuInforLayout = new javax.swing.GroupLayout(menuInfor);
        menuInfor.setLayout(menuInforLayout);
        menuInforLayout.setHorizontalGroup(
            menuInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
            .addComponent(imageAvatar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuInforLayout.setVerticalGroup(
            menuInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuInforLayout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        txtName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(sp))
                    .addComponent(menuInfor))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            menuBox.setSelected(false);
            showPeople();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGroupActionPerformed
       if (!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            menuBox.setSelected(false);
            showGroup();
       }
    }//GEN-LAST:event_menuGroupActionPerformed

    private void menuBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoxActionPerformed
        if (!menuBox.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(false);
            menuBox.setSelected(true);
            showSystem();
        }
        PublicEvent.getInstance().getEventMain().SelectSystem();
    }//GEN-LAST:event_menuBoxActionPerformed

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked
        FormUpdateInfo update = new FormUpdateInfo();
        update.setVisible(true);
    }//GEN-LAST:event_imageAvatar1MouseClicked

    private void imageAvatar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar2MouseClicked
        CreateGroup cg = new CreateGroup();
        cg.setVisible(true);
        cg.listUser(listPeople);
    }//GEN-LAST:event_imageAvatar2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ImageAvatar imageAvatar1;
    private component.ImageAvatar imageAvatar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLayeredPane menu;
    private component.MenuButton menuBox;
    private component.MenuButton menuGroup;
    private javax.swing.JLayeredPane menuInfor;
    private javax.swing.JPanel menuList;
    private component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    private javax.swing.JLabel txtName;
    // End of variables declaration//GEN-END:variables
}
