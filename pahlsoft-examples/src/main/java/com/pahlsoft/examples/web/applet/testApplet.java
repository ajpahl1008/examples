package com.pahlsoft.examples.web.applet;

import java.awt.*;
import java.applet.*;

public class testApplet extends Applet 
{
 /**
	 * 
	 */
	private static final long serialVersionUID = -7589403055361267636L;
// A Button to click
     Button okButton;
 // A textField to get text input
     TextField nameField;
 // A group of radio buttons
 // necessary to only allow one radio button to be selected at the same time.
     CheckboxGroup radioGroup;
 // The radio buttons to be selected
     Checkbox radio1;
     Checkbox radio2;
 // An independant selection box
     Checkbox option;
 

     public void init()
     {
  // Tell the applet not to use a layout manager.
        setLayout(null);
  // Initialize the button and give it a text.
        okButton = new Button("A button");
  // text and length of the field
        nameField = new TextField("A TextField",100);
  // initialize the radio buttons group
          radioGroup = new CheckboxGroup();
  // first radio button. Gives the label text, tells to which
  // group it belongs and sets the default state (unselected)
          radio1 = new Checkbox("Radio1", radioGroup,false);
  // same but selected 
          radio2 = new Checkbox("Radio2", radioGroup,true);
  // Label and state of the checkbox
          option = new Checkbox("Option",false);

  // now we will specify the positions of the GUI components.
  // this is done by specifying the x and y coordinate and
  //the width and height.
          okButton.setBounds(20,20,100,30);
          nameField.setBounds(20,70,100,40);
          radio1.setBounds(20,120,100,30);
          radio2.setBounds(140,120,100,30);
          option.setBounds(20,170,100,30);

  // now that all is set we can add these components to the applet
      add(okButton);
      add(nameField);
      add(radio1);
      add(radio2);
      add(option);
     }

    }

// Thats's it, you now have given the user visual options.
// However there are no actions related to these comonents.
// You will see that later, first an example of a layoutmanager
// that makes it easier to place components.
// Go to LayoutExample.