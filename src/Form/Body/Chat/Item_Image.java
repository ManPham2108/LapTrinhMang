package Form.Body.Chat;

import Model.FileModel;
import javax.swing.Icon;

public class Item_Image extends javax.swing.JLayeredPane {

    public Item_Image() {
        initComponents();
    }
    
    public void setImage(Icon img, FileModel fileSender) {
        pic.setImage(img);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new component.PictureBox();

        setLayer(pic, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}
