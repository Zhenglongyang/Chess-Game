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
public class Cavalier extends Piece {

    public Cavalier(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
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
        int[] diffRow =     new int[] {-2,-2,-1,-1, 1, 1, 2, 2};
        int[] diffColumn =  new int[] {-1, 1,-2, 2,-2, 2,-1, 1};
        
        for (int i=0; i<8; i++) { //Pour les 4 possibilites
            int newColumn = getColonne() + diffColumn[i];
            int newRow = getLigne()+ diffRow[i];
            
            if (super.deplacementValide(newColumn, newRow)) { //Si valide
                if (getEchiquier().examinePiece(newColumn, newRow) != null) { //Si il ya une piece ennemie, set a 2
                    tableau_de_deplacements[newColumn][newRow] = 2;
                } else {
                    tableau_de_deplacements[newColumn][newRow] = 1; //Sinon set a 1
                }
            }
        }
        return tableau_de_deplacements;
    }

    @Override
    public String representationAscii() {
        return (estBlanc()) ? "C" : "c";
    }

    @Override
    public String representationUnicode() {
        return (estBlanc()) ? "\u2658" : "\u265E";
    }

    @Override
    public boolean isMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
