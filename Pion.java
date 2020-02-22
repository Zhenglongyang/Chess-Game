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
public class Pion extends Piece {
    private boolean isMoved = false;
    public Pion(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
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
        
        int[] diffMoveRow, diffMoveColumn, diffCaptureRow, diffCaptureColumn;
        
        if (estBlanc()) {
            
            if (isMoved) {
                
                diffMoveRow =         new int[] {-1};
                diffMoveColumn =      new int[] { 0};
                diffCaptureRow =      new int[] {-1,-1};
                diffCaptureColumn =   new int[] {-1, 1};
                
            } else {
                
                diffMoveRow =         new int[] {-1,-2};
                diffMoveColumn =      new int[] { 0, 0};
                diffCaptureRow =      new int[] {-1,-1};
                diffCaptureColumn =   new int[] {-1, 1};
            }
            
        } else {
            
            if (isMoved) {
                
                diffMoveRow =         new int[] { 1};
                diffMoveColumn =      new int[] { 0};
                diffCaptureRow =      new int[] { 1, 1};
                diffCaptureColumn =   new int[] {-1, 1};
                
            } else {
                
                diffMoveRow =         new int[] { 1, 2};
                diffMoveColumn =      new int[] { 0, 0};
                diffCaptureRow =      new int[] { 1, 1};
                diffCaptureColumn =   new int[] {-1, 1};
            }
        }
        
        for (int i=0; i<diffMoveRow.length; i++) { //Pour les movements
            int newColumn = getColonne() + diffMoveColumn[i];
            int newRow = getLigne()+ diffMoveRow[i];
            
            if (super.deplacementValide(newColumn, newRow) && getEchiquier().examinePiece(newColumn, newRow) == null) { //Si valide et n'est pas occupé
                tableau_de_deplacements[newColumn][newRow] = 1; //set a 1
            }
        }
        
        for (int i=0; i<diffCaptureRow.length; i++) { //Pour les captures
            int newColumn = getColonne() + diffCaptureColumn[i];
            int newRow = getLigne()+ diffCaptureRow[i];
            
            if (super.deplacementValide(newColumn, newRow) && getEchiquier().examinePiece(newColumn, newRow) != null) { //Si valide et est occupé
                tableau_de_deplacements[newColumn][newRow] = 2; //set a 1
            }
        }
        return tableau_de_deplacements;
    }
    @Override
    public void deplace(int nouvelle_colonne, int nouvelle_ligne) {
        super.deplace(nouvelle_colonne, nouvelle_ligne);
        this.isMoved = true;
    }

    @Override
    public String representationAscii() {
        return (estBlanc()) ? "P" : "p";
    }

    @Override
    public String representationUnicode() {
        return (estBlanc()) ? "\u2659" : "\u265F";
    }

    @Override
    public boolean isMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
