package Frontend;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import Global.Logger;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseMotionListener, MouseListener {

    UIController UI;
    long last_time = System.nanoTime();

    public MouseEvents(UIController ui){
        UI = ui;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
           
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Refresh rate is 10ms
        if (  (System.nanoTime() - last_time)/1000000 > 10 && UI.isMarkerBeingDragged())
        {
            UI.updateDraggedMarkerCords();
            last_time = System.nanoTime();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (UI.isMarkerBeingDragged())
        { 
            if(e.getButton() == MouseEvent.BUTTON1)
                UI.tryToPlaceMarker();
            else if (e.getButton() == MouseEvent.BUTTON3)
                UI.removeDraggedMarker();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
