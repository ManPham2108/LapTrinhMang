/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Body.Event;

/**
 *
 * @author man21
 */
public class PublicEvent {
    private static PublicEvent instance;
    private EvenChat evenchat;
    public static PublicEvent getInstance() {
        if(instance==null){
            instance = new PublicEvent();
        }
        return instance;
    }
    public void addEventChat(EvenChat event){
        this.evenchat= event;
    }
    public EvenChat getEventChat(){
        return evenchat;
    }
}
