
package Form.Body;

import net.miginfocom.swing.MigLayout;

public class Menu_left extends javax.swing.JPanel {

    
    public Menu_left() {
        initComponents();
        init();
    }
    private void init(){
        MenuList.setLayout(new MigLayout("fillx","0[]0","1[]1"));
        showPeople();
    }
    private void showPeople(){
        for(int i =0;i<10;i++){
            MenuList.add(new Item_People("People "+i),"wrap");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuButton1 = new component.MenuButton();
        menuButton2 = new component.MenuButton();
        menuButton3 = new component.MenuButton();
        MenuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));

        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.LINE_AXIS));

        menuButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/message_selected.png"))); // NOI18N
        menu.add(menuButton1);

        menuButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/group.png"))); // NOI18N
        menu.add(menuButton2);

        menuButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/box.png"))); // NOI18N
        menu.add(menuButton3);

        javax.swing.GroupLayout MenuListLayout = new javax.swing.GroupLayout(MenuList);
        MenuList.setLayout(MenuListLayout);
        MenuListLayout.setHorizontalGroup(
            MenuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MenuListLayout.setVerticalGroup(
            MenuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MenuList)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuList)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane MenuList;
    private javax.swing.JLayeredPane menu;
    private component.MenuButton menuButton1;
    private component.MenuButton menuButton2;
    private component.MenuButton menuButton3;
    // End of variables declaration//GEN-END:variables
}
