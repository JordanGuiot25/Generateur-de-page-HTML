import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class LectureDATA 
{
    private TypeLigne ligne;
    private StructureHTML structure;

    public LectureDATA()
    {
        this.ligne = new TypeLigne();
        this.structure = new StructureHTML();

        this.Lecture();
    }
    //balise  = curLine.substring(0,3);
    public void Lecture()
    {
        PrintWriter pw;
        
        String nameFile     ;
        String curLine      ;
        String oldLine = "" ;
        String fga = ""     ;
        String header = ""  ;
        String sommaire = "";
        String html = ""    ;
        String footer = ""  ;

        int numFiche = 0;

        try
        {
            Scanner sc1 = new Scanner ( new FileInputStream ( "../SOURCE/table_ordonancement.data" ) );

            while ( sc1.hasNextLine() )
            {
                nameFile = sc1.nextLine();
                try
                {
                    Scanner sc2 = new Scanner ( new FileInputStream ( "../SOURCE/" + nameFile)); // lis le fichier de la ligne en cours

                    while (sc2.hasNextLine())
                    {
                        curLine = sc2.nextLine(); // stock la ligne du fichier lu precedemment

                        if (!curLine.isEmpty())
                            switch ( curLine.substring(0,3) ) 
                            {
                                case "FGA" -> fga = curLine.substring(4); // Texte correspondant au prefixe des noms de fichier.
                                case "CTI" -> ligne.setSommaireCTI(curLine, fga + "_" + String.format("%02d", numFiche));
                                case "CT1" -> ligne.setSommaireCT1(curLine, fga + "_" + String.format("%02d", numFiche));
                                case "CT2" -> ligne.setSommaireCT2(curLine, fga + "_" + String.format("%02d", numFiche));
                            }
                    }
                    numFiche ++;
                    sc2.close();
                } catch (Exception e){ e.printStackTrace(); }
            }
            sc1.close();
        } catch (Exception e){ e.printStackTrace(); }

        sommaire = "\t\t<nav>\n" + ligne.getSommaire() + "\n\t\t</nav>";
        numFiche = 0;

        try
        {
            Scanner sc1 = new Scanner ( new FileInputStream ( "../SOURCE/table_ordonancement.data" ) );

            while ( sc1.hasNextLine() )
            {
                nameFile = sc1.nextLine(); // stock le nom de la ligne

                try
                {
                    Scanner sc2 = new Scanner ( new FileInputStream ( "../SOURCE/" + nameFile)); // lis le fichier de la ligne en cours

                    while (sc2.hasNextLine())
                    {
                        curLine = sc2.nextLine(); // stock la ligne du fichier lu precedemment

                        if (!curLine.isEmpty())
                        {   
                            curLine = curLine.replace("#CRLF#", "<br />"); // Remplace dans la ligne les #CRLF# par des balises <br />
                            curLine = curLine.replace("#/G#"  , "<b>"   ); // Remplace dans la ligne les #/G# par des balises <b>
                            curLine = curLine.replace(" #\\G#" , "</b>"  );// Remplace dans la ligne les #\\G# par des balises </b>
                            curLine = curLine.replace(" #/I#" , "<i>"   ); // Remplace dans la ligne les #/I# par des balises <i>
                            curLine = curLine.replace("#\\I#"  , "</i>"  );// Remplace dans la ligne les #\\I# par des balises </i>
                            
                            switch ( curLine.substring(0,3) ) 
                            {
                                case "TGA"  -> html += ligne.getTGA(curLine);
                                case "IGA"  -> html += ligne.getIGA(curLine);
                                case "TEN"  -> header = "\t\t<header>" + curLine.substring(4) + "</header>";
                                case "TPI"  -> footer = "\t\t<footer>" + curLine.substring(4) + "</footer>";

                                case "PGA"  -> html += ligne.getPGA(curLine, oldLine);
                                
                                case "CTI"  -> html += ligne.getCTI(curLine, fga + "_" + String.format("%02d", numFiche));
                                case "CT1"  -> html += ligne.getCT1(curLine, fga + "_" + String.format("%02d", numFiche));
                                case "CT2"  -> html += ligne.getCT2(curLine, fga + "_" + String.format("%02d", numFiche));
                                case "CIM"  -> html += ligne.getCIM(curLine);
                                case "CAN"  -> html += ligne.getCAN(curLine);

                                case "CPS"  -> html += ligne.getCPS(curLine, oldLine);
                                case "CPC"  -> html += ligne.getCPC(curLine, oldLine);
                                case "CCO"  -> html += ligne.getCCO(curLine, oldLine);
                                case "C/C"  -> html += ligne.getCSlashC(curLine);
                                case "C\\C" -> html += ligne.getCaSlashC(curLine);
                                case "CLO"  -> html += ligne.getCLO(curLine, oldLine);
                            }
                        }
                        else
                        {
                            html += ligne.dernierBalise + "\t";
                            ligne.dernierBalise = "";
                        }

                        oldLine = curLine;

                    }
                    if (numFiche == 0) pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + fga + ".html"), "UTF8"));
                    else pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + fga + "_" + String.format("%02d", numFiche) + ".html"), "UTF8"));
                    pw.println(structure.html());
                    
                    pw.println(header);
                    pw.println(sommaire);
                    if ( !html.isEmpty() )
                    {
                        pw.println("\t\t<article>");
                        pw.println(html);
                        pw.println("\t\t</article>");
                    }
                    pw.println(footer);

                    pw.println(structure.html());

                    numFiche ++;
                    html = "";
                    pw.close();
                    sc2.close();
                } catch (Exception e){ e.printStackTrace(); }
            }
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../WEB/" + fga + "_00.html"), "UTF8"));

            pw.println(structure.html());
            pw.println(header);
            pw.println(sommaire);
            pw.println(footer);
            pw.println(structure.html());
            pw.close();

            sc1.close();
        } catch (Exception e){ e.printStackTrace(); }
    }
}
