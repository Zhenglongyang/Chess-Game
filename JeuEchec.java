/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package chess;
//Par Bowen Peng et Zhenglong

import java.util.Scanner;

/**
 *
 * @author bowen
 */
public class JeuEchec {

    /**
     * @param args the command line arguments
     */
        static boolean isUnicode = false;
        static boolean isBlanc = true;
    public static void main(String[] args) {
        
        
        if (args.length > 0) {
            if ("unicode".equals(args[0])) {
                isUnicode = true;
            }
        }
        Scanner sc = new Scanner(System.in);
        Echiquier echiquier = new Echiquier();
        
        boolean gameRunning = true;
        
        while (gameRunning) {
            
            boolean isNextTurn = false;
            while (!isNextTurn) {
                System.out.print("Joueur " + ((isBlanc) ? "Blanc" : "Noir") + "? ");
                String str = sc.nextLine();
                isNextTurn = applyLogic(interpret(str), echiquier, isBlanc);
                System.out.println("");
            }
            isBlanc = !isBlanc;
            
            boolean roiBlancCapture = echiquier.roiBlancCapture();
            boolean roiNoirCapture = echiquier.roiNoirCapture();
            
            if (roiBlancCapture) {
                gameRunning = false;
                System.out.println("Victoire du joueur Noir.");
            } else if (roiNoirCapture) {
                gameRunning = false;
                System.out.println("Victoire du joueur Blanc.");
            }
            
        }
    }
    
    public static boolean applyLogic(int[] interpret, Echiquier echiquier, boolean isBlanc) {
        switch (interpret.length) {
            case 4:
                int x0 = interpret[0];
                int y0 = interpret[1];
                int x1 = interpret[2];
                int y1 = interpret[3];
                if (echiquier.examinePiece(x0, y0) != null) {
                    Piece piece = echiquier.examinePiece(x0, y0);
                    if (piece.estBlanc() == isBlanc) {
                        if (piece.deplacementValide(x1, y1)) {
                            piece.deplace(x1, y1);
                            return true;
                        } else {
                            System.out.println("Ce n'est pas un déplacement valide.");
                            return false;
                        }
                    } else {
                        System.out.println("Vous ne pouvez pas bouger une pièce de l'autre joueur!");
                        return false;
                    }
                } else {
                    System.out.println("La case ne contient aucune pièce!");
                    return false;
                }
            case 2:
                if (echiquier.examinePiece(interpret[0], interpret[1]) != null) {
                    Piece piece = echiquier.examinePiece(interpret[0], interpret[1]);
                    if (!isUnicode) {
                        piece.afficheDeplacementsPossiblesAscii();
                    } else {
                        piece.afficheDeplacementsPossiblesUnicode();
                    }
                    return false;
                } else {
                    System.out.println("La case ne contient aucune pièce!");
                    return false;
                }
            case 1:
                if (!isUnicode) {
                    echiquier.afficheAscii();
                } else {
                    echiquier.afficheUnicode();
                }
                return false;
            case 0:
                System.out.println("Ce n'est pas une commande valide.");
                System.err.println("Un example de commande valide est: c3 c4");
                return false;
            default:
                throw new IllegalStateException("ERROR");
        }
        
        
        
    }
    
    public static int[] interpret(String string) {
        if (string.length() == 5) {
            char c1 = string.charAt(0);
            char c2 = string.charAt(1);
            char c3 = string.charAt(2);
            char c4 = string.charAt(3);
            char c5 = string.charAt(4);
            if (isValidLetter(c1) && isValidDigit(c2) && c3 == ' ' && isValidLetter(c4) && isValidDigit(c5)) {
                int col1 = letterToColumn(c1);
                int row1 = digitToRow(c2);
                int col2 = letterToColumn(c4);
                int row2 = digitToRow(c5);
                return new int[] {col1, row1, col2, row2};
            } else {
                return new int[0];
            }
        } else if (string.length() == 2) {
            char c1 = string.charAt(0);
            char c2 = string.charAt(1);
            if (isValidLetter(c1) && isValidDigit(c2)) {
                int col1 = letterToColumn(c1);
                int row1 = digitToRow(c2);
                return new int[] {col1, row1};
            } else {
                return new int[0];
            }
        } else if (string.length() == 0) {
            return new int[1];
        } else {
            return new int[0];
        }
    }
    
    public static boolean isValidLetter(char c) {
        return (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h');
    }
    
    public static boolean isValidDigit(char c) {
        String s = Character.toString(c);
        int d = Integer.parseInt(s);
        return (d <= 8 && d >= 1);
    }
    
    public static int digitToRow(char c) {
        return 8 - (c - 48);
    }
    public static int letterToColumn(char c) {
        return (c - 96) - 1;
    }
    
    
}
