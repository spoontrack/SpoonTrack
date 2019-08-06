package com.spoontrack.core;

import com.spoontrack.core.database.DatabaseFunctions;
import com.spoontrack.core.entity.Pub;
import com.spoontrack.core.io.RequestHandler;

import java.util.List;

public class SpoonTrack {
    private static List<Pub> pubs;

    public static void main(String[] args) {
        pubs = new RequestHandler().getPubs();
    }

    private List<Pub> retrievePubs() {
        return pubs;
    }

    private void updateDatabase() {
        for(Pub pub: pubs) {
            DatabaseFunctions.addPub(pub);
        }
    }
}
