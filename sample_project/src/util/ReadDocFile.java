package com.rapidus.urlread.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class ReadDocFile {
public static void main(String[] args) {
File file = null;
WordExtractor extractor = null ;
try {
	
	
file = new File("Sample.docx");
//code for reading docx file
XWPFDocument docx = new XWPFDocument(OPCPackage.openOrCreate(file));
XWPFWordExtractor wx = new XWPFWordExtractor(docx);
String text = wx.getText();
System.out.println("text = "+text);

/*
//code for reading doc file
FileInputStream fis=new FileInputStream(file);
HWPFDocument document=new HWPFDocument(fis);
extractor = new WordExtractor(document);
String [] fileData = extractor.getParagraphText();
for(int i=0;i<fileData.length;i++){
if(fileData[i] != null)
System.out.println(fileData[i]);
}*/
}
catch(Exception exep){}
}
}