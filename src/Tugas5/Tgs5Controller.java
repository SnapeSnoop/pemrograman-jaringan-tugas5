/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author snape
 */
public class Tgs5Controller {

    private Tugas5 view;
    private List<Integer> list = new ArrayList<>();

    public Tgs5Controller(Tugas5 view) {
        this.view = view;

        this.view.getBtBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                process();
            }
        });

        this.view.getBtSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    private void process() {

        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getTxtPane().getStyledDocument();
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            BufferedReader reader = null;
            try {

                int desimal;
                char ascii;
                String line = null;

                int wCount = 0;
                int cCount = 0;

                LineNumberReader numReader = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                LineNumberInputStream inputStream = new LineNumberInputStream(new FileInputStream(loadFile.getSelectedFile()));

                reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));

                String data = null;
                doc.insertString(0, "", null);
                while ((data = reader.readLine()) != null) {
                    doc.insertString(doc.getLength(), data, null);
                    doc.insertString(doc.getLength(), "\n", null);
                }

                while ((desimal = inputStream.read()) != -1) {
                    ascii = (char) desimal;
                }

                while ((line = numReader.readLine()) != null) {
                    String[] wList = line.split("\\s");
                    wCount += wList.length;
                    cCount += line.length();
                }
                JOptionPane.showMessageDialog(view, "File berhasil dibaca!"
                        + "\nJumlah baris     : " + (inputStream.getLineNumber() + 1)
                        + "\nJumlah kata      : " + (wCount)
                        + "\nJumlah karakter  : " + (cCount), "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | BadLocationException ex) {
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void save() {

        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedWriter writer = null;
            try {
                String contents = view.getTxtPane().getText();
                if (contents != null && !contents.isEmpty()) {
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getTxtPane().setText("");
                        JOptionPane.showMessageDialog(null, "Sukses!", "Information", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException ex) {
                        Logger.getLogger(Tgs5Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Teks Tidak Boleh Kosong Bro!", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
