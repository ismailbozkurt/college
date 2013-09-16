/*
 *
 *	Errors, classe que gestiona l'impresió dels errors en els fitxers.
 *
 */
import java.io.*;
import java.util.*;

public class Errors
{
	/*Errors lexics*/
	public static final int ERR_LEX_1  = 1;
	public static final int ERR_LEX_2  = 2;
	/*El fitxer d'error*/
	public static FileWriter m_fwFitxer;
	public static BufferedWriter m_fitxer;

	public Errors(String nomFitxer)
	{
		try {
			m_fwFitxer = new FileWriter(nomFitxer);
			m_fitxer   = new BufferedWriter(m_fwFitxer);
		} catch(IOException ioe) {
			ioe.printStackTrace(System.err);
		}
	}

	public static void tanca()
	{
		try {
			m_fitxer.close();
			m_fwFitxer.close();
		} catch(IOException ioe) {
			ioe.printStackTrace(System.err);
		}
	}
	//IMAGINO QUE AIX� HO HEM DE TREURE, NO?
	public static void errorLexic(int tipus, Token matchedToken)
	{
 		String error;
		if(tipus == ERR_LEX_1) {
			error  = new String("[ERR_LEX_1] "+matchedToken.beginLine+", caràcter["+matchedToken.image+"] desconegut");
		} else {
            error = new String("[ERR_LEX_2] "+matchedToken.beginLine+", identificador ["+matchedToken.image+"] massa llarg");
		}
		try {
			m_fitxer.write(error);
			m_fitxer.newLine();
			m_fitxer.flush();
		} catch(IOException ioe) {
			ioe.printStackTrace(System.err);
		}
		System.out.println(error); //per mostrar-ho per pantalla
	}
	
	public void writeErrorSem(Integer idError, Semantic oSemantic, Token oToken){
		
		String sError = "";
		switch(idError){
					//Errores de enunciado:
					case 1:		sError = "[ERR_SEM_1] "+oToken.beginLine+", Constant ["+oToken.image+"] doblement definida"; break;
					case 2:		sError = "[ERR_SEM_2] "+oToken.beginLine+", Expressi� no est�tica en declaraci� de constant"; break;
					case 3:		sError = "[ERR_SEM_3] "+oToken.beginLine+", Variable ["+oToken.image+"] doblement definida"; break;
					case 4:		sError = "[ERR_SEM_4] "+oToken.beginLine+", Funci� doblement definida"; break;
					case 5:		sError = "[ERR_SEM_5] "+oToken.beginLine+", Par�metre ["+oToken.image+"] doblement definit"; break;
					case 6:		sError = "[ERR_SEM_6] "+oToken.beginLine+", Tipus no sencers en l�mits de vector"; break;
					case 7:		sError = "[ERR_SEM_7] "+oToken.beginLine+", L�mits decreixents en vector"; break;
					case 8:		sError = "[ERR_SEM_8] "+oToken.beginLine+", L�expressi� en el l�mit del vector no �s est�tica"; break;
					case 9:		sError = "[ERR_SEM_9] "+oToken.beginLine+", El tipus de l�expressi� no �s SENCER"; break;
					case 10:	sError = "[ERR_SEM_10] "+oToken.beginLine+", El tipus de l�expressi� no �s LOGIC"; break;
					case 11:	sError = "[ERR_SEM_11] "+oToken.beginLine+", El tipus de l�expressi� no �s CADENA"; break;
					case 12:	sError = "[ERR_SEM_12] "+oToken.beginLine+", La condici� no �s de tipus LOGIC"; break;
					case 13:	sError = "[ERR_SEM_13] "+oToken.beginLine+", L�identificador ["+oToken.image+"] no ha estat declarat"; break;
					case 14:	sError = "[ERR_SEM_14] "+oToken.beginLine+", L�identificador ["+oToken.image+"] en la instrucci� LLEGIR no �s una variable"; break;
					case 15:	sError = "[ERR_SEM_15] "+oToken.beginLine+", L�identificador ["+oToken.image+"] en la instrucci� LLEGIR no �s una variable de tipus simple"; break;
					case 16:	sError = "[ERR_SEM_16] "+oToken.beginLine+", L�identificador ["+oToken.image+"] en part esquerra d�assignaci� no �s una variable"; break;
					case 17:	sError = "[ERR_SEM_17] "+oToken.beginLine+", La variable ["+oToken.image+"] i l'expressi� d'assignaci� tenen tipus diferents. El tipus de la variable �s ["+(String)oSemantic.getValue("TIPUS_VAR")+"] i el de l�expressi� �s ["+(String)oSemantic.getValue("TIPUS_EXP")+"]"; break;
					case 18:	sError = "[ERR_SEM_18] "+oToken.beginLine+", El tipus de l��ndex d�acc�s del vector no �s SENCER"; break;
					case 19:	sError = "[ERR_SEM_19] "+oToken.beginLine+", El tipus de l'expressi� en ESCRIURE no �s simple o no �s una constant cadena"; break;
					case 20:	sError = "[ERR_SEM_20] "+oToken.beginLine+", La funci� en declaraci� t� ["+(Integer)oSemantic.getValue("PARAM_DEC")+"] par�metres mentre que en �s t� ["+(Integer)oSemantic.getValue("PARAM_US")+"]"; break;
					case 21:	sError = "[ERR_SEM_21] "+oToken.beginLine+", El tipus del par�metre n�mero ["+(Integer)oSemantic.getValue("NUM_PARAM")+"] de la funci� no coincideix amb el tipus en la seva declaraci� ["+(String)oSemantic.getValue("TIPUS_PARAM")+"]"; break;
					case 22:	sError = "[ERR_SEM_22] "+oToken.beginLine+", El par�metre n�mero ["+(Integer)oSemantic.getValue("NUM_PARAM")+"] de la funci� no es pot passar per refer�ncia"; break;
					case 23:	sError = "[ERR_SEM_23] "+oToken.beginLine+", La funci� ["+(String)oSemantic.getValue("NOMFUNCIO")+"] ha de ser del tipus ["+(String)oSemantic.getValue("TIPUSFUNCIO")+"] per� en la expressi� del seu valor el tipus �s ["+(String)oSemantic.getValue("TIPUSEXP")+"]"; break;
					case 24:	sError = "[ERR_SEM_24] "+oToken.beginLine+", S'ha excedit el l�mit del vector ["+oToken.image+"] , �ndex fora de rang."; break;
					case 25:	sError = "[ERR_SEM_25] "+oToken.beginLine+", L�identificador ["+oToken.image+"] no �s de tipus sencer a l�instrucci� PER"; break;
					case 26:	sError = "[ERR_SEM_26] "+oToken.beginLine+", SURTCICLE fora de CICLE"; break;
					case 101:	sError = "[WARNING_1] "+oToken.beginLine+", CICLE sense SURTCICLE"; break;
					
					//Errores propios:
					case 30:	sError = "[ERR_SEM_30] "+oToken.beginLine+", Els l�mits del vector s�n de diferent tipus"; break;
					case 31:	sError = "[ERR_SEM_31] "+oToken.beginLine+", L�expressi� en la longitud de la cadena no �s est�tica"; break;
					case 32:	sError = "[ERR_SEM_32] "+oToken.beginLine+", Tipus no sencers en la longitud de la cadena"; break;
					case 33:	sError = "[ERR_SEM_33] "+oToken.beginLine+", La longitud de la cadena no pot ser 0"; break;
					case 34:	sError = "[ERR_SEM_34] "+oToken.beginLine+", La funci� ha de retornar un tipus simple (sencer o logic)"; break;
					case 35:	sError = "[ERR_SEM_35] "+oToken.beginLine+", No �s possible fer una divisi� entre 0"; break;
					case 36:	sError = "[ERR_SEM_36] "+oToken.beginLine+", La funci� ["+oToken.image+"] no ha estat declarada"; break;
					case 37:	sError = "[ERR_SEM_37] "+oToken.beginLine+", L'identificador ["+oToken.image+"] no ha estat declarat com a VECTOR"; break;
					case 38:	sError = "[ERR_SEM_38] "+oToken.beginLine+", L�identificador ["+oToken.image+"] no ha estat declarat com a constant o variable"; break;
					case 39:	sError = "[ERR_SEM_39] "+oToken.beginLine+", L�identificador ["+oToken.image+"] en part esquerra d�assignaci� no �s una cadena. No es pot concatenar"; break;
					case 40:	sError = "[ERR_SEM_40] "+oToken.beginLine+", La part dreta d�assignaci� no �s de tipus cadena. No es pot concatenar"; break;
					case 41:	sError = "[ERR_SEM_41] "+oToken.beginLine+", L�identificador ["+oToken.image+"] en part esquerra d�assignaci� no �s un sencer. No es pot operar"; break;
					case 42:	sError = "[ERR_SEM_42] "+oToken.beginLine+", La part dreta d�assignaci� no �s de tipus sencer. No es pot operar"; break;
					case 43:	sError = "[ERR_SEM_43] "+oToken.beginLine+", La variable ["+oToken.image+"] de tipus [CADENA] i l'expressi� d'assignaci� no s�n estructuralment equivalents. El tipus de la variable t� longitud ["+(String)oSemantic.getValue("LONG_VAR")+"] mentre que el de l�expressi� t� longitud ["+(String)oSemantic.getValue("LONG_EXP")+"]"; break;
					case 44:	sError = "[ERR_SEM_44] "+oToken.beginLine+", L'identificador de la Funci� ja ha estat declarat pr�viament per a una constant o variable"; break;
					case 45:	sError = "[ERR_SEM_45] "+oToken.beginLine+", La variable i l'expressi� d'assignaci� s�n vectors per� no s�n estructuralment equivalents"; break;
					case 46:	sError = "[ERR_SEM_46] "+oToken.beginLine+", Hi ha m�s d'un SURTCICLE al mateix bloc d'instruccions"; break;
					case 47:	sError = "[ERR_SEM_47] "+oToken.beginLine+", Tipus no aptes per l'operador relacional"; break;
					case 48:	sError = "[ERR_SEM_48] "+oToken.beginLine+", La instrucci� PER no es pot realitzar degut a que els seus l�mits no s�n sencers"; break;
					case 49:	sError = "[ERR_SEM_49] "+oToken.beginLine+", Hi ha m�s d'un RETORNAR al mateix bloc d'instruccions de la funci�"; break;
					case 50:	sError = "[ERR_SEM_50] "+oToken.beginLine+", RETORNAR fora de FUNCIO"; break;
					case 51:	sError = "[ERR_SEM_51] "+oToken.beginLine+", FUNCIO sense RETORNAR"; break;
					case 52:	sError = "[ERR_SEM_52] "+oToken.beginLine+", El tipus del par�metre n�mero ["+(Integer)oSemantic.getValue("NUM_PARAM")+"] de la funci� �s tipus CADENA per� no �s estructuralment equivalent al declarat, de longitud ["+(Integer)oSemantic.getValue("TIPUS_LONG")+"]"; break;
					case 53:	sError = "[ERR_SEM_53] "+oToken.beginLine+", El tipus del par�metre n�mero ["+(Integer)oSemantic.getValue("NUM_PARAM")+"] de la funci� �s tipus VECTOR per� no �s estructuralment equivalent al declarat"; break;
					case 54:	sError = "[ERR_SEM_54] "+oToken.beginLine+", El par�metre n�mero ["+(Integer)oSemantic.getValue("NUM_PARAM")+"] de la funci� correspon al resultat d'una funci�, i no es pot passar per refer�ncia"; break;
					case 55:	sError = "[ERR_SEM_55] "+oToken.beginLine+", El n�mero de par�metres en �s excedeixen els declarats"; break;				
		
					
					//ERRORES DE GENERACI�N DE C�DIGO
					
					
					
		}
		try {
			m_fitxer.write(sError);
			m_fitxer.newLine();
		} catch (IOException e) { System.out.println("No se ha podido escribir en el archivo");}
	}

} //fi de la classe
