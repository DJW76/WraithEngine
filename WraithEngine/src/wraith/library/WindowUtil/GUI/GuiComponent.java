package wraith.library.WindowUtil.GUI;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import wraith.library.MiscUtil.ImageUtil;

public abstract class GuiComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
	protected int x, y;
	protected int width, height;
	private boolean disposed;
	protected GuiContainer parent;
	protected boolean needsRepaint;
	private BufferedImage staticImage;
	private Graphics2D g;
	public GuiComponent(GuiContainer parent, int bufferWidth, int bufferHeight){
		this.parent=parent;
		staticImage=ImageUtil.getBestFormat(bufferWidth, bufferHeight);
		g=staticImage.createGraphics();
	}
	public void setSizeAndLocation(int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		setNeedsRepaint();
	}
	public void addListeners(Component c){
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
		c.addMouseWheelListener(this);
		c.addKeyListener(this);
	}
	public void removeListeners(Component c){
		c.removeMouseListener(this);
		c.removeMouseMotionListener(this);
		c.removeMouseWheelListener(this);
		c.removeKeyListener(this);
	}
	public void dispose(){
		disposed=true;
		if(parent!=null){
			parent.components.remove(this);
			parent=null;
		}
		g.dispose();
		g=null;
		staticImage=null;
	}
	public void setNeedsRepaint(){
		needsRepaint=true;
		if(parent!=null)parent.setNeedsRepaint();
	}
	public void update(){
		if(!needsRepaint)return;
		g.clearRect(0, 0, width, height);
		render(g);
		setRepainted();
	}
	public BufferedImage getPane(){ return staticImage; }
	public void setRepainted(){ needsRepaint=false; }
	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	public boolean isDisposed(){ return disposed; }
	public boolean needsRepaint(){ return needsRepaint; }
	public abstract void render(Graphics2D g);
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseWheelMoved(MouseWheelEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}