import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class LectureDATA 
{
    private String rep_Entre ;      // Stock le repertoire d'entré
    private String rep_Sortie;      // Stock le repertoire de sortie

    private TypeLigne ligne;
    private StructureHTML structure;

    public LectureDATA(String rep_Entre, String rep_Sortie, String rep_CSS)
    {
        this.rep_Entre  = rep_Entre ;
        this.rep_Sortie = rep_Sortie;

        this.ligne = new TypeLigne(rep_Sortie);             // Creer un nouveau TypeLigne avec en parametre le repertoire de sortie
        this.structure = new StructureHTML(rep_CSS);        // Creer un nouveau StructureHTML avec en parametre le fichier CCS

        this.Lecture();
    }

    public void Lecture()        // Gere la lecture et l'ecriture pour chaque fichier
    {
        PrintWriter pw;
        
        String nameFile     ;     // Stock le nom des fichiers de table_ordonancement.data
        String curLine      ;     // Stock la ligne courante
        String oldLine = "" ;     // Stock la ligne precedante
        String fga = ""     ;     // Stock le nom de prefixe des fichiers
        String header = ""  ;     // Stock la division "entete"
        String sommaire = "";     // Stock la division "navigation"
        String html = ""    ;     // Stock la division "article"
        String footer = ""  ;     // Stock la division "pied de page"

        int numFiche = 0;         // Stock le numero du fichier en cours


        /*********************/
        /* Creer le sommaire */
        /*********************/
        try
        {
            Scanner sc1 = new Scanner ( new FileInputStream ( "../SOURCE/" + this.rep_Entre + "/table_ordonancement.data" ) );     // lis le fichier table_ordonancement.data du repertoire saisie en parametre

            while ( sc1.hasNextLine() )     // Continue tant qu'il y a des ligne
            {
                nameFile = sc1.nextLine();    // affecte le contenue de la ligne en cours
                try
                {
                    Scanner sc2 = new Scanner ( new FileInputStream ( "../SOURCE/" + this.rep_Entre + "/" + nameFile)); // lis le fichier du nom correspondant à la ligne en cours

                    while (sc2.hasNextLine())     // Continue tant qu'il y a des ligne
                    {
                        curLine = sc2.nextLine();    // affecte le contenue de la ligne en cours

                        if (!curLine.isEmpty())      // Verifie que la ligne n'est pas vide
                            switch ( curLine.substring(0,3) )     // recupere le type de ligne
                            {
                                case "FGA" -> fga = curLine.substring(4);      // recupere la donnée de FGA
                                case "CTI" -> ligne.setSommaireCTI(curLine, fga + "_" + String.format("%02d", numFiche));    // ajoute les noms de chapitre  au sommaire
                                case "CT1" -> ligne.setSommaireCT1(curLine, fga + "_" + String.format("%02d", numFiche));    // ajoute les titre de niveau 1 au sommaire
                                case "CT2" -> ligne.setSommaireCT2(curLine, fga + "_" + String.format("%02d", numFiche));    // ajoute les titre de niveau 2 au sommaire
                            }
                    }
                    numFiche ++;    // passe au fichier suivant
                    sc2.close();    // ferme la lecture du fichier
                } catch (Exception e){ e.printStackTrace(); }
            }
            sc1.close();     // ferme la lecture du fichier
        } catch (Exception e){ e.printStackTrace(); }

        /* Creer la navigation */
        sommaire  = "\t\t<nav>\n";
        sommaire += "\t\t\t<a href=\"../" + this.rep_Sortie + "/" + fga + ".html\"> Page de garde </a></br>\n";
        sommaire += "\t\t\t<a href=\"../" + this.rep_Sortie + "/" + fga + "_00.html\"> Sommaire </a>\n";
        sommaire += ligne.getSommaire() + "\n\t\t</nav>";
        

        numFiche = 0;  // permet de relire les fichier depuis le debut


        /*********************/
        /*  Creer le reste   */
        /*********************/
        try
        {
            Scanner sc1 = new Scanner ( new FileInputStream ( "../SOURCE/" + this.rep_Entre + "/table_ordonancement.data" ) );     // lis le fichier table_ordonancement.data du repertoire saisie en parametre

            while ( sc1.hasNextLine() )     // Continue tant qu'il y a des ligne
            {
                nameFile = sc1.nextLine();    // affecte le contenue de la ligne en cours

                try
                {
                    Scanner sc2 = new Scanner ( new FileInputStream ( "../SOURCE/" + this.rep_Entre + "/" + nameFile)); // lis le fichier du nom correspondant à la ligne en cours

                    while (sc2.hasNextLine())     // Continue tant qu'il y a des ligne
                    {
                        curLine = sc2.nextLine();    // affecte le contenue de la ligne en cours

                        if (!curLine.isEmpty())      // Verifie que la ligne n'est pas vide
                        {   
                            curLine = curLine.replace("#CRLF#" , "<br />");     // Remplace dans la ligne tous les #CRLF# par des balises <br />
                            curLine = curLine.replace("#/G#"   , "<b>"   );     // Remplace dans la ligne tous les #/G# par des balises <b>
                            curLine = curLine.replace(" #\\G#" , "</b>"  );     // Remplace dans la ligne tous les #\G# par des balises </b>
                            curLine = curLine.replace(" #/I#"  , "<i>"   );     // Remplace dans la ligne tous les #/I# par des balises <i>
                            curLine = curLine.replace("#\\I#"  , "</i>"  );     // Remplace dans la ligne tous les #\I# par des balises </i>
                            
                            switch ( curLine.substring(0,3) )      // recupere le type de ligne
                            {
                                case "TGA"  -> html += ligne.getTGA(curLine);                                                 // recupere le titre principal du document avec les balises html
                                case "IGA"  -> html += ligne.getIGA(curLine);                                                 // recupere l'image de la pages de garde avec les balises html
                                case "TEN"  -> header = "\t\t<header>" + curLine.substring(4) + "</header>";                  // recupere l'en-tete avec les balises html
                                case "TPI"  -> footer = "\t\t<footer>" + curLine.substring(4) + "</footer>";                  // recupere le pied de page avec les balises html

                                case "PGA"  -> html += ligne.getPGA(curLine, oldLine);                                        // recupere le paragraphe de page de garde avec les balises html
                                
                                case "CTI"  -> html += ligne.getCTI(curLine, fga + "_" + String.format("%02d", numFiche));    // recupere le nom du chapitre avec les balises html
                                case "CT1"  -> html += ligne.getCT1(curLine, fga + "_" + String.format("%02d", numFiche));    // recupere le titre de niveau 1 avec les balises html
                                case "CT2"  -> html += ligne.getCT2(curLine, fga + "_" + String.format("%02d", numFiche));    // recupere le titre de niveau 2 avec les balises html
                                case "CIM"  -> html += ligne.getCIM(curLine);                                                 // recupere l'image avec les balises html
                                case "CAN"  -> html += ligne.getCAN(curLine);                                                 // recupere l'animation avec les balises html

                                case "CPS"  -> html += ligne.getCPS(curLine, oldLine);                                        // recupere le paragraphe avec les balises html
                                case "CPC"  -> html += ligne.getCPC(curLine, oldLine);                                        // recupere le paragraphe encadré avec les balises html
                                case "CCO"  -> html += ligne.getCCO(curLine, oldLine);                                        // recupere le paragraphe de type code avec les balises html
                                case "C/C"  -> html += ligne.getCSlashC(curLine);                                             // ouvre une balise html et encadre le contenue
                                case "C\\C" -> html += ligne.getCaSlashC(curLine);                                            // ferme la balise html qui encadre le contenue
                                case "CLO"  -> html += ligne.getCLO(curLine, oldLine);                                        // recupere la liste non ordonnée de niveau 1 avec les balises html
                            }
                        }
                        else
                        {
                            html += ligne.dernierBalise;    // ajoute la balise fermante utilisé dernierement
                            ligne.dernierBalise = "";
                        }

                        oldLine = curLine;    // affect la ligne precedente avec la ligne en cour

                    }
                    if (numFiche == 0) pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + this.rep_Sortie + "/" + fga + ".html"), "UTF8"));                               // creer le fichier de page de garde
                    else pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + this.rep_Sortie + "/"  + fga + "_" + String.format("%02d", numFiche) + ".html"), "UTF8"));    // sinon creer le fichier de chapitre
                    pw.println(structure.html(fga));     // ecrit le head de la page
                    
                    pw.println(header);                  // ecrit le header de la page
                    pw.println(sommaire);                // ecrit le sommaire de la page
                    if ( !html.isEmpty() )               // verifie que html n'est pas vide
                    {
                        pw.println("\t\t<article>");     // ecrit l'ouverture de la balise article
                        pw.println(html);                // ecrit les donnée en fonction des balises de leur type
                        pw.println("\t\t</article>");    // ecrit la fermeture de la balise article
                    }
                    pw.println(footer);                  // ecrit le footer de la page

                    pw.println(structure.html(fga));     // ecrit les balise de fin de la page

                    numFiche ++;    // passe au fichier suivant
                    html = "";      // vide le contenue de html pour le fichier suivant
                    pw.close();     // ferme le fichier d'ecriture
                    sc2.close();    // ferme le fichier de lecture
                } catch (Exception e){ e.printStackTrace(); }
            }
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + this.rep_Sortie + "/" + fga + "_00.html"), "UTF8"));    // creer le fichier de la page sommaire

            pw.println(structure.html(fga));    // ecrit le head de la page
            pw.println(header);                 // ecrit le header de la page
            pw.println(sommaire);               // ecrit le sommaire de la page
            pw.println(footer);                 // ecrit le footer de la page
            pw.println(structure.html(fga));    // ecrit les balise de fin de la page
            pw.close();                         // ferme le fichier d'ecriture

            sc1.close();                        // ferme le fichier de lecture
        } catch (Exception e){ e.printStackTrace(); }
    }
}
