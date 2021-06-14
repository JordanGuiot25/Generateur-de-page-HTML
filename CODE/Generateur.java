public class Generateur 
{
	private LectureDATA read;

	public Generateur(String rep_Entre, String rep_Sortie, String rep_CSS)  // Generateur avec comme parametre un repertoire d'entre, un de sorti et un fichier CSS
	{
		this.read = new LectureDATA(rep_Entre, rep_Sortie, rep_CSS);  // Creer un nouveau lectureDATA avec 3 parametre
	}
	
	public static void main(String[] args) 
	{
		new Generateur(args[0], args[1], args[2]);  // Creer un nouveau generateur avec 3 parametre
	}
}