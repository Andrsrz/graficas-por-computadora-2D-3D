
public class Slinea {
	
	public int ra = 10;
	int xp =10, yp=10, zp = 30;
	public Punto3d[] point;

	public Slinea(int sx, int sy, int sz, int puntosMaya, int ra) {
		this.point = new Punto3d[puntosMaya];
		this.ra = ra;
		for (int i = 0; i<puntosMaya ; i++) {
			int x = sx+ra+(int)( Math.pow(i-2,2)/2 );
			int y = sy+(ra*i);
			int z = sz;
			point[i]= new Punto3d( x, y, z);
			/*if(i<4)
				point[i]= new Punto3d(sx+(ra*i), sy+(i*ra), sz+(ra*i));
			else
				point[i]= new Punto3d(sx+(ra*i), sy-i*2, sz+(i*ra));*/
		}
	}

	public int getx2d(Punto3d a){
		double u = -a.z/zp;
		return (int)(a.x+(u*xp));
	}

	public int gety2d(Punto3d a){
		double u = -a.z/zp;
		return (int)(a.y+(u*yp));
	}

}