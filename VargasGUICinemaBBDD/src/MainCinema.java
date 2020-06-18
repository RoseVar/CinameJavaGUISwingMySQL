
import GUICinema.Model.CinemaModel;
import GUICinema.Views.MainFrame;

/**
 *
 * @author Roser
 */
public class MainCinema {


    public static void main(String[] args) {
        MainCinema myCinema = new MainCinema();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //call our method to start
                myCinema.run();
            }
        });
        
                
    }

    /**
     * Method to star running our application.
     */
    private void run() {
        //Instantiate model and main frame
        CinemaModel cinmodel= new CinemaModel();
        MainFrame myCinemaFrame = new MainFrame();
        //Put it in the center of screen
        myCinemaFrame.setLocationRelativeTo(null);
        //set visible the main frame
        myCinemaFrame.setVisible(true);
        //use the main frame method setModel to set the instantiated model
        myCinemaFrame.setModel(cinmodel);
        
        
    }
}
