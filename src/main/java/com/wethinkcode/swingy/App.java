package com.wethinkcode.swingy;

import com.wethinkcode.swingy.View.Controller;

/**
 * Main App Class
 */

public class App {
    private static Controller _Controller;

    public static void main( String[] args ) {
        if( args.length == 0 || args.length > 1) {
            System.out.println("[ Swingy Error ]: Invalid number of arguments!");
            System.exit(1);
        }
        /* Setup the Initial View of the Project */
        if ( args[0].equals("console") )
            _Controller = new Controller(true);
        else if ( args[0].equals("gui") )
            _Controller = new Controller(false);

        _Controller.Start();
    }
}
