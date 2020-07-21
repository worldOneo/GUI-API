# GUI-API 1.8-1.16
![Release](https://jitpack.io/v/worldOneo/GUI-API.svg)  
[Jitpack](https://jitpack.io/#worldOneo/GUI-API)
### What is this?
This API aims to create a GUI as easy as possible!
### How do I implement the API in my plugin?
1) You can add the dependency to your development environment via [Jitpack](https://jitpack.io/#worldOneo/GUI-API).
2) Add the GUI-API plugin to your server.
## Implementation
This is a simple implementation with a Button and all other slots filled with glass panes:
```Java
public void createGUI(Player player) {
    GUI gui = new GUI();               //Create a new GUI
    gui
            .setSize(9)                //set the size of the gui
            .setGUITitle("My GUI!");   //Set the title of the gui
    
    Button button = new Button(this::myClickEvent); //Create a button with the function 'myClickEvent' as ClickEvent
    //The Button is an instance of "IWidget"
    button
            .setMaterial(Material.STONE)//define the material of the button
            .setSlot(4)                 //Define the GUI slot of the button
            .addToGUI(gui);             //Adds the button to the GUI
    
    MultipartPlaceHolder multipartPlaceHolder = new MultipartPlaceHolder(); //Create a "Multipart"PlaceHolder
    //The MultipartPlaceHolder is an instance of IMultipartWidget
    multipartPlaceHolder
            .setMaterial(Material.GLASS_PANE)                //Set the material of the placeholder
            .setSlots(Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8)) //MultipartWidgets are renderd over multiple slots!
            .addToGUI(gui);                                  //Adds the Placeholders to the GUI
    
    gui.open(player); //Opens the GUI for the player
}

public void myClickEvent(InventoryClickEvent e) {
    e.getWhoClicked().sendMessage("You clicked the Button!");
}
```