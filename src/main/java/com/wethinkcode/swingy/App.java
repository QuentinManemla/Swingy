package com.wethinkcode.swingy;

import com.wethinkcode.swingy.Database.Database;
import com.wethinkcode.swingy.View.ViewController;

/**
 * Main App Class
 */

public class App {
    private static ViewController _ViewController;
    private static Database _Database = new Database();

    public static void main( String[] args ) {
        if( args.length == 0 || args.length > 1) {
            System.out.println("[ Swingy Error ]: Invalid number of arguments!");
            System.exit(1);
        }
        /* Setup the Initial View of the Project */
        if ( args[0].equals("console") )
            _ViewController = new ViewController(true);
        else if ( args[0].equals("gui") )
            _ViewController = new ViewController(false);

        Integer _heroCount = _Database.countHeroes();

        _ViewController.Menu(_heroCount);
    }
}
