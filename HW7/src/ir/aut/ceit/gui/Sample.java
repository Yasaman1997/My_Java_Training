package ir.aut.ceit.gui;

/**
 * Created by Admin on 5/11/2017.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Yasaman Mirmohammad
 * @Id 9431022
 * @Email : y.mirmohammad@yahoo.com
 * @github : https://github.com/jasmine1997
 * @gitlab :
 */
public class Sample extends JFrame {

    JFrame mainFrame;
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu, editMenu;
    JMenuItem newAction, openAction, closeAction, saveAction, rotateAction, cropAction, filterAction, colorAction;
    JMenuItem addTextAction, addStickerAction;
    final JFileChooser fileDialog = new JFileChooser();

    public Sample() {

        JButton showFileDialogButton = new JButton("Open File");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Define and add two drop down menu to the menubar
        setJMenuBar(menuBar);


        fileMenu = new JMenu("file");
        editMenu = new JMenu("Edit");


        // Create and add simple menu item to one of the drop down menu
        newAction = fileMenu.add("New");
        fileMenu.addSeparator();

        openAction = fileMenu.add("Open");
        fileMenu.addSeparator();

        closeAction = fileMenu.add("Close");
        fileMenu.addSeparator();

        saveAction = fileMenu.add("Save ");
        fileMenu.addSeparator();


        addTextAction = editMenu.add("Add Text");
        editMenu.addSeparator();

        addStickerAction = editMenu.add("Add Stickers");
        editMenu.addSeparator();

        rotateAction = editMenu.add("rotate");
        editMenu.addSeparator();

        cropAction = editMenu.add("crop");
        editMenu.addSeparator();

        filterAction = editMenu.add("filter");
        editMenu.addSeparator();


        colorAction = editMenu.add("color");
        editMenu.addSeparator();


        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setTitle("Hello Ceit:)");
        setSize(500, 500);


    }


    /**
     * Invoked when an action occurs.
     *
     * @param
     */
    public void actionHandler() {


        newAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        openAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(menuBar);


                BufferedImage image = null;
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    File file = fileChooser.getSelectedFile();
                    try {
                        image = ImageIO.read(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
                ImageIcon img = new ImageIcon(image);
                JLabel showingImage = new JLabel(img, JLabel.CENTER);

                setVisible(true);
            }


        });


        closeAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        saveAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addStickerAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        addTextAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        rotateAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        cropAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        filterAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        colorAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // controlPanel.add(showFileDialogButton);
        mainFrame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
}

