/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package chess;
//Par Bowen Peng et Zhenglong
/**
 *
 * @author bowen
 */
public class Dame extends Piece {

    public Dame(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
        super(est_blanc, colonne, ligne, echiquier);
    }
    @Override
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
        boolean condition1 = super.deplacementValide(nouvelle_colonne, nouvelle_ligne);
        boolean condition2 = deplacementPossibles()[nouvelle_colonne][nouvelle_ligne] > 0;
        return condition1 && condition2;
    }
    @Override
    public int[][] deplacementPossibles() {
        
        int[][] tableau_de_deplacements = new int[8][8];
        
        for (int x=getColonne()+1; x<8; x++) { //Pour les 8 lignes, vers la droite
            int y = getLigne();
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int x=getColonne()-1; x>=0; x--) { //vers la gauche
            int y = getLigne();
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int y=getLigne()+1; y<8; y++) { //vers le bas
            int x = getColonne();
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int y=getLigne()-1; y>=0; y--) { //vers le haut
            int x = getColonne();
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int x=getColonne()+1, y=getLigne()+1; x<8 && y<8; x++, y++) { //vers en bas a droite
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int x=getColonne()+1, y=getLigne()-1; x<8 && y>=0; x++, y--) { //vers en haut a droite
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int x=getColonne()-1, y=getLigne()+1; x>=0 && y<8; x--, y++) { //vers en bas a gauche
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        for (int x=getColonne()-1, y=getLigne()-1; x>=0 && y>=0; x--, y--) { //vers en haut a gauche
            if (subDep(tableau_de_deplacements, x, y)) break;
        }
        return tableau_de_deplacements;
    }
    
    private boolean subDep(int[][] tableau_de_deplacements, int x, int y) { //Fonction repetitive pour deplacementPossibles()
        if (super.deplacementValide(x, y)) { //Si valide
            if (getEchiquier().examinePiece(x, y) != null) { //Si il ya une piece ennemie, set a 2
                tableau_de_deplacements[x][y] = 2;
                return true; //On est rendu a une piece qui bloque, retourner true pour break;
            } else {
                tableau_de_deplacements[x][y] = 1; //Sinon set a 1
                return false;
            }
        } else {
            return true; //On est rendu a une case qui bloque, retourner true pour break;
        }
    }

    @Override
    public String representationAscii() {
        return (estBlanc()) ? "D" : "d";
    }

    @Override
    public String representationUnicode() {
        return (estBlanc()) ? "\u2655" : "\u265B";
    }

    @Override
    public boolean isMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
