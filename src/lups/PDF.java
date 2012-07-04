package lups;

/**
 * PDF-Ausgabe-Klasse des Projekts
 * Erzeugt PDF-Dokumente (durch Nutzung der itext-5.2.1.-Library)
 * 
 * @author SE_Team08
 * @version 1.0
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lups.Database;

public final class PDF {
  private PDF(){}
  private static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
  private static Font font1 = new Font(Font.FontFamily.TIMES_ROMAN,28,Font.BOLD,BaseColor.BLUE);
  private static Font font2 = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
  private static Font font3 = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
  private static Font font4 = new Font(Font.FontFamily.TIMES_ROMAN,6,Font.BOLD);
  
  private static final String USER_DIR = System.getProperty("user.dir");
  private static final String UNI = "\nUniversitaet Weissenberg";
  private static final String NAME = "\nName:            ";
  private static final String MATNR = "\nMatrikelnummer:  ";
  private static final String FACH = "Studienfach:           ";
  
  /**
   * Erstellt eine Leistungsuebersicht
   * @param datenbank Referenz auf zu nutzende Datenbank
   * @param nutzer Benutzer auf den peronalisiert wird
   * @return true wenn PDF erfolgreich erstellt, false sonst
   */
  public static boolean leistungsuebersicht(final Database datenbank, final String email){
    final List<String> liste=datenbank.getLeistungsuebersicht(email);
    final String filename=USER_DIR + "/" + email + "-leistungsuebersicht.pdf";
    final java.util.Date now = new java.util.Date();
    boolean returnvalue;
    
    try{
          final Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(filename));
          document.open();
          document.add(new Paragraph(UNI, font1));
          document.add(new Paragraph("\n\nLeistungsuebersicht", font2));
          document.add(new Paragraph(NAME + email, font3));
          document.add(new Paragraph(MATNR + datenbank.getMatNr(email), font3));
          document.add(new Paragraph("Semester:                "+datenbank.getSemester(email), font3));
          document.add(new Paragraph(FACH + datenbank.getFach(email), font3));
          document.add(new Paragraph("\n", font3));
          
          final PdfPTable table = new PdfPTable(3);

      PdfPCell zelle = new PdfPCell(new Phrase("Kurs", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Bewertung", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Note", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      for(String leistung : liste){
            table.addCell(new Phrase(leistung, font3));
          }
      
      document.add(table);
      
          document.add(new Paragraph("\n\nDiese Bescheinigung wurde maschinell erstellt. Sie gilt ohne Unterschrift und Siegel. - erstellt "+sdf.format(now), font4));
          document.add(new Paragraph(FACH , font4));
          document.close();
          Desktop.getDesktop().open(new File(filename));
          returnvalue = true;
      
    } catch (DocumentException e){
        returnvalue = false;
      }
    catch (IOException e){
      returnvalue = false;
    }
      catch (Exception e){
        returnvalue = false;
      }
    return returnvalue;
  }
  /**
   * Erzeugt eine Studienbescheinigung
   * @param datenbank Referenz auf zu nutzende Datenbank
   * @param nutzer Benutzer auf den peronalisiert wird
   * @return true wenn PDF erfolgreich erstellt, false sonst
   */
    public static boolean studienbescheinigung(final Database datenbank, final String email){
      final String filename=USER_DIR + "/" + email + "-studienbescheinigung.pdf";
      final java.util.Date now = new java.util.Date();
      boolean returnvalue;
      
      try{
          final Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(filename));
          document.open();
          document.add(new Paragraph(UNI , font1));
          document.add(new Paragraph("\n\nStudienbescheinigung", font2));
          document.add(new Paragraph(NAME + email, font3));
          document.add(new Paragraph("\nist ordnungsgemaess an der Universitaet Weissenberg immatrikuliert.", font3));
          document.add(new Paragraph(MATNR + datenbank.getMatNr(email), font3));
          document.add(new Paragraph("Semester:                "+datenbank.getSemester(email), font3));
          document.add(new Paragraph(FACH + datenbank.getFach(email), font3));
          document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nDiese Bescheinigung wurde maschinell erstellt. Sie gilt ohne Unterschrift und Siegel. - erstellt "+sdf.format(now), font4));
          document.add(new Paragraph("Universitaet Weissenberg", font4));
          document.close();
          Desktop.getDesktop().open(new File(filename));
          returnvalue = true;
      
      } catch (DocumentException e){
        returnvalue = false;
      }
    catch (IOException e){
      returnvalue = false;
    }
      catch (Exception e){
        returnvalue = false;
      }
      return returnvalue;
  }
    /**
   * Erzeugt einen Stundenplan
   * @param datenbank Referenz auf zu nutzende Datenbank
   * @param nutzer Benutzer auf den peronalisiert wird
   * @return true wenn PDF erfolgreich erstellt, false sonst
   */
    public static boolean stundenplan(final Database datenbank, final String email){
    final List<String> liste=datenbank.getKursListe(email);
    final String filename=USER_DIR+"/"+email+"-stundenplan.pdf";
    final java.util.Date now = new java.util.Date();
    boolean returnvalue;
    int zaehler=1;
    int index=0;
    
    String temp = "";
    String tag="";
    String eintrag="";
    String montag08="", montag10="", montag12="", montag14="", montag16="", montag18="";
    String dienstag08="", dienstag10="", dienstag12="", dienstag14="", dienstag16="", dienstag18="";
    String mittwoch08="", mittwoch10="", mittwoch12="", mittwoch14="", mittwoch16="", mittwoch18="";
    String donnerstag08="", donnerstag10="", donnerstag12="", donnerstag14="", donnerstag16="", donnerstag18="";
    String freitag08="", freitag10="", freitag12="", freitag14="", freitag16="", freitag18="";
    
    try{
          final Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(filename));
          document.open();
          document.add(new Paragraph(UNI, font1));
          document.add(new Paragraph("\n\nStundenplan", font2));
          document.add(new Paragraph(NAME + email, font3));
          document.add(new Paragraph(MATNR + datenbank.getMatNr(email), font3));
          document.add(new Paragraph("Semester:                "+datenbank.getSemester(email), font3));
          document.add(new Paragraph(FACH + datenbank.getFach(email), font3));
          document.add(new Paragraph("\n", font3));
          
          for(String kurs : liste){
            if(zaehler%2==1){
              temp=kurs;
            } else{
              temp=kurs+" "+temp;
              index=temp.indexOf(' ');
              tag=temp.substring(0, index);
              eintrag=temp.substring(index+1);
              temp=eintrag.substring(0, 2);
              
              if(tag.equals("Montag")){
                if(temp.equals("08")||temp.equals("09")||temp.equals("8:")||temp.equals("9:")){
                  if(!montag08.equals("")){
                    montag08+="\n";
                  }
                  montag08+=eintrag;
                }
                else if(temp.equals("10")||temp.equals("11")){
                  if(!montag10.equals("")){
                    montag10+="\n";
                  }
                  montag10+=eintrag;
                }
                else if(temp.equals("12")||temp.equals("13")){
                  if(!montag12.equals("")){
                    montag12+="\n";
                  }
                  montag12+=eintrag;
                }
                else if(temp.equals("14")||temp.equals("15")){
                  if(!montag14.equals("")){
                    montag14+="\n";
                  }
                  montag14+=eintrag;
                }
                else if(temp.equals("16")||temp.equals("17")){
                  if(!montag16.equals("")){
                    montag16+="\n";
                  }
                  montag16+=eintrag;
                }
                else if(temp.equals("18")||temp.equals("19")){
                  if(!montag18.equals("")){
                    montag18+="\n";
                  }
                  montag18+=eintrag;
                }
              }
              
              else if(tag.equals("Dienstag")){
                if(temp.equals("08")||temp.equals("09")){
                  if(!dienstag08.equals("")){
                    dienstag08+="\n";
                  }
                  dienstag08+=eintrag;
                }
                else if(temp.equals("10")||temp.equals("11")){
                  if(!dienstag10.equals("")){
                    dienstag10+="\n";
                  }
                  dienstag10+=eintrag;
                }
                else if(temp.equals("12")||temp.equals("13")){
                  if(!dienstag12.equals("")){
                    dienstag12+="\n";
                  }
                  dienstag12+=eintrag;
                }
                else if(temp.equals("14")||temp.equals("15")){
                  if(!dienstag14.equals("")){
                    dienstag14+="\n";
                  }
                  dienstag14+=eintrag;
                }
                else if(temp.equals("16")||temp.equals("17")){
                  if(!dienstag16.equals("")){
                    dienstag16+="\n";
                  }
                  dienstag16+=eintrag;
                }
                else if(temp.equals("18")||temp.equals("19")){
                  if(!dienstag18.equals("")){
                    dienstag18+="\n";
                  }
                  dienstag18+=eintrag;
                }
              }
              
              else if(tag.equals("Mittwoch")){
                if(temp.equals("08")||temp.equals("09")){
                  if(!mittwoch08.equals("")){
                    mittwoch08+="\n";
                  }
                  mittwoch08+=eintrag;
                }
                else if(temp.equals("10")||temp.equals("11")){
                  if(!mittwoch10.equals("")){
                    mittwoch10+="\n";
                  }
                  mittwoch10+=eintrag;
                }
                else if(temp.equals("12")||temp.equals("13")){
                  if(!mittwoch12.equals("")){
                    mittwoch12+="\n";
                  }
                  mittwoch12+=eintrag;
                }
                else if(temp.equals("14")||temp.equals("15")){
                  if(!mittwoch14.equals("")){
                    mittwoch14+="\n";
                  }
                  mittwoch14+=eintrag;
                }
                else if(temp.equals("16")||temp.equals("17")){
                  if(!mittwoch16.equals("")){
                    mittwoch16+="\n";
                  }
                  mittwoch16+=eintrag;
                }
                else if(temp.equals("18")||temp.equals("19")){
                  if(!mittwoch18.equals("")){
                    mittwoch18+="\n";
                  }
                  mittwoch18+=eintrag;
                }
              }
              
              else if(tag.equals("Donnerstag")){
                if(temp.equals("08")||temp.equals("09")){
                  if(!donnerstag08.equals("")){
                    donnerstag08+="\n";
                  }
                  donnerstag08+=eintrag;
                }
                else if(temp.equals("10")||temp.equals("11")){
                  if(!donnerstag10.equals("")){
                    donnerstag10+="\n";
                  }
                  donnerstag10+=eintrag;
                }
                else if(temp.equals("12")||temp.equals("13")){
                  if(!donnerstag12.equals("")){
                    donnerstag12+="\n";
                  }
                  donnerstag12+=eintrag;
                }
                else if(temp.equals("14")||temp.equals("15")){
                  if(!donnerstag14.equals("")){
                    donnerstag14+="\n";
                  }
                  donnerstag14+=eintrag;
                }
                else if(temp.equals("16")||temp.equals("17")){
                  if(!donnerstag16.equals("")){
                    donnerstag16=donnerstag16+"\n";
                  }
                  donnerstag16+=eintrag;
                }
                else if(temp.equals("18")||temp.equals("19")){
                  if(!donnerstag18.equals("")){
                    donnerstag18+="\n";
                  }
                  donnerstag18+=eintrag;
                }
              }
              
              else if(tag.equals("Freitag")){
                if(temp.equals("08")||temp.equals("09")){
                  if(!freitag08.equals("")){
                    freitag08+="\n";
                  }
                  freitag08+=eintrag;
                }
                else if(temp.equals("10")||temp.equals("11")){
                  if(!freitag10.equals("")){
                    freitag10+="\n";
                  }
                  freitag10+=eintrag;
                }
                else if(temp.equals("12")||temp.equals("13")){
                  if(!freitag12.equals("")){
                    freitag12+="\n";
                  }
                  freitag12+=eintrag;
                }
                else if(temp.equals("14")||temp.equals("15")){
                  if(!freitag14.equals("")){
                    freitag14+="\n";
                  }
                  freitag14+=eintrag;
                }
                else if(temp.equals("16")||temp.equals("17")){
                  if(!freitag16.equals("")){
                    freitag16+="\n";
                  }
                  freitag16+=eintrag;
                }
                else if(temp.equals("18")||temp.equals("19")){
                  if(!freitag18.equals("")){
                    freitag18+="\n";
                  }
                  freitag18+=eintrag;
                }
              }
            } 
            zaehler++;
          }
          
          final PdfPTable table = new PdfPTable(6);

      PdfPCell zelle = new PdfPCell(new Phrase("", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Montag", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Dienstag", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);
      
      zelle = new PdfPCell(new Phrase("Mittwoch", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Donnerstag", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);

      zelle = new PdfPCell(new Phrase("Freitag", font3));
      zelle.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(zelle);
      table.setHeaderRows(1);

      table.addCell(new Phrase("08:00", font3));
      table.addCell(new Phrase(montag08, font3));
      table.addCell(new Phrase(dienstag08, font3));
      table.addCell(new Phrase(mittwoch08, font3));
      table.addCell(new Phrase(donnerstag08, font3));
      table.addCell(new Phrase(freitag08, font3));
      table.addCell(new Phrase("10:00", font3));
      table.addCell(new Phrase(montag10, font3));
      table.addCell(new Phrase(dienstag10, font3));
      table.addCell(new Phrase(mittwoch10, font3));
      table.addCell(new Phrase(donnerstag10, font3));
      table.addCell(new Phrase(freitag10, font3));
      table.addCell(new Phrase("12:00", font3));
      table.addCell(new Phrase(montag12, font3));
      table.addCell(new Phrase(dienstag12, font3));
      table.addCell(new Phrase(mittwoch12, font3));
      table.addCell(new Phrase(donnerstag12, font3));
      table.addCell(new Phrase(freitag12, font3));
      table.addCell(new Phrase("14:00", font3));
      table.addCell(new Phrase(montag14, font3));
      table.addCell(new Phrase(dienstag14, font3));
      table.addCell(new Phrase(mittwoch14, font3));
      table.addCell(new Phrase(donnerstag14, font3));
      table.addCell(new Phrase(freitag14, font3));
      table.addCell(new Phrase("16:00", font3));
      table.addCell(new Phrase(montag16, font3));
      table.addCell(new Phrase(dienstag16, font3));
      table.addCell(new Phrase(mittwoch16, font3));
      table.addCell(new Phrase(donnerstag16, font3));
      table.addCell(new Phrase(freitag16, font3));
      table.addCell(new Phrase("18:00", font3));
      table.addCell(new Phrase(montag18, font3));
      table.addCell(new Phrase(dienstag18, font3));
      table.addCell(new Phrase(mittwoch18, font3));
      table.addCell(new Phrase(donnerstag18, font3));
      table.addCell(new Phrase(freitag18, font3));

      document.add(table);
          
          document.add(new Paragraph("\n\nDieses Dokument wurde maschinell erstellt - erstellt "+sdf.format(now), font4));
          document.add(new Paragraph(UNI, font4));
                    
          document.close();
          Desktop.getDesktop().open(new File(filename));
          returnvalue = true;
      
    } catch (DocumentException e){
        returnvalue = false;
      }
    catch (IOException e){
      returnvalue = false;
    }
      catch (Exception e){
        returnvalue = false;
      }
      return returnvalue;
    }
    /**
   * Erzeugt eine Studienverlaufsbescheinigung
   * @param datenbank Referenz auf zu nutzende Datenbank
   * @param nutzer Benutzer auf den peronalisiert wird
   * @return true wenn PDF erfolgreich erstellt, false sonst
   */
    public static boolean studienverlaufsbescheinigung(final Database datenbank, final String email){
    final int semester=datenbank.getSemester(email);
    final String filename=USER_DIR+"/"+email+"-studienverlaufsbescheinigung.pdf";
    final java.util.Date now = new java.util.Date();
    boolean returnvalue;
    
    try{
          final Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(filename));
          document.open();
          document.add(new Paragraph(UNI, font1));
          document.add(new Paragraph("\n\nStudienverlaufsbescheinigung", font2));
          document.add(new Paragraph(NAME + email, font3));
          document.add(new Paragraph(MATNR + datenbank.getMatNr(email), font3));
          document.add(new Paragraph(FACH + datenbank.getFach(email), font3));
          document.add(new Paragraph("\n\nSemster 1"+"     Einschreibung", font3));
          for(int i=2;i<=semester;i++){
            document.add(new Paragraph("Semster "+i+"     Rueckmeldung", font3));
          }
          document.add(new Paragraph("\n\nDiese Bescheinigung wurde maschinell erstellt. Sie gilt ohne Unterschrift und Siegel. - erstellt "+sdf.format(now), font4));
          document.add(new Paragraph(UNI, font4));
          document.close();
          Desktop.getDesktop().open(new File(filename));
          returnvalue = true;
      } catch (DocumentException e){
        returnvalue  = false;
      }
    catch (IOException e){
      returnvalue = false;
    }
      catch (Exception e){
        returnvalue = false;
      }
      return returnvalue;
  }
    /**
   * Erzeugt eine Kursteilnehmerliste
   * @param datenbank Referenz auf zu nutzende Datenbank
   * @param kurs Kurs fuer den erstellt wird
   * @return true wenn PDF erfolgreich erstellt, false sonst
   */
    public static boolean teilnehmerliste(final Database datenbank, final String kursid){
      final List<String> liste=datenbank.getTeilnehmerListe(kursid);
      final String kurs=datenbank.getTitel(kursid);
      final String filename=USER_DIR+"/"+kurs+"-teilnehmerliste.pdf";
    boolean returnvalue;
      final java.util.Date now = new java.util.Date();
      
      try{
          final Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(filename));
          document.open();
          document.add(new Paragraph(UNI, font1));
          document.add(new Paragraph("\n\nTeilnehmerliste Kurs: "+kurs, font2));
          document.add(new Paragraph("\n", font3));
          for(String teilnehmer : liste){
            document.add(new Paragraph(teilnehmer, font3));
          }
          document.add(new Paragraph("\n\nDieses Dokument wurde maschinell erstellt - erstellt "+sdf.format(now), font4));
          document.add(new Paragraph(UNI, font4));
          document.close();
          Desktop.getDesktop().open(new File(filename));
          returnvalue = true;
      } catch (DocumentException e){
        returnvalue = false;
      }
    catch (IOException e){
      returnvalue = false;
    }
      catch (Exception e){
        returnvalue = false;
      }
      return returnvalue;
  }
}