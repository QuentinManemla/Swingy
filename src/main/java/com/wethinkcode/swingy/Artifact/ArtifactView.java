package com.wethinkcode.swingy.Artifact;

public class ArtifactView {
    public ArtifactView() {}

    public void displayArtifact(Artifact _Artifact) {
        System.out.println("[Artifact]\n==========");
        System.out.println("Name: " + _Artifact.getName());
        System.out.println("Value: " + _Artifact.getValue());
        System.out.println("==========");
    }
}
