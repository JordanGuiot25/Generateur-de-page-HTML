public class TypeLigne 
{
	private String rep_Sortie;

	private String sommaire;
	private int ancre;

	private int niv1;
	private int niv2;
	private int niv3;

	public String dernierBalise;

	public TypeLigne(String rep_Sortie)
	{
		this.rep_Sortie = rep_Sortie;

		this.sommaire = "";
		this.ancre = 0;

		this.niv1 = 1;
		this.niv2 = 1;
		this.niv3 = 1;

		this.dernierBalise = "";
	}

	public String getTGA( String curLine )
	{
		return "\t\t\t<h1>" + curLine.substring(4) + "</h1>\n";    // retourne le titre principal avec les balises html
	}

	public String getPGA( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</p>\n";    // affecte la balise fermante p

			return "\t\t\t<p>\n\t\t\t\t" + curLine.substring(4) + "\n";    // retourne le paragraphe avec les balises html
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n";    // retourne le paragraphe avec les balises html
	}

	public String getIGA( String curLine )
	{
		String[] iga = curLine.substring(4).split(":");    // separe la ligne image en deux a partir du ":"

		return "\t\t\t<img src=\"../images/" + iga[0] + "\" alt=\"" + iga[1] + "\">\n";    // retourne l'image avec les balise html
	}

	public String getCTI( String curLine, String fga )
	{
		ancre ++;    // increment l'ancre pour la redirection

		return "\t\t\t<h1 id=\"" + ancre + "\">" + curLine.substring(4) + "</h1>\n";    // retourne le nom de chapitre avec les balise html
	}

	public String getCT1( String curLine, String fga )
	{
		ancre ++;    // increment l'ancre pour la redirection

		return "\t\t\t<h1 id=\"" + ancre + "\">" + curLine.substring(4) + "</h1>\n";    // retourne le titre de niveau 1 avec les balise html
	}

	public String getCT2( String curLine, String fga )
	{
		ancre ++;    // increment l'ancre pour la redirection

		return "\t\t\t<h2 id=\"" + ancre + "\">" + curLine.substring(4) + "</h2>\n";    // retourne le titre de niveau 2 avec les balise html
	}

	public String getCPS( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</p>\n";    // affecte la balise fermante p

			return "\t\t\t<p>\n\t\t\t\t" + curLine.substring(4) + "\n";    // retourne le paragraphe avec les balises html
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n";    // retourne le paragraphe avec les balises html
	}

	public String getCIM( String curLine )
	{
		String[] iga = curLine.substring(4).split(":");    // separe la ligne image en deux a partir du ":"

		return "\t\t\t<img src=\"../images/" + iga[0] + "\" alt=\"" + iga[1] + "\">\n";    // retourne l'image avec les balise html
	}

	public String getCAN( String curLine)
	{
		String[] can = curLine.substring(4).split(":");    // separe la ligne d'animation en deux a partir du ":"
		String[] img = can[0].split("\\.");                // separe la ligne d'animation en deux a partir du "."

		String anime = "";    // vide le contenue de anime
		

		/************************************************/
		/* Permet la gestion des animation en Bootstrap */
		/************************************************/

		anime += "\t\t\t<div class=\"container\">";
		anime += "\t\t\t\t<div id=\"" + img[0] + "\" class=\"carousel slide\" data-ride=\"carousel\" style=\"width: -webkit-max-content;\">";
		anime += "\t\t\t\t\t<ol class=\"carousel-indicators\">";
		
		/* permet de creer les bulle dans le bas de l'animation */
		/* rend la premiere active */
		for (int i = 0; i < Integer.parseInt(can[1]); i++) 
		{
			if (i == 0)
				anime += "\t\t\t\t\t\t<li data-target=\"#" + img[0] + "\" data-slide-to=\"" + i + "\" class=\"active\"></li>\n";
			else
				anime += "\t\t\t\t\t\t<li data-target=\"#" + img[0] + "\" data-slide-to=\"" + i + "\"></li>\n";
		}
		anime += "\t\t\t\t\t</ol>\n";
		anime += "\t\t\t\t\t<div class=\"carousel-inner\">\n";
		
		/* ajoute les images sur au carousel */
		/* rend la premiere active */
		for (int i = 1; i <= Integer.parseInt(can[1]); i++) 
		{
			if (i == 1) 
			{
				anime += "\t\t\t\t\t\t<div class=\"item active\">\n";
				anime += "\t\t\t\t\t\t\t<img src=\"../animations/" + img[0] + String.format("%02d", i) + "." + img[1] + "\" alt=\"Unloaded image\" style=\"width:400px; height:400px;\">\n";
				anime += "\t\t\t\t\t\t</div>\n";
			}
			else
			{
				anime += "\t\t\t\t\t\t<div class=\"item\">\n";
				anime += "\t\t\t\t\t\t\t<img src=\"../animations/" + img[0] + String.format("%02d", i) + "." + img[1] + "\" alt=\"Unloaded image\" style=\"width:400px; height:400px;\">\n";
				anime += "\t\t\t\t\t\t</div>\n";
			}
		}
		anime += "\t\t\t\t\t</div>\n";
		anime += "\t\t\t\t\t<a class=\"left carousel-control\" href=\"#" + img[0] + "\" data-slide=\"prev\">\n";     /**************************************/
		anime += "\t\t\t\t\t\t<span class=\"glyphicon glyphicon-chevron-left\"></span>\n";                           /* Creer est gère la fleche de gauche */
		anime += "\t\t\t\t\t\t<span class=\"sr-only\">Previous</span>\n";                                            /**************************************/
		anime += "\t\t\t\t\t</a>";
		anime += "\t\t\t\t\t<a class=\"right carousel-control\" href=\"#" + img[0] + "\" data-slide=\"next\">\n";    /**************************************/
		anime += "\t\t\t\t\t\t<span class=\"glyphicon glyphicon-chevron-right\"></span>\n";                          /* Creer est gère la fleche de droite */
		anime += "\t\t\t\t\t\t<span class=\"sr-only\">Next</span>\n";                                                /**************************************/
		anime += "\t\t\t\t\t</a>\n";
		anime += "\t\t\t\t</div>\n";
		anime += "\t\t\t</div>\n";

		return anime;    // retourne l'animation
	}

	public String getCPC( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )                  // Nous permet de rentrer uniquement si la dernière ligne est vide et que celle actuel contient CPC (car on appelle getCPC uniquement si dans la ligne actuelle elle contient CPC)
		{
			this.dernierBalise = "\t\t\t</p>\n";    // affecte la balise fermante p

			return "\t\t\t<p class=\"cadre\">\n\t\t\t\t" + curLine.substring(4) + "\n";
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n"; // Si la dernière ligne contient CPC on rentre dans cette condition
	}

	public String getCCO( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t\t</code>\n\t\t\t</pre>\n";    // affecte la balise fermante code puis pre

			return "\t\t\t<pre>\n\t\t\t\t<code>\n" + curLine.substring(4) + "\n";
		}
		else return curLine.substring(4) + "\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getCSlashC( String curLine )
	{
		return "\t\t\t<div class=\"cadre\">\n";    // retourne la balise qui creer un cadre
	}

	public String getCaSlashC( String curLine )
	{
		return "\t\t\t</div>\n";                   // retourne la balise qui ferme le cadre
	}

	public String getCLO( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</ul>\n";    // affecte la balise fermante ul

			return "\t\t\t<ul>\n\t\t\t\t<li>" + curLine.substring(4) + "</li>\n";
		}
		else return "\t\t\t\t<li>" + curLine.substring(4) + "</li>\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public void setSommaireCTI(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;    // increment l'ancre pour la redirection
		this.setSommaire(ct1, fga, ancre, 1);    // ajoute au sommaire le nom du chapitre
	}

	public void setSommaireCT1(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;    // increment l'ancre pour la redirection
		this.setSommaire(ct1, fga, ancre, 2);    // ajoute au sommaire le titre de niveau 1
	}

	public void setSommaireCT2(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;    // increment l'ancre pour la redirection
		this.setSommaire(ct1, fga, ancre, 3);    // ajoute au sommaire le titre de niveau 2
	}

	private void setSommaire(String ct, String fga, int ancre, int niveau)
	{
		if (niveau == 1)    // type de ligne CTI
		{
			if (niv3 >  1) sommaire += "\t\t\t\t\t</ol>\n";    // ferme la balise de niveau 3 si utilisé
			if (niv2 >  1) sommaire += "\t\t\t\t</ol>\n";      // ferme la balise de niveau 2 si utilisé
			if (niv1 == 1) sommaire += "\t\t\t<ol>\n";         // ouvre la balise de niveau 1 si jamais utilisé

			sommaire += "\t\t\t\t<li>" + this.ConverRomain(niv1) + ". <a href=\"../" + this.rep_Sortie + "/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";    // creer le lien vers le chapitre
			
			niv1 ++;     // increment pour le prochain chapitre
			niv2 = 1;    // reintialise la numerotation du titre de niveau 1
			niv3 = 1;    // reintialise la numerotation du titre de niveau 2
		}
		if (niveau == 2)    // type de ligne CT1
		{
			if (niv3 >  1) sommaire += "\t\t\t\t\t</ol>\n";    // ferme la balise de niveau 3 si utiliser
			if (niv2 == 1) sommaire += "\t\t\t\t<ol>\n";       // ouvre la balise de niveau 2 si jamais utiliser

			sommaire += "\t\t\t\t\t<li>" + niv2 + ". <a href=\"../" + this.rep_Sortie + "/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";    // creer le lien vers le le titre de niveau 1 dans le chapitre
		
			niv2 ++;     // increment pour le prochain titre de niveau 1
			niv3 = 1;    // reintialise la numerotation du titre de niveau 2
		}
		if (niveau == 3)    // type de ligne CT2
		{
			if (niv3 == 1) sommaire += "\t\t\t\t\t<ol>\n";    // ouvre la balise de niveau 3 si jamais utiliser

			sommaire += "\t\t\t\t\t\t<li>" + (niv2 - 1) + "." + niv3 + ". <a href=\"../" + this.rep_Sortie + "/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";    // creer le lien vers le le titre de niveau 2 dans le chapitre
		
			niv3 ++;    // increment pour le prochain titre de niveau 2
		}
	}

    public String getSommaire()
    {
        if (niv3 > 1) sommaire += "\t\t\t\t\t</ol>\n";    // ferme la balise de niveau 3 si utilisé
		if (niv2 > 1) sommaire += "\t\t\t\t</ol>\n";      // ferme la balise de niveau 2 si utilisé
		if (niv1 > 1) sommaire += "\t\t\t</ol>";          // ferme la balise de niveau 1 si utilisé

		ancre = 0;    // reintialise l'ancre

        return sommaire;   // retourne le sommaire avec les balise html
	}
	
	public String getDernierBalise()
	{
		return dernierBalise;        // retourne la derniere balise fermante affecter
	}

	public String ConverRomain( int ChapPrincipal )
    {
        /*------------*/
        /*--Données---*/
        /*------------*/
        
        //variables
        int debNb;                       // Variable correspondant aux dizaines
        int finNb;                       // Variable correspondant aux unités
        String newNb="";                 // Variable correspondant au chiffre Romain
        
        
        /*----------------*/
        /*--Instructions--*/
        /*----------------*/
        
        debNb = ChapPrincipal / 10;         // Récupération de la dizaine du nombre
        finNb = ChapPrincipal % 10;         // Récupération de l'unité du nombre 

        switch (debNb)                      // En fonction de la dizaine la variable newNB va être affecté du chiffre romain correspondant
        {
            case 0  -> newNb = ""     ;
            case 1  -> newNb = "X"    ;
            case 2  -> newNb = "XX"   ;
            case 3  -> newNb = "XXX"  ;
            case 4  -> newNb = "XL"   ;
            case 5  -> newNb = "L"    ;
            case 6  -> newNb = "LX"   ;
            case 7  -> newNb = "LXX"  ;
            case 8  -> newNb = "LXXX" ;
            case 9  -> newNb = "XC"   ;
        }

        switch (finNb)                      // En fonction de l'unité la variable newNB va être affecté du chiffre romain correspondant
        {
            case 0  -> newNb += ""     ;
            case 1  -> newNb += "I"    ;
            case 2  -> newNb += "II"   ;
            case 3  -> newNb += "III"  ;
            case 4  -> newNb += "IV"   ;
            case 5  -> newNb += "V"    ;
            case 6  -> newNb += "VI"   ;
            case 7  -> newNb += "VII"  ;
            case 8  -> newNb += "VIII" ;
            case 9  -> newNb += "IX"   ;
        }

        return newNb;                         // Retourne le nombre Romain
    }
}