package package1;

import java.awt.Graphics;

public class Visage extends FormeCirculaireReguliere {
	 // ---------------------------------------------------------
    // Les constantes de la classe Visage
    // ---------------------------------------------------------
    /**
     * Largeur par d�faut pour un Visage
     */
    public static final int LARGEUR_DEFAUT = 50;

    /**
     * Hauteur par defaut pour un Visage.
     */
    public static final int HAUTEUR_DEFAUT = 50;

    /**
     * Largeur minimale pour un Visage
     */
    public static final int LARGEUR_MIN = 15;

    /**
     * Hauteur minimale pour un Visage
     */
    public static final int HAUTEUR_MIN = 15;

    /**
     * d�placement par d�faut.
     */
    public static final int DEPLACEMENT_DEFAUT = 5;

    // -------------------------------------------------------------
    // Les attributs (variables d'instance) de la classe VisageRond
    // -------------------------------------------------------------
    /**
     * La z�ne de dessin dans laquelle se trouve le Visage.
     */
    private Dessin d;

    /**
     * abscisse coin sup�rieur gauche du rectangle englobant le visage.
     */
    private int xhg = 0;

    /**
     * ordonn�e coin sup�rieur gauche du rectangle englobant le visage.
     */
    private int yhg = 0;

    /**
     * largeur du visage.
     */
    private int largeur;

    /**
     * hauteur du visage.
     */
    private int hauteur;

    /**
     * d�placement �l�mentaire horizontal du visage. Par d�faut 5 pixels.
     */
    private int dx = DEPLACEMENT_DEFAUT;

    /**
     * deplacement �l�mentaire vertical du visage. Par d�faut 5 pixels.
     */
    private int dy = DEPLACEMENT_DEFAUT;

    /**
     * indique l'�tat du visage (impassible ou souriant). Si true le visage est
     * impassible, si false le visage est souriant.
     */
    private boolean impassible = true;

    // ---------------------------------------------------------
    // Les constructeurs de la classe VisageRond
    // ---------------------------------------------------------
    /**
     * Constructeur avec valeurs par d�faut. Cr�e un visage de taille 50x50 dont
     * le coin sup�rieur gauche du rectangle englobant est situ� au centre de la
     * z�ne de dessin. Ce visage est �galement dot� d'un d�placement �l�mentaire
     * horizontal et vertical de +15 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se d�place
     */
    public Visage(Dessin d) {
        this(d, d.getLargeur() / 2, d.getHauteur() / 2, LARGEUR_DEFAUT, HAUTEUR_DEFAUT);
    }

    /**
     * Constructeur avec positionnement du visage. Cr�e un visage de taille
     * 50x50 mais dont la position du coin sup�rieur gauche du rectangle
     * englobant est fix�e � la cr�ation. Ce visage est dot� d'un d�placement
     * �l�mentaire horizontal et vertical de +15 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se d�place
     * @param xg abscisse du coin sup�rieur gauche du rectangle englobant.
     * @param yg ordonn�e du coin sup�rieur gauche du rectangle englobant.
     */
    public Visage(Dessin d, int xg, int yg) {
        this(d, xg, yg, LARGEUR_DEFAUT, HAUTEUR_DEFAUT);

    }

    /**
     * Constructeur avec positionnement du visage et d�finition de sa taille.
     * Cr�e un visage dont les diemensions et la position du coin sup�rieur
     * gauche du rectangle englobant sont fix�es � la cr�ation. Ce visage est
     * dot� d'un d�placement �l�mentaire horizontal et vertical de +5 pixels.
     *
     * @param d la zone de dessin dans laquelle le visage rond se d�place
     * @param xg abscisse du coin sup�rieur gauche du rectangle englobant.
     * @param yg abscisse du coin sup�rieur gauche du rectangle englobant.
     * @param larg largeur du visage. La largeur doit �tre sup�rieure �
     * LARGEUR_MIN
     * @param haut hauteur du visage. La hauteur doit �tre sup�rieure �
     * HAUTEUR_MIN
     *
     * @see VisageRond#LARGEUR_DEFAUT
     * @see VisageRond#HAUTEUR_DEFAUT
     */
    public Visage(Dessin d, int xg, int yg, int larg, int haut) {
        this.d = d;
        this.xhg = xg;
        this.yhg = yg;
        this.largeur = Math.max(larg, LARGEUR_MIN);
        this.hauteur = Math.max(haut, HAUTEUR_MIN);
    }

    /**
     * Donne la valeur du d�placement �l�mentaire horizontal.
     *
     * @return valeur de dx, d�placement �l�mentaire horizontal.
     */
    public int getDx() {
        return dx;
    }

    /**
     * Fixe d�placement �l�mentaire horizontal.
     *
     * @param v Valeur � affecter � dx, d�placement �l�mentaire horizontal.
     */
    public void setDx(int v) {
        this.dx = v;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int v) {
        this.dy = v;
    }

    /**
     * Inverse sens du d�placement horizontal.
     */
    public void inverserDx() {
        dx = -dx;
        this.changeExpression();
    }

    /**
     * Inverse sens du d�placement vertical.
     */
    public void inverserDy() {
        dy = -dy;
        this.changeExpression();
    }

    /**
     * Inverse sens des d�placements horizontal et vertical.
     */
    public void inverserDxEtDy() {
        dx = -dx;
        dy = -dy;
        this.changeExpression();
    }

    /**
     * change l'expression du visage. Si il �tait impassible, il devient
     * souriant, si il �tait souriant il devient impassible.
     */
    public void changeExpression() {
        impassible = !impassible;
    }

    /**
     * Fait effectuer au visage un d�placement �lementaire. La position du coin
     * sup�rieur gauche du visage est modifi�e en lui ajoutant le d�placement
     * �l�mentaire d�fini par dx et dy.
     */
    public void deplacerSansRebond() {
        xhg += dx;
        yhg += dy;
    }

    /**
     * Fait effectuer au visage un d�placement �lementaire. La position du coin
     * sup�rieur gauche du visage est modifi�e en lui ajoutant le d�placement
     * �l�mentaire d�fini par dx et dy. Si le visage d�passe de l'un des bords
     * de la zone de dessin il inverse sa direction de d�placement.
     */
	public void deplacer() {
        if (bordGaucheAtteint() || bordDroitAtteint()) {
            inverserDx();
        }
        if (bordHautAtteint() || bordBasAtteint()) {
            inverserDy();
        }
        deplacerSansRebond();
    }

    /**
     * Evalue si le visage atteint le bord gauche de la z�ne de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le cot� gauche de la z�ne de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordGaucheAtteint() {
        return (xhg < 0);
    }

    /**
     * Evalue si le visage atteint le bord droit de la z�ne de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le cot� droit de la z�ne de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordDroitAtteint() {
        return ((xhg + largeur) > d.getLargeur());
    }

    /**
     * Evalue si le visage atteint le bord haut de la z�ne de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le cot� haut de la z�ne de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordHautAtteint() {
        return (yhg < 0);
    }

    /**
     * Evalue si le visage atteint le bord bas de la z�ne de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * le cot� bas de la z�ne de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordBasAtteint() {
        return ((yhg + hauteur) >= d.getHauteur());
    }

    /**
     * Evalue si le visage atteint l'un des bords de la z�ne de dessin.
     *
     * @return <code>true</code> si le rectangle englobant le visage intersecte
     * l'un des cot�s de la z�ne de dessin, <code>
     *         false</code> sinon.
     */
    public boolean bordAtteint() {
        return bordDroitAtteint() || bordGaucheAtteint() || bordHautAtteint()
                || bordBasAtteint();
    }

    /**
     * affiche le visage.
     *
     * @param g le contexte graphique de la z�ne de dessin en charge de
     * l'affichage.
     *
     * @see java.awt.Graphics
     * @see Dessinable
     */
    public void dessiner(Graphics g) {
        // dessiner le contour du visage
        g.drawOval(xhg, yhg, largeur, hauteur);

        // dessiner la bouche
        if (impassible) {
            g.drawLine(xhg + largeur / 4, yhg + (2 * hauteur) / 3,
                    xhg + (3 * largeur) / 4, yhg + (2 * hauteur) / 3);
        } else {
            g.drawArc(xhg + largeur / 4, yhg + (2 * hauteur) / 3,
                    largeur / 2, hauteur / 5, -45, -90);
        }

        // dessiner les yeux
        int largeurOeil = largeur / 5;
        int hauteurOeil = hauteur / 5;
        g.drawOval(xhg + largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);
        g.drawOval(xhg + 3 * largeurOeil, yhg + hauteurOeil, largeurOeil,
                hauteurOeil);

    }
}
