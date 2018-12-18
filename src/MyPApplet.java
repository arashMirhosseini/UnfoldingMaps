import processing.core.*;
public class MyPApplet extends PApplet
{
	private String url = "https://s3-us-east-2.amazonaws.com/rvshare-wordpress/wp-content/uploads/2017/11/09124352/Cannon-Beach.jpg";
	private PImage backgroundImg;
	
	public void setup()
	{
		size(200,200);
		backgroundImg = loadImage(url, "jpg");
	}
	public void draw()
	{
		
		backgroundImg.resize(0, height);
		image(backgroundImg, 0, 0);
		int[] rgb = setSunColor(second());
		fill(rgb[0], rgb[1], rgb[2]);
		ellipse(width/4, height/5, width/5, height/5);
		
	}
	
	public int[] setSunColor(float second)
	{
		int[] color = new int[3];
		float diffFrom = Math.abs(30 - second);
		
		float ratio = diffFrom/30;
		color[0] = (int) (ratio * 255);
		color[1] = (int) (ratio * 255);
		color[2] = 0;
		return color; 
	}
}
