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
public class Echiquier {
    private Piece[][] tableau_de_jeu;
    private Piece[] blancs_captures; //size of 16, max number of pieces
    private Piece[] noirs_captures;
    
    public Echiquier() {
        blancs_captures = new Piece[16];
        noirs_captures = new Piece[16];
        tableau_de_jeu = new Piece[8][8];
        for (int i=0; i<8; i++) {
            posePiece(new Pion(false, i, 1, this));
            posePiece(new Pion(true, i, 6, this));
        }
        
        posePiece(new Tour(false, 0, 0, this));
        posePiece(new Cavalier(false, 1, 0, this));
        posePiece(new Fou(false, 2, 0, this));
        posePiece(new Dame(false, 3, 0, this));
        posePiece(new Roi(false, 4, 0, this));
        posePiece(new Fou(false, 5, 0, this));
        posePiece(new Cavalier(false, 6, 0, this));
        posePiece(new Tour(false, 7, 0, this));
        
        posePiece(new Tour(true, 0, 7, this));
        posePiece(new Cavalier(true, 1, 7, this));
        posePiece(new Fou(true, 2, 7, this));
        posePiece(new Dame(true, 3, 7, this));
        posePiece(new Roi(true, 4, 7, this));
        posePiece(new Fou(true, 5, 7, this));
        posePiece(new Cavalier(true, 6, 7, this));
        posePiece(new Tour(true, 7, 7, this));
        
    }
    public boolean roiNoirCapture() {
        for (int i=0; i<noirs_captures.length; i++) {
            if (noirs_captures[i] instanceof Roi) {
                return true;
            }
        }
        return false;
    }
    public boolean roiBlancCapture() {
        for (int i=0; i<blancs_captures.length; i++) {
            if (blancs_captures[i] instanceof Roi) {
                return true;
            }
        }
        return false;
    }
    
    public boolean caseValide(int colonne, int ligne) {
        return (colonne >= 0 && colonne <8 && ligne >= 0 && ligne <8);
    }
    public Piece examinePiece(int colonne, int ligne) {
        return tableau_de_jeu[colonne][ligne];
    }
    public Piece prendsPiece(int colonne, int ligne) {
        Piece piece = examinePiece(colonne, ligne);
        tableau_de_jeu[colonne][ligne] = null;
        return piece;
    }
    public void removePiece(Piece p) {
        tableau_de_jeu[p.getColonne()][p.getLigne()] = null;
    }
    public final void posePiece(Piece p) {
        tableau_de_jeu[p.getColonne()][p.getLigne()] = p;
    }
    public void capturePiece(int colonne, int ligne) {
        Piece piece = prendsPiece(colonne, ligne);
        Piece[] refArr = (piece.estBlanc()) ? blancs_captures : noirs_captures;
        
        for (int i=0; i<refArr.length; i++) {
            if (!(refArr[i] instanceof Piece)) {
                refArr[i] = piece;
                return;
            }
        }
        throw new IllegalStateException("Captured peices array is Full!");
    }
    public void afficheAscii() {
        System.out.println("");
        System.out.print("Les noirs ont capture:");
        
        for (int n=0; n<blancs_captures.length; n++) {
            Piece piece = blancs_captures[n];
            if (piece instanceof Piece) {
                System.out.print(" " + piece.representationAscii());
            }
        }
        System.out.println("");
        
        
        System.out.println("   a b c d e f g h");
        System.out.println("   ---------------");
        
        for (int n=0; n<8; n++) { //Rangees
            System.out.print((8-n) + "| ");
            for (int i=0; i<8; i++) { //Colonnes
                Piece piece = examinePiece(i, n);
                
                if (piece instanceof Piece) {
                    System.out.print(piece.representationAscii() + " ");
                } else {
                    System.out.print(". ");
                }
                
            }
            System.out.print("|" + (8-n) + "\n");
        }
        System.out.println("   ---------------");
        System.out.println("   a b c d e f g h");
        
        System.out.println("");
        System.out.print("Les Blancs ont capture:");
        
        for (int n=0; n<noirs_captures.length; n++) {
            Piece piece = noirs_captures[n];
            if (piece instanceof Piece) {
                System.out.print(" " + piece.representationAscii());
            }
        }
        System.out.println("");
    }
    public void afficheUnicode() {
        System.out.println("");
        System.out.print("Les noirs ont capture:");
        
        for (int n=0; n<blancs_captures.length; n++) {
            Piece piece = blancs_captures[n];
            if (piece instanceof Piece) {
                System.out.print(" " + piece.representationUnicode());
            }
        }
        System.out.println("");
        
        
        System.out.println("   a    b    c    d    e    f    g    h");
        System.out.println(" ┌───┬───┬───┬───┬───┬───┬───┬───┐");
        for (int n=0; n<8; n++) { //Rangees
            System.out.print(8-n);
            for (int i=0; i<8; i++) { //Colonnes
                Piece piece = examinePiece(i, n);
                
                if (piece instanceof Piece) {
                    System.out.print("│ " + piece.representationUnicode() + " ");
                } else {
                    System.out.print("│   ");
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
        System.out.print("Les Blancs ont capture:");
        
        for (int n=0; n<noirs_captures.length; n++) {
            Piece piece = noirs_captures[n];
            if (piece instanceof Piece) {
                System.out.print(" " + piece.representationUnicode());
            }
        }
        System.out.println("");
        
    }
}
