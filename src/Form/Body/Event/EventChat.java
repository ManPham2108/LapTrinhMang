/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Body.Event;

public interface EventChat {
    public void sendMessage(String text);
    public void reciveMessage(String text);
    public void seenMessage(String userid);
    public void removeAllChatBody();
    public void loadMessage(String text);
    public void loadBlock(String text);
    public void loadListBlock(String text);
    public void updateUserBlock(String text);
}
