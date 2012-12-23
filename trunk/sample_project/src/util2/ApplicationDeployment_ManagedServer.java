package com.rapidus.urlread.util2;

import java.io.*;
import weblogic.deploy.api.tools.*;  //SesionHelper
import weblogic.deploy.api.spi .*;  //WebLogicDeploymentManager
import weblogic.deploy.api.spi.DeploymentOptions;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.status.ProgressObject;
import javax.enterprise.deploy.spi.status.DeploymentStatus;
import javax.enterprise.deploy.shared.ModuleType;
import javax.enterprise.deploy.spi.Target;

import javax.enterprise.deploy.shared.*;

public class ApplicationDeployment_ManagedServer
{
     public static void main(String ar[]) throws Exception
		{
			ApplicationDeployment_ManagedServer appDeploy=new ApplicationDeployment_ManagedServer();
			String protocol="t3";
			String hostName="10.10.10.10";
			String portString="7001";
			String adminUser="weblogic";
			String adminPassword="weblogic";

			WebLogicDeploymentManager deployManager=SessionHelper.getRemoteDeploymentManager( protocol,hostName,portString,adminUser,adminPassword);
			System.out.println("\n\t WebLogicDeploymentManager: "+deployManager);
			DeploymentOptions options = new DeploymentOptions();
                        options.setRemote(true);
                        options.setStageMode(DeploymentOptions.NOSTAGE);
                        options.setTimeout(10000);   //Deployment will timeput in 10 seconds...change it accordingly
			System.out.println("\n\t DeploymentOptions: "+options);

			Target targets[]=deployManager.getTargets();
			System.out.println("------------------------------------------------------------");
			System.out.println("\t Application Can be Deployed in the Following targets:");
			int i=0;
			for (i=0;i<targets.length;i++)
					{
					   System.out.println("\n"+i+"   => Available Deployment Targets Are Below : "+targets[i]);
				        }
                        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("\n\n Please Enter the Target Number in which you want to deploy Your Application: ");
                        int targetNumber=Integer.parseInt(br.readLine());
			Target deployTargets[]=new Target[1];
			//SUPPOSE You Chose it TO BE DEPLOYWD ON Managed Server ...
			deployTargets[0]=targets[targetNumber];
                        br.close();
            //-------------DEPLOYING FIRST APPLICATION-------------//
			String applicationDisplayName="FirstEARApplication";
			options.setName(applicationDisplayName);
			ProgressObject processStatus=deployManager.distribute(deployTargets, new File("FirstEAR.ear"), null,options);
			Thread.sleep(500);
			processStatus=deployManager.deploy(deployTargets, new File("FirstEAR.ear"), null,options);
			DeploymentStatus deploymentStatus=processStatus.getDeploymentStatus() ;
			System.out.println("\n\n\t For FirstEAR.ear DeploymentStayus.getState(): "+deploymentStatus.getState() +"\n\n");

			System.out.println("Sleeping for atleast 5-Seconds. Sothat the Deployment Activation gets Completed Successfully.");
			Thread.sleep(5000);

            //-------------DEPLOYING SECOND APPLICATION-------------//
			deployManager=null;
			deployManager=SessionHelper.getRemoteDeploymentManager( protocol,hostName,portString,adminUser,adminPassword);
			applicationDisplayName="SecondEARApplication";
			options.setName(applicationDisplayName);
			processStatus=deployManager.distribute(deployTargets, new File("SecondEAR.ear"), null,options);
			Thread.sleep(500);
			Thread.sleep(5000);
			processStatus=deployManager.deploy(deployTargets, new File("SecondEAR.ear"), null,options);
			deploymentStatus=processStatus.getDeploymentStatus() ;
			System.out.println("\n\n\t For SecondEAR.ear DeploymentStayus.getState(): "+deploymentStatus.getState() +"\n\n");
                        deployManager.release();
		}
}
