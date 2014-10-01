package btp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ForceFieldCalculator {
	
	
	
	private static int Rcount=0 , Ocount=0, RAcount=0, OAcount=0; 
	private static double RbondLength=0, ObondLength=0,RAbondAngle=0, OAbondAngle=0;
	
	private void fetchBondLegth(String aFileName)
	{
		 //Make a new file.
		 File file = new File(aFileName);

		 //Declare the reader outside the scope so we can use it
		 //in the finally block.
		 BufferedReader reader = null;
		try{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		 


		 try {
		 	 reader = new BufferedReader(new FileReader(file));
		  	 String line;
			 
		     //Read one line at a time, printing it.
		      while ((line = reader.readLine()) != null) {
		      	if(line.startsWith("REMARK   3   BOND LENGTHS REFINED ATOMS        (A):"))
					{
						String s1=new String(line.substring(53,57));
						//System.out.println(s1);
						Rcount = Integer.valueOf(s1);
						System.out.println(Rcount);
						//System.out.println(s1);
						String s2=new String(line.substring(60, 65));
						//System.out.println(s2);
						RbondLength = Double.parseDouble(s2);
						System.out.println(RbondLength);
						
					}
					
				if(line.startsWith("REMARK   3   BOND LENGTHS OTHERS               (A):"))
				   {
				        String s1=new String(line.substring(53,57));
						
						if(!s1.contains("NULL")){
						   Ocount = Integer.parseInt(s1);
						   System.out.println(Ocount);
						}
						String s2=new String(line.substring(60, 65));
						if(!s2.contains("NULL")){
						   ObondLength = Double.parseDouble(s2);
						   System.out.println(ObondLength);
						   }
				   
				   }
				
		  		}
				
		 	} catch (IOException e) 
			{
		  		e.printStackTrace();
		 	} catch (Exception e) {
		  		e.printStackTrace();
		 	} finally 
			{
		  		try {

		   	//Try to close it, this might throw an exception anyway.
		   reader.close();
		   
		  	}
		 catch (Exception ex) {
		   	     ex.printStackTrace();
		  		}
		 	}
	}
	
	private void fetchBondAngle(String aFileName)
	{
		//Make a new file.
		 File file = new File(aFileName);

		 //Declare the reader outside the scope so we can use it
		 //in the finally block.
		 BufferedReader reader = null;
		try{
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		 


		 try {
		 	 reader = new BufferedReader(new FileReader(file));
		  	 String line;
			 
		     //Read one line at a time, printing it.
		      while ((line = reader.readLine()) != null) {
		      	if(line.startsWith("REMARK   3   BOND ANGLES REFINED ATOMS   (DEGREES):"))
					{
						String s1=new String(line.substring(53,57));
						//System.out.println(s1);
						RAcount = Integer.valueOf(s1);
						System.out.println(Rcount);
						//System.out.println(s1);
						String s2=new String(line.substring(60, 65));
						//System.out.println(s2);
						RAbondAngle = Double.parseDouble(s2);
						System.out.println(RbondLength);
						
					}
					
				if(line.startsWith("REMARK   3   BOND ANGLES OTHERS          (DEGREES):"))
				   {
				        String s1=new String(line.substring(53,57));
						
						if(!s1.contains("NULL")){
						   OAcount = Integer.parseInt(s1);
						   System.out.println(Ocount);
						}
						String s2=new String(line.substring(60, 65));
						if(!s2.contains("NULL")){
						   OAbondAngle = Double.parseDouble(s2);
						   System.out.println(ObondLength);
						   }
				   
				   }
				
		  		}
				
		 	} catch (IOException e) 
			{
		  		e.printStackTrace();
		 	} catch (Exception e) {
		  		e.printStackTrace();
		 	} finally 
			{
		  		try {

		   	//Try to close it, this might throw an exception anyway.
		   reader.close();
		   
		  	}
		 catch (Exception ex) {
		   	     ex.printStackTrace();
		  		}
		 	}
	}
	public double energybtwnBondedAtom(String aFileName, double a , double b)
	{
		double ka = a;
		double kb = b;
		this.fetchBondLegth(aFileName);
		this.fetchBondAngle(aFileName);
		double E_bond = ka*(Rcount*Math.pow(RbondLength,2)+Ocount*Math.pow(ObondLength,2));
		double E_angle = kb*(RAcount*Math.pow(RAbondAngle,2)+OAcount*Math.pow(OAbondAngle,2)); 
		double sum = E_bond + E_angle;
		RbondLength=0; ObondLength=0; RAbondAngle=0; OAbondAngle=0;
		return sum;
	}
	
	/*public double tortionalEnergy()
	{
		
	}
	
	public double vanderWalEnergy()
	{
		
	}*/

}

