package ir.aut.ceit.gui;

/**
 * Created by Admin on 5/11/2017.
 */

import javax.swing.*;

/**
 * @author Yasaman Mirmohammad
 */
public class Sample extends JFrame {

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu, editMenu;
    JMenuItem newAction, openAction, closeAction, saveAction;
    JMenuItem addTextAction, addStickerAction;

    public Sample() {

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


        menuBar.add(fileMenu);
        menuBar.add(editMenu);


        setTitle("Hello Ceit:)");
        setSize(500, 500);


    }


}

