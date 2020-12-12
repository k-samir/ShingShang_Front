package presentation;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {

	GraphicsContext gc                  = getGraphicsContext2D();
	public static final int MAX_ZOOM = 10; // Combien de niveaux de zoom souhaitons nous
	public static final String FICHIER_IMAGE = "map.jpg"; // fichier contenant l'image du reseau
	public static final String FICHIER_COORDONNEES = "coordonnees.txt"; // fichier contenant les coordonnees des stations

	private double colPressed, ligPressed; // coordonnees de la souris lorsqu'on enfonce le bouton 
	private double colDepart, ligDepart; // dernieres positions connues de la souris depuis que le 
	//bouton de la souris a ete enfonce

	private Image carte; // la carte du reseau
	private int largeur, hauteur; // largeur et hauteur de l'image (carte) en prenant en compte le zoom
	private double x,y; // position du coin haut gauche de l'image
	private double minX, maxX, minY, maxY; // positions limites pour le coin haut gauche de l'image

	private int niveauZoom; // le niveau de zoom actuel
	private double zoom, zoomMin, zoomMax; // coefficients multiplicateurs du zoom actuel, du zoomMin et du zoomMax

	private ArrayList<String> stations;    // les noms des stations
	private ArrayList<Point> coordonnees ; // les coordonnees des stations

	private ArrayList<Integer> result; // la selection des index de stations


	public ResizableCanvas() {
		carte = null;
		try {
			carte = new Image(new FileInputStream("map.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.coordonnees = new ArrayList<Point>();
		this.stations = new ArrayList<String>();
		this.result=new ArrayList<Integer>();
		this.zoomMax=1.0;
		this.charger();

		this.setOnScroll((EventHandler<? super ScrollEvent>) new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent arg0) {
				if (arg0.getDeltaY()<0) {
					niveauZoom=niveauZoom-1>=0 ? niveauZoom-1 : 0;
				} else {
					niveauZoom=niveauZoom+1<11 ? niveauZoom=niveauZoom+1:10;
				}
				//System.out.println("deltax="+arg0.getDeltaX()+" deltay "+arg0.getDeltaY()+ " toatal="+arg0.getTotalDeltaY()+" niveauZoom="+niveauZoom);
				ajuster();
				draw();
			}
		});

		this.setOnMousePressed((EventHandler<? super MouseEvent>)new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ev) {
				colPressed =(ev.getX());
				colDepart=colPressed;
				ligPressed=(ev.getY());
				ligDepart=ligPressed;
			}
		});
		this.setOnMouseDragged((EventHandler<? super MouseEvent>)new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ev) {
				//System.out.println("dragged "+ev.getX()+", "+ev.getY());
				double colArrivee =(ev.getX());
				double ligArrivee =(ev.getY());
				x=x-(colDepart-colArrivee);
				y=y-(ligDepart-ligArrivee);
				recadrer();
				draw();
				colDepart=(ev.getX());
				ligDepart=(ev.getY());
			}
		});
		this.setOnMouseClicked((EventHandler<? super MouseEvent>)new EventHandler<MouseEvent>() {
			public void handle(MouseEvent ev) {
				//System.out.println("clicked "+ev.getX()+", "+ev.getY()+" old:"+colDepart+"x"+ligDepart);
				if (ev.getX()==colPressed && ev.getY()==ligPressed) {
					if (ev.getButton()==MouseButton.PRIMARY ) { // bouton gauche de la souris
						Integer plusProche = plusProche((int) ((double)(colDepart-x)/zoom) ,(int)((double)(ligDepart-y)/zoom));
						if (result.contains(plusProche))  {
							if (result.size()>=2 && result.get(result.size()-1).equals(plusProche)) {
								System.out.print("Itineraire : ");
								for (int i=0; i<result.size()-1; i++) {
									System.out.print(" "+stations.get(result.get(i))+", ");
								};
								System.out.println(result.size()>0 ? stations.get(result.get(result.size()-1)) :"");
							}
						} else {
							result.add(plusProche);
						}
						draw();
					} else { // Bouton droit
						if (result.size()>0) {
							result.remove(result.size()-1);
						}
					}
				}
				double colArrivee =(ev.getX());
				double ligArrivee =(ev.getY());
				x=x-(colDepart-colArrivee);
				y=y-(ligDepart-ligArrivee);
				recadrer();
				draw();
				colDepart=(ev.getX());
				ligDepart=(ev.getY());
			}
		});
	}

	/**
	 * Redraw the Canvas
	 */
	private void draw() {

//		System.out.println(" Real Canvas Width is:" + getWidth() + " , Real Canvas Height is:" + getHeight() + "\n");
//		System.out.println("x="+this.x+" y="+this.y+" zoom="+zoom+" largeur="+largeur+" hauteur="+hauteur);

		gc.clearRect(0, 0, getWidth(),getHeight());
		gc.drawImage(carte, this.x,  this.y, this.largeur, this.hauteur);
		gc.setStroke(Color.RED);
		for (Integer i : result) {
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.RED);
			double col = coordonnees.get(i).getX();
			double lig = coordonnees.get(i).getY();
			gc.fillOval(x+(col*zoom)-10, y+(lig*zoom)-10, 20,  20);
			gc.strokeText(""+(result.indexOf(i)+1), x+(col*zoom)-3, y+(lig*zoom)+6);
		}
	}

	@Override
	public double minHeight(double width) {
		return 50;
	}
	@Override
	public double minWidth(double height) {
		return 100;
	}
	@Override
	public boolean isResizable() {
		return true;
	}
	@Override
	public void resize(double width, double height) {
	//	System.out.println("resize "+width+" x "+height);
		ajuster();
		draw();
	}
	private void ajuster() {
	//	System.out.println("ajuster ecran=" +this.getWidth()+"x"+this.getHeight()+" image="+carte.getWidth()+"x"+carte.getHeight());
		this.zoomMin = Math.max( (double)this.getWidth()/carte.getWidth(),   (double)this.getHeight()/carte.getHeight());
		this.zoom= zoomMin+ ((zoomMax-zoomMin)/MAX_ZOOM)*this.niveauZoom;
	//	System.out.println("zoomMin="+zoomMin+" zoom="+zoom);
		this.largeur = (int)(zoom*carte.getWidth());
		this.hauteur = (int)(zoom* carte.getHeight());
		this.minX=(int)(this.getWidth()-this.largeur);
		this.maxX=0;
		this.minY=(int)(this.getHeight()-this.hauteur);
		this.maxY=0;
		this.recadrer();
	}

	/**
	 * Modifie this.x (resp. this.y) Si this.x (resp. this.y) est en dehors de [minX, maxX] (resp. [minY, maxY]) 
	 * pour le remettre dans cette plage de validite 
	 */
	private void recadrer() {
		this.x=Math.max(this.minX, Math.min(this.x, this.maxX));
		this.y=Math.max(this.minY, Math.min(this.y, this.maxY));
	}

	/**
	 * Consulte le fichier de coordonnes afin de mettre a jour la liste des noms de station et leurs coordonnees
	 */
	private void charger() {
		String nom;
		try {
			BufferedReader aLire = new BufferedReader(new FileReader(FICHIER_COORDONNEES)) ;
			do { 
				nom = aLire.readLine() ;
				if (nom!=null) {
					this.stations.add(nom);
					this.coordonnees.add( new Point( Integer.parseInt(aLire.readLine()), Integer.parseInt(aLire.readLine())));
				}
			} while (nom !=null) ; 
			aLire.close() ;
		}
		catch (IOException e) {
			System.out.println("Une operation sur le fichier coordonnees.txt a leve l�exception "+e) ;
		}
	}
	private Integer plusProche(int x, int y) {
		int i=0;
		int distance=Integer.MAX_VALUE;
		int min=0;
		for (Point p : this.coordonnees) {
			int d =(int) ((p.getX()-x)*(p.getX()-x) + (p.getY()-y)*(p.getY()-y));
			if (d<distance) {
				min=i;
				distance = d;
			}
			i++;
		}
		return min;
	}
}
