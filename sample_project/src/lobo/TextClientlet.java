package com.rapidus.urlread.lobo;

import org.lobobrowser.clientlet.*;
import org.lobobrowser.util.io.IORoutines;
import java.io.*;
import javax.swing.*;

public class TextClientlet implements Clientlet {
  public TextClientlet() {
  }

  public void process(ClientletContext context) throws ClientletException {
    try {
      InputStream in = context.getResponse().getInputStream();
      try {
        String text = IORoutines.loadAsText(in, "ISO-8859-1");
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        JScrollPane pane = new JScrollPane(textArea);
        context.setResultingContent(pane);
      } finally {
        in.close();
      }
    } catch(IOException ioe) {
      throw new ClientletException(ioe);
    }
  }
}
