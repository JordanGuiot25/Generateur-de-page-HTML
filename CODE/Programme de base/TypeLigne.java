public class TypeLigne 
{
	private String sommaire;

	private int ancre;

	private int niv1;
	private int niv2;
	private int niv3;
	private String baliseSommaire1;

	public String dernierBalise;

	public TypeLigne()
	{
		this.sommaire = "";
		this.ancre = 0;

		this.niv1 = 1;
		this.niv2 = 1;
		this.niv3 = 1;
		this.baliseSommaire1 = "";

		this.dernierBalise = "";
	}

	public String getTGA( String curLine )
	{
		return "\t\t\t<h1>" + curLine.substring(4) + "</h1>\n";
	}

	public String getPGA( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</p>\n";

			return "\t\t\t<p>\n\t\t\t\t" + curLine.substring(4) + "\n";
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getIGA( String curLine )
	{
		String[] iga = curLine.substring(4).split(":");

		return "\t\t\t<img src=\"images/" + iga[0] + "\" alt=\"" + iga[1] + "\">\n";
	}

	public String getCTI( String curLine, String fga )
	{
		ancre ++;

		return "\t\t\t<h1 id=\"" + ancre + "\">" + curLine.substring(4) + "</h1>\n";
	}

	public String getCT1( String curLine, String fga )
	{
		ancre ++;

		return "\t\t\t<h1 id=\"" + ancre + "\">" + curLine.substring(4) + "</h1>\n";
	}

	public String getCT2( String curLine, String fga )
	{
		ancre ++;

		return "\t\t\t<h2 id=\"" + ancre + "\">" + curLine.substring(4) + "</h2>\n";
	}

	public String getCPS( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</p>\n";

			return "\t\t\t<p>\n\t\t\t\t" + curLine.substring(4) + "\n";
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getCIM( String curLine )
	{
		String[] iga = curLine.substring(4).split(":");

		return "\t\t\t<img src=\"images/" + iga[0] + "\" alt=\"" + iga[1] + "\">\n";
	}

	public String getCPC( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</p>\n";

			return "\t\t\t<p class=\"cadre\">\n\t\t\t\t" + curLine.substring(4) + "\n";
		}
		else return "\t\t\t\t" + curLine.substring(4) + "\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getCCO( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t\t</code>\n\t\t\t</pre>\n";

			return "\t\t\t<pre>\n\t\t\t\t<code>\n" + curLine.substring(4) + "\n";
		}
		else return curLine.substring(4) + "\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getCSlashC( String curLine )
	{
		return "\t\t\t<div class=\"cadre\">\n";
	}

	public String getCaSlashC( String curLine )
	{
		return "\t\t\t</div class=\"cadre\">\n";
	}

	public String getCLO( String curLine, String oldLine )
	{
		if( oldLine.equals( "" ) )    // si derniere ligne = vide et ligne actuelle = CPS
		{
			this.dernierBalise = "\t\t\t</ul>\n";

			return "\t\t\t<ul>\n\t\t\t\t<li>" + curLine.substring(4) + "</li>\n";
		}
		else return "\t\t\t\t<li>" + curLine.substring(4) + "</li>\n"; // si derniere ligne = CPS  et ligne actuelle = CPS
	}

	public String getCAN( String curLine )
	{
		return curLine.substring(4);
	}

	public void setSommaireCTI(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;
		this.setSommaire(ct1, fga, ancre, 1);
	}

	public void setSommaireCT1(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;
		this.setSommaire(ct1, fga, ancre, 2);
	}

	public void setSommaireCT2(String curLine, String fga)
	{
		String ct1 = curLine.substring(4);

		ancre ++;
		this.setSommaire(ct1, fga, ancre, 3);
	}

	private void setSommaire(String ct, String fga, int ancre, int niveau)
	{
		if (niveau == 1) 
		{
			if (niv3 >  1) sommaire += "\t\t\t\t\t</ol>\n";
			if (niv2 >  1) sommaire += "\t\t\t\t</ol>\n";
			if (niv1 == 1) sommaire += "\t\t\t<ol>\n";

			sommaire += "\t\t\t\t<li>" + this.ConverRomain(niv1) + ". <a href=\"../WEB/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";
			
			niv1 ++;
			niv2 = 1;
			niv3 = 1;
		}
		if (niveau == 2) 
		{
			if (niv3 >  1) sommaire += "\t\t\t\t\t</ol>\n";
			if (niv2 == 1) sommaire += "\t\t\t\t<ol>\n";

			sommaire += "\t\t\t\t\t<li>" + niv2 + ". <a href=\"../WEB/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";
		
			niv2 ++;
			niv3 = 1;
		}
		if (niveau == 3) 
		{
			if (niv3 == 1) sommaire += "\t\t\t\t\t<ol>\n";

			sommaire += "\t\t\t\t\t\t<li>" + (niv2 - 1) + "." + niv3 + ". <a href=\"../WEB/" + fga + ".html#" + ancre + "\">" + ct + "</a></li>\n";
		
			niv3 ++;
		}
	}

    public String getSommaire()
    {
        if (niv3 > 1) sommaire += "\t\t\t\t\t</ol>\n";
		if (niv2 > 1) sommaire += "\t\t\t\t</ol>\n";
		if (niv1 > 1) sommaire += "\t\t\t</ol>";

		ancre = 0;

        return sommaire;
	}
	
	public String getDernierBalise()
	{
		return dernierBalise;
	}

	public String ConverRomain( int ChapPrincipal )
    {
        /*------------*/
        /*--Données---*/
        /*------------*/
        
        //variables
        int debNb;
        int finNb;
        String newNb="";
        
        
        /*----------------*/
        /*--Instructions--*/
        /*----------------*/
        
        debNb = ChapPrincipal / 10;
        finNb = ChapPrincipal % 10;

        switch (debNb)
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

        switch (finNb)
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

        return newNb;
    }
}