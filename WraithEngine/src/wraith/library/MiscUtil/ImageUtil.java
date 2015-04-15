package wraith.library.MiscUtil;

import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageUtil{
	public static BufferedImage[] splitImage(BufferedImage img, int rows, int cols){
		if(img==null)throw new IllegalArgumentException("Image cannot be null!");
		if(rows<1||cols<1)throw new IllegalArgumentException("Rows and columns must be at least 1!");
		BufferedImage[] bufs = new BufferedImage[rows*cols];
		int width = img.getWidth()/cols;
		int height = img.getHeight()/rows;
		for(int i = 0; i<bufs.length; i++)bufs[i]=img.getSubimage(i%cols*width, i/cols*height, width, height);
		return bufs;
	}
	public static void saveImage(File file, BufferedImage img){
		try{
			if(!file.exists()){
				new File(file.getParent()).mkdirs();
				file.createNewFile();
			}
			ImageIO.write(img, StringUtil.getFileExtension(file.getName()), file);
		}catch(Exception exception){ exception.printStackTrace(); }
	}
	public static BufferedImage getBestFormat(int width, int height){ return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); }
}