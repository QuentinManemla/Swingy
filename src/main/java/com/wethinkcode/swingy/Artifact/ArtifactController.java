package com.wethinkcode.swingy.Artifact;

import com.wethinkcode.swingy.Hero.Hero;
import com.wethinkcode.swingy.Hero.HeroController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ArtifactController {
    public static ArtifactView _ArtifactView = new ArtifactView();
    public static HeroController _HeroController = new HeroController();
    public static BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public ArtifactController() {}

    public boolean pickupArtifact() {
        System.out.println("[Swingy] Pick up Artifact ?\n1: Yes\n2: No");
        try {
            Integer choice = Integer.parseInt(_bufferedReader.readLine());

            if (choice.equals(1))
                return true;
            else if (choice.equals(2))
                return false;
            else if (!choice.equals(1) || !choice.equals(2)) {
                System.out.println("[Swingy]: Unknown Option Selected [Artifact Controller - Pick up Artifact]");
                pickupArtifact();
            }
        } catch (IOException ex) {
            System.out.println("[Swingy]: IO Exception [Artifact Controller - Pick up Artifact]: " + ex);
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("[Swingy]: Unknown Option Selected [Artifact Controller - Pick up Artifact]");
            pickupArtifact();
        }
        return false;
    }

    public void dropArtifact(Hero _Hero) {
        Random rand = new Random();
        Artifact _droppedArtifact;

        if (rand.nextInt(100) < 50)
            _droppedArtifact = new Artifact("Sword", 10);
        else
            _droppedArtifact = new Artifact("Shield", 10);

        if (rand.nextInt(100) < 60) {
            System.out.println("[Swingy]: An Artifact has been dropped");
            _ArtifactView.displayArtifact(_droppedArtifact);
            if (pickupArtifact())
                _HeroController.setArfifact(_droppedArtifact, _Hero);
        }
    }
}
