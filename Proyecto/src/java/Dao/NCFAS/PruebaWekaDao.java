/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import static java.lang.System.in;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import weka.core.converters.ConverterUtils.DataSource;
import weka.associations.Apriori;
import weka.core.Instances;

public class PruebaWekaDao {
    
    
public List<String> retornarReglas() throws DAOException, IOException, Exception{
    
  
    FileWriter fichero = null; 
    PrintWriter pw = null; 
    String dataset="DataSetSimulado.arff";
    
    int retorno=0;
    List<String> almacenaRetorno = null;
    almacenaRetorno = new ArrayList<String>();
        
    final String nomFich = "Resultado.txt";  
    Scanner in = null;
    int band=0;
 DataSource source = new DataSource(dataset);
 Apriori model = new Apriori();
    
 try{
    
    
    Instances data = source.getDataSet();
    
    model.buildAssociations(data);
    

    //FileOutputStream out = new FileOutputStream("test.txt");
    //ObjectOutputStream oout = new ObjectOutputStream(out);
         
    fichero = new FileWriter("Resultado.txt",true); 
    pw = new PrintWriter(fichero);
    pw.println(model);
    
    //SE MUESTRA LOS RESULTADOS OBTENIDOS POR APRIORI
     System.out.println("LOS RESULTADOS DE APRIORI A DataSetSimulado.arff son:");
    System.out.println(model);
    fichero.close();
    
    
    System.out.println("Ahora vamos a leer los resultados obtenidos por apriori");
//BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\Resultado.txt"));
	// abre el fichero
	in = new Scanner(new FileReader(nomFich));
	 
	// lee el fichero palabra a palabra
	while (in.hasNext()) {
	 
	// lee primera palabra
	String palabra0 = in.next();
        
        List<String> almacenaAnterior = null;
        almacenaAnterior = new ArrayList<String>();
        String palabra1;
        String palabra2;
        String palabra3;
        String palabra4;
        String palabra5;
        String palabra6;
        
        String frase1;
        String frase2;
        String frase3;
        
        Pattern pat = Pattern.compile("DIM2.*"); 
        Pattern pat2 = Pattern.compile("==>");
        Pattern pat3 = Pattern.compile("^\\d.^");
        Pattern pat4 = Pattern.compile("DIM.*");
        
        String num1=("1.");
        String num2=("2.");
        String num3=("3.");
        String num4=("4.");
        String num5=("5.");
        String num6=("6.");
        String num7=("7.");
        String num8=("8.");
        String num9=("9.");
        String num10=("10.");
        
        
        Matcher mat;
           //PRIMERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num1.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                    
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);
                     //       band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                  //  band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                            almacenaRetorno.add(frase1);;
                          //  band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
                
                
                //NUEVO CODIGO
                //SEGUNDA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num2.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); 
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2 
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);           
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //TERCERA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num3.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);              
                   // band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //CUARTA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num4.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);     
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //QUINTA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num5.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2   
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);              
                   // band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //SEXTA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num6.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                   // band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2 
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);            
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //SEPTIMA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num7.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                           System.out.println(frase1); 
                    almacenaRetorno.add(frase1);  
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //OCTAVA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num8.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                           System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2   
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);   
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //NOVENA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num9.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); 
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                   almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2 
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);      
                    //band=1;
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
            
            //DECIMA RELGA DE ASOCIACIÓN
            //BLOQUE 1
            //COMPROBAR SI ESTÁ DIM2 INVOLUCRADA
            mat = pat3.matcher(palabra0);
            if(num10.equals(palabra0)){
                if(in.hasNext()){
                palabra1=in.next(); // SEGUNDA PALABRA             
                //GUARDO LO LEIDO
                almacenaAnterior.add(palabra0);
                almacenaAnterior.add(palabra1);               
                mat = pat.matcher(palabra1);
                //PALABRA 1 ES DIMENSION 2?
                if (mat.matches()) {        
               //REVISAMOS LO QUE VIENE DESPUES DE DIM2=
                    if(in.hasNext()){
                    palabra2=in.next(); //PALABRA 3 EN EL CAMINO FELIZ
                    if(in.hasNext()){
                    palabra3=in.next(); //PALABRA 4 EN EL CAMINO FELIZ                    
                    almacenaAnterior.add(palabra2);
                    almacenaAnterior.add(palabra3);                   
                    mat = pat2.matcher(palabra3);                    
               //SI ES UNA ==> ENTONCES
                if (mat.matches()) {                   
                    if(in.hasNext()){
                    palabra4=in.next(); //PALABRA 5 CAMINO FELIZ
                    frase1=("La dimensión 2 afecta a la dimensión "+ palabra4 + " una cantidad de " + palabra2 + " veces.");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                }}
                
                // LA REGLA TIENE MAS DE UNA DIMENSIÓN INVOLUCRADA ANTES DE ==>
                else{
                    if(in.hasNext()){
                    palabra4=in.next(); // DEBERÍA SER FLECHA 
                    //COMPROBAMOS SI LA ==> ESTÁ EN LA cuarta POSICIÓN
                    mat = pat2.matcher(palabra4);
                    if(mat.matches()){
                        if(in.hasNext()){
                        palabra5=in.next();                
                        frase1=(" La dimensión 2  junto con la dimensión " + almacenaAnterior.get(2) + " afectan a la dimensión " + palabra5 + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    //band=1;
                        }   
                    }
                    }  
                }
                    }
                }}
             
                //BLOQUE 2
                //ACÁ SE COMPRUEBA SI SE ENCUENTRA LA DIMENSIÓN 2 EN SEGUNDA POSICIÓN           
                if(in.hasNext()){
                palabra2=in.next(); // 
                if(in.hasNext()){
                palabra3=in.next(); //               
                //GUARDAMOS LO LEÍDO
                almacenaAnterior.add(palabra2);
                almacenaAnterior.add(palabra3);              
                mat = pat2.matcher(palabra3);              
                // SI LA PALABRA 3 ES UNA FLECHA, ENTONCES HAY SOLO UNA DIMENSIÓN INVOLUCRADA
                if (mat.matches()) {              
                if(in.hasNext()){
                palabra4=in.next();            
                // DESPUES DE LA ==> ESTÁ INVOLUCRADA LA DIM2 ?
                mat = pat.matcher(palabra4);
                if(mat.matches()){    
                    frase1=("La dimensión 2  es afectada por la " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(2) + " veces");
                    System.out.println(frase1); 
                    almacenaRetorno.add(frase1); // CAMINO FELIZ DIM2 EN POSICIÓN 2  
                    //band=1;
                }               
                }
                }               
                else{                   
                    mat = pat.matcher(palabra2);
                    if(mat.matches()){
                    
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                     
                            frase1=("La dimensión 2 junto con la dimensión " + almacenaAnterior.get(1) + "afectan a la " + palabra5 + " una cantidad de " + palabra3 + " veces.");
                            System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    
                        }                      
                        }                       
                        }                   
                    }                   
                    //NO HAY DIMENSIÓN 2 NI EN LA PRIMERA NI EN LA SEGUNDA POSICIÓN 
                    else {                  
                        if(in.hasNext()){
                        palabra4=in.next();
                        if(in.hasNext()){
                        palabra5=in.next();
                        if(in.hasNext()){                           
                            mat = pat.matcher(palabra5);
                            if(mat.matches()){                           
                            frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(1) + " y por la dimensión " + almacenaAnterior.get(2) + " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                                System.out.println(frase1); 
                    almacenaRetorno.add(frase1);
                    
                            }     
                        }
                        }
                        }   
                    }      
                }
                }}}}
                
                
                
                
                
                
                
                
                
                /*
                }
                
                
                }}
                
                //COMPROBAMOS LA POS 1 NO ERA COMPROBAMOS LA 2 TAMPOCO ERA
                //VEMOS SI HAY UNA ==> LUEGO DE COMPROBAR LAS 2 PRIMERAS PALABRAS
                if(in.hasNext()){
                palabra4=in.next(); 
                if(in.hasNext()){
                palabra5=in.next(); 
                      
                
                mat = pat2.matcher(palabra4);
                //PALABRA 4 ES UNA DIM2 ?
                //guardo lo leído
                almacenaAnterior.add(palabra4);
                almacenaAnterior.add(palabra5);
                
                if(mat.matches()){
                    
                    //SI ESTÁ INVOLUCRADA LA DIM2 ENTONCES
                    mat = pat.matcher(palabra5);
                    
                    //LA DIMENSIÓN 2 ESTÁ INVOLUCRADA ENTONCES
                    //ESTÁ INVOLUCRADA POR 1 ELEMENTO
                        frase1=("La dimensión 2 es afectada por " + almacenaAnterior.get(2) + " y por la dimensión " + almacenaAnterior.get(1) +  " una cantidad de " + almacenaAnterior.get(3) + " veces.");
                        System.out.println(frase1);
                    
                    
                }else{ //SI NO ES UNA ==> ENTONCES ES UNA DIMENSIÓN, COMPROBAMOS SI ES DIM2
                    mat = pat.matcher(almacenaAnterior.get(4));
                    if(mat.matches()){
                        //entonces ES DIM 2
                         
                        frase1=("La dimensión 2 es afectada por la " + almacenaAnterior.get(1) + " un numero de " + almacenaAnterior.get(2) + " veces." );
                            System.out.println(frase1);
                                                                         
                                               
                    } 
                    
                }//ELSE             
                
                }
                }               
            }//IF NUMERO 1 INICIAL DE LA ASOCIACIÓN
                
                //ACÁ TERMINA LA ULTIMA COMPROBACIÓN
            
            */
            
        
        /*if(num2.equals(palabra)){
            
            if(in.hasNext()){
            palabra2=in.next();
            
            mat = pat.matcher(palabra2);
            if(mat.matches()){
                System.out.println("ENCONTRAMOS LA DIMENSION 2");
                
            }
        }
        }*/
        //System.out.println("Palabra:"+palabra);
        }// while (in.hasNext())
	 
 
 
 
 
 }  catch (FileNotFoundException e) {
	 System.out.println("Error abriendo el fichero "+nomFich);
        
	 } finally {
	 if (in!=null){
	 in.close();
         
         
         }
	 } // try
        
 
   return almacenaRetorno;
     //retorno=Integer.parseInt(arreglo2[3]);
     //System.out.println(lineas[18]);
}

}
