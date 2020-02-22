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
public class Roi extends Piece {
    private boolean isMoved = false;

    public Roi(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
        super(est_blanc, colonne, ligne, echiquier);
    }
    @Override
    public void deplace(int nouvelle_colonne, int nouvelle_ligne) { //Castling
        if (deplacementPossibles()[nouvelle_colonne][nouvelle_ligne] == 3) {
            if (getColonne() > nouvelle_colonne) { //Si roi a droite de la tour
                Piece roi = getEchiquier().prendsPiece(getColonne(), getLigne());
                Piece tour = getEchiquier().prendsPiece(nouvelle_colonne-2, nouvelle_ligne);
                
                tour.setColonne(getColonne()-1);
                this.setColonne(nouvelle_colonne);
                getEchiquier().posePiece(roi);
                getEchiquier().posePiece(tour);
            } else { //Si roi a gauche de la tour
                Piece roi = getEchiquier().prendsPiece(getColonne(), getLigne());
                Piece tour = getEchiquier().prendsPiece(nouvelle_colonne+1, nouvelle_ligne);
                tour.setColonne(getColonne()+1);
                this.setColonne(nouvelle_colonne);
                getEchiquier().posePiece(roi);
                getEchiquier().posePiece(tour);
            }
        } else {
            super.deplace(nouvelle_colonne, nouvelle_ligne);
        }
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
        int[] diffRow =     new int[] {1, 1, 1, 0, 0,-1,-1,-1};
        int[] diffColumn =  new int[] {1, 0,-1, 1,-1, 1, 0,-1};
        
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
        
        if (!isMoved) {
            if (getEchiquier().examinePiece(5, getLigne()) == null && getEchiquier().examinePiece(6, getLigne()) == null && getEchiquier().examinePiece(7, getLigne()) instanceof Tour) {
                if (getEchiquier().examinePiece(7, getLigne()).estBlanc() == estBlanc() && !getEchiquier().examinePiece(7, getLigne()).isMoved()) {
                    tableau_de_deplacements[6][getLigne()] = 3;
                }
            }
            if (getEchiquier().examinePiece(3, getLigne()) == null && getEchiquier().examinePiece(2, getLigne()) == null && getEchiquier().examinePiece(1, getLigne()) == null && getEchiquier().examinePiece(0, getLigne()) instanceof Tour) {
                if (getEchiquier().examinePiece(0, getLigne()).estBlanc() == estBlanc() && !getEchiquier().examinePiece(0, getLigne()).isMoved()) {
                    tableau_de_deplacements[1][getLigne()] = 3;
                }
            }
            
        }
        
        return tableau_de_deplacements;
        //for (int x=getColonne(); x<8; x++) {
            
        //}
    }

    @Override
    public String representationAscii() {
        return (estBlanc()) ? "R" : "r";
    }

    @Override
    public String representationUnicode() {
        return (estBlanc()) ? "\u2654" : "\u265A";
    }

    @Override
    public boolean isMoved() {
        return isMoved;
    }
    
}
