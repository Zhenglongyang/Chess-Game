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
public abstract class Piece {
    private boolean est_blanc, est_capture;
    private int colonne, ligne;
    
    private Echiquier echiquier;
    
    public boolean estBlanc() {
        return est_blanc;
    };
    public boolean estNoir() {
        return !est_blanc;
    };
    public boolean estCapture() {
        return est_capture;
    };
    public int getLigne() {
        return ligne;
    };
    public int getColonne(){
        return colonne;
    };
    
    protected void setLigne(int ligne) {
        this.ligne = ligne;
    }
    
    protected void setColonne(int colonne) {
        this.colonne = colonne;
    }
    
    protected Piece(boolean est_blanc, int colonne, int ligne, Echiquier echiquier) {
        this.est_blanc = est_blanc;
        this.colonne = colonne;
        this.ligne = ligne;
        this.echiquier = echiquier;
        this.est_capture = false;
    };
    
    public void meSuisFaitCapture() {
        est_capture = true;
    }
    
    public boolean deplacementValide(int nouvelle_colonne, int nouvelle_ligne) {
        if (echiquier.caseValide(nouvelle_colonne, nouvelle_ligne) && !est_capture) {
            Piece piece = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);
            if (piece != null) {
                if (piece.est_blanc == est_blanc) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        
        return false;
    }
    public void deplace(int nouvelle_colonne, int nouvelle_ligne) {
        Piece piece = echiquier.examinePiece(nouvelle_colonne, nouvelle_ligne);
        if (piece != null) {
            echiquier.capturePiece(nouvelle_colonne, nouvelle_ligne);
        }
        echiquier.prendsPiece(colonne, ligne);
        this.colonne = nouvelle_colonne;
        this.ligne = nouvelle_ligne;
        echiquier.posePiece(this);
    }
    @Override
    public String toString() {
        String colour = (est_blanc) ? "White" : "Black";
        String[] colNames = {"a","b","c","d","e","f","g","h"};
        String colName = colNames[colonne];
        String rowName = ligne + 1 + "";
        return "Colour: " + colour + " Captured: " + est_capture + " Position: " + colName + rowName;
    }
    
    public Echiquier getEchiquier() {
        return echiquier;
    }
    public abstract int[][] deplacementPossibles();
    public abstract String representationAscii();
    public abstract String representationUnicode();
    public abstract boolean isMoved();
    
    public void afficheDeplacementsPossiblesAscii() {
        
        int[][] deplacements = deplacementPossibles();
        System.out.println("");
        System.out.println("Déplacements possibles pour: " + this.getClass().getSimpleName() + " " + ((estBlanc()) ? "Blanc" : "Noir"));
        System.out.println("   a b c d e f g h");
        System.out.println("   ---------------");
        
        for (int n=0; n<8; n++) { //Rangees
            System.out.print((8-n) + "| ");
            for (int i=0; i<8; i++) { //Colonnes
                int valide = deplacements[i][n];
                
                if (valide == 1 || valide > 2) {
                    System.out.print("x ");
                } else if (valide == 2) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
                
            }
            System.out.print("|" + (8-n) + "\n");
        }
        System.out.println("   ---------------");
        System.out.println("   a b c d e f g h");
        System.out.println("");
    }
    public void afficheDeplacementsPossiblesUnicode() {
        int[][] deplacements = deplacementPossibles();
        System.out.println("");
        
        
        System.out.println("   a    b    c    d    e    f    g    h");
        System.out.println(" ┌───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int n=0; n<8; n++) { //Rangees
            System.out.print(8-n);
            for (int i=0; i<8; i++) { //Colonnes
                int valide = deplacements[i][n];
                
                if (valide == 1 || valide > 2) {
                    System.out.print("│ ｘ ");
                } else if (valide == 2) {
                    System.out.print("│ Ｘ ");
                } else {
                    System.out.print("│   ");
                }
                
            }
            System.out.print("│" + (8-n) + "\n");
            if (n < 7) {
                System.out.println(" ├───┼───┼───┼───┼───┼───┼───┼───┤");
            }
        }
        System.out.println(" └───┴───┴───┴───┴───┴───┴───┴───┘");
        System.out.println("   a    b    c    d    e    f    g    h");
        
        System.out.println("");
    }
}
