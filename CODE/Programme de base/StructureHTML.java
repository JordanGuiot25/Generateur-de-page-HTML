public class StructureHTML
{
    private boolean topBot;

    public StructureHTML()
    {
        this.topBot = true;
    }

	public String html()
	{
        if (this.topBot == true)
        {
            String top;
            top  = "<!doctype html>\n";
            top += "<html lang=\"fr\">\n";
            top += "\t<head>\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Chevalier Thomas\">\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Moinier Mael\">\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Correia Matieu\">\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Bosquain Maxence\">\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Guiot Jordan\">\n";
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Germain Clement\">\n";
            top += "\t\t<meta charset=\"utf-8\">\n";
            top += "\t\t<title>Titre de la page</title>\n";
            top += "\t\t<link rel=\"stylesheet\" href=\"CSS/style.css\">\n";
            top += "\t</head>\n";
            top += "\t<body>";

            this.topBot = false;
            return top;
        }
        else
        {
            String bot;

            bot  = "\t</body>\n";
            bot += "</html>";

            this.topBot = true;
            return bot;
        }
    }
}