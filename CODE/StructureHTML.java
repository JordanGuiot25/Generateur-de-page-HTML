public class StructureHTML
{
	private String rep_CSS; // Création de la variable permettant de stocker le CSS choisi
    private boolean topBot; // Création du booleen permettant de basculer entre le haut et le bas de L'HTML

    public StructureHTML(String rep_CSS)
    {
    	this.rep_CSS = rep_CSS; // Attribution du CSS choisi en entré de programme
        this.topBot = true;    // le booleen prends vrai à l'initialisation du programme pour pouvoir écrire la partie haute de L'HTML
    }

	public String html(String fga)
	{
		// La condition avec le booleen nous permet de savoir si on print la partie haute ou partie basse de L'HTML

        if (this.topBot == true)
        {
			// HTML présent au début de la page

            String top;    // Création de la chaine permettant de stocker la partie haute de l'HTML
            top  = "<!doctype html>\n";
            top += "<html lang=\"fr\">\n";    // Ecriture de language Français
            top += "\t<head>\n";             // Ouverture de la balise <Head>
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Chevalier Thomas\">\n"; // Auteur 
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Bosquain Maxence\">\n"; // Auteur
            top += "\t\t<meta name=\"Author\" lang=\"fr\" content=\"Guiot Jordan\">\n";     // Auteur
            top += "\t\t<meta charset=\"utf-8\">\n";     // Force la page a être en UTF8
            top += "\t\t<title>" + fga + "</title>\n";   // Permet d'écrire le titre de la page grâce au FGA qui nous est envoyé depuis LectureDATA
            top += "\t\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n";    // Permet de gérer les images animées 
            top += "\t\t<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n";                  // Permet de gérer les images animées
            top += "\t\t<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n";               // Permet de gérer les images animées
            top += "\t\t<link rel=\"stylesheet\" href=\"../CSS/" + this.rep_CSS + "\">\n";                                              // Emplacement du CSS personnalisé
            top += "\t</head>\n";    // Fermeture de la balise <Head>
            top += "\t<body>";       // Ouverture du corps de la page

            this.topBot = false;     // Le booleen prends faux pour permettre lorsqu'on rappelera la fonction d'écrire la partie basse de L'HTML
            return top;              // Retourne le booleen
        }
        else
        {
			// HTML Présent en fin de page

            String bot;              // Création de la chaine permettant de stocker les femetures des balises <body> et <head>

            bot  = "\t</body>\n";    // Fermeture du corps de la page HTML
            bot += "</html>";        // Fermeture de la page HTML

            this.topBot = true;      // Remet le booleen en vrai pour pouvoir plus tard écrire la partie haute pour la prochaine page
            return bot;              // Retourne la chaine de caractère contenant le fin de page en HTML
        }
    }
}