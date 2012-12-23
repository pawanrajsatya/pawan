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

public class ApplicationDeployment
{
     public static void main(String ar[]) throws Exception
		{
			ApplicationDeployment appDeploy=new ApplicationDeployment();
			String protocol="t3";
			String hostName="localhost";
			String portString="7001";
			String adminUser="weblogic";
			String adminPassword="weblogic";

			WebLogicDeploymentManager deployManager=SessionHelper.getRemoteDeploymentManager( protocol,hostName,portString,adminUser,adminPassword);
			System.out.println("\n\t WebLogicDeploymentManager: "+deployManager);
			DeploymentOptions options = new DeploymentOptions();
			System.out.println("\n\t DeploymentOptions: "+options);

			Target targets[]=deployManager.getTargets();
			System.out.println("------------------------------------------------------------");
			System.out.println("\t Application Can be Deployed in the Following targets:");
			int i=0;
			for (i=0;i<targets.length;i++)
					{
					   System.out.println("\n\t "+targets[i]);
				    }
			System.out.println("-----------Above We need to write our Logic to Choose the Deploy target---------\n\n");
			Target deployTargets[]=new Target[1];
			//SUPPOSE WE WAANT TO DEPLOY IT ON AdminServer ...
			deployTargets[0]=targets[0];

            //-------------DEPLOYING FIRST APPLICATION-------------//
			String applicationDisplayName="FirstEARApplication";
			options.setName(applicationDisplayName);
			ProgressObject processStatus=deployManager.distribute(deployTargets, new File("FirstEAR.ear"), null,options);
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
			Thread.sleep(5000);
			processStatus=deployManager.deploy(deployTargets, new File("SecondEAR.ear"), null,options);
			deploymentStatus=processStatus.getDeploymentStatus() ;
			System.out.println("\n\n\t For SecondEAR.ear DeploymentStayus.getState(): "+deploymentStatus.getState() +"\n\n");

		}
}
